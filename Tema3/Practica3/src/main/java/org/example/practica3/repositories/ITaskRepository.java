package org.example.practica3.repositories;

import org.example.practica3.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskRepository  extends JpaRepository<Task, Long> {
}
