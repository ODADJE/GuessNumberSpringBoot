package sn.esmt.guess_the_number.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sn.esmt.guess_the_number.GuessTheNumberApplication;
import sn.esmt.guess_the_number.exception.NumberOutOfBoundError;
import sn.esmt.guess_the_number.models.Tentative;
import sn.esmt.guess_the_number.models.User;
import sn.esmt.guess_the_number.services.IUserService;
import sn.esmt.guess_the_number.services.TentativesService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class GuessNumberController {

  private int theGuessNumber = GuessTheNumberApplication.getRandomNumber();
  @Autowired
  private IUserService userService;
  @Autowired
  private TentativesService tentativesService;



  @GetMapping("/")
  public String getUserInfoForm() {
      return "userInfo";
  }

  @PostMapping("/")
  public String login(@ModelAttribute("login") String login, HttpServletResponse response) {
    User user = null;
    user = userService.findByLogin(login);
    if (user == null) {
      return "redirect:/";
    }
    Cookie cookieUserID = new Cookie("userId", String.valueOf(user.getId()));
    Cookie cookieStartDate = new Cookie("startDate", LocalDateTime.now().toString());

    response.addCookie(cookieUserID);
    response.addCookie(cookieStartDate);
    return "home";
  }
  

  @GetMapping("/game")
  public String getHome() {
      return "home";
  }


  @GetMapping("/users/{id}")
  public String getUsers(Model model, @PathVariable("id") int userId) {
    User user = null;
    user = userService.findById(userId);
    model.addAttribute("user",user);
    return "user";
  }

  @GetMapping("/tentatives")
  public String getTentatives(Model model) {
    User user = null;
    List<Tentative> tentatives = tentativesService.findAllTentative();
    model.addAttribute("user",tentatives);
    return "user";
  }

 
  @RequestMapping(value = "/game", method=RequestMethod.POST)
  public String guessTheNumber(Model model, @ModelAttribute("number") String number, HttpServletRequest request, HttpServletResponse response) throws NumberOutOfBoundError {
    String contenu = "";
    int guessNumber = Integer.parseInt(number);
    int test = 0;
    int userId = 0;
    LocalDateTime startDate = null;
    Cookie testCookie;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("test".equals(cookie.getName())) {
              test = Integer.parseInt(cookie.getValue());
                break;
            }
        }

        for (Cookie cookie : cookies) {
          if ("userId".equals(cookie.getName())) {
            userId = Integer.parseInt(cookie.getValue());
              break;
          }
      }

      for (Cookie cookie : cookies) {
        if ("startDate".equals(cookie.getName())) {
          String sd = cookie.getValue();
          startDate = LocalDateTime.parse(sd);
            break;
        }
    
}
    }

 

    if (guessNumber < 1 || guessNumber > 100) {
        throw new NumberOutOfBoundError();
    }
      if (guessNumber > theGuessNumber) {
        contenu = "le nombre mystere est plus bas";
        test++;
      } else if (guessNumber < theGuessNumber) {
        contenu = "le nombre mystere est plus haut";
        test++;
      } else {
        contenu = "Bravo, vous avez trouve le nombre mystere\n Vous avez fait " + test + " essais";
        User user = null;
        user = userService.findById(userId);

        LocalDateTime finalDate = LocalDateTime.now();
        Duration duration = Duration.between(startDate, finalDate);
        tentativesService.createTentative(new Tentative(test, user, duration));
        
      }
      testCookie = new Cookie("test", String.valueOf(test));
      Cookie newStartDate = new Cookie("startDate", LocalDateTime.now().toString());
      response.addCookie(testCookie);
      response.addCookie(newStartDate);
      model.addAttribute("message",contenu);

      return "home";
  }

  @ExceptionHandler(NumberOutOfBoundError.class)
  public ResponseEntity<String> handleUserNotFoundException(NumberOutOfBoundError ex) {
    return ResponseEntity.status(403).body(ex.getMessage());
  }
}
