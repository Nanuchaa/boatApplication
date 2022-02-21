package ch.openwt.shop.boat_app.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="Owt_BoatDetails")
public class BoatDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer boatIdfk;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Date manufacturedDate;

    @Column
    private Integer quantity;

    @Column
    private String type;

}
