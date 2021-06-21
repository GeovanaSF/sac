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
public class Estado implements Serializable{
    @Id    
    private Integer estado_id;
    private Integer pais_id;
    private String nome;

    public Estado(Integer estado_id, String nome) {
        this.estado_id = estado_id;
        this.nome = nome;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        this.estado_id = estado_id;
    }

    public Integer getPais_id() {
        return pais_id;
    }

    public void setPais_id(Integer pais_id) {
        this.pais_id = pais_id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
