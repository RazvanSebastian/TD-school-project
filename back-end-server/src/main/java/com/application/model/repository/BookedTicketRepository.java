package com.application.model.repository;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.application.model.BookedTickets;
import com.application.model.Spectacle;
import com.application.model.SpectacleSchedule;
import com.application.model.User;

@Transactional
public interface BookedTicketRepository extends JpaRepository<BookedTickets, Long> {
	
	@Query("SELECT COUNT(*) FROM BookedTickets")
	long count();

	@Query("select s.spectacle.name,s.spectacleDate,s.price from BookedTickets b inner join b.spectacleScheduler s where b.userField.id= :id")
	List<BookedTickets> findAllUserSpectaclesByUserId(@Param("id") Long id);
	
	@Query("SELECT b from BookedTickets b WHERE b.seatNumber=:seatNumber AND b.spectacleScheduler=:schedule")
	BookedTickets findBySeatNumberAndScheduleId(@Param("seatNumber") int seatNumber,
			@Param("schedule") SpectacleSchedule schedule);
	
	@Query("SELECT b FROM BookedTickets b INNER JOIN b.spectacleScheduler s WHERE s.idSpectacleSchedule=:id")
	List<BookedTickets> findOccupiedSeatsByIdSchedule(@Param("id") Long id);
	
	@Query("SELECT ticket FROM BookedTickets ticket "
			+ "INNER JOIN ticket.spectacleScheduler sch WHERE sch.spectacleDate BETWEEN :startDay and :endDay "
			+ "AND ticket.userField=:user")
	List<BookedTickets> findUserTicketByDateToday(@Param("user") User user,@Param("startDay") Date startDayAsLong,@Param("endDay") Date endDayAsLong);
}
