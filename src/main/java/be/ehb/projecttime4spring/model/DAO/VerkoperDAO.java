package be.ehb.projecttime4spring.model.DAO;

import be.ehb.projecttime4spring.model.entities.Verkoper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VerkoperDAO extends CrudRepository<Verkoper, String> {

    @Query("select v.naam from Verkoper v")
    List<String> findAllVerkopersNamen();


}
