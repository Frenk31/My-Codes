package it.epicode.netflix.dto;

import java.util.List;

import it.epicode.netflix.film.Film;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
/** 
 * Questa è la classe DTO che fa riferimento al metodo che restitusisce tutti i film presenti all'interno 
 * del DataBase
 */
public class TuttiIFilmResponseDTO {

	private int filmTrovati;
	private List<Film> elencofilms;
	
}
