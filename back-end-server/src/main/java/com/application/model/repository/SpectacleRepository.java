package com.application.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.application.model.Spectacle;

@Transactional
public interface SpectacleRepository extends JpaRepository<Spectacle, Long> {

	@Query("SELECT COUNT(*) FROM Spectacle")
	long count();
	// For auto populate
	@Query("SELECT spectacle FROM Spectacle spectacle WHERE spectacle.idSpectacle=:id")
	Spectacle findById(@Param("id") Long id);
	// -----------------------------------------------------

	@Query("SELECT COUNT(*) FROM Spectacle")
	Long countSpectacles();
}
