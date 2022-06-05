package it.epicode.netflix.film;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository classe Film
 * @author Francesco Donati
 */
public interface FilmRepository extends CrudRepository<Film, Integer> {	
	
	/**
	 * Ricerca i film permettendo di inserire come parametro il regista 
	 * @param regista
	 * @return  Il film associato al Regista
	 */	
	public List<Film> findByRegista(String regista);
	
}
