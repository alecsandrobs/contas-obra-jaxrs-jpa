package com.stolk.alecsandro.obra;

import com.stolk.alecsandro.obra.banco.Dao;
import com.stolk.alecsandro.obra.modelo.Conta;
import com.stolk.alecsandro.obra.modelo.Contato;
import com.stolk.alecsandro.obra.modelo.Fornecedor;
import com.stolk.alecsandro.obra.modelo.Lancamento;

import java.util.List;

import static com.stolk.alecsandro.obra.modelo.Lancamento.TipoLancamento.PAGAMENTO;
import static com.stolk.alecsandro.obra.modelo.Lancamento.TipoLancamento.RECEBIMENTO;
import static com.stolk.alecsandro.obra.util.Util.dataBr;
import static java.util.Arrays.asList;

public class ObraTest {
    public static void main(String[] args) {
        List<Conta> contas = asList(
                new Conta("Nu Pagamentos", "1", "1234567"),
                new Conta("Banco Inter", "1", "7654321"),
                new Conta("Itaú", "2662", "12647"),
                new Conta("Bradesco", "1677", "45679"),
                new Conta("Santander", "6543", "165450"),
                new Conta("Banco do Brasil", "5460", "65404"),
                new Conta("Caixa Econômica Federal", "6506", "65460")
        );

        Dao<Conta> daoConta = new Dao<>();
        daoConta.iniciar();
        contas.forEach(conta -> daoConta.cadastrar(conta));
        daoConta.encerrar();

        List<Contato> contatos = asList(
                new Contato("José da Silva de Alencar", "12345678909", "57995876341", "josesa@email.com"),
                new Contato("Maria Souza de Alencar", null, "23977931594", "mariasa@email.com"),
                new Contato("Maurício Aquino Rego", null, "46931782597", null),
                new Contato("Flávia Madeira Rego", "98765432100", "10932594621", "rego.flavia@regomail.com.br"),
                new Contato("Rui Barba Rosa", "41378847698253", "60991734682", "rbarbarosa@barbamail.com.br"),
                new Contato("Joana Flores Rosa", null, null, "hfloresrosa@barbamail.com.br"),
                new Contato("Amim Amou Amado", "89512792061900", null, null)
        );

        Dao<Contato> daoContato = new Dao<>();
        daoContato.iniciar();
        contatos.forEach(contato -> daoContato.cadastrar(contato));
        daoConta.encerrar();

        List<Fornecedor> fornecedores = asList(
                new Fornecedor("Madereira Madeira LTDA", "26315520658700", asList(contas.get(3), contas.get(2), contas.get(1)), asList(contatos.get(1), contatos.get(0))),
                new Fornecedor("Olaria Tijolada ME", "42305910346185", asList(contas.get(5)), asList(contatos.get(5), contatos.get(4))),
                new Fornecedor("Materiais de construção Construtivo EPP", "65468294543502", null, asList(contatos.get(6))),
                new Fornecedor("Empreiteira Dia-a-dia LTDA", "87613260861612", asList(contas.get(0), contas.get(4)), null),
                new Fornecedor("Revestimentos sem Ânimos EPP", "25216885251607", asList(contas.get(6)), null),
                new Fornecedor("Ferragens Ferrado LTDA ME", "51671360140837", null, asList(contatos.get(2), contatos.get(3))),
                new Fornecedor("Caixa Econômica Federal", null, null, null)
        );

        Dao<Fornecedor> daoFornecedor = new Dao<>();
        daoFornecedor.iniciar();
        fornecedores.forEach(fornecedor -> daoFornecedor.cadastrar(fornecedor));
        daoFornecedor.encerrar();

        List<Lancamento> lancamentos = asList(
                new Lancamento(dataBr("01/07/2019"), fornecedores.get(6), null, RECEBIMENTO, 1000.0, dataBr("05/09/2019"), "converter a resposta em comentário"),
                new Lancamento(dataBr("02/05/2019"), fornecedores.get(4), contas.get(0), PAGAMENTO, 73159.99, dataBr("05/06/2019"), "Tem que analisar a utilidade"),
                new Lancamento(dataBr("03/11/2019"), fornecedores.get(1), contas.get(5), PAGAMENTO, 46.82, dataBr("07/11/2019"), "Exclusão da resposta só em casos extremos"),
                new Lancamento(dataBr("10/09/2019"), fornecedores.get(3), null, PAGAMENTO, 75.35, dataBr("10/09/2019"), "deixar um comentário"),
                new Lancamento(dataBr("10/10/2019"), fornecedores.get(2), contas.get(3), PAGAMENTO, 1597.53, dataBr("10/10/2019"), "Nosso objetivo é mostrar para as pessoas que elas podem fazer melhor"),
                new Lancamento(dataBr("12/07/2019"), fornecedores.get(5), null, PAGAMENTO, 543.21, dataBr("12/07/2019"), "Editar para torná-la útil não é uma opção"),
                new Lancamento(dataBr("07/12/2019"), fornecedores.get(0), contas.get(2), RECEBIMENTO, 12033.45, null, "Votar contra")
        );

        Dao<Lancamento> daoLancamento = new Dao<>();
        daoLancamento.iniciar();
        lancamentos.forEach(lancamento -> daoLancamento.cadastrar(lancamento));
        daoLancamento.encerrar();

        daoConta.fechar();
        daoContato.fechar();
        daoFornecedor.fechar();
        daoLancamento.fechar();


    }
}
