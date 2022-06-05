package it.epicode.netflix.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
/**
 * Questa è la classe DTO che fa riferimento al metodo che inserisce un film nel DataBase
 */
public class InserisciFilmRequestDTO {

	private String titolo;
	private String anno;
	private String regista;
	private String tipo;
	private String incasso;
}
