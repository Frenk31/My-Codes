package segreteria.repository;

import org.springframework.data.repository.CrudRepository;

import segreteria.model.Studente;
/**
 * Repository classe Studente
 * @author Francesco Donati
 */
public interface StudenteRepository extends CrudRepository<Studente, String> {
	
}
