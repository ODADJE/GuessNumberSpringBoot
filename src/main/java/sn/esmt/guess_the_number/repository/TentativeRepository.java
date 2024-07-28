package sn.esmt.guess_the_number.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.esmt.guess_the_number.models.Tentative;

@Repository
public interface TentativeRepository extends JpaRepository<Tentative, Integer> {}
