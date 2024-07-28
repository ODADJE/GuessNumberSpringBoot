package sn.esmt.guess_the_number.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.esmt.guess_the_number.models.User;
import sn.esmt.guess_the_number.repository.UserRepository;

@Service
public class UserServiceJPA implements IUserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User findById(int id) {
    return  userRepository.findById(id);
  }

  @Override
  public User findByLogin(String login) {
    return userRepository.findByLogin(login);
  }

  public User createUser(User user){
    return userRepository.save(user);
  }
  
}
