package com.FDMVC.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.FDMVC.enums.TipoUsuario;



public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;
	private Usuario usuario;

    public UserDetailsImpl(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	   TipoUsuario tipoUsuario =
    	            usuario.getTipoUsuario().toString().equals("ADMIN") ?
    	            TipoUsuario.ADMIN :
    	            TipoUsuario.USER;
    	        return AuthorityUtils.createAuthorityList(tipoUsuario.toString());
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
