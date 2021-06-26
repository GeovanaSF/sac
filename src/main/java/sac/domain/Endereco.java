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
public class Endereco implements Serializable{
    @Id
    private Integer endereco_id;
    private Integer cidade_id;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    public Endereco(Integer endereco_id, Integer cidade_id, String rua, String numero, String complemento, String bairro, String cep) {        
        this.endereco_id = endereco_id;
        this.cidade_id = cidade_id;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
    }

    public Integer getEndereco_id() {
        return endereco_id;
    }

    public void setEndereco_id(Integer endereco_id) {
        this.endereco_id = endereco_id;
    }

    public Integer getCidade_id() {
        return cidade_id;
    }

    public void setCidade_id(Integer cidade_id) {
        this.cidade_id = cidade_id;
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
    
}
