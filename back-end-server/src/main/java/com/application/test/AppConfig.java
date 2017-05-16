package com.application.test;

import java.util.Date;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.application.model.BookedTickets;
import com.application.model.Spectacle;
import com.application.model.SpectacleSchedule;
import com.application.model.User;
import com.application.model.repository.BookedTicketRepository;
import com.application.model.repository.SpectacleRepository;
import com.application.model.repository.SpectacleScheduleRepository;
import com.application.model.repository.UserRepository;

@Configuration
public class AppConfig {

	@Bean
	public InitializingBean initUser() {
		return new InitializingBean() {

			@Autowired
			private UserRepository userRepo;
			@Autowired
			private SpectacleRepository spectacleRepository;
			@Autowired
			private SpectacleScheduleRepository spectacleScheduleRepository;
			@Autowired
			private BookedTicketRepository bookedTicketRepository;

			@Override
			public void afterPropertiesSet() throws Exception {
				if (this.userRepo.count() == 0) {
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
					userRepo.save(new User("rzvs95@gmail.com", encoder.encode("password1")));
					userRepo.save(new User("ambro95@gmail.com", encoder.encode("password2")));

					spectacleRepository.save(new Spectacle("DON JUAN",
							"În spectacolul propus de Roberto Bacci, acţiunea se petrece în casa abandonată a Comandorului, unde, printre mobile vechi, degradate de trecerea anilor, personajele populează spaţiul pe tot parcursul reprezentaţiei."));
					spectacleRepository.save(new Spectacle("ULTIMA NOAPTE DE DRAGOSTE, ÎNTÂIA NOAPTE DE RĂZBOI",
							"Romanul Ultima noapte de dragoste, întâia noapte de război a fost scris în anul 1930"));
					spectacleRepository.save(new Spectacle("Stand-up", "Club 99"));
					spectacleRepository.save(new Spectacle("La rascruce de vanturi",
							"după Emily Brontë, dramatizare de Mariana Vartic / scenariul şi regia: Ada Lupu"));
					spectacleRepository
							.save(new Spectacle("12 OAMENI FURIOȘI", "după Jean-Paul Sartre și William Shakespeare"));

					Long dateReference = (long) (1000 * 60 * 60 * 24);// one day
					spectacleScheduleRepository
							.save(new SpectacleSchedule(new Date(System.currentTimeMillis() + dateReference * 6), 60,
									10, this.spectacleRepository.findById((long) 1)));
					spectacleScheduleRepository
							.save(new SpectacleSchedule(new Date(System.currentTimeMillis() + dateReference * 7), 50,
									12, this.spectacleRepository.findById((long) 2)));
					spectacleScheduleRepository
							.save(new SpectacleSchedule(new Date(System.currentTimeMillis() + dateReference * 8), 55,
									11, this.spectacleRepository.findById((long) 3)));
					spectacleScheduleRepository
							.save(new SpectacleSchedule(new Date(System.currentTimeMillis() + dateReference * 9), 60,
									12, this.spectacleRepository.findById((long) 2)));
					spectacleScheduleRepository
							.save(new SpectacleSchedule(new Date(System.currentTimeMillis() + dateReference * 10), 60,
									15, this.spectacleRepository.findById((long) 4)));
					spectacleScheduleRepository
							.save(new SpectacleSchedule(new Date(System.currentTimeMillis() + dateReference * 11), 55,
									12, this.spectacleRepository.findById((long) 5)));
					spectacleScheduleRepository
							.save(new SpectacleSchedule(new Date(System.currentTimeMillis() + dateReference * 12), 60,
									11, this.spectacleRepository.findById((long) 1)));

					bookedTicketRepository.save(new BookedTickets(12, this.userRepo.findById((long) 1),
							this.spectacleScheduleRepository.findById((long) 1)));
					bookedTicketRepository.save(new BookedTickets(10, this.userRepo.findById((long) 2),
							this.spectacleScheduleRepository.findById((long) 1)));
					bookedTicketRepository.save(new BookedTickets(5, this.userRepo.findById((long) 1),
							this.spectacleScheduleRepository.findById((long) 2)));
					bookedTicketRepository.save(new BookedTickets(1, this.userRepo.findById((long) 2),
							this.spectacleScheduleRepository.findById((long) 2)));
				}
			}
		};

	}
}
