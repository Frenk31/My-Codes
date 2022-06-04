package segreteria.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
/**
 * Questa Classe gestisce la persistenza su DataBase
 * @author Francesco Donati
 */
public class CorsoLaurea {
	
	@Id
	private String codice;
	@NotBlank
	private String nomeCorso;
	@NotBlank
	private String indirizzo;
	@Min(10)
	private Integer numeroDiEsami;
	
	@OneToMany(mappedBy = "corso", cascade = CascadeType.ALL)
	private List<Studente> Studenti;
	
	
	
}
