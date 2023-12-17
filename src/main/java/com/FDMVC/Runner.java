package com.FDMVC;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.FDMVC.enums.TipoUsuario;
import com.FDMVC.model.Usuario;
import com.FDMVC.repositories.UsuarioRepository;
import com.FDMVC.utils.SenhaUtils;

@Configuration
public class Runner implements CommandLineRunner{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void run(String... args) throws Exception {

		//usuario//
		Usuario admin0 = new Usuario();
		admin0.setId((long) 1);
		admin0.setCpf("777.888.999-44");
		admin0.setDataNascimento(LocalDate.now());
		admin0.setDataNascimento(LocalDate.now());
		admin0.setNome("admin");
		admin0.setTelefone("(31)99988-7744");
		admin0.setEmail("admin@admin.com");
		admin0.setTipoUsuario(TipoUsuario.ADMIN);
		String senhaEncriptada = SenhaUtils.encode("0000");
		admin0.setSenha(senhaEncriptada);
		usuarioRepository.save(admin0);
	}

}
