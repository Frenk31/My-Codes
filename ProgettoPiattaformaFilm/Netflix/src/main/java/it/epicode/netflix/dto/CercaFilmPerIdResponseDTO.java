package it.epicode.netflix.dto;

import it.epicode.netflix.film.Film;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
/**
 * Questa è la classe DTO del metodo che ricerca un determinato film con l'Id 
 * che viene messo a paramento.
 */
public class CercaFilmPerIdResponseDTO {

	private Film filmTrovato;
}
