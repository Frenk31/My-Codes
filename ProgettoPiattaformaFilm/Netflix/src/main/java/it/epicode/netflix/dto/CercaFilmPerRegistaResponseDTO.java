package it.epicode.netflix.dto;

import java.util.List;

import it.epicode.netflix.film.Film;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
/**
 * Questa è la classe DTO che fa riferimento al metodo che cerca un determinato film 
 * inserendo il regista come paramento 
 */
public class CercaFilmPerRegistaResponseDTO {

	private String regista;
	private int filmTrovati;
	private List<Film> listaFilm;
}
