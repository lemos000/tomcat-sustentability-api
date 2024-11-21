package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.ProjetoSustentavel;
import br.com.fiap.bo.ProjetosSustentaveisBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/projetos")
public class ProjetosSustentaveisResource {

    private ProjetosSustentaveisBO projetoService;

    public ProjetosSustentaveisResource() {
        try {
            this.projetoService = new ProjetosSustentaveisBO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarProjeto(ProjetoSustentavel projeto) {
        try {
            if (projeto == null || projeto.getDescricao() == null || projeto.getIdTipoFonte() <= 0 || projeto.getIdRegiao() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Dados do projeto são obrigatórios")
                               .build();
            }

            boolean isCadastrado = projetoService.cadastrarProjeto(projeto);
            if (isCadastrado) {
                return Response.status(Response.Status.CREATED)
                               .entity("Projeto cadastrado com sucesso")
                               .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Erro no cadastro do projeto")
                               .build();
            }

        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro no banco de dados: " + e.getMessage())
                           .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro: " + e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosProjetos() {
        try {
            List<ProjetoSustentavel> projetos = projetoService.selecionarTodosProjetos();
            if (!projetos.isEmpty()) {
                return Response.ok(projetos).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT)
                               .entity("Nenhum projeto encontrado")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao listar projetos: " + e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarProjeto(@QueryParam("id") int projetoId) {
        try {
            ProjetoSustentavel projeto = projetoService.selecionarProjeto(projetoId);
            if (projeto != null) {
                return Response.ok(projeto).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Projeto não encontrado")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao buscar projeto: " + e.getMessage())
                           .build();
        }
    }

    @PUT
    @Path("/atualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarProjeto(@QueryParam("id") int projetoId, ProjetoSustentavel projeto) {
        try {
            if (projetoId <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("ID do projeto é obrigatório.")
                               .build();
            }

            projeto.setId(projetoId);
            String resultado = projetoService.atualizarProjeto(projeto);
            if (resultado.equals("Atualizado com sucesso!")) {
                return Response.ok(resultado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Projeto não encontrado para atualizar")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao atualizar projeto: " + e.getMessage())
                           .build();
        }
    }

    @DELETE
    @Path("/deletar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarProjeto(@QueryParam("id") int projetoId) {
        try {
            boolean resultado = projetoService.deletarProjeto(projetoId);
            if (resultado) {
                return Response.status(Response.Status.OK)
                               .entity("{ \"message\": \"Projeto deletado com sucesso\" }")
                               .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("{ \"message\": \"Projeto não encontrado\" }")
                               .build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{ \"message\": \"Erro: " + e.getMessage() + "\" }")
                           .build();
        }
    }
}