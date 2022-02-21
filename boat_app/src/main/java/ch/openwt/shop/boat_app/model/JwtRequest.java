package ch.openwt.shop.boat_app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtRequest {

    private String username;
    private String password;

}