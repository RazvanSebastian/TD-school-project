package com.application.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.application.model.BookedTickets;
import com.application.model.Spectacle;

public interface BookedTicketRepository extends JpaRepository<BookedTickets, Long> {

	@Query("select s.spectacle from BookedTickets b inner join b.spectacleScheduler s where b.user.id= :id")
	List<Spectacle> findAllUserSpectaclesByUserId(@Param("id") Long id);

	@Query("SELECT b from BookedTickets b WHERE b.seatNumber=:seatNumber AND b.spectacleScheduler.idSpectacleSchedule=:idSchedule")
	List<BookedTickets> findBySeatNumberAndScheduleId(@Param("seatNumber") int seatNumber,
			@Param("idSchedule") Long idSchedule);

}
