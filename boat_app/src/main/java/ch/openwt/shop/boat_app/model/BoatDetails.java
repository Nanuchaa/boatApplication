package ch.openwt.shop.boat_app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class BoatDetails {

    private Integer id;
    private Integer boatIdfk;
    private Double weight;
    private Date manufacturedDate;
    private Integer quantity;
    private String type;

}
