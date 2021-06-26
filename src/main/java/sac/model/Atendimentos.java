/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.model;

import java.sql.Date;

/**
 *
 * @author geova
 */
public class Atendimentos {
    private int atendimento_id;
    private String cliente;
    private String funcionario;
    private Date dataCriacao;
    private Date dataResolucao;
    private String produto;
    private int situacaoAtendimento;

    public Atendimentos(int atendimento_id, String cliente, String funcionario, Date dataCriacao, Date dataResolucao, String produto, int situacaoAtendimento) {
        this.atendimento_id = atendimento_id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.dataCriacao = dataCriacao;
        this.dataResolucao = dataResolucao;
        this.produto = produto;
        this.situacaoAtendimento = situacaoAtendimento;
    }

    public int getSituacaoAtendimento() {
        return situacaoAtendimento;
    }

    public void setSituacaoAtendimento(int situacaoAtendimento) {
        this.situacaoAtendimento = situacaoAtendimento;
    }

    public int getAtendimento_id() {
        return atendimento_id;
    }

    public void setAtendimento_id(int atendimento_id) {
        this.atendimento_id = atendimento_id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataResolucao() {
        return dataResolucao;
    }

    public void setDataResolucao(Date dataResolucao) {
        this.dataResolucao = dataResolucao;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }


    
}
