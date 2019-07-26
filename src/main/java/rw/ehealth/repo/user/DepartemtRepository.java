package rw.ehealth.repo.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.ehealth.model.Departemt;

@Repository
public interface DepartemtRepository extends JpaRepository<Departemt, Long> {
	List<Departemt> findAll();
	Departemt findByName( String name);

}
