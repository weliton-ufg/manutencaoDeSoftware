package com.algaworks.pedidovenda.controller;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.Endereco;
import com.algaworks.pedidovenda.repository.Clientes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class ClientesManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Clientes clientesRepository;
    private List<Cliente> clientesFiltrados;
    private Cliente clienteEdicao = new Cliente();
    private Endereco enderecoEdicao;
    private String nome;

    @PostConstruct
    public void onInit() {
        pesquisar();
    }

    public ClientesManager() {

    }

    public void pesquisar() {
        try {
            clientesFiltrados = new ArrayList<>();
            clientesFiltrados = clientesRepository.porNome(nome);
            RequestContext.getCurrentInstance().update(":frm:dtCliente");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void novoEndereco() {
        enderecoEdicao = new Endereco();
    }

    public String novoCliente() {
        clienteEdicao = new Cliente();
        return "CadastroCliente?faces-redirect=true";
    }

    public void salvar() {
        // if (!clientes.contains(clienteEdicao)) {
        //     clientes.add(clienteEdicao);
        // }
        clienteEdicao = new Cliente();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente salvo com sucesso!"));
    }

    public Cliente getClienteEdicao() {
        return clienteEdicao;
    }

    public void setClienteEdicao(Cliente clienteEdicao) {
        this.clienteEdicao = clienteEdicao;
    }

    public Endereco getEnderecoEdicao() {
        return enderecoEdicao;
    }

    public void setEnderecoEdicao(Endereco enderecoEdicao) {
        this.enderecoEdicao = enderecoEdicao;
    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }

    public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
        this.clientesFiltrados = clientesFiltrados;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

}
