package com.stolk.alecsandro.obra.recurso;

import com.stolk.alecsandro.obra.banco.Dao;
import com.stolk.alecsandro.obra.modelo.Conta;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("contas")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class ContaResource {

    @GET
    public Response get() {
        List<Conta> contas = new Dao<>(Conta.class).buscar();
//        List<Conta> contas = new ListaConta().buscar();
        return Response.ok(contas).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        Conta conta = new Dao<>(Conta.class).buscarUm(id);
        return Response.ok(conta).build();
    }

    @POST
    public Response post(Conta conta) {
        Dao<Conta> dao = new Dao<>();
        dao.iniciar()
                .cadastrar(conta)
                .encerrar()
                .fechar();
        return Response.created(URI.create(String.format("/contas/%s", conta.getId()))).build();
    }

    @PUT
    public Response put(Conta conta) {
        Dao<Conta> dao = new Dao<>();
        dao.iniciar()
                .editar(conta)
                .encerrar()
                .fechar();
        return Response.ok(URI.create(String.format("/contas/%s", conta.getId()))).build();
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") Long id, Conta conta) {
        Conta contaBusca = new Dao<>(Conta.class).buscarUm(id);
        conta.setId(contaBusca.getId());
        Dao<Conta> dao = new Dao<>();
        dao.iniciar()
                .editar(conta)
                .encerrar()
                .fechar();
        return Response.ok(URI.create(String.format("/contas/%s", conta.getId()))).build();
    }

    @DELETE
    public Response delete(Conta conta) {
        Dao<Conta> dao = new Dao<>(Conta.class);
        Conta contaRemover = dao.buscarUm(conta.getId());
        if (contaRemover != null) {
            dao.iniciar()
                    .excluir(contaRemover);
            dao.encerrar()
                    .fechar();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Dao<Conta> dao = new Dao<>(Conta.class);
        Conta conta = dao.buscarUm(id);
        if (conta != null) {
            dao.iniciar()
                    .excluir(conta);
            dao.encerrar()
                    .fechar();
        }
        return Response.noContent().build();
    }
}
