package com.stolk.alecsandro.obra;

import com.stolk.alecsandro.obra.banco.Dao;
import com.stolk.alecsandro.obra.modelo.Lancamento;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.stolk.alecsandro.obra.modelo.Lancamento.TipoLancamento.PAGAMENTO;
import static com.stolk.alecsandro.obra.modelo.Lancamento.TipoLancamento.RECEBIMENTO;
import static com.stolk.alecsandro.obra.util.Util.*;
import static java.lang.String.format;

public class Consulta {

    public static void main(String[] args) {
        Dao<Lancamento> lancamentoDao = new Dao<>(Lancamento.class);
        List<Lancamento> lancamentos = lancamentoDao.buscar();
        System.out.println("+------------+-------------------------------------------+-------------------------------------------+--------------+-----------------+------------+-------------------------------------------------------------------------------------------------------+");
        System.out.println("| Data       | Fornecedor                                | Conta                                     | Tipo         | Valor           | Pago em    | Observações                                                                                           |");
        System.out.println("+------------+-------------------------------------------+-------------------------------------------+--------------+-----------------+------------+-------------------------------------------------------------------------------------------------------+");
        lancamentos.forEach(lancamento -> {
            String conta = lancamento.getConta() != null ? lancamento.getConta().getBanco() + " " + lancamento.getConta().getAgencia() + "." + lancamento.getConta().getNumero() : "-";
            String texto = format("| %s | %s | %s | %s | %s | %s | %s |",
                    dataTxtBr(lancamento.getData()),
                    completaEsquerda(lancamento.getFornecedor().getNome(), 40),
                    completaEsquerda(conta, 40),
                    completaEsquerda(lancamento.getTipo().getValor(), 11),
                    completaDireita(emReal(lancamento.getValor())),
                    lancamento.getPagamento() != null ? dataTxtBr(lancamento.getPagamento()) : "--/--/----",
                    completaEsquerda(lancamento.getObservacoes(), 100));
            System.out.println(texto);
        });
        System.out.println("+------------+-------------------------------------------+-------------------------------------------+--------------+-----------------+------------+-------------------------------------------------------------------------------------------------------+");
        System.out.println();

        Predicate<Lancamento> tipoPagamento = lancamento -> lancamento.getTipo().equals(PAGAMENTO);
        Predicate<Lancamento> tipoRecebimento = lancamento -> lancamento.getTipo().equals(RECEBIMENTO);

        Predicate<Lancamento> pagamento = lancamento -> lancamento.getPagamento() != null;
        Predicate<Lancamento> recebimento = lancamento -> lancamento.getPagamento() != null;

        Function<Lancamento, Double> valores = lancamento -> lancamento.getValor();

        BinaryOperator<Double> somaPagamentos = (acumulador, valor) -> acumulador + valor;
        BinaryOperator<Double> somaRecebimentos = (acumulador, valor) -> acumulador + valor;

        Double pago = lancamentos.parallelStream()
                .filter(tipoPagamento)
                .filter(pagamento)
                .map(valores)
                .reduce(somaPagamentos).get();

        Double recebido = lancamentos.parallelStream()
                .filter(tipoRecebimento)
                .filter(recebimento)
                .map(valores)
                .reduce(somaRecebimentos).get();

        Double totalPagar = lancamentos.stream()
                .filter(tipoPagamento)
                .map(valores)
                .reduce(somaPagamentos).get();

        Double totalReceber = lancamentos.parallelStream()
                .filter(tipoRecebimento)
                .map(valores)
                .reduce(somaRecebimentos).get();

        System.out.println("+---------------------------+---------------------------+---------------------------+");
        Double saldoPagar = totalPagar - pago;
        Double saldoRecebidoPago = recebido - pago;
        System.out.println(format("| Recebido: %s | Pago: %s | Saldo: %s |", completaDireita(emReal(recebido), 15), completaDireita(emReal(pago), 19), completaDireita(emReal(saldoRecebidoPago), 18)));
        Double saldoReceber = totalReceber - recebido;
        Double saldoReceberPagar = saldoReceber - saldoPagar;
        System.out.println(format("| Receber: %s | Pagar: %s | Saldo: %s |", completaDireita(emReal(saldoReceber), 16), completaDireita(emReal(saldoPagar), 18), completaDireita(emReal(saldoReceberPagar), 18)));
        Double saldoEfetivo = saldoReceber - saldoPagar;
        Double saldoTotal = totalReceber - totalPagar;
        System.out.println(format("| Total: %s | Total: %s | Saldo: %s |", completaDireita(emReal(totalReceber), 18), completaDireita(emReal(totalPagar), 18), completaDireita(emReal(saldoTotal), 18)));
        System.out.print("+---------------------------+---------------------------+---------------------------+");
    }
}
