package com.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.model.SpectacleSchedule;
import com.application.model.repository.SpectacleScheduleRepository;

@Service
public class SpectacleScheduleService {
	
	@Autowired
	private SpectacleScheduleRepository spectacleScheduleRepository;

	public List<SpectacleSchedule> getAllSpectacleScheduleBySpectacleId(Long id){
		List<SpectacleSchedule> scheduler=this.spectacleScheduleRepository.findAllSpectacleSchedulerBySpectacleId(id);
		if(scheduler!=null)
			return scheduler;
		return new ArrayList<SpectacleSchedule>();
	}
}
