package ch.openwt.shop.boat_app.controller;

import ch.openwt.shop.boat_app.model.Boat;
import ch.openwt.shop.boat_app.model.BoatDetails;
import ch.openwt.shop.boat_app.services.BoatDetailsService;
import ch.openwt.shop.boat_app.services.BoatService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@CrossOrigin
public class BoatDetailsController {

    @Autowired
    private BoatDetailsService boatDetailsService;


    @GetMapping("/getboatdetails/byboatidfk/{boatIdfk}")
    public BoatDetails getBoatDetails_byBoatIdfk(@PathVariable Integer boatIdfk){
        //log.info("Get BoatDetails by boatIdfk: " +boatIdfk);
        return boatDetailsService.getBoatBy_boatId_fk(boatIdfk);
    }

    @PostMapping("/saveboatdetails")
    public Integer saveBoatDetails(@RequestBody BoatDetails boatDetails){
        //log.info("Boat details to save in controller: " +boatDetails);
        return boatDetailsService.saveBoatDetails(boatDetails);
    }

    @DeleteMapping("/deleteboatdetails/{id}")
    public void deleteBoatDetails(@PathVariable Integer id){
        //log.info("Inside delete Boat details in controller with id: " +id);
        boatDetailsService.deleteBoatDetailsById(id);
    }

}
