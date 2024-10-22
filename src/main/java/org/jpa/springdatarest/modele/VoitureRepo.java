package org.jpa.springdatarest.modele;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource

public interface VoitureRepo extends CrudRepository<Voiture, Long> {

    List<Voiture> findAll();
    List<Voiture> findByModele(@Param("modele")String modelo);



    List<Voiture> findByCouleur(@Param("couleur")String couleur);




}
