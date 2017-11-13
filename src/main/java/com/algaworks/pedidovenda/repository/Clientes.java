package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import javax.persistence.EntityTransaction;

public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Cliente porId(Long id) {
        return this.manager.find(Cliente.class, id);
    }

    public List<Cliente> porNome(String nome) {
        return this.manager.createQuery("from Cliente "
                + "where upper(nome) like :nome", Cliente.class)
                .setParameter("nome", nome.toUpperCase() + "%")
                .getResultList();
    }

    @Transactional
    public Cliente salvar(Cliente cliente) throws NegocioException {
        return manager.merge(cliente);
    }

    public void excluir(Cliente cliente) {
        EntityTransaction trx = manager.getTransaction();
        cliente = porId(cliente.getId());
        trx.begin();
        manager.remove(cliente);
        manager.flush();
        trx.commit();

    }

}
