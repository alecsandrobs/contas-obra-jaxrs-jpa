package com.stolk.alecsandro.obra.modelo;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "fornecedores")
public class Fornecedor extends EntidadeId {

    @NotBlank(message = "O nome deve ser infromado")
    private String nome;

    private String documento;

    @ManyToMany
    private List<Conta> contas;

    @ManyToMany
    private List<Contato> contatos;

    public Fornecedor() {
    }

    public Fornecedor(@NotBlank(message = "O nome deve ser infromado") String nome, String documento, List<Conta> contas, List<Contato> contatos) {
        this.nome = nome;
        this.documento = documento;
        this.contas = contas;
        this.contatos = contatos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
}
