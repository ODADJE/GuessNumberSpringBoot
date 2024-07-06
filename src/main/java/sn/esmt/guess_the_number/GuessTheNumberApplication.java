package sn.esmt.guess_the_number;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import sn.esmt.guess_the_number.interceptor.LoggingInterceptor;

@SpringBootApplication
public class GuessTheNumberApplication {

	private static int randomNumber =  (int) (Math.random() * (100 - 1)) + 1;


	public static int getRandomNumber() {
		return randomNumber;
	}

	public static void main(String[] args) {
		SpringApplication.run(GuessTheNumberApplication.class, args);
	}


public void addInterceptors(InterceptorRegistry registry) {
registry.addInterceptor(new LoggingInterceptor());
}

}
