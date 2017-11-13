package com.algaworks.pedidovenda.controller;

import com.algaworks.pedidovenda.model.Cliente;
import com.algaworks.pedidovenda.model.Endereco;
import com.algaworks.pedidovenda.model.TipoPessoa;
import com.algaworks.pedidovenda.repository.Clientes;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Clientes clientesRepository;
    private List<Cliente> clientesFiltrados;
    @Inject
    private Cliente cliente;
    private Endereco enderecoEdicao;
    private String nome;

    public CadastroClienteBean() {
        limpar();
        pesquisar();
    }

    public void limpar() {
        cliente = new Cliente();
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
        cliente = new Cliente();
        return "CadastroCliente?faces-redirect=true";
    }

    public void inicializar() {
        try {
            if (this.cliente.getId() == null) {
                limpar();
            }

        } catch (Exception ex) {
            Logger.getLogger(CadastroClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            cliente = clientesRepository.salvar(cliente);

        } catch (NegocioException ex) {
            Logger.getLogger(CadastroClienteBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        cliente = new Cliente();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente salvo com sucesso!"));
    }

    public void excluir() {
        try {

            clientesRepository.excluir(cliente);
            //buscarContatos();
            FacesUtil.addInfoMessage("Cliente " + cliente.getNome() + " Excluído com sucesso!");
            clientesFiltrados.remove(cliente);
        } catch (Exception e) {
            FacesUtil.addInfoMessage("Erro ao excluir o cliente " + cliente.getNome());
        }

    }

    public void setClienteEdicao(Cliente clienteEdicao) {
        this.cliente = clienteEdicao;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isEditando() {
        return this.cliente.getId() != null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoPessoa[] getListaTipoPessoa() {
        return TipoPessoa.values();
    }
}
