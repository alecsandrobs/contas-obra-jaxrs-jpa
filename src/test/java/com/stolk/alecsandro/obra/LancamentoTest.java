package com.stolk.alecsandro.obra;

import com.stolk.alecsandro.obra.banco.Dao;
import com.stolk.alecsandro.obra.modelo.Conta;
import com.stolk.alecsandro.obra.modelo.Fornecedor;
import com.stolk.alecsandro.obra.modelo.Lancamento;

import java.util.List;

import static com.stolk.alecsandro.obra.modelo.Lancamento.TipoLancamento.PAGAMENTO;
import static com.stolk.alecsandro.obra.modelo.Lancamento.TipoLancamento.RECEBIMENTO;
import static com.stolk.alecsandro.obra.util.Util.dataBr;
import static java.util.Arrays.asList;

public class LancamentoTest {
    static Conta conta1 = new Conta();
    //    static Conta conta2 = new Conta();
    static Conta conta3 = new Conta();
    static Conta conta4 = new Conta();
    //    static Conta conta5 = new Conta();
    static Conta conta6 = new Conta();
//    static Conta conta7 = new Conta();

    static Fornecedor fornecedor1 = new Fornecedor();
    static Fornecedor fornecedor2 = new Fornecedor();
    static Fornecedor fornecedor3 = new Fornecedor();
    static Fornecedor fornecedor4 = new Fornecedor();
    static Fornecedor fornecedor5 = new Fornecedor();
    static Fornecedor fornecedor6 = new Fornecedor();
    static Fornecedor fornecedor7 = new Fornecedor();

    public static List<Lancamento> lancamentos = asList(
            new Lancamento(dataBr("01/07/2019"), fornecedor7, null, RECEBIMENTO, 1000.0, dataBr("05/09/2019"), "converter a resposta em comentário"),
            new Lancamento(dataBr("02/05/2019"), fornecedor5, conta1, PAGAMENTO, 73159.99, dataBr("05/06/2019"), "Tem que analisar a utilidade"),
            new Lancamento(dataBr("03/11/2019"), fornecedor2, conta6, PAGAMENTO, 46.82, dataBr("07/11/2019"), "Exclusão da resposta só em casos extremos"),
            new Lancamento(dataBr("10/09/2019"), fornecedor4, null, PAGAMENTO, 75.35, dataBr("10/09/2019"), "deixar um comentário"),
            new Lancamento(dataBr("10/10/2019"), fornecedor3, conta4, PAGAMENTO, 1597.53, dataBr("10/10/2019"), "Nosso objetivo é mostrar para as pessoas que elas podem fazer melhor"),
            new Lancamento(dataBr("12/07/2019"), fornecedor6, null, PAGAMENTO, 543.21, dataBr("12/07/2019"), "Editar para torná-la útil não é uma opção"),
            new Lancamento(dataBr("07/12/2019"), fornecedor1, conta3, RECEBIMENTO, 12033.45, null, "Votar contra")
    );

    public static void main(String[] args) {
        conta1.setId(1L);
//        conta2.setId(2L);
        conta3.setId(3L);
        conta4.setId(4L);
//        conta5.setId(5L);
        conta6.setId(6L);
//        conta7.setId(7L);

        fornecedor1.setId(1L);
        fornecedor2.setId(2L);
        fornecedor3.setId(3L);
        fornecedor4.setId(4L);
        fornecedor5.setId(5L);
        fornecedor6.setId(6L);
        fornecedor7.setId(7L);

        adiciona();
    }

    public static void adiciona() {
        Dao<Lancamento> dao = new Dao<>();
        dao.iniciar();

        lancamentos.forEach(lancamento -> dao.cadastrar(lancamento));

        dao.encerrar();
        dao.fechar();
    }
}
