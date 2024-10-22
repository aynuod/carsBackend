package org.jpa.springdatarest.web;

import org.jpa.springdatarest.modele.Voiture;
import org.jpa.springdatarest.modele.VoitureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/voitures")
public class VoitureController {

    @Autowired
    private VoitureRepo voitureRepo;

    // Get all cars
    @GetMapping("/all")
    public List<Voiture> getAll() {
        return voitureRepo.findAll();
    }

    // Get car by ID
    @GetMapping("/{id}")
    public ResponseEntity<Voiture> getVoitureById(@PathVariable Long id) {
        return voitureRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Add new car
    @PostMapping("/add")
    public ResponseEntity<Voiture> addVoiture(@RequestBody Voiture voiture) {
        System.out.println("Voiture reçue : " + voiture);
        return ResponseEntity.ok(voitureRepo.save(voiture));
    }

    // Update car by ID
    @PutMapping("/{id}")
    public ResponseEntity<Voiture> updateVoiture(@PathVariable Long id, @RequestBody Voiture voitureDetails) {
        return voitureRepo.findById(id)
                .map(voiture -> {
                    voiture.setMarque(voitureDetails.getMarque());
                    voiture.setModele(voitureDetails.getModele());
                    voiture.setCouleur(voitureDetails.getCouleur());
                    voiture.setImmatricule(voitureDetails.getImmatricule());
                    voiture.setPrix(voitureDetails.getPrix());
                    voiture.setAnnee(voitureDetails.getAnnee());
                    Voiture updatedVoiture = voitureRepo.save(voiture); // Sauvegarder et retourner la voiture mise à jour
                    return ResponseEntity.ok(updatedVoiture);
                })
                .orElse(ResponseEntity.notFound().build()); // Si l'ID n'existe pas
    }

    // Delete car
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoiture(@PathVariable Long id) {
        if (!voitureRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        voitureRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
