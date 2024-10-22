package org.jpa.springdatarest;

import jakarta.annotation.security.RunAs;
import org.jpa.springdatarest.web.VoitureController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)

@SpringBootTest
class SpringDataRestApplicationTests {
    @Autowired
    VoitureController voitureController;

    @Test
    void contextLoads() {
        assertThat(voitureController).isNotNull();
    }

}
