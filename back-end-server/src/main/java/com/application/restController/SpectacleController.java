package com.application.restController;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.BookedTickets;
import com.application.model.Spectacle;
import com.application.model.SpectacleSchedule;
import com.application.model.repository.BookedTicketRepository;
import com.application.service.BoockedTicketService;
import com.application.service.SpectacleScheduleService;
import com.application.service.SpectacleService;



@RestController
public class SpectacleController {
	
	@Autowired
	private SpectacleService spectacleService;
	@Autowired
	private SpectacleScheduleService spectacleScheduleService;
	@Autowired
	private BoockedTicketService bookedTicketService;
	
	
	@RequestMapping(value="/api/get-all-spectacles",method=RequestMethod.GET)
	public List<Spectacle> snedAllSpectacles(){
		return spectacleService.getAllSpectacles();
	}
	
	@RequestMapping(value="/api/get-user-spectacles/userId={id}",method=RequestMethod.GET)
	public List<BookedTickets> sendUserSpectacles(@PathVariable("id") Long id){
		return spectacleService.getAllUserSpectacles(id);
	}
	
	@RequestMapping(value="/api/get-spectacle-scheduler/idSpectacle={id}", method=RequestMethod.GET)
	public List<SpectacleSchedule> sendSpectacleScheduler(@PathVariable("id") Long id){
		return spectacleScheduleService.getAllSpectacleScheduleBySpectacleId(id);
	}
	
	@RequestMapping(value="/api/new-user-ticket/seatNumber={seat}&userId={userId}&spectacleSchedule={idSchedule}",method=RequestMethod.POST)
	public ResponseEntity<String> addNewUserTicket(@PathVariable("seat") int seat,@PathVariable("userId") Long userId,@PathVariable("idSchedule") Long scheduleId){
		try {
			System.out.println(seat+" "+userId+" "+scheduleId);
			this.bookedTicketService.addNewUserTicketByUserIdAndScheduleId(userId, scheduleId, seat);		
			return new ResponseEntity<String>("OK",HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	

	@RequestMapping(value="/api/get-empty-seats/idSchedule={id}",method=RequestMethod.GET)
	public int[] sendEmptySeatsList(@PathVariable("id") Long id){
		try {
			return this.bookedTicketService.getAllEmptySeatsByIdSchedule(id);
		} catch (Exception e) {
			return new int[0];		
		}
	}
}
