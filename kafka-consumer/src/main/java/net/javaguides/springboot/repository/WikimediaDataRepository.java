package net.javaguides.springboot.repository;

import net.javaguides.springboot.entities.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {}
