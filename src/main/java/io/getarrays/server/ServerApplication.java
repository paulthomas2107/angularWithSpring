package io.getarrays.server;

import io.getarrays.server.enumerations.Status;
import io.getarrays.server.model.Server;
import io.getarrays.server.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(null,
					"192.168.0.160",
					"Ubuntu Linux",
					"16 GB",
					"Personal PC",
					"http://localhost:8080/server/image/1.png",
					Status.SERVER_UP));
			serverRepo.save(new Server(null,
					"192.168.0.58",
					"Fedora Linux",
					"16 GB",
					"Dell Tower",
					"http://localhost:8080/server/image/2.png",
					Status.SERVER_DOWN));
			serverRepo.save(new Server(null,
					"192.168.1.21",
					"MS 2008",
					"32 GB",
					"Web Server",
					"http://localhost:8080/server/image/3.png",
					Status.SERVER_UP));
			serverRepo.save(new Server(null,
					"192.168.0.160",
					"Red Hat Enterprise",
					"12 TB",
					"Mail Server",
					"http://localhost:8080/server/image/4.png",
					Status.SERVER_DOWN));

		};
	}
}
