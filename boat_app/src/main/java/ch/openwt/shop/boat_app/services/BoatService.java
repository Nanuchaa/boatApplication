package ch.openwt.shop.boat_app.services;

import ch.openwt.shop.boat_app.daos.BoatDao;
import ch.openwt.shop.boat_app.daos.BoatDetailsDao;
import ch.openwt.shop.boat_app.entities.BoatDetailsEntity;
import ch.openwt.shop.boat_app.entities.BoatEntity;
import ch.openwt.shop.boat_app.model.Boat;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class BoatService {

    @Autowired
    private BoatDao boatDao;

    @Autowired
    private BoatDetailsDao boatDetailsDao;

    public Integer saveBoat(Boat boat){
        BoatEntity entity = fromBoatToBoatEntity(boat);
        entity = boatDao.save(entity);
        log.info("New Boat added!!!");
        return entity.getId();
    }


    public void deleteBoatById(Integer id) {
        log.info("Deleting Boat with id: " +id);
        boatDao.deleteById(id);

        BoatDetailsEntity bdEntity = boatDetailsDao.findByBoatIdfk(id);
        if(bdEntity!=null) {
            boatDetailsDao.delete(bdEntity);
        }
    }


    public Boat getBoatById(Integer id){
        log.info("Getting Boat with id: " +id);
        BoatEntity entity = boatDao.getById(id);
        if(entity==null) {
            return null;
        }
        Boat boat = this.fromBoatEntityToBoat(entity);
        return boat;
    }

    public List<Boat> getBoatlist(){

        List<BoatEntity> entitylist = boatDao.findAll();
        List<Boat> boatlist = new ArrayList<Boat>();
        for(BoatEntity entity : entitylist){
            Boat boat  = fromBoatEntityToBoat(entity);
            boatlist.add(boat);
        }
        log.info("List of Boat =>>>>> " + boatlist);
        return boatlist;
    }


    private Boat fromBoatEntityToBoat(BoatEntity entity){
        Boat boat = new Boat();

        if(entity == null) {
            return null;
        }
        boat.setId(entity.getId());
        boat.setName(entity.getName());
        boat.setDescription(entity.getDescription());
        boat.setPrice(entity.getPrice());

        return boat;
    }

    private BoatEntity fromBoatToBoatEntity(Boat boat) {
        BoatEntity entity = new BoatEntity();

        if (boat == null) {
            return null;
        }
        entity.setId(boat.getId());
        entity.setName(boat.getName());
        entity.setDescription(boat.getDescription());
        entity.setPrice(boat.getPrice());

        return entity;
    }

}
