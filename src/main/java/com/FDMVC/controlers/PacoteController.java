package com.FDMVC.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.FDMVC.model.Pacote;
import com.FDMVC.model.Passagem;
import com.FDMVC.repositories.PacoteRepository;
import com.FDMVC.repositories.PassagemRepository;

@Controller
@RequestMapping("/pacotes")
public class PacoteController {
	@Autowired
	private PacoteRepository pacoteRepository;
	@Autowired
	private PassagemRepository passagemRepository;

	@GetMapping
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("pacote/home");
		modelAndView.addObject("pacotes", pacoteRepository.findAll());
		List<Passagem> passagens = passagemRepository.findAll();
		modelAndView.addObject("passagens", passagens);
		return modelAndView;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhes(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("pacote/detalhes");
		modelAndView.addObject("pacote", pacoteRepository.getReferenceById(id));
		List<Passagem> passagens = passagemRepository.findAll();
		modelAndView.addObject("passagens", passagens);
		return modelAndView;
	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("pacote/formulario");
		modelAndView.addObject("pacote", new Pacote());
		List<Passagem> passagens = passagemRepository.findAll();
		modelAndView.addObject("passagens", passagens);
		return modelAndView;
	}

	@GetMapping("{id}/editar")
	public ModelAndView editar(@PathVariable long id) {
		ModelAndView modelAndView = new ModelAndView("pacote/formulario");
		modelAndView.addObject("pacote", pacoteRepository.getReferenceById(id));
		List<Passagem> passagens = passagemRepository.findAll();
		modelAndView.addObject("passagens", passagens);
		return modelAndView;
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@ModelAttribute Pacote pacote) {
		pacoteRepository.save(pacote);
		return "redirect:/pacotes";
	}

	@PostMapping("/{id}/editar")
	public String editar(@ModelAttribute Pacote pacote, @PathVariable Long id) {
		pacoteRepository.save(pacote);
		return "redirect:/pacotes";
	}
	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		pacoteRepository.deleteById(id);
		return "redirect:/pacotes";
	}
}
