package segreteria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import segreteria.model.Studente;
import segreteria.services.SegreteriaService;

/**
 * Metodi che fanno parte della classe Studente
 * @author Francesco Donati
 */


@Controller
@Slf4j
public class StudenteController {

	/**
	 * Nei metodi che richiamano la Form l'utente inserira i giusti parametri definiti dal metodo,
	 * mentre nel metodo successivo di Post avviene un richiamo del metodo che 
	 * effettivamente effettua l'operazione.
	 * 
	 * @author Francesco Donati
	 */

	@Autowired
	SegreteriaService ss;


	@GetMapping("/index")
	public ModelAndView home() {
		log.info("siamo nella home del file index.html");
		ModelAndView mv = new ModelAndView("index");
		return mv;

	}

	@GetMapping("/gestione-studenti-home")
	public ModelAndView home2() {	
		log.info("siamo nella gestione degli studenti");
		ModelAndView mv = new ModelAndView("gestione-studenti-home");
		return mv;

	}

	@GetMapping("/form-inseriscistudente")
	public ModelAndView formInserisciStudente() {
		log.info("siamo nella form inserisci studente");
		ModelAndView mv = new ModelAndView("form-inseriscistudente", "studente", new Studente());
		mv.addObject("elencoCorso", ss.getAllCorsi());
		return mv;
	}

	@PostMapping("/post-inseriscistudente")
	public ModelAndView postInserisciStudente(@Valid Studente studente, BindingResult result) {
		if(result.hasErrors()) {
			log.debug("siamo entrati dentro l'if del metodo inserisci studente");
			return new ModelAndView("error",HttpStatus.BAD_REQUEST);
		}
		ss.inserisciStudente(studente);
		ModelAndView mv = new ModelAndView("redirect:/elenco-studenti");

		return mv;
	}

	@GetMapping("/elenco-studenti")
	public ModelAndView elencoStudente() {
		log.info("siamo nel metodo che visualizza titti gli studenti presenti a db");
		ModelAndView mv = new ModelAndView("elenco-studenti");
		mv.addObject("elencoStudenti", ss.getAllStudenti());
		return mv;
	}


	@GetMapping("/form-modificastudente/{matricola}")
	public ModelAndView formModificaStudente(@PathVariable ("matricola") String matricola) {
		ModelAndView mv = new ModelAndView("form-modificastudente", "studente", ss.trovaStudenteId(matricola));
		mv.addObject("elencoCorso", ss.getAllCorsi());


		return mv;
	}

	@GetMapping("/put-modificastudente")
	public ModelAndView postModificaStudente(@Valid @ModelAttribute Studente studente,BindingResult result) {
		if(result.hasErrors()) {
			log.debug("siamo dentro l'if di modifica studente");
			return new ModelAndView("error",HttpStatus.BAD_REQUEST);
		}
		ss.modificaStudente(studente);
		ModelAndView mv = new ModelAndView("redirect:/elenco-studenti");

		return mv;
	}




	@GetMapping("/form-eliminastudente/{matricola}")	
	public ModelAndView formEliminaStudente(@PathVariable ("matricola") String matricola) {
		ModelAndView mv = new ModelAndView("form-eliminastudente", "studente", ss.trovaStudenteId(matricola));
		mv.addObject("elencoStudenti", ss.getAllStudenti());
		return mv;
	}

	@GetMapping("/delete-eliminastudente")
	public ModelAndView deleteEliminaStudente( @Valid @ModelAttribute Studente studente,BindingResult result) {
		if(result.hasErrors()) {
			log.debug("siamo dentro l'if di elimina studente");
			return new ModelAndView("error",HttpStatus.BAD_REQUEST);
		}
		ss.eliminaStudente(studente);
		ModelAndView mv = new ModelAndView("redirect:/elenco-studenti");

		return mv;
	}



}
