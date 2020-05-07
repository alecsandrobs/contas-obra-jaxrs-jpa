package com.stolk.alecsandro.obra;

import com.stolk.alecsandro.obra.banco.Dao;
import com.stolk.alecsandro.obra.modelo.Contato;

import java.util.List;

import static java.util.Arrays.asList;

public class ContatoTest {

    public static List<Contato> contatos = asList(
            new Contato("José da Silva de Alencar", "12345678909", "57995876341", "josesa@email.com"),
            new Contato("Maria Souza de Alencar", null, "23977931594", "mariasa@email.com"),
            new Contato("Maurício Aquino Rego", null, "46931782597", null),
            new Contato("Flávia Madeira Rego", "98765432100", "10932594621", "rego.flavia@regomail.com.br"),
            new Contato("Rui Barba Rosa", "41378847698253", "60991734682", "rbarbarosa@barbamail.com.br"),
            new Contato("Joana Flores Rosa", null, null, "hfloresrosa@barbamail.com.br"),
            new Contato("Amim Amou Amado", "89512792061900", null, null)
    );

    public static void main(String[] args) {
        adiciona();
    }

    public static void adiciona() {
        Dao<Contato> dao = new Dao<>();
        dao.iniciar();

        contatos.forEach(contato -> dao.cadastrar(contato));

        dao.encerrar();
        dao.fechar();
    }
}
