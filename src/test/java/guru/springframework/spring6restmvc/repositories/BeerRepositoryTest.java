package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.Beer;
import guru.springframework.spring6restmvc.model.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeerNameTooLong(){

        assertThrows(ConstraintViolationException.class, () -> {
            Beer savedBeer = beerRepository.save(Beer.builder()
                    .beerName("My Beer 10291021909339429832442308498389123089234098410249109843218340984321890348180390891980132892389080913")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("2312313123")
                    .price(new BigDecimal("11.99"))
                    .build());

            beerRepository.flush();
        });
    }

    @Test
    void testSaveBeer(){
        Beer savedBeer = beerRepository.save(Beer.builder()
                        .beerName("My Beer")
                        .beerStyle(BeerStyle.PALE_ALE)
                        .upc("2312313123")
                        .price(new BigDecimal("11.99"))
                .build());

        beerRepository.flush();

        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }
}