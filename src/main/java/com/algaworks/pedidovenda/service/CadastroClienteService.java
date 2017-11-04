package com.algaworks.pedidovenda.service;

import com.algaworks.pedidovenda.model.Cliente;
import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.repository.Clientes;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class CadastroClienteService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Clientes clientes;

    @Transactional
    public Cliente salvar(Cliente cliente) throws NegocioException {

        //cliente = this.clientes.guardar(cliente);
        return cliente;
    }

}
