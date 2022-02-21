package ch.openwt.shop.boat_app.daos;

import ch.openwt.shop.boat_app.entities.BoatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatDao extends JpaRepository<BoatEntity, Integer> {



}
