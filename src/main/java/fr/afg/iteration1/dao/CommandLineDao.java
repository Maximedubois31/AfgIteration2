package fr.afg.iteration1.dao;

import fr.afg.iteration1.entity.CommandLine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Command line dao.
 */
public interface CommandLineDao extends JpaRepository<CommandLine, Long> {
}
