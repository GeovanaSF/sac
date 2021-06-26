/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.model;

import java.util.List;

/**
 *
 * @author geova
 */
public class Consultas {

    private String queryConsulta;
    private List<Atendimentos> atendimentos;

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

}
