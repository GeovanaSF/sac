/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.model;

import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author geova
 */
public class Atendimentos {
    public int atendimento_id;
    public String cliente;
    public String funcionario;
    public String dataCriacao;
    public String dataResolucao;
    public String produto;
    public String situacaoAtendimento;
    public int situacao;

    public Atendimentos(int atendimento_id, String cliente, String funcionario, String dataCriacao, String dataResolucao, String produto, String situacaoAtendimento, int situacao) {
        this.atendimento_id = atendimento_id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.dataCriacao = dataCriacao;
        this.dataResolucao = dataResolucao;
        this.produto = produto;
        this.situacaoAtendimento = situacaoAtendimento;
        this.situacao = situacao;
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

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataResolucao() {
        return dataResolucao;
    }

    public void setDataResolucao(String dataResolucao) {
        this.dataResolucao = dataResolucao;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getSituacaoAtendimento() {
        return situacaoAtendimento;
    }

    public void setSituacaoAtendimento(String situacaoAtendimento) {
        this.situacaoAtendimento = situacaoAtendimento;
    }

    
}
