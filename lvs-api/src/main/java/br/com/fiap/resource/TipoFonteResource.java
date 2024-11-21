package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.TipoFonte;
import br.com.fiap.bo.TipoFonteBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tiposfontes")
public class TipoFonteResource {

    private TipoFonteBO tipoFonteService;

    public TipoFonteResource() {
        try {
            this.tipoFonteService = new TipoFonteBO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarTipoFonte(TipoFonte tipoFonte) {
        try {
            if (tipoFonte == null || tipoFonte.getNome() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Dados do tipo de fonte são obrigatórios")
                               .build();
            }

            boolean isCadastrado = tipoFonteService.cadastrarTipoFonte(tipoFonte);
            if (isCadastrado) {
                return Response.status(Response.Status.CREATED)
                               .entity("Tipo de fonte cadastrado com sucesso")
                               .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Erro no cadastro do tipo de fonte")
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
    public Response listarTodosTiposFontes() {
        try {
            List<TipoFonte> tiposFontes = tipoFonteService.selecionarTodosTiposFontes();
            if (!tiposFontes.isEmpty()) {
                return Response.ok(tiposFontes).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT)
                               .entity("Nenhum tipo de fonte encontrado")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao listar tipos de fontes: " + e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarTipoFonte(@QueryParam("id") int tipoFonteId) {
        try {
            TipoFonte tipoFonte = tipoFonteService.selecionarTipoFonte(tipoFonteId);
            if (tipoFonte != null) {
                return Response.ok(tipoFonte).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Tipo de fonte não encontrado")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao buscar tipo de fonte: " + e.getMessage())
                           .build();
        }
    }

    @PUT
    @Path("/atualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarTipoFonte(@QueryParam("id") int tipoFonteId, TipoFonte tipoFonte) {
        try {
            if (tipoFonteId <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("ID do tipo de fonte é obrigatório.")
                               .build();
            }

            tipoFonte.setId(tipoFonteId);
            String resultado = tipoFonteService.atualizarTipoFonte(tipoFonte);
            if (resultado.equals("Atualizado com sucesso!")) {
                return Response.ok(resultado).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Tipo de fonte não encontrado para atualizar")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao atualizar tipo de fonte: " + e.getMessage())
                           .build();
        }
    }

    @DELETE
    @Path("/deletar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarTipoFonte(@QueryParam("id") int tipoFonteId) {
        try {
            boolean resultado = tipoFonteService.deletarTipoFonte(tipoFonteId);
            if (resultado) {
                return Response.status(Response.Status.OK)
                               .entity("{ \"message\": \"Tipo de fonte deletado com sucesso\" }")
                               .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("{ \"message\": \"Tipo de fonte não encontrado\" }")
                               .build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{ \"message\": \"Erro: " + e.getMessage() + "\" }")
                           .build();
        }
    }
}
   