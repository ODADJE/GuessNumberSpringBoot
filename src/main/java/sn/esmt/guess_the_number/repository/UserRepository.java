package sn.esmt.guess_the_number.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.esmt.guess_the_number.models.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {
 List<User> findById(int id);
}
