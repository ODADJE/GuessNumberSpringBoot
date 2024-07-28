package sn.esmt.guess_the_number.services;

import sn.esmt.guess_the_number.models.User;

public interface IUserService {
   public User findById(int id) ;
   public User createUser(User user);
   public User findByLogin(String login) ;
}
