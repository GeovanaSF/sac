/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.model;

import java.util.List;

/**
 *
 * @author 
 */
public class Consultas {

    private String queryConsulta;
    private List<Atendimentos> atendimentos;
    private int atendimentos_efetuados;
    private int atendimentos_abertos;
    private float atendimentos_abertos_porcentagem;
    private List<DadosDashboard> dadosDashboard;

    public Consultas() {
    }

    public String getQueryConsulta() {
        return queryConsulta;
    }

    public void setQueryConsulta(String queryConsulta) {
        this.queryConsulta = queryConsulta;
    }

    public List<Atendimentos> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(List<Atendimentos> atendimentos) {
        this.atendimentos = atendimentos;
    }

    public int getAtendimentos_efetuados() {
        return atendimentos_efetuados;
    }

    public void setAtendimentos_efetuados(int atendimentos_efetuados) {
        this.atendimentos_efetuados = atendimentos_efetuados;
    }

    public int getAtendimentos_abertos() {
        return atendimentos_abertos;
    }

    public void setAtendimentos_abertos(int atendimentos_abertos) {
        this.atendimentos_abertos = atendimentos_abertos;
    }

    public float getAtendimentos_abertos_porcentagem() {
        return atendimentos_abertos_porcentagem;
    }

    public void setAtendimentos_abertos_porcentagem(float atendimentos_abertos_porcentagem) {
        this.atendimentos_abertos_porcentagem = atendimentos_abertos_porcentagem;
    }

    public List<DadosDashboard> getDadosDashboard() {
        return dadosDashboard;
    }

    public void setDadosDashboard(List<DadosDashboard> dadosDashboard) {
        this.dadosDashboard = dadosDashboard;
    }

}
