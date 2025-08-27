package com.ffucks.services;

import com.ffucks.entities.Team;
import com.ffucks.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public Team create(String name) {
        repository.findByName(name).ifPresent(t -> {
            throw new IllegalArgumentException("Team already exists: " + name);
        });
        return repository.save(new Team(name));
    }

    public List<Team> list() {
        return repository.findAll();
    }
}
