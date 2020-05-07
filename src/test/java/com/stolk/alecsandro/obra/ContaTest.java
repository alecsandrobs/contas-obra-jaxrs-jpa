package com.stolk.alecsandro.obra;

import com.stolk.alecsandro.obra.banco.Dao;
import com.stolk.alecsandro.obra.modelo.Conta;

import java.util.Arrays;
import java.util.List;

public class ContaTest {

    public static List<Conta> contas = Arrays.asList(
            new Conta("Nu Pagamentos", "1", "1234567"),
            new Conta("Banco Inter", "1", "7654321"),
            new Conta("Itaú", "2662", "12647"),
            new Conta("Bradesco", "1677", "45679"),
            new Conta("Santander", "6543", "165450"),
            new Conta("Banco do Brasil", "5460", "65404"),
            new Conta("Caixa Econômica Federal", "6506", "65460")
    );

    public static void main(String[] args) {
        adiciona();
    }

    public static void adiciona() {
        Dao<Conta> dao = new Dao<>();

        dao.iniciar();

        contas.forEach(conta ->
                dao.cadastrar(conta)
        );

        dao.encerrar();
        dao.fechar();
    }
}
