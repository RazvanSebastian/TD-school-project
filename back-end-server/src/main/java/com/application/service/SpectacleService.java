package com.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.model.BookedTickets;
import com.application.model.Spectacle;
import com.application.model.SpectacleSchedule;
import com.application.model.repository.BookedTicketRepository;
import com.application.model.repository.SpectacleRepository;
import com.application.model.repository.SpectacleScheduleRepository;

@Service
public class SpectacleService {
	
	@Autowired
	private SpectacleRepository spectacleRepository;
	@Autowired
	private BookedTicketRepository bookedTicketRepository;
	
	public List<Spectacle> getAllSpectacles(){
		return spectacleRepository.countSpectacles()!=0?spectacleRepository.findAll():new ArrayList<Spectacle>();
	}
	
	public List<BookedTickets> getAllUserSpectacles(Long id){
		List<BookedTickets> userSpectacle=this.bookedTicketRepository.findAllUserSpectaclesByUserId(id);
		if(userSpectacle!=null)
			return userSpectacle;
		return new ArrayList<BookedTickets>();
	}
	
	
}
