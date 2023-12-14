package com.FDMVC.controlers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.FDMVC.model.Usuario;
import com.FDMVC.repositories.UsuarioRepository;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }

    @GetMapping("/perfil")
    public ModelAndView perfil(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("usuario/perfil");

        Usuario usuario = usuarioRepository.findByEmail(principal.getName()).get();
        modelAndView.addObject("usuario", usuario);
   

        return modelAndView;
    }


    
}
