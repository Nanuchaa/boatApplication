package ch.openwt.shop.boat_app;

import ch.openwt.shop.boat_app.entities.BoatEntity;
import ch.openwt.shop.boat_app.model.Boat;
import ch.openwt.shop.boat_app.model.BoatDetails;
import ch.openwt.shop.boat_app.services.BoatDetailsService;
import ch.openwt.shop.boat_app.services.BoatService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoatServiceUnitTest {

    @Autowired
    private BoatService boatService;

    @Autowired
    private BoatDetailsService boatDetailsService;

    @Test
    public void getBoatByIdTest(){
        Integer id=1;
        Boat boat = boatService.getBoatById(id);

        Assert.assertEquals(boat, 1);
        System.out.println("Getting boat: "+ boat);
    }


    @Test
    public void getBoatlistTest(){
        List<Boat> boatlist = boatService.getBoatlist();

        Assert.assertEquals(boatlist.size(), 1);
        System.out.println("boat is "+ boatlist.get(0));
    }


    @Test
    public void deleteBoatById() {
        Integer id=9;
        boatService.deleteBoatById(id);
        System.out.println("Boat deleted with id: "+ id);
    }


    @Test
    public void saveBoatTest(){
        Boat boat = new Boat();
        boat.setName("TestBoat");
        boat.setDescription("This is Titanic!");
        boat.setPrice(1200.00);

        boatService.saveBoat(boat);

        List<Boat> boatlist = boatService.getBoatlist();

        Assert.assertEquals(boatlist.size(), 1);
        System.out.println("boat is "+ boatlist.get(0));
    }


    @Test
    public void saveBoatandBoatDetailsTest(){
        Boat boat = new Boat();
        boat.setName("TestBoat");
        boat.setDescription("This is Titanic!");
        boat.setPrice(1200.00);
        Integer boatId = boatService.saveBoat(boat);

        BoatDetails details = new BoatDetails();
        details.setBoatIdfk(boatId);
        details.setWeight(10.00);
        details.setManufacturedDate(new Date());
        details.setQuantity(1);
        details.setType("sailing Boat");
        Integer bdId = boatDetailsService.saveBoatDetails(details);

        boatService.deleteBoatById(boatId);
        BoatDetails bd = boatDetailsService.getBoatBy_boatId_fk(boatId);
        Assert.assertNull(bd);
    }

}
