package be.techroots.tinker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class TinkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinkerApplication.class, args);
	}

	@RestController
	class MyRestController {

		@GetMapping("/apis/todos")
		public String getTodos() {
			return "getTodos";
		}
	}
}
