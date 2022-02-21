package ch.openwt.shop.boat_app.services;

import ch.openwt.shop.boat_app.daos.BoatDao;
import ch.openwt.shop.boat_app.daos.BoatDetailsDao;
import ch.openwt.shop.boat_app.entities.BoatDetailsEntity;
import ch.openwt.shop.boat_app.entities.BoatEntity;
import ch.openwt.shop.boat_app.model.Boat;
import ch.openwt.shop.boat_app.model.BoatDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class BoatDetailsService {

    @Autowired
    private BoatDetailsDao boatDetailsDao;


    public Integer saveBoatDetails(BoatDetails boatDetails){
        BoatDetailsEntity entity = fromBoatDetailsToBoatDetailsEntity(boatDetails);
        entity = boatDetailsDao.save(entity);
        log.info("New Boat details added!!!");
        return entity.getId();
    }


    public void deleteBoatDetailsById(Integer id) {
        log.info("Deleting Boat details with id: " +id);
        boatDetailsDao.deleteById(id);
    }

    public void deleteBoatDetailsByBoatIdfk(Integer boatIdfk){
        boatDetailsDao.deleteByBoatIdfk(boatIdfk);
    }


    public BoatDetails getBoatBy_boatId_fk(Integer boatId_fk){
        log.info("Getting Boat details with boatId_fk: " +boatId_fk);
        BoatDetailsEntity entity = boatDetailsDao.findByBoatIdfk(boatId_fk);
        if(entity==null) {
            return null;
        }
        BoatDetails boatDetails = this.fromBoatDetailsEntityToBoatDetails(entity);
        return boatDetails;
    }


    private BoatDetails fromBoatDetailsEntityToBoatDetails(BoatDetailsEntity entity){
        BoatDetails boatDetails = new BoatDetails();

        if(entity == null) {
            return null;
        }
        boatDetails.setId(entity.getId());
        boatDetails.setBoatIdfk(entity.getBoatIdfk());
        boatDetails.setWeight(entity.getWeight());
        boatDetails.setManufacturedDate(entity.getManufacturedDate());
        boatDetails.setQuantity(entity.getQuantity());
        boatDetails.setType(entity.getType());

        return boatDetails;
    }

    private BoatDetailsEntity fromBoatDetailsToBoatDetailsEntity(BoatDetails boatDetails) {
        BoatDetailsEntity entity = new BoatDetailsEntity();

        if (boatDetails == null) {
            return null;
        }
        entity.setId(boatDetails.getId());
        entity.setBoatIdfk(boatDetails.getBoatIdfk());
        entity.setWeight(boatDetails.getWeight());
        entity.setManufacturedDate(boatDetails.getManufacturedDate());
        entity.setQuantity(boatDetails.getQuantity());
        entity.setType(boatDetails.getType());

        return entity;
    }

}
