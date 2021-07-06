/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author 
 */
@Entity
public class Usuario implements Serializable {

    @Id
    private Integer usuario_Id;
    private String email;
    private String senha;
    private String key;
    private Integer perfil_Id;

    public Usuario() {
        this(null, null, null, null, null);
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario(Integer usuario_Id, String email, String senha, String key, Integer perfil_id) {
        this.usuario_Id = usuario_Id;
        this.email = email;
        this.senha = senha;
        this.perfil_Id = perfil_id;
        this.key = key;
    }

    public Integer getUsuario_Id() {
        return usuario_Id;
    }

    public void setUsuario_Id(Integer usuario_Id) {
        this.usuario_Id = usuario_Id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getPerfil_Id() {
        return perfil_Id;
    }

    public void setPerfil_Id(Integer perfil_Id) {
        this.perfil_Id = perfil_Id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    
}
