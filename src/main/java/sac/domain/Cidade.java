/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.domain;

import java.io.Serializable;
import javax.persistence.Id;

/**
 *
 * @author geova
 */
public class Cidade implements Serializable{
    @Id
    private Integer cidade_id;
    private Integer estado_id;
    private String nome;

    public Cidade(Integer cidade_id, String nome) {
        this.cidade_id = cidade_id;
        this.nome = nome;
    }

    public Integer getCidade_id() {
        return cidade_id;
    }

    public void setCidade_id(Integer cidade_id) {
        this.cidade_id = cidade_id;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        this.estado_id = estado_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
