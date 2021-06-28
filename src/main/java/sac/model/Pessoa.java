/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.model;

import java.io.Serializable;

/**
 *
 * @author geova
 */
public class Pessoa implements Serializable {

    private int pessoa_id;
    private String nome;
    private String cpf;
    private String telefone;
    private Integer cidade_id;
    private Integer estado_id;
    private String email;
    private String senha;
    private Integer perfil_Id;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String endereco_id;
    private int usuario_id;

    public Pessoa() {
    }

    public Pessoa(int pessoa_id, String nome, String cpf, String telefone, Integer cidade_id, Integer estado_id, String email, String senha, Integer perfil_Id, String rua, String numero, String complemento, String bairro, String cep, String endereco_id, int usuario_id) {
        this.pessoa_id = pessoa_id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cidade_id = cidade_id;
        this.estado_id = estado_id;
        this.email = email;
        this.senha = senha;
        this.perfil_Id = perfil_Id;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.endereco_id = endereco_id;
        this.usuario_id = usuario_id;
    }

    public int getPessoa_id() {
        return pessoa_id;
    }

    public void setPessoa_id(int pessoa_id) {
        this.pessoa_id = pessoa_id;
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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco_id() {
        return endereco_id;
    }

    public void setEndereco_id(String endereco_id) {
        this.endereco_id = endereco_id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

}
