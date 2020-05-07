package com.stolk.alecsandro.obra.recurso;

import com.stolk.alecsandro.obra.banco.Dao;
import com.stolk.alecsandro.obra.modelo.Lancamento;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("lancamentos")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class LancamentoResource {

    @GET
    public Response get() {
        List<Lancamento> lancamentos = new Dao<>(Lancamento.class).buscar();
        return Response.ok(lancamentos).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        Lancamento lancamento = new Dao<>(Lancamento.class).buscarUm(id);
        return Response.ok(lancamento).build();
    }

    @POST
    public Response post(Lancamento lancamento) {
        Dao<Lancamento> dao = new Dao<>();
        dao.iniciar()
                .cadastrar(lancamento)
                .encerrar()
                .fechar();
        return Response.created(URI.create(String.format("/lancamentos/%s", lancamento.getId()))).build();
    }

    @PUT
    public Response put(Lancamento lancamento) {
        Dao<Lancamento> dao = new Dao<>();
        dao.iniciar()
                .editar(lancamento)
                .encerrar()
                .fechar();
        return Response.ok(URI.create(String.format("/lancamentos/%s", lancamento.getId()))).build();
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") Long id, Lancamento lancamento) {
        Lancamento lancamentoBusca = new Dao<>(Lancamento.class).buscarUm(id);
        lancamento.setId(lancamentoBusca.getId());
        Dao<Lancamento> dao = new Dao<>();
        dao.iniciar()
                .editar(lancamento)
                .encerrar()
                .fechar();
        return Response.ok(URI.create(String.format("/lancamentos/%s", lancamento.getId()))).build();
    }

    @DELETE
    public Response delete(Lancamento lancamento) {
        Dao<Lancamento> dao = new Dao<>(Lancamento.class);
        Lancamento lancamentoRemover = dao.buscarUm(lancamento.getId());
        if (lancamentoRemover != null) {
            dao.iniciar()
                    .excluir(lancamentoRemover);
            dao.encerrar()
                    .fechar();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Dao<Lancamento> dao = new Dao<>(Lancamento.class);
        Lancamento lancamento = dao.buscarUm(id);
        if (lancamento != null) {
            dao.iniciar()
                    .excluir(lancamento);
            dao.encerrar()
                    .fechar();
        }
        return Response.noContent().build();
    }
}
