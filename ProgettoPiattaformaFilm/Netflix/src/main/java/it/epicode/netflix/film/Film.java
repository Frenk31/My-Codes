package it.epicode.netflix.film;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
/**
 * Questa Classe gestisce la persistenza su DataBase
 * @author Francesco Donati
 */
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "IL TITOLO DEVE ESSERE PRESENTE")
	private String titolo;
	@NotBlank(message = "L'ANNO DEVE ESSERE PRESENTE")
	private String anno;
	@NotBlank(message = "IL TIPO DEVE ESSERE PRESENTE")
	private String tipo;
	@NotBlank(message = "L'INCASSO DEVE ESSERE PRESENTE")
	private String incasso;
	@NotBlank(message = "IL REGISTA DEVE ESSERE PRESENTE")
	@Size(min = 3, max = 20, message = "IL REGISTA DEVE ESSERE MAGGIORE A 3 CARATTERI E INFERIORE A 20")
	private String regista;
	
	
	
}
