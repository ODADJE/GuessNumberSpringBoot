package sn.esmt.guess_the_number.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.esmt.guess_the_number.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
 User findById(int id);
 User findByLogin(String login);
}
