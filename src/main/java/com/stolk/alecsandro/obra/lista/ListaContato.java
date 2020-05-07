package com.stolk.alecsandro.obra.lista;

import com.stolk.alecsandro.obra.modelo.Contato;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ListaContato {

    private static AtomicLong contador = new AtomicLong(1L);
    public static List<Contato> contatos = new ArrayList<>();

    static {
        adicionar(new Contato("José da Silva de Alencar", "12345678909", "57995876341", "josesa@email.com"));
        adicionar(new Contato("Maria Souza de Alencar", null, "23977931594", "mariasa@email.com"));
        adicionar(new Contato("Maurício Aquino Rego", null, "46931782597", null));
        adicionar(new Contato("Flávia Madeira Rego", "98765432100", "10932594621", "rego.flavia@regomail.com.br"));
        adicionar(new Contato("Rui Barba Rosa", "41378847698253", "60991734682", "rbarbarosa@barbamail.com.br"));
        adicionar(new Contato("Joana Flores Rosa", null, null, "hfloresrosa@barbamail.com.br"));
        adicionar(new Contato("Amim Amou Amado", "89512792061900", null, null));
    }

    public static void adicionar(Contato contato) {
        contato.setId(contador.getAndIncrement());
        contatos.add(contato);
    }

    public List<Contato> buscar() {
        return contatos;
    }

    public Contato buscar(Long id) {
        for (Contato contato : contatos) {
            if (id.equals(contato.getId())) return contato;
        }
        return null;
    }

    public void editar(Contato contato) {
        for (Contato contatoBusca : contatos) {
            if (contato.getId().equals(contatoBusca.getId())) {
                contatoBusca.setNome(contato.getNome());
            }
        }
    }

    public void editar(Long id, Contato contato) {
        for (Contato contatoBusca : contatos) {
            if (id.equals(contatoBusca.getId())) {
                contatoBusca.setNome(contato.getNome());
            }
        }
    }

    public void excluir(Contato contato) {
        contatos.removeIf(contatoBusca -> contato.getId().equals(contatoBusca.getId()));
    }

    public void excluir(Long id) {
        contatos.removeIf(contato -> id.equals(contato.getId()));
    }
}
