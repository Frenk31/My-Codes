package segreteria.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Valid
/**
 * Questa Classe gestisce la persistenza su DataBase
 * @author Francesco Donati
 */
public class Studente {
	
	@Id
	@NotBlank
	private String matricola;
	@NotBlank
	private String nome;
	@NotBlank
	private String cognome;
	@NotBlank
	private String dataDiNascita;
	@NotBlank
	private String email;
	@NotBlank
	private String indirizzo;
	@NotBlank
	private String cittaDiResidenza;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="codice_corso")
	private CorsoLaurea corso;
	
}
