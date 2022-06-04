package segreteria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;
import segreteria.model.CorsoLaurea;
import segreteria.model.Studente;
import segreteria.repository.CorsoLaureaRepository;
import segreteria.repository.StudenteRepository;

/**
 * @author Francesco Donati
 */
@Service
@Slf4j
public class SegreteriaService {

	@Autowired
	StudenteRepository sr;

	@Autowired
	CorsoLaureaRepository cr;

	/**
	 * Questo metodo mostra tutti gli studenti presenti a DB,
	 * @return una lista di Studenti
	 */
	public List<Studente> getAllStudenti(){
		log.info("siamo nel metodo che mostra tutti gli studenti presenti nel DB");
		return (List<Studente>) sr.findAll(); 	
	}

	/**
	 * Questo metodo mostra tutti i corsi presenti a DB,
	 * @return una lista di corsi
	 */
	public List<CorsoLaurea> getAllCorsi(){
		log.info("siamo nel metodo che mostra tutti i corsi presenti nel DB");
		return (List<CorsoLaurea>) cr.findAll(); 	
	}

	/**
	 * Inserisci Un nuovo Studente, passando a parametro tutti gli attributi che gli appartengono
	 * @param studente
	 */
	public void inserisciStudente(Studente studente) {
		log.info("siamo nel metodo che inserisci un nuovo studente");
		if(!sr.existsById(studente.getMatricola())) {
			sr.save(studente);
		}
	}

	/**
	 * Inserisce un nuovo corso di laurea, passando a parametro tutti gli attributi che gli appartengono
	 * @param corso
	 */
	public void inserisciCorso(CorsoLaurea corso) {
		log.info("siamo nel metodo che inserisci un nuovo corso di laurea");
		if(!cr.existsById(corso.getCodice())) {
			cr.save(corso);
		}
	}

	/**
	 * Trova uno studente passando a parametro una matricola
	 * @param matricola
	 * @return niente se non sono presenti errori
	 */
	public Studente trovaStudenteId(String matricola) {
		if(sr.existsById(matricola)) {
			Studente studente = sr.findById(matricola).get();
			return studente;
		}
		log.debug("ATTENZIONE!! il metodo trovaStudenteId non ha trovato nessuna matricola uguale a quella passata a parametro");
		return null;
	}

	/**
	 * Modifica uno studente passando a parametro tutti gli attributi che si vogliono modificare
	 * @param studente
	 */
	public void modificaStudente(Studente studente) {
		if(sr.existsById(studente.getMatricola())) {
			sr.findById(studente.getMatricola()).get();
			sr.save(studente);
		}
		log.debug("ATTENZIONE!! il metodo modificaStudente non ha trovato nessun studente uguale a quello passato a parametro");
	}

	/**
	 * Modifica un corso passando a parametro tutti gli attributi che si vogliono modificare
	 * @param corso
	 */
	public void modificaCorso(CorsoLaurea corso) {
		if(cr.existsById(corso.getCodice())) {
			cr.findById(corso.getCodice()).get();
			cr.save(corso);
		}
		log.debug("ATTENZIONE!! il metodo modificaCorso non ha trovato nessun codice uguale a quello passato a parametro");
	}

	/**
	 * Elimina uno Studente con associato la sua matricola
	 * @param studente
	 */
	public void eliminaStudente(Studente studente) {
		if(sr.existsById(studente.getMatricola())) {
			sr.findById(studente.getMatricola()).get();
			sr.deleteById(studente.getMatricola());
		}
	}

	/**
	 * Elimina un corso con associato il suo codice
	 * @param corso
	 */
	public void eliminaCorso(CorsoLaurea corso) {
		if(cr.existsById(corso.getCodice())) {
			cr.findById(corso.getCodice()).get();
			cr.deleteById(corso.getCodice());
		}
	}

	/**
	 * Trova un corso passano a parametro un codice
	 * @param codice
	 * @return niente se non sono presenti errori
	 */
	public CorsoLaurea trovaCorsoId( String codice) {
		if(cr.existsById(codice)){
			CorsoLaurea corso= cr.findById(codice).get();
			return corso;
		}
		return null;
	}


}
