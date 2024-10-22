package org.jpa.springdatarest;

import static org.assertj.core.api.Assertions.assertThat;
import org.jpa.springdatarest.modele.Voiture;
import org.jpa.springdatarest.modele.VoitureRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@DataJpaTest
@ActiveProfiles("test")  // Ensure you are using the "test" profile
public class VoitureRepoTest {

    @Autowired
    private VoitureRepo voitureRepo;

    @Test
    public void ajouterVoiture() {
        Voiture voiture = new Voiture("MiolaCar", "Uber", "Blanche", "M-2020", 2021, 180000);
        voitureRepo.save(voiture);
        assertThat(voiture.getId()).isNotNull();
    }

    @Test
    public void supprimerVoiture() {
        voitureRepo.save(new Voiture("MiolaCar", "Uber", "Blanche", "M-2020", 2021, 180000));
        voitureRepo.save(new Voiture("MiniCooper", "Uber", "Rouge", "C-2020", 2021, 180000));
        voitureRepo.deleteAll();
        assertThat(voitureRepo.findAll()).isEmpty();
    }
}
