package it.epicode.netflix.film;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.epicode.netflix.dto.InserisciFilmRequestDTO;
import it.epicode.netflix.services.FilmService;


/**
 * Servizi Rest relativi alla classe Film
 * @author Francesco Donati
 */

@RestController
@RequestMapping("/film")
@Tag(name = "film rest services", description = "implementazione delle api rest dei film")
public class FilmController {

	@Autowired
	FilmService fs;
	
	/**
	 * Inserimento a DataBase di un Film
	 * @param f
	 * @param errors
	 * @return un errore se vengono inseriti i parametri sbagliati
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Operation(summary = "Inserisce un film nel Database", description = "Inserisce un film con un id che genera in automatico, con titolo, anno, tipo, incasso, regista")
	@ApiResponse(responseCode = "200", description = "Film inserito con successo nel DataBase!")
	@ApiResponse(responseCode = "500", description = "ATTENZIONE!! Errore nel server")
	@PostMapping(produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity inserisciFilm(@Valid @RequestBody InserisciFilmRequestDTO dto, BindingResult errors) {
		if(errors.hasErrors()) {
			List<String> descrizioneDiErrore = new ArrayList<String>();
			for(ObjectError e : errors.getAllErrors()) {
				descrizioneDiErrore.add(e.getDefaultMessage());
			}
			return new ResponseEntity(descrizioneDiErrore , HttpStatus.BAD_REQUEST);
		}
		if(fs.inserisciFilm(dto)) {
			return ResponseEntity.ok("FILM INSERITO");
		}
		else {
			return new ResponseEntity("ATTENZIONE!! ERRORE NELL'INSERIMENTO", HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Cerca film mettendo come parametro il regista
	 * @param regista
	 * @return il film che è associato al regista che viene messo a parametro 
	 */
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Cerca un film per regista", description =  "ATTENZIONE! siate sicuri che il regista che andrete ad inserire sia esistente se no darà errore")
	@ApiResponse(responseCode = "200", description = "Ricerca andata a buon fine!")
	@ApiResponse(responseCode = "404", description = "Regista per regista")
	@GetMapping(path = "/Cerca/{regista}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity cercaFilmPerRegista(@PathVariable("regista") String regista) {
		return ResponseEntity.ok(fs.trovaTuttiIFilmPerRegista(regista));
	}
	
	/**
	 * Recupera tutti i Film a DataBase
	 * @return una lista di Film presenti nel DataBase
	 */
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Cerca tutti i film presenti nel DataBase", description = "Premere il tasto per vedere tutti i film presenti all'interno del DataBase")
	@ApiResponse(responseCode = "200", description = "Ricerca film andata a buon fine!")
	@ApiResponse(responseCode = "404", description = "Nessun Film presente nel DataBase")
	@GetMapping(path = "/listaDeiFilm", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity tuttiIFilm() {
		return ResponseEntity.ok(fs.tuttiIFilmNelDb());
	} 
	
	/**
	 * Elimina un film passando per paramentro tale id
	 * @param id
	 * @return se non trova l'id ritorna un errore con un messaggio "FILM NON TROVATO"
	 */	
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Elimina un film inserendo come parametro un id", description = "ATTENZIONE! assicuratevi che l'id che andrete ad inserire sia presente a DataBase se no darà errore ")
	@ApiResponse(responseCode = "200", description = "Elimazione del film andata a buon fine!")
	@ApiResponse(responseCode = "500", description = "ATTENZIONE!! Problema nell'eliminazione del film")
	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity eliminaFilmPerId(@PathVariable("id") int id ) {	
		return ResponseEntity.ok(fs.eliminaFilm(id));

	}
	
	/**
	 * Cerca il film con l'id associato che viene messo a parametro
	 * @param id
	 * @return
	 */
	@Operation(summary = "Cerca un film per id", description = "ATTENZIONE! assicurarsi che l'id messo a parametro sia presento nel DataBase")
	@ApiResponse(responseCode = "200", description = "Ricerca andata a buon fine!")
	@ApiResponse(responseCode = "404", description = "Nessun film con tale id presente nel DataBase!")
	@SuppressWarnings("rawtypes")
	@GetMapping(path = "/cercaPerId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity cercaPerId(@PathVariable("id") int id) {		
			return ResponseEntity.ok(fs.trovaFilmPerId(id));
	}
	
}
