package com.stolk.alecsandro.obra.lista;

import com.stolk.alecsandro.obra.modelo.Conta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ListaConta {

    private static AtomicLong contador = new AtomicLong(1L);
    public static List<Conta> contas = new ArrayList<>();

    static {
        adicionar(new Conta("Nu Pagamentos", "1", "1234567"));
        adicionar(new Conta("Banco Inter", "1", "7654321"));
        adicionar(new Conta("Itaú", "2662", "12647"));
        adicionar(new Conta("Bradesco", "1677", "45679"));
        adicionar(new Conta("Santander", "6543", "165450"));
        adicionar(new Conta("Banco do Brasil", "5460", "65404"));
        adicionar(new Conta("Caixa Econômica Federal", "6506", "65460"));
    }

    public static void adicionar(Conta conta) {
        conta.setId(contador.getAndIncrement());
        contas.add(conta);
    }

    public List<Conta> buscar() {
        return contas;
    }

    public Conta buscar(Long id) {
        for (Conta conta : contas) {
            if (id.equals(conta.getId())) return conta;
        }
        return null;
    }

    public void editar(Conta conta) {
        for (Conta contaBusca : contas) {
            if (conta.getId().equals(contaBusca.getId())) {
                contaBusca.setBanco(conta.getBanco());
                contaBusca.setAgencia(conta.getAgencia());
                contaBusca.setNumero(conta.getNumero());
            }
        }
    }

    public void editar(Long id, Conta conta) {
        for (Conta contaBusca : contas) {
            if (id.equals(contaBusca.getId())) {
                contaBusca.setBanco(conta.getBanco());
                contaBusca.setAgencia(conta.getAgencia());
                contaBusca.setNumero(conta.getNumero());
            }
        }
    }

    public void excluir(Conta conta) {
        contas.removeIf(contaBusca -> conta.getId().equals(contaBusca.getId()));
    }

    public void excluir(Long id) {
        contas.removeIf(conta -> id.equals(conta.getId()));
    }
}
