package it.epicode.netflix.dto;

import it.epicode.netflix.film.Film;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
/**
 * Questa è la classe DTO che fa riferimento al metodo che elimina un film 
 * associato all'id che viene messo a parametro
 */
public class EliminaFilmPerIdResponseDTO {

	private Film filmEliminato;
	
}
