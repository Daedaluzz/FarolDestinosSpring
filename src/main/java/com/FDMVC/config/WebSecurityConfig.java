package com.FDMVC.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.FDMVC.enums.TipoUsuario;
import com.FDMVC.services.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                		.requestMatchers("/css/**").permitAll()
                        .requestMatchers("/img/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/**/cadastrar").hasAuthority(TipoUsuario.ADMIN.toString())
        	            .requestMatchers("/**/editar").hasAuthority(TipoUsuario.ADMIN.toString())
        	            .requestMatchers("/**/excluir").hasAuthority(TipoUsuario.ADMIN.toString())
                        .anyRequest().authenticated());

        http.formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/perfil", true)
                .permitAll());

        http.logout(logout -> logout
                .logoutRequestMatcher(
                        new AntPathRequestMatcher("/logout", "GET")
                )
                .logoutSuccessUrl("/login"));
	        
			return http.build();
	    }
    
	  @Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
	}
}