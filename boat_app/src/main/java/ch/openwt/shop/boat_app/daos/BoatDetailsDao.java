package ch.openwt.shop.boat_app.daos;

import ch.openwt.shop.boat_app.entities.BoatDetailsEntity;
import ch.openwt.shop.boat_app.entities.BoatEntity;
import ch.openwt.shop.boat_app.model.BoatDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatDetailsDao extends JpaRepository<BoatDetailsEntity, Integer> {


    public BoatDetailsEntity findByBoatIdfk(Integer boatId_fk);

    void deleteByBoatIdfk(Integer boatId_fk);

}
