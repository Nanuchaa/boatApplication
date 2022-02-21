package ch.openwt.shop.boat_app.controller;

import ch.openwt.shop.boat_app.model.Boat;
import ch.openwt.shop.boat_app.services.BoatService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@CrossOrigin
public class BoatController {

    @Autowired
    private BoatService boatService;

    @GetMapping("/")
    public String getBoat(){
        return "Hello Boat App";
    }


    @GetMapping("/getboatlist")
    public List<Boat> getBoatlist(){
        //log.info("Inside get Boatlist in a controller");
        return boatService.getBoatlist();
    }

    @GetMapping("/getboatbyid/{id}")
    public Boat getBoatById(@PathVariable Integer id){
        //log.info("Get BoatById in a controller for id: " +id);
        return boatService.getBoatById(id);
    }

    @PostMapping("/saveboat")
    public Integer saveBoat(@RequestBody Boat boat){
        //log.info("Boat to save in controller: " +boat);
        return boatService.saveBoat(boat);
    }

    @DeleteMapping("/deleteboat/{id}")
    public void deleteBoat(@PathVariable Integer id){
        //log.info("Inside delete Boat in controller with id: " +id);
        boatService.deleteBoatById(id);
    }

}
