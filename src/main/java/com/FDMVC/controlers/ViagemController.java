package com.FDMVC.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.FDMVC.model.Viagem;
import com.FDMVC.repositories.PacoteRepository;
import com.FDMVC.repositories.PassagemRepository;
import com.FDMVC.repositories.UsuarioRepository;
import com.FDMVC.repositories.ViagemRepository;

@Controller
@RequestMapping("/viagens")
public class ViagemController {

	@Autowired
	private ViagemRepository viagemRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PacoteRepository pacoteRepository;
	@Autowired
	private PassagemRepository passagemRepository;

	@GetMapping
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("viagem/home");
		modelAndView.addObject("viagens", viagemRepository.findAll());
		return modelAndView;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhes(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("viagem/detalhes");
		modelAndView.addObject("viagem", viagemRepository.getReferenceById(id));
		modelAndView.addObject("usuarios", usuarioRepository.findAll());
		modelAndView.addObject("passagens", passagemRepository.findAll());
		modelAndView.addObject("pacotes", pacoteRepository.findAll());
		return modelAndView;
	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("viagem/formulario");
		modelAndView.addObject("viagem", new Viagem());
		modelAndView.addObject("usuarios", usuarioRepository.findAll());
		modelAndView.addObject("passagens", passagemRepository.findAll());
		modelAndView.addObject("pacotes", pacoteRepository.findAll());
		return modelAndView;
	}

	@GetMapping("{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("viagem/formulario");
		modelAndView.addObject("viagem", viagemRepository.getReferenceById(id));
		modelAndView.addObject("usuarios", usuarioRepository.findAll());
		modelAndView.addObject("passagens", passagemRepository.findAll());
		modelAndView.addObject("pacotes", pacoteRepository.findAll());

		return modelAndView;
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@ModelAttribute Viagem viagem) {
		viagemRepository.save(viagem);
		return "redirect:/viagens";
	}

	@PostMapping("/{id}/editar")
	public String editar(@ModelAttribute Viagem viagem, @PathVariable Long id) {
		viagemRepository.save(viagem);
		return "redirect:/viagens";
	}

	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		viagemRepository.deleteById(id);
		return "redirect:/viagens";
	}
}
