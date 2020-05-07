package com.stolk.alecsandro.obra;

import com.stolk.alecsandro.obra.banco.Dao;
import com.stolk.alecsandro.obra.modelo.Lancamento;

import java.util.List;

public class Consulta {

    public static void main(String[] args) {
        Dao<Lancamento> lancamentoDao = new Dao<>(Lancamento.class);
        List<Lancamento> lancamentos = lancamentoDao.buscar();

        lancamentos.forEach(lancamento -> {
            System.out.println(
                    "| " +
                            lancamento.getId() + " | " +
                            lancamento.getData() + " | " +
                            lancamento.getFornecedor().getNome() + " | " +
                            lancamento.getTipo().getTipo() + " | " +
                            lancamento.getTipo().getValor() + " | " +
                            lancamento.getValor() + " | " +
//                            lancamento.getConta() != null ? lancamento.getConta().getBanco() + " " + lancamento.getConta().getAgencia() + "." + lancamento.getConta().getNumero() : "[sem conta]" + " | " +
                            lancamento.getPagamento() + " | " +
                            lancamento.getObservacoes() + " | "
            );
        });
    }
}
