package com.stolk.alecsandro.obra;

import com.stolk.alecsandro.obra.banco.Dao;
import com.stolk.alecsandro.obra.modelo.Conta;
import com.stolk.alecsandro.obra.modelo.Contato;
import com.stolk.alecsandro.obra.modelo.Fornecedor;

import java.util.List;

import static java.util.Arrays.asList;

public class FornecedorTest {

    static Conta conta1 = new Conta();
    static Conta conta2 = new Conta();
    static Conta conta3 = new Conta();
    static Conta conta4 = new Conta();
    static Conta conta5 = new Conta();
    static Conta conta6 = new Conta();
    static Conta conta7 = new Conta();

    static Contato contato1 = new Contato();
    static Contato contato2 = new Contato();
    static Contato contato3 = new Contato();
    static Contato contato4 = new Contato();
    static Contato contato5 = new Contato();
    static Contato contato6 = new Contato();
    static Contato contato7 = new Contato();

    public static List<Fornecedor> fornecedores = asList(
            new Fornecedor("Madereira Madeira LTDA", "26315520658700", asList(conta4, conta3, conta2), asList(contato2, contato1)),
            new Fornecedor("Olaria Tijolada ME", "42305910346185", asList(conta6), asList(contato6, contato5)),
            new Fornecedor("Materiais de construção Construtivo EPP", "65468294543502", null, asList(contato7)),
            new Fornecedor("Empreiteira Dia-a-dia LTDA", "87613260861612", asList(conta1, conta5), null),
            new Fornecedor("Revestimentos sem Ânimos EPP", "25216885251607", asList(conta7), null),
            new Fornecedor("Ferragens Ferrado LTDA ME", "51671360140837", null, asList(contato3, contato4)),
            new Fornecedor("Caixa Econômica Federal", null, null, null)
    );

    public static void main(String[] args) {
        conta1.setId(1L);
        conta2.setId(2L);
        conta3.setId(3L);
        conta4.setId(4L);
        conta5.setId(5L);
        conta6.setId(6L);
        conta7.setId(7L);

        contato1.setId(1L);
        contato2.setId(2L);
        contato3.setId(3L);
        contato4.setId(4L);
        contato5.setId(5L);
        contato6.setId(6L);
        contato7.setId(7L);
        adiciona();
    }

    public static void adiciona() {
        Dao<Fornecedor> dao = new Dao<>();
        dao.iniciar();

        fornecedores.forEach(fornecedor -> dao.cadastrar(fornecedor));

        dao.encerrar();
        dao.fechar();
    }
}
