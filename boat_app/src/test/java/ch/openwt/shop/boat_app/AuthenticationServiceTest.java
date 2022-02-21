package ch.openwt.shop.boat_app;

import ch.openwt.shop.boat_app.config.JwtTokenUtil;
import ch.openwt.shop.boat_app.model.JwtRequest;
import ch.openwt.shop.boat_app.services.JwtUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceTest {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Test
    public void getJwtTokenTest() throws Exception {

        JwtRequest authenticationRequest = new JwtRequest();
        authenticationRequest.setUsername("openwt");
        authenticationRequest.setPassword("password");
        log.info("inside request");
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        log.info("the use name is " + userDetails);
        final String token = jwtTokenUtil.generateToken(userDetails);
        log.info("the token is " + token);

    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}

