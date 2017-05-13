package com.application.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.application.model.Spectacle;
import com.application.model.SpectacleSchedule;

@Transactional
public interface SpectacleScheduleRepository extends JpaRepository<SpectacleSchedule, Long> {

	// For auto populate
	@Query("SELECT spectacleSchedule FROM SpectacleSchedule spectacleSchedule WHERE spectacleSchedule.idSpectacleSchedule=:id")
	SpectacleSchedule findById(@Param("id") Long id);
	// ---------------------------------------------------------------

	@Query("SELECT s FROM SpectacleSchedule s WHERE s.spectacle.idSpectacle=:id)")
	List<SpectacleSchedule> findAllSpectacleSchedulerBySpectacleId(@Param("id") Long id);
}
