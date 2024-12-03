package org.example.practica3.repositories;

import org.example.practica3.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeamRepository  extends JpaRepository<Team, Long> {
}
