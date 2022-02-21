package ch.openwt.shop.boat_app.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Owt_Boat")
public class BoatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

}
