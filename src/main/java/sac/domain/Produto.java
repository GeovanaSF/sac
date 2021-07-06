/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.domain;

/**
 *
 * @author 
 */
public class Produto {
    private int produto_id;
    private int categoria_id;
    private String nome;
    private String descricao;
    private float peso;
    private String categoria;

    public Produto(int produto_id, int categoria_id, String nome, String descricao, float peso, String categoria) {
        this.produto_id = produto_id;
        this.categoria_id = categoria_id;
        this.nome = nome;
        this.descricao = descricao;
        this.peso = peso;
        this.categoria = categoria;
    }

    public int getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(int produto_id) {
        this.produto_id = produto_id;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
}
