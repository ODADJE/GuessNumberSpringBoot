package sn.esmt.guess_the_number.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import sn.esmt.guess_the_number.models.User;
import sn.esmt.guess_the_number.services.IUserService;

@Configuration
public class LoadDatabase implements CommandLineRunner {

  @Autowired
  private IUserService userService;
  @Override
  public void run(String... args) throws Exception {
    userService.createUser(new User("Thomas", "thomas", "passer"));
    userService.createUser(new User("Aboubacar", "abou", "passer"));
  }
  
}
