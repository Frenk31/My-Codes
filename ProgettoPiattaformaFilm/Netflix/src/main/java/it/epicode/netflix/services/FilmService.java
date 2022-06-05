package it.epicode.netflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import it.epicode.netflix.dto.CercaFilmPerRegistaResponseDTO;
import it.epicode.netflix.dto.CercaFilmPerIdResponseDTO;
import it.epicode.netflix.dto.InserisciFilmRequestDTO;
import it.epicode.netflix.dto.TuttiIFilmResponseDTO;
import it.epicode.netflix.film.Film;
import it.epicode.netflix.film.FilmRepository;

@Service
public class FilmService {

	@Autowired
	FilmRepository fr;
	

	/**
	 * Inserimento nel DataBase di un film attraverso il DTO
	 * @param dto
	 * @return true
	 */
	public boolean inserisciFilm(InserisciFilmRequestDTO dto) {
		Film f = new Film();

		f.setTitolo(dto.getTitolo());
		f.setAnno(dto.getAnno());
		f.setRegista(dto.getRegista());
		f.setTipo(dto.getTipo());
		String hash = BCrypt.hashpw(dto.getIncasso(), BCrypt.gensalt());
		f.setIncasso(hash);
		fr.save(f);
		return true;
	}
	
	/**
	 * Trova il film con l'id che viene messo in parametro
	 * @param id
	 * @return
	 */
	public CercaFilmPerIdResponseDTO trovaFilmPerId(int id) {
		CercaFilmPerIdResponseDTO dto = new  CercaFilmPerIdResponseDTO();
		if(fr.existsById(id)) {
		dto.setFilmTrovato( fr.findById(id).get());
		return dto;
		}
		else {return null;}
	}
	
	/**
	 * Trova tutti i film che sono collegati al regista
	 * @param regista
	 * @return un errore se non trova il regista 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity trovaTuttiIFilmPerRegista(String regista) {
		CercaFilmPerRegistaResponseDTO dto = new  CercaFilmPerRegistaResponseDTO();
		List<Film>lf = fr.findByRegista(regista) ;
		if(lf.size()>0) {
			dto.setRegista(regista);
			dto.setFilmTrovati(lf.size());
			dto.setListaFilm(lf);
			return ResponseEntity.ok(lf);}
		else {
			return new ResponseEntity("Regista inesistente", HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * 
	 * @return il numero totale dei film nel DataBase e la lista di tutti i film
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TuttiIFilmResponseDTO tuttiIFilmNelDb() {
		TuttiIFilmResponseDTO dto = new TuttiIFilmResponseDTO();
		List<Film> lf = (List)fr.findAll();
			if(lf.size() > 0) {
				dto.setFilmTrovati(lf.size());
				dto.setElencofilms(lf);
				return dto;}
			else {
				return null;	
			}
	}
	/**
	 * Elimina il film con associato l'id che viene messo a parametro 
	 * @param id
	 * @return un errore se l'id non corrisponde a nessun film
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity eliminaFilm(@PathVariable("id") int id ) {
		if(fr.existsById(id)) {
		fr.deleteById(id);
		return ResponseEntity.ok("FILM ELIMINATO");
	}
		else {
			return new ResponseEntity("ERRORE NEL METODO", HttpStatus.NOT_FOUND);
		}
	
}
}
