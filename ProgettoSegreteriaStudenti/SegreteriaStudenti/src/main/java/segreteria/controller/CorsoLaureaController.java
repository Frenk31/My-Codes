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
import segreteria.model.CorsoLaurea;
import segreteria.services.SegreteriaService;

/**
 * Metodi che fanno parte della classe CorsoLaurea
 * @author Francesco Donati
 */

@Controller
@Slf4j
public class CorsoLaureaController {
	/**
	 * Nei metodi che richiamano la Form l'utente inserira i giusti parametri definiti dal metodo,
	 * mentre nel metodo successivo di Post avviene un richiamo del metodo che 
	 * effettivamente effettua l'operazione.
	 * 
	 * @author Francesco Donati
	 */



	@Autowired
	SegreteriaService ss;

	@GetMapping("/gestione-corsi-home")
	public ModelAndView home3() {		
		log.info("siamo dentro gestione corsi di laurea");
		ModelAndView mv = new ModelAndView("gestione-corsi-home");
		return mv;

	}

	@GetMapping("/form-inseriscicorso")
	public ModelAndView formInseriscicorso() {
		log.info("siamo dentro la form di inserisci corso");
		ModelAndView mv = new ModelAndView("form-inseriscicorso", "corsodilaurea", new CorsoLaurea());
		return mv;
	}


	@PostMapping("/post-inseriscicorso")
	public ModelAndView postInserisciCorso(@Valid @ModelAttribute CorsoLaurea corso, BindingResult result) {
		if(result.hasErrors()) {
			log.debug("siamo dentro l'if degli errori");
			return new ModelAndView("error",HttpStatus.BAD_REQUEST);
		}
		log.info("siamo nella parte dove il metodo inserisce il corso");
		ss.inserisciCorso(corso);
		ModelAndView mv = new ModelAndView("redirect:/elenco-corso");
		return mv;
	}

	/**
	 * Questo è il metodo che visualizza l'elenco dei corsi e appunto
	 * @return un elenco completo di tutti i corsi presenti a DB
	 */
	@GetMapping("/elenco-corso")
	public ModelAndView elencoCorso() {
		log.info("siamo dentro il metodo dell'elenco dei corsi");
		ModelAndView mv = new ModelAndView("elenco-corso");
		mv.addObject("elencoCorso", ss.getAllCorsi());
		return mv;

	}

	@GetMapping("/form-modificacorso/{codice}")
	public ModelAndView formModificaCorso(@PathVariable("codice")  String codice) {
		ModelAndView mv = new ModelAndView("form-modificacorso", "corsodilaurea", ss.trovaCorsoId(codice));
		mv.addObject("elencoCorso", ss.getAllCorsi());
		return mv;
	}

	@GetMapping("/put-modificacorso/{codice}")
	public ModelAndView putModificaCorso(@Valid @ModelAttribute CorsoLaurea corso, BindingResult result) {
		if(result.hasErrors()) {
			log.info("siamo dentro l'if di modifica corso");
			return new ModelAndView("error", HttpStatus.BAD_REQUEST);
		}
		ss.modificaCorso(corso);
		ModelAndView mv = new ModelAndView("redirect:/elenco-corso");
		return mv;
	}


	@GetMapping("/form-eliminacorso/{codice}")
	public ModelAndView formEliminaCorso(@PathVariable("codice") String codice) {
		ModelAndView mv = new ModelAndView("form-eliminacorso", "corsodilaurea", ss.trovaCorsoId(codice));
		mv.addObject("elencoCorso", ss.getAllCorsi());
		return mv;
	}


	@GetMapping("/delete-eliminacorso")
	public ModelAndView deleteEliminaCorso(@Valid @ModelAttribute CorsoLaurea corso, BindingResult result) {
		if(result.hasErrors()) {
			log.debug("siamo dentro l'if di elimina corso");
			return new ModelAndView("error",HttpStatus.BAD_REQUEST);
		}
		ss.eliminaCorso(corso);
		ModelAndView mv = new ModelAndView("redirect:/elenco-corso");

		return mv;
	}
}
