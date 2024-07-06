package sn.esmt.guess_the_number.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import sn.esmt.guess_the_number.GuessTheNumberApplication;
import sn.esmt.guess_the_number.exception.NumberOutOfBoundError;
import sn.esmt.guess_the_number.models.User;
import sn.esmt.guess_the_number.repository.UserRepository;
import sn.esmt.guess_the_number.services.UserService;



@Controller
public class GuessNumberController {

  private int theGuessNumber = GuessTheNumberApplication.getRandomNumber();
  @Autowired
  private UserService userService;

  @GetMapping("/")
  public String getHome() {
      return "home";
  }


  @GetMapping("/users/{id}")
  public String getUsers(Model model, @PathVariable("id") int userId) {
    User user = null;
    // user = userService.getOneUser(userId);
    user = userRepository.findById(userId).orElse(null);
    model.addAttribute("user",user);
    return "user";
  }

 
  @RequestMapping(value = "/", method=RequestMethod.POST)
  public String guessTheNumber(Model model, @ModelAttribute("number") String number, HttpServletRequest request, HttpServletResponse response) throws NumberOutOfBoundError {
    String contenu = "";
    int guessNumber = Integer.parseInt(number);
    int test = 0;
    Cookie testCookie;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("test".equals(cookie.getName())) {
              test = Integer.parseInt(cookie.getValue());
                break;
            }
        }
    }

    if (guessNumber < 1 || guessNumber > 100) {
        throw new NumberOutOfBoundError();
    }
      if (guessNumber > theGuessNumber) {
        contenu = "le nombre mystere est plus bas";
      } else if (guessNumber < theGuessNumber) {
        contenu = "le nombre mystere est plus haut";
      } else {
        contenu = "Bravo, vous avez trouve le nombre mystere\n Vous avez fait " + test + " essais";
        testCookie = new Cookie("test", String.valueOf(0));
      }
      test++;
      testCookie = new Cookie("test", String.valueOf(test));
      response.addCookie(testCookie);

      model.addAttribute("message",contenu);

      return "home";
  }

  @ExceptionHandler(NumberOutOfBoundError.class)
  public ResponseEntity<String> handleUserNotFoundException(NumberOutOfBoundError ex) {
    return ResponseEntity.status(403).body(ex.getMessage());
  }
}
