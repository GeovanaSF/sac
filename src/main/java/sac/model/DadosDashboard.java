/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.model;

/**
 *
 * @author geova
 */
public class DadosDashboard {
    private int tipoatendimento_id;
    private String tipoatendimento;
    private Integer quantidade_total;
    private Integer quantidade_aberto;

    public DadosDashboard(int tipoatendimento_id, String tipoatendimento, Integer quantidade_total, Integer quantidade_aberto) {
        this.tipoatendimento_id = tipoatendimento_id;
        this.tipoatendimento = tipoatendimento;
        this.quantidade_total = quantidade_total;
        this.quantidade_aberto = quantidade_aberto;
    }

    public int getTipoatendimento_id() {
        return tipoatendimento_id;
    }

    public void setTipoatendimento_id(int tipoatendimento_id) {
        this.tipoatendimento_id = tipoatendimento_id;
    }

    public String getTipoatendimento() {
        return tipoatendimento;
    }

    public void setTipoatendimento(String tipoatendimento) {
        this.tipoatendimento = tipoatendimento;
    }

    public Integer getQuantidade_total() {
        return quantidade_total;
    }

    public void setQuantidade_total(Integer quantidade_total) {
        this.quantidade_total = quantidade_total;
    }

    public Integer getQuantidade_aberto() {
        return quantidade_aberto;
    }

    public void setQuantidade_aberto(Integer quantidade_aberto) {
        this.quantidade_aberto = quantidade_aberto;
    }

    
}
