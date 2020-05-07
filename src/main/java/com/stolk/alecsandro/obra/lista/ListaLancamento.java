package com.stolk.alecsandro.obra.lista;

import com.stolk.alecsandro.obra.modelo.Lancamento;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.stolk.alecsandro.obra.lista.ListaConta.contas;
import static com.stolk.alecsandro.obra.lista.ListaFornecedor.fornecedores;
import static com.stolk.alecsandro.obra.modelo.Lancamento.TipoLancamento.PAGAMENTO;
import static com.stolk.alecsandro.obra.modelo.Lancamento.TipoLancamento.RECEBIMENTO;
import static com.stolk.alecsandro.obra.util.Util.dataBr;

public class ListaLancamento {

    private static AtomicLong contador = new AtomicLong(1L);
    public static List<Lancamento> lancamentos = new ArrayList<>();

    static {
        adicionar(new Lancamento(dataBr("01/07/2019"), fornecedores.get(6), null, RECEBIMENTO, 1000.0, dataBr("05/09/2019"), "converter a resposta em comentário"));
        adicionar(new Lancamento(dataBr("02/05/2019"), fornecedores.get(4), contas.get(0), PAGAMENTO, 73159.99, dataBr("05/06/2019"), "Tem que analisar a utilidade"));
//        adicionar(new Lancamento(dataBr("03/11/2019"), fornecedores.get(1), contas.get(5), PAGAMENTO, 46.82, dataBr("07/11/2019"), "Exclusão da resposta só em casos extremos"));
//        adicionar(new Lancamento(dataBr("10/09/2019"), fornecedores.get(3), null, PAGAMENTO, 75.35, dataBr("10/09/2019"), "deixar um comentário"));
//        adicionar(new Lancamento(dataBr("10/10/2019"), fornecedores.get(2), contas.get(3), PAGAMENTO, 1597.53, dataBr("10/10/2019"), "Nosso objetivo é mostrar para as pessoas que elas podem fazer melhor"));
//        adicionar(new Lancamento(dataBr("12/07/2019"), fornecedores.get(5), null, PAGAMENTO, 543.21, dataBr("12/07/2019"), "Editar para torná-la útil não é uma opção"));
//        adicionar(new Lancamento(dataBr("07/12/2019"), fornecedores.get(0), contas.get(2), RECEBIMENTO, 12033.45, null, "Votar contra"));
    }

    public static void adicionar(Lancamento lancamento) {
        lancamento.setId(contador.getAndIncrement());
        lancamentos.add(lancamento);
    }

    public List<Lancamento> buscar() {
        return lancamentos;
    }

    public Lancamento buscar(Long id) {
        for (Lancamento lancamento : lancamentos) {
            if (id.equals(lancamento.getId())) return lancamento;
        }
        return null;
    }

    public void editar(Lancamento lancamento) {
        for (Lancamento lancamentoBusca : lancamentos) {
            if (lancamento.getId().equals(lancamentoBusca.getId())) {
                lancamentoBusca.setData(lancamento.getData());
                lancamentoBusca.setFornecedor(lancamento.getFornecedor());
                lancamentoBusca.setConta(lancamento.getConta());
                lancamentoBusca.setTipo(lancamento.getTipo());
                lancamentoBusca.setValor(lancamento.getValor());
                lancamentoBusca.setPagamento(lancamento.getPagamento());
                lancamentoBusca.setObservacoes(lancamento.getObservacoes());
            }
        }
    }

    public void editar(Long id, Lancamento lancamento) {
        for (Lancamento lancamentoBusca : lancamentos) {
            if (id.equals(lancamentoBusca.getId())) {
                lancamentoBusca.setData(lancamento.getData());
                lancamentoBusca.setFornecedor(lancamento.getFornecedor());
                lancamentoBusca.setConta(lancamento.getConta());
                lancamentoBusca.setTipo(lancamento.getTipo());
                lancamentoBusca.setValor(lancamento.getValor());
                lancamentoBusca.setPagamento(lancamento.getPagamento());
                lancamentoBusca.setObservacoes(lancamento.getObservacoes());
            }
        }
    }

    public void excluir(Lancamento lancamento) {
        lancamentos.removeIf(lancamentoBusca -> lancamento.getId().equals(lancamentoBusca.getId()));
    }

    public void excluir(Long id) {
        lancamentos.removeIf(lancamento -> id.equals(lancamento.getId()));
    }
}
