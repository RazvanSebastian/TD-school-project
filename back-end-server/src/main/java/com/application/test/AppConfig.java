package com.application.test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.application.model.BookedTickets;
import com.application.model.Role;
import com.application.model.Spectacle;
import com.application.model.SpectacleSchedule;
import com.application.model.User;
import com.application.model.repository.BookedTicketRepository;
import com.application.model.repository.IRoleRepository;
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
			@Autowired
			private IRoleRepository roleRepository;

			@Override
			public void afterPropertiesSet() throws Exception {
				if (this.userRepo.count() == 0) {
					Role r1 = roleRepository.save(new Role("ROLE_USER"));
					Role r2 = roleRepository.save(new Role("ROLE_ADMIN"));
					Set<Role> roles = new HashSet<>();
					roles.add(r1);roles.add(r2);
					
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
					User user = new User("rzvs95@gmail.com", encoder.encode("password1"));
					user.setRoles(roles);
					userRepo.save(user);
					
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

					List<Spectacle> spectacles = this.spectacleRepository.findAll();
					Long dateReference = (long) (1000 * 60 * 60 * 24);// one day

					spectacleScheduleRepository
							.save(new SpectacleSchedule(new Date(1495698300000L), 60, 10, spectacles.get(0)));
					spectacleScheduleRepository
							.save(new SpectacleSchedule(new Date(1495702800000L), 50, 12, spectacles.get(1)));
					spectacleScheduleRepository
							.save(new SpectacleSchedule(new Date(1495616400000L), 55, 11, spectacles.get(2)));
					spectacleScheduleRepository
							.save(new SpectacleSchedule(new Date(1495609200000L), 60, 12, spectacles.get(3)));
					spectacleScheduleRepository
							.save(new SpectacleSchedule(new Date(1495724400000L), 60, 15, spectacles.get(4)));
					spectacleScheduleRepository
							.save(new SpectacleSchedule(new Date(1495796400000L), 55, 12, spectacles.get(0)));
					spectacleScheduleRepository
							.save(new SpectacleSchedule(new Date(1495882800000L), 60, 11, spectacles.get(1)));

					List<SpectacleSchedule> scheduleList = this.spectacleScheduleRepository.findAll();
					bookedTicketRepository
							.save(new BookedTickets(12, this.userRepo.findById((long) 1), scheduleList.get(0)));
					bookedTicketRepository
							.save(new BookedTickets(10, this.userRepo.findById((long) 1), scheduleList.get(1)));
					bookedTicketRepository
							.save(new BookedTickets(5, this.userRepo.findById((long) 1), scheduleList.get(2)));
					bookedTicketRepository
							.save(new BookedTickets(1, this.userRepo.findById((long) 1), scheduleList.get(3)));
					bookedTicketRepository
							.save(new BookedTickets(11, this.userRepo.findById((long) 1), scheduleList.get(4)));
					bookedTicketRepository
							.save(new BookedTickets(13, this.userRepo.findById((long) 1), scheduleList.get(5)));
				}
			}
		};

	}
}
