package com.FDMVC.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.FDMVC.enums.TipoUsuario;
import com.FDMVC.model.Usuario;
import com.FDMVC.repositories.UsuarioRepository;
import com.FDMVC.utils.SenhaUtils;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("usuario/home");
		modelAndView.addObject("usuarios", usuarioRepository.findAll());
		return modelAndView;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhes(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("usuario/detalhes");
		modelAndView.addObject("usuario", usuarioRepository.getReferenceById(id));
		return modelAndView;
	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("usuario/formulario");
		modelAndView.addObject("usuario", new Usuario());
		modelAndView.addObject("tipoUsuario", TipoUsuario.values());
		return modelAndView;
	}

	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("usuario/formulario");
		modelAndView.addObject("usuario", usuarioRepository.getReferenceById(id));
		modelAndView.addObject("tipoUsuario", TipoUsuario.values());
		return modelAndView;
	}

	@PostMapping("/cadastrar")
	public String cadastrar(Usuario usuario) {
		String senhaEncriptada = SenhaUtils.encode(usuario.getSenha());
		usuario.setSenha(senhaEncriptada);
		usuarioRepository.save(usuario);
		return "redirect:/usuarios";
	}

	@PostMapping("/{id}/editar")
	public String editar(Usuario usuario, @PathVariable Long id) {
		String senhaAtual = usuarioRepository.getReferenceById(id).getSenha();
		usuario.setSenha(senhaAtual);
		usuarioRepository.save(usuario);
		return "redirect:/usuarios";
	}
    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        usuarioRepository.deleteById(id);

        return "redirect:/usuarios";
    }
}
