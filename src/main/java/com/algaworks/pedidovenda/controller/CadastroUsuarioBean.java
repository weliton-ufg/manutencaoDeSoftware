package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.service.UsuarioServce;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	
	@Inject
	UsuarioServce userService;
	
	public CadastroUsuarioBean(){
	   limpar();
	}
	
	@PostConstruct
	public void inicializar() {
	   if (this.usuario == null) {
	      limpar();
	   }
	}
	 
	 
	private void limpar(){
		this.usuario = new Usuario();
	}
	 
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	 public void salvar() {
	        try {
	            this.usuario = userService.salvar(this.usuario);
	            limpar();

	            FacesUtil.addInfoMessage("Usuário cadastrado com sucesso!");
	        } catch (NegocioException ne) {
	            FacesUtil.addErrorMessage(ne.getMessage());
	        }
	}

}
