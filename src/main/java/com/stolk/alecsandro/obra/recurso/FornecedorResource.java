package com.stolk.alecsandro.obra.recurso;

import com.stolk.alecsandro.obra.banco.Dao;
import com.stolk.alecsandro.obra.modelo.Conta;
import com.stolk.alecsandro.obra.modelo.Contato;
import com.stolk.alecsandro.obra.modelo.Fornecedor;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("fornecedores")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class FornecedorResource {

    @GET
    public Response get() {
        List<Fornecedor> fornecedores = new Dao<>(Fornecedor.class).buscar();
        return Response.ok(fornecedores).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        Fornecedor fornecedor = new Dao<>(Fornecedor.class).buscarUm(id);
        return Response.ok(fornecedor).build();
    }

    @POST
    public Response post(Fornecedor fornecedor) {
        Dao<Fornecedor> dao = new Dao<>();
        dao.iniciar()
                .cadastrar(fornecedor)
                .encerrar()
                .fechar();
        return Response.created(URI.create(String.format("/fornecedores/%s", fornecedor.getId()))).build();
    }

    @PUT
    public Response put(Fornecedor fornecedor) {
        Dao<Fornecedor> dao = new Dao<>();
        dao.iniciar()
                .editar(fornecedor)
                .encerrar()
                .fechar();
        return Response.ok(URI.create(String.format("/fornecedores/%s", fornecedor.getId()))).build();
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") Long id, Fornecedor fornecedor) {
        Fornecedor fornecedorBusca = new Dao<>(Fornecedor.class).buscarUm(id);
        fornecedor.setId(fornecedorBusca.getId());
        Dao<Fornecedor> dao = new Dao<>();
        dao.iniciar()
                .editar(fornecedor)
                .encerrar()
                .fechar();
        return Response.ok(URI.create(String.format("/fornecedores/%s", fornecedor.getId()))).build();
    }

    @DELETE
    public Response delete(Fornecedor fornecedor) {
        Dao<Fornecedor> dao = new Dao<>(Fornecedor.class);
        Fornecedor fornecedorRemover = dao.buscarUm(fornecedor.getId());
        if (fornecedorRemover != null) {
            dao.iniciar()
                    .excluir(fornecedorRemover);
            dao.encerrar()
                    .fechar();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Dao<Fornecedor> dao = new Dao<>(Fornecedor.class);
        Fornecedor fornecedor = dao.buscarUm(id);
        if (fornecedor != null) {
            dao.iniciar()
                    .excluir(fornecedor);
            dao.encerrar()
                    .fechar();
        }
        return Response.noContent().build();
    }

    @POST
    @Path("{fornecedorId}/contas")
    public Response postContas(@PathParam("fornecedorId") Long fornecedorId, Conta conta) {
        Dao<Fornecedor> dao = new Dao<>(Fornecedor.class);
        Fornecedor fornecedor = dao.buscarUm(fornecedorId);
        dao.iniciar();
        fornecedor.adicionarConta(conta);
        dao.encerrar().fechar();
        return Response.created(URI.create(String.format("/fornecedores/%s/contas/%s", fornecedorId, conta.getId()))).build();
    }

    @DELETE
    @Path("{fornecedorId}/contas/{id}")
    public Response deleteContas(@PathParam("fornecedorId") Long fornecedorId, @PathParam("id") Long id) {
        Dao<Fornecedor> dao = new Dao<>(Fornecedor.class);
        Fornecedor fornecedor = dao.buscarUm(fornecedorId);
        dao.iniciar();
        fornecedor.removerConta(id);
        dao.encerrar().fechar();
        return Response.noContent().build();
    }

    @POST
    @Path("{fornecedorId}/contatos")
    public Response postContatos(@PathParam("fornecedorId") Long fornecedorId, Contato contato) {
        Dao<Fornecedor> dao = new Dao<>(Fornecedor.class);
        Fornecedor fornecedor = dao.buscarUm(fornecedorId);
        dao.iniciar();
        fornecedor.adicionarContato(contato);
        dao.encerrar().fechar();
        return Response.created(URI.create(String.format("/fornecedores/%s/contatos/%s", fornecedorId, contato.getId()))).build();
    }

    @DELETE
    @Path("{fornecedorId}/contatos/{id}")
    public Response deleteContatos(@PathParam("fornecedorId") Long fornecedorId, @PathParam("id") Long id) {
        Dao<Fornecedor> dao = new Dao<>(Fornecedor.class);
        Fornecedor fornecedor = dao.buscarUm(fornecedorId);
        dao.iniciar();
        fornecedor.removerContato(id);
        dao.encerrar().fechar();
        return Response.noContent().build();
    }

    /*

    ####### IMPLEMENTAR OS SUBRECURSOS ABAIXO #######

    @PUT
    @Path("{fornecedorId}/contas/{id}")
    public Response putConta(Conta conta, @PathParam("fornecedorId") Long fornecedorId, @PathParam("id") Long id) {
        Fornecedor fornecedor = new ListaFornecedor().busca(fornecedorId);
        fornecedor.removeConta(id);
        fornecedor.adicionaConta(conta);
        return Response.ok().build();
    }

    @PUT
    @Path("{fornecedorId}/contatos/{id}")
    public Response putContato(Contato contato, @PathParam("fornecedorId") Long fornecedorId, @PathParam("id") Long id) {
        Fornecedor fornecedor = new ListaFornecedor().busca(fornecedorId);
        fornecedor.removeContato(id);
        fornecedor.adicionaContato(contato);
        return Response.ok().build();
    }

    @DELETE
    @Path("{fornecedorId}/contas/{id}")
    public Response deleteConta(@PathParam("fornecedorId") Long fornecedorId, @PathParam("id") Long id) {
        Fornecedor fornecedor = new ListaFornecedor().busca(fornecedorId);
        fornecedor.removeConta(id);
        return Response.ok().build();
    }

    @DELETE
    @Path("{fornecedorId}/contatos/{id}")
    public Response deleteContato(@PathParam("fornecedorId") Long fornecedorId, @PathParam("id") Long id) {
        Fornecedor fornecedor = new ListaFornecedor().busca(fornecedorId);
        fornecedor.removeContato(id);
        return Response.ok().build();
    }

    ####### IMPLEMENTAR OS SUBRECURSOS ACIMA #######

    */
}
