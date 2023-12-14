package com.FDMVC.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.FDMVC.enums.Portao;
import com.FDMVC.enums.Terminal;
import com.FDMVC.model.Passagem;
import com.FDMVC.repositories.PassagemRepository;

@Controller
@RequestMapping("/passagens")
public class PassagemController {
	@Autowired
	private PassagemRepository passagemRepository;

	@GetMapping
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("passagem/home");
		modelAndView.addObject("passagens", passagemRepository.findAll());
		return modelAndView;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhes(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("passagem/detalhes");
		modelAndView.addObject("passagem", passagemRepository.getReferenceById(id));
		return modelAndView;
	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("passagem/formulario");
		modelAndView.addObject("passagem", new Passagem());
		modelAndView.addObject("terminais", Terminal.values());
		modelAndView.addObject("portoes", Portao.values());
		return modelAndView;
	}

	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("passagem/formulario");
		modelAndView.addObject("passagem", passagemRepository.getReferenceById(id));
		modelAndView.addObject("terminais", Terminal.values());
		modelAndView.addObject("portoes", Portao.values());
		return modelAndView;
	}

	@PostMapping("/cadastrar")
	public String cadastrar(Passagem passagem) {
		passagemRepository.save(passagem);
		return "redirect:/passagens";
	}

	@PostMapping("/{id}/editar")
	public String editar(Passagem passagem, @PathVariable Long id) {
		passagemRepository.save(passagem);
		return "redirect:/passagens";
	}

	@GetMapping("/{id}/excluir")
	public String excluir(@PathVariable Long id) {
		passagemRepository.deleteById(id);
		return "redirect:/passagens";
	}

}
