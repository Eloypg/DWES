package org.example.practica3.services;

import org.example.practica3.entities.Team;
import org.example.practica3.repositories.ITeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final ITeamRepository teamRepository;

    public TeamService(ITeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> findAll(){
        return teamRepository.findAll();
    }

    public Team findById(Long id){
        return teamRepository.findById(id).orElse(null);
    }

    public Team save(Team team){
        return teamRepository.save(team);
    }

    public void deleteById(Long id){
        teamRepository.deleteById(id);
    }

}
