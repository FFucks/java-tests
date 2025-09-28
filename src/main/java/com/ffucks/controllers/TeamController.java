package com.ffucks.controllers;

import com.ffucks.entities.Team;
import com.ffucks.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService service;

    public TeamController(TeamService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Team create(@RequestParam String name) {
        return service.create(name);
    }

    @GetMapping
    public List<Team> list() {
        return service.list();
    }

    @GetMapping
    public Team getName() {
        return service.list().get(0);
    }
}
