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
public class Pessoa implements Serializable{
    @Id
    private Integer pessoa_Id;
    private Integer endereco_Id;
    private Integer perfil_Id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    private String key;

    public Pessoa(Integer pessoa_Id, Integer endereco_Id, Integer perfil_Id, String nome, String cpf, String telefone) {
        this.pessoa_Id = pessoa_Id;
        this.endereco_Id = endereco_Id;
        this.perfil_Id = perfil_Id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Pessoa(String nome, String cpf, String telefone,Integer perfil_Id) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.perfil_Id = perfil_Id;
    }

    public Pessoa(Integer pessoa_Id, String nome, String telefone) {
        this.pessoa_Id = pessoa_Id;
        this.nome = nome;
        this.telefone = telefone;
    }
    

    public Pessoa() {
        
    }

    public Integer getPessoa_Id() {
        return pessoa_Id;
    }

    public void setPessoa_Id(Integer pessoa_Id) {
        this.pessoa_Id = pessoa_Id;
    }

    public Integer getEndereco_Id() {
        return endereco_Id;
    }

    public void setEndereco_Id(Integer endereco_Id) {
        this.endereco_Id = endereco_Id;
    }

    public Integer getPerfil_Id() {
        return perfil_Id;
    }

    public void setPerfil_Id(Integer perfil_Id) {
        this.perfil_Id = perfil_Id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }  
}
