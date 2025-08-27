package com.ffucks.repositories;

import com.ffucks.entities.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class TeamRepositoryIT {

    @Autowired
    TeamRepository repository;

    @Test
    void save_and_findByName() {
        repository.save(new Team("Yellow"));
        Optional<Team> found = repository.findByName("Yellow");
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Yellow");
    }
}
