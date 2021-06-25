package fr.afg.iteration1.dao;

import fr.afg.iteration1.entity.CommandLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandLineDao extends JpaRepository<CommandLine, Long> {
}
