package br.com.fiap.beans;

public class ProjetoSustentavel {
    private int id;
    private String descricao;
    private double custo;
    private String status;
    private int idTipoFonte;
    private int idRegiao;

    public ProjetoSustentavel() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdTipoFonte() {
        return idTipoFonte;
    }

    public void setIdTipoFonte(int idTipoFonte) {
        this.idTipoFonte = idTipoFonte;
    }

    public int getIdRegiao() {
        return idRegiao;
    }

    public void setIdRegiao(int idRegiao) {
        this.idRegiao = idRegiao;
    }
}