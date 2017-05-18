package com.application.service;


import java.util.ArrayList;
import java.util.List;

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
		if(user==null)
			throw new Exception("Id user is required!!");
		SpectacleSchedule schedule= this.spectacleScheduleRepository.findById(scheduleId);
		if(schedule==null)
			throw new Exception("Id schedule is required!");
		if(this.bookedTicketRepository.findBySeatNumberAndScheduleId(seatNumber, schedule)!=null)
			throw new Exception("The seat is occupied!");
		this.bookedTicketRepository.save(new BookedTickets(seatNumber, user, schedule));
	}
	
	public int[] getAllEmptySeatsByIdSchedule(Long idSchedule) throws Exception{
		List<BookedTickets> listOfOccupiedSeats=this.bookedTicketRepository.findOccupiedSeatsByIdSchedule(idSchedule);	
		if(listOfOccupiedSeats==null)
			throw new Exception("Empty list!");
		
		int numberOfSeats=listOfOccupiedSeats.get(0).getSpectacleScheduler().getSeatsNumber();
		int[] allSeats=new int[numberOfSeats+1];	
		int occupied=0;
		for(BookedTickets b:listOfOccupiedSeats){
			allSeats[b.getSeatNumber()]=1;
			occupied++;
		}
		int[] listEMptySeats=new int[allSeats.length-occupied-1];
		int nr=-1;
		for(int i=1;i<allSeats.length;i++){
			if(allSeats[i]==0){
				nr++;
				listEMptySeats[nr]=i;
			}
		}
		return listEMptySeats;
	}
}
