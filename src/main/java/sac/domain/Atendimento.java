/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Id;

/**
 *
 * @author geova
 */
public class Atendimento implements Serializable {

    @Id
    private int atendimento_id;
    private int cliente_id;
    private Integer funcionario_id;
    private int produto_id;
    private int tipoatendimento_id;
    private Date datacriacao;
    private Date datafinalizacao;
    private int situacao;
    private String descricao;
    private String solucao;

    public Atendimento(int atendimento_id, int cliente_id, Object funcionario_id, int produto_id, int tipoatendimento_id, Timestamp datacriacao, Timestamp datafinalizacao, int situacao, String descricao, String solucao) {
        this.atendimento_id = atendimento_id;
        this.cliente_id = cliente_id;
        if (funcionario_id != null) {
            this.funcionario_id = (Integer) funcionario_id;
        }

        this.produto_id = produto_id;
        this.tipoatendimento_id = tipoatendimento_id;
        if (datacriacao != null) {
            this.datacriacao = new Date(datacriacao.getTime());
        }
        if (datafinalizacao != null) {
            this.datafinalizacao = new Date(datafinalizacao.getTime());
        }
        this.situacao = situacao;
        this.descricao = descricao;
        this.solucao = solucao;
    }

    public Atendimento() {
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public int getAtendimento_id() {
        return atendimento_id;
    }

    public void setAtendimento_id(int atendimento_id) {
        this.atendimento_id = atendimento_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(int produto_id) {
        this.produto_id = produto_id;
    }

    public int getTipoatendimento_id() {
        return tipoatendimento_id;
    }

    public void setTipoatendimento_id(int tipoatendimento_id) {
        this.tipoatendimento_id = tipoatendimento_id;
    }

    public Date getDatacriacao() {
        return datacriacao;
    }

    public Timestamp getDatacriacaoTime() {
        return datacriacao != null ? new Timestamp(datacriacao.getTime()) : null;
    }

    public void setDatacriacao(Date datacriacao) {
        this.datacriacao = datacriacao;
    }

    public Date getDatafinalizacao() {
        return datafinalizacao;
    }

    public Timestamp getDatafinalizacaoTime() {
        return datafinalizacao != null ? new Timestamp(datafinalizacao.getTime()) : null;
    }

    public void setDatafinalizacao(Date datafinalizacao) {
        this.datafinalizacao = datafinalizacao;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getFuncionario_id() {
        return funcionario_id;
    }

    public void setFuncionario_id(Integer funcionario_id) {
        this.funcionario_id = funcionario_id;
    }

}
