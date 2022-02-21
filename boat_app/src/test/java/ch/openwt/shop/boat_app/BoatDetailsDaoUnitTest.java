package ch.openwt.shop.boat_app;


import ch.openwt.shop.boat_app.daos.BoatDao;
import ch.openwt.shop.boat_app.daos.BoatDetailsDao;
import ch.openwt.shop.boat_app.entities.BoatDetailsEntity;
import ch.openwt.shop.boat_app.entities.BoatEntity;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class BoatDetailsDaoUnitTest {

    @Autowired
    private BoatDetailsDao boatDetailsDao;

    @Autowired
    private BoatDao boatDao;

    @Test
    public void getBoatDetailsById_fkTest(){
        BoatEntity boat = new BoatEntity();
        boat.setPrice(2300.00);
        boat.setDescription("Test boat, a very cheap boat");
        boat.setName("MY first cheap boat");
        BoatEntity saved = boatDao.save(boat);
        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());

        Integer boatIdfk = saved.getId();
        BoatDetailsEntity boatDetail = new BoatDetailsEntity();
        boatDetail.setType("Luxury");
        boatDetail.setQuantity(1);
        boatDetail.setWeight(2.3);
        boatDetail.setBoatIdfk(boatIdfk);
        boatDetail.setManufacturedDate(new Date());
        BoatDetailsEntity savedDetail = boatDetailsDao.save(boatDetail);
        Assertions.assertNotNull(savedDetail);

        BoatDetailsEntity boatDetailByBoatId = boatDetailsDao.findByBoatIdfk(boatIdfk);
        Assertions.assertEquals(boatDetailByBoatId.getId(), savedDetail.getId());
        log.info("saved with Id: " +savedDetail.getId());

    }


}
