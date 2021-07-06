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
 * @author 
 */
public class Perfil implements Serializable{
    @Id
    private Integer perfil_id;
    private String nome;

    public Perfil(Integer perfil_id, String nome) {
        this.perfil_id = perfil_id;
        this.nome = nome;
    }

    public Integer getPerfil_id() {
        return perfil_id;
    }

    public void setPerfil_id(Integer perfil_id) {
        this.perfil_id = perfil_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
