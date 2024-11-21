package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.EmissaoCarbono;
import br.com.fiap.bo.EmissaoCarbonoBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/emissoes")
public class EmissaoCarbonoResource {

    private EmissaoCarbonoBO emissaoService;

    public EmissaoCarbonoResource() {
        try {
            this.emissaoService = new EmissaoCarbonoBO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarEmissao(EmissaoCarbono emissao) {
        try {
            if (emissao == null || emissao.getIdTipoFonte() <= 0 || emissao.getEmissao() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Dados da emissão são obrigatórios")
                               .build();
            }

            boolean isCadastrado = emissaoService.cadastrarEmissao(emissao);
            if (isCadastrado) {
                return Response.status(Response.Status.CREATED)
                               .entity("Emissão cadastrada com sucesso")
                               .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Erro no cadastro da emissão")
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
    public Response listarTodasEmissoes() {
        try {
            List<EmissaoCarbono> emissoes = emissaoService.selecionarTodasEmissoes();
            if (!emissoes.isEmpty()) {
                return Response.ok(emissoes).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT)
                               .entity("Nenhuma emissão encontrada")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao listar emissões: " + e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarEmissao(@QueryParam("id") int emissaoId) {
        try {
            EmissaoCarbono emissao = emissaoService.selecionarEmissao(emissaoId);
            if (emissao != null) {
                return Response.ok(emissao).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Emissão não encontrada")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao buscar emissão: " + e.getMessage())
                           .build();
        }
    }

    @PUT
    @Path("/atualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarEmissao(@QueryParam("id") int emissaoId, EmissaoCarbono emissao) {
        try {
            if (emissaoId <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("ID da emissão é obrigatório.")
                               .build();
            }

            emissao.setId(emissaoId);
            String resultado = emissaoService.atualizarEmissao(emissao);
            if (resultado.equals("Atualizado com sucesso!")) {
                return Response.ok(resultado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Emissão não encontrada para atualizar")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao atualizar emissão: " + e.getMessage())
                           .build();
        }
    }

    @DELETE
    @Path("/deletar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarEmissao(@QueryParam("id") int emissaoId) {
        try {
            boolean resultado = emissaoService.deletarEmissao(emissaoId);
            if (resultado) {
                return Response.status(Response.Status.OK)
                               .entity("{ \"message\": \"Emissão deletada com sucesso\" }")
                               .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("{ \"message\": \"Emissão não encontrada\" }")
                               .build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{ \"message\": \"Erro: " + e.getMessage() + "\" }")
                           .build();
        }
    }
}