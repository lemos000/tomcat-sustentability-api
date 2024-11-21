package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.RegiaoSustentavel;
import br.com.fiap.bo.RegioesSustentaveisBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/regioes")
public class RegioesSustentaveisResource {

    private RegioesSustentaveisBO regiaoService;

    public RegioesSustentaveisResource() {
        try {
            this.regiaoService = new RegioesSustentaveisBO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarRegiao(RegiaoSustentavel regiao) {
        try {
            if (regiao == null || regiao.getNome() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Dados da região são obrigatórios")
                               .build();
            }

            boolean isCadastrado = regiaoService.cadastrarRegiao(regiao);
            if (isCadastrado) {
                return Response.status(Response.Status.CREATED)
                               .entity("Região cadastrada com sucesso")
                               .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Erro no cadastro da região")
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
    public Response listarTodasRegioes() {
        try {
            List<RegiaoSustentavel> regioes = regiaoService.selecionarTodasRegioes();
            if (!regioes.isEmpty()) {
                return Response.ok(regioes).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT)
                               .entity("Nenhuma região encontrada")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao listar regiões: " + e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarRegiao(@QueryParam("id") int regiaoId) {
        try {
            RegiaoSustentavel regiao = regiaoService.selecionarRegiao(regiaoId);
            if (regiao != null) {
                return Response.ok(regiao).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Região não encontrada")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao buscar região: " + e.getMessage())
                           .build();
        }
    }

    @PUT
    @Path("/atualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarRegiao(@QueryParam("id") int regiaoId, RegiaoSustentavel regiao) {
        try {
            if (regiaoId <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("ID da região é obrigatório.")
                               .build();
            }

            regiao.setId(regiaoId);
            String resultado = regiaoService.atualizarRegiao(regiao);
            if (resultado.equals("Atualizado com sucesso!")) {
                return Response.ok(resultado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Região não encontrada para atualizar")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao atualizar região: " + e.getMessage())
                           .build();
        }
    }

    @DELETE
    @Path("/deletar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarRegiao(@QueryParam("id") int regiaoId) {
        try {
            boolean resultado = regiaoService.deletarRegiao(regiaoId);
            if (resultado) {
                return Response.status(Response.Status.OK)
                               .entity("{ \"message\": \"Região deletada com sucesso\" }")
                               .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("{ \"message\": \"Região não encontrada\" }")
                               .build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{ \"message\": \"Erro: " + e.getMessage() + "\" }")
                           .build();
        }
    }
}