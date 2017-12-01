package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.repository.Usuarios;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class UsuarioServce implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
	
	@Transactional
	public Usuario salvar(Usuario usuario) throws NegocioException {
		Usuario ususarioExist = usuarios.porEmail(usuario.getEmail());
		
		if (ususarioExist != null) {
			throw new NegocioException("Usuário já cadastrado!.");
		}
		
		return usuarios.gravar(usuario);
	}
	
}
