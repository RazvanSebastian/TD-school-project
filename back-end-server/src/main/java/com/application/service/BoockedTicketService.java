package com.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.model.BookedTickets;
import com.application.model.SpectacleSchedule;
import com.application.model.User;
import com.application.model.repository.BookedTicketRepository;
import com.application.model.repository.SpectacleScheduleRepository;
import com.application.model.repository.UserRepository;

@Service
public class BoockedTicketService {

	@Autowired
	private UserRepository userRepostiory;
	@Autowired
	private SpectacleScheduleRepository spectacleScheduleRepository;
	@Autowired
	private BookedTicketRepository bookedTicketRepository;
	
	public void addNewUserTicketByUserIdAndScheduleId(Long userId,Long scheduleId,int seatNumber) throws Exception{
		User user = this.userRepostiory.findById(userId);
		SpectacleSchedule schedule= this.spectacleScheduleRepository.findById(scheduleId);
		if(this.bookedTicketRepository.findBySeatNumberAndScheduleId(seatNumber, scheduleId).isEmpty() && user!=null && schedule!=null){
			this.bookedTicketRepository.save(new BookedTickets(seatNumber, user, schedule));
			return;
		}
		throw new Exception("Incompatible data!");
			
	}
}
