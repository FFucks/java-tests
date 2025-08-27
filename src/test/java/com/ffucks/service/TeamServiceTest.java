package com.ffucks.service;

import com.ffucks.entities.Team;
import com.ffucks.repositories.TeamRepository;
import com.ffucks.services.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    @Mock
    TeamRepository repository;

    @InjectMocks
    TeamService service;

    @Test
    void savesWhenNotExists() {
        when(repository.findByName("Yellow")).thenReturn(Optional.empty());
        when(repository.save(any(Team.class))).thenAnswer(inv -> {
            Team team = inv.getArgument(0);
            return team;
        });

        Team created = service.create("Yellow");

        ArgumentCaptor<Team> captor = ArgumentCaptor.forClass(Team.class);
        verify(repository).save(captor.capture());
        assertThat(captor.getValue().getName()).isEqualTo("Yellow");
        assertThat(created.getName()).isEqualTo("Yellow");
    }

    @Test
    void throwsIfExists() {
        when(repository.findByName("Yellow")).thenReturn(Optional.of(new Team("Yellow")));
        assertThatThrownBy(() -> service.create("Yellow"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("already exists");
        verify(repository, never()).save(any());
    }
}
