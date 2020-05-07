package com.stolk.alecsandro.obra.lista;

import com.stolk.alecsandro.obra.modelo.Fornecedor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.stolk.alecsandro.obra.lista.ListaConta.contas;
import static com.stolk.alecsandro.obra.lista.ListaContato.contatos;
import static java.util.Arrays.asList;

public class ListaFornecedor {

    private static AtomicLong contador = new AtomicLong(1L);
    public static List<Fornecedor> fornecedores = new ArrayList<>();

    static {
        adicionar(new Fornecedor("Madereira Madeira LTDA", "26315520658700", asList(contas.get(3), contas.get(2), contas.get(1)), asList(contatos.get(1), contatos.get(0))));
        adicionar(new Fornecedor("Olaria Tijolada ME", "42305910346185", asList(contas.get(5)), asList(contatos.get(5), contatos.get(4))));
        adicionar(new Fornecedor("Materiais de construção Construtivo EPP", "65468294543502", null, asList(contatos.get(6))));
        adicionar(new Fornecedor("Empreiteira Dia-a-dia LTDA", "87613260861612", asList(contas.get(0), contas.get(4)), null));
        adicionar(new Fornecedor("Revestimentos sem Ânimos EPP", "25216885251607", asList(contas.get(6)), null));
        adicionar(new Fornecedor("Ferragens Ferrado LTDA ME", "51671360140837", null, asList(contatos.get(2), contatos.get(3))));
        adicionar(new Fornecedor("Caixa Econômica Federal", null, null, null));
    }

    public static void adicionar(Fornecedor fornecedor) {
        fornecedor.setId(contador.getAndIncrement());
        fornecedores.add(fornecedor);
    }

    public List<Fornecedor> buscar() {
        return fornecedores;
    }

    public Fornecedor buscar(Long id) {
        for (Fornecedor fornecedor : fornecedores) {
            if (id.equals(fornecedor.getId())) return fornecedor;
        }
        return null;
//        Fornecedor fornecedor = (Fornecedor) fornecedores.stream().filter(f -> id.equals(f.getId()));
//        return fornecedor;
    }

    public void editar(Fornecedor fornecedor) {
        for (Fornecedor fornecedorBusca : fornecedores) {
            if (fornecedor.getId().equals(fornecedorBusca.getId())) {
                fornecedorBusca.setNome(fornecedor.getNome());
            }
        }
    }

    public void editar(Long id, Fornecedor fornecedor) {
        for (Fornecedor fornecedorBusca : fornecedores) {
            if (id.equals(fornecedorBusca.getId())) {
                fornecedorBusca.setNome(fornecedor.getNome());
            }
        }
    }

    public void excluir(Fornecedor fornecedor) {
        fornecedores.removeIf(fornecedor1 -> fornecedor.getId().equals(fornecedor1.getId()));
    }

    public void excluir(Long id) {
        fornecedores.removeIf(fornecedor -> id.equals(fornecedor.getId()));
    }
}
