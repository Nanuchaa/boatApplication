package ch.openwt.shop.boat_app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Boat {

    private Integer id;
    private String name;
    private String description;
    private Double price;

}
