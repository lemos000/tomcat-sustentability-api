package br.com.fiap.beans;

public class EmissaoCarbono {
    private int id;
    private int idTipoFonte;
    private double emissao;

    public EmissaoCarbono() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTipoFonte() {
        return idTipoFonte;
    }

    public void setIdTipoFonte(int idTipoFonte) {
        this.idTipoFonte = idTipoFonte;
    }

    public double getEmissao() {
        return emissao;
    }

    public void setEmissao(double emissao) {
        this.emissao = emissao;
    }
}