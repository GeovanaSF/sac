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
public class TipoAtendimento implements Serializable{
    @Id    
    private int tipoAtendimento_id;
    private String nome;

    public TipoAtendimento(int tipoAtendimento_id, String nome) {
        this.tipoAtendimento_id = tipoAtendimento_id;
        this.nome = nome;
    }

    public int getTipoAtendimento_id() {
        return tipoAtendimento_id;
    }

    public void setTipoAtendimento_id(int tipoAtendimento_id) {
        this.tipoAtendimento_id = tipoAtendimento_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
