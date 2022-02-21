package ch.openwt.shop.boat_app;

import ch.openwt.shop.boat_app.config.JwtTokenUtil;
import ch.openwt.shop.boat_app.model.Boat;
import ch.openwt.shop.boat_app.model.BoatDetails;
import ch.openwt.shop.boat_app.model.JwtRequest;
import ch.openwt.shop.boat_app.model.JwtResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
class BoatAppApplicationTests {

    private String token = "Bearer ";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void getToken() throws Exception {
        JwtRequest authenticationRequest = new JwtRequest();
        authenticationRequest.setUsername("openwt");
        authenticationRequest.setPassword("password");

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        log.info("the user name is " + userDetails);
        final String token = jwtTokenUtil.generateToken(userDetails);

        this.token = "Bearer "+token;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    @Test
    public void givenUsernameAndPassword_AuthenticateTest() throws Exception {

        JwtRequest authenticationRequest = new JwtRequest();
        authenticationRequest.setUsername("openwt");
        authenticationRequest.setPassword("password");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(authenticationRequest);

        Assertions.assertNotNull(this.mockMvc);
        MockHttpServletRequestBuilder request = post("/authenticate")
                .contentType("application/json")
                .content(json);

        MvcResult result = this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        JwtResponse res = mapper.readValue(json, JwtResponse.class);

        log.info(res);
        Assertions.assertNotNull(res);

    }


    @Test
    public void givenURI_whenPostBoat_thenReturnsItem() throws Exception {

        Boat boat = new Boat();
        boat.setId(1);
        boat.setName("Test Boat");
        boat.setDescription("This is my first cheap Boat.");
        boat.setPrice(500.00);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(boat);

        Assertions.assertNotNull(this.mockMvc);
        MockHttpServletRequestBuilder request = post("/saveboat")
                .header("Authorization", token)
                .contentType("application/json")
                .content(json);

        MvcResult result = this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        json = result.getResponse().getContentAsString();
        log.info("Json: " + json);
    }

    @Test
    public void givenURI_whenGetBoat_thenReturnsBoatItem() throws Exception {

        this.givenURI_whenPostBoat_thenReturnsItem();

        Assertions.assertNotNull(this.mockMvc);
        MockHttpServletRequestBuilder request = get("/getboatbyid/1")
                .header("Authorization", token)
                .contentType("application/json");

        MvcResult result = this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        log.info("Json: " + json);
    }

    @Test
    public void givenURI_whenDeleteBoat() throws Exception {

        this.givenURI_whenPostBoat_thenReturnsItem();

        Assertions.assertNotNull(this.mockMvc);
        MockHttpServletRequestBuilder request = delete("/deleteboat/1")
                .header("Authorization", token)
                .contentType("application/json");

        MvcResult result = this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        log.info("Json: " + json);
    }



    /*
    * Boat Details TESTS
    * */

    @Test
    public void givenURI_whenPostBoatDetails_thenReturnsItem() throws Exception {

        this.givenURI_whenPostBoat_thenReturnsItem();

        BoatDetails boatDetails = new BoatDetails();
        boatDetails.setId(1);
        boatDetails.setBoatIdfk(1);
        boatDetails.setWeight(80.5);
        boatDetails.setManufacturedDate(new Date());
        boatDetails.setQuantity(10);
        boatDetails.setType("Luxury Boat");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(boatDetails);

        Assertions.assertNotNull(this.mockMvc);
        MockHttpServletRequestBuilder request = post("/saveboatdetails")
                .header("Authorization", token)
                .contentType("application/json")
                .content(json);

        MvcResult result = this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        json = result.getResponse().getContentAsString();
        log.info("Json: " + json);
    }

    @Test
    public void givenURI_whenGetBoatDetails_thenReturnsBoatItem() throws Exception {

        this.givenURI_whenPostBoat_thenReturnsItem();

        this.givenURI_whenPostBoatDetails_thenReturnsItem();

        Assertions.assertNotNull(this.mockMvc);
        MockHttpServletRequestBuilder request = get("/getboatdetails/byboatidfk/1")
                .header("Authorization", token)
                .contentType("application/json");

        MvcResult result = this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        log.info("Json: " + json);
    }

    @Test
    public void givenURI_whenDeleteBoatDetails() throws Exception {

        this.givenURI_whenPostBoatDetails_thenReturnsItem();

        Assertions.assertNotNull(this.mockMvc);
        MockHttpServletRequestBuilder request = delete("/deleteboatdetails/1")
                .header("Authorization", token)
                .contentType("application/json");

        MvcResult result = this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        log.info("Json: " + json);
    }

}
