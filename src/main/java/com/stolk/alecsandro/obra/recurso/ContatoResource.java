package com.stolk.alecsandro.obra.recurso;

import com.stolk.alecsandro.obra.banco.Dao;
import com.stolk.alecsandro.obra.modelo.Contato;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("contatos")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class ContatoResource {

    @GET
    public Response get() {
        List<Contato> contatos = new Dao<>(Contato.class).buscar();
        return Response.ok(contatos).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        Contato contato = new Dao<>(Contato.class).buscarUm(id);
        return Response.ok(contato).build();
    }

    @POST
    public Response post(Contato contato) {
        Dao<Contato> dao = new Dao<>();
        dao.iniciar()
                .cadastrar(contato)
                .encerrar()
                .fechar();
        return Response.created(URI.create(String.format("/contatos/%s", contato.getId()))).build();
    }

    @PUT
    public Response put(Contato contato) {
        Dao<Contato> dao = new Dao<>();
        dao.iniciar()
                .editar(contato)
                .encerrar()
                .fechar();
        return Response.ok(URI.create(String.format("/contatos/%s", contato.getId()))).build();
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") Long id, Contato contato) {
        Contato contatoBusca = new Dao<>(Contato.class).buscarUm(id);
        contato.setId(contatoBusca.getId());
        Dao<Contato> dao = new Dao<>();
        dao.iniciar()
                .editar(contato)
                .encerrar()
                .fechar();
        return Response.ok(URI.create(String.format("/contatos/%s", contato.getId()))).build();
    }

    @DELETE
    public Response delete(Contato contato) {
        Dao<Contato> dao = new Dao<>(Contato.class);
        Contato contatoRemover = dao.buscarUm(contato.getId());
        if (contatoRemover != null) {
            dao.iniciar()
                    .excluir(contatoRemover);
            dao.encerrar()
                    .fechar();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Dao<Contato> dao = new Dao<>(Contato.class);
        Contato contato = dao.buscarUm(id);
        if (contato != null) {
            dao.iniciar()
                    .excluir(contato);
            dao.encerrar()
                    .fechar();
        }
        return Response.noContent().build();
    }
}
