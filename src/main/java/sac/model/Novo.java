/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.model;

import java.util.List;
import sac.domain.Categoria;
import sac.domain.Produto;

/**
 *
 * @author 
 */
public class Novo {
    public List<Pessoa> funcionarios;
    public List<Produto> produtos;
    public List<Categoria> categorias;

    public Novo(List<Pessoa> funcionarios, List<Produto> produtos, List<Categoria> categorias) {
        this.funcionarios = funcionarios;
        this.produtos = produtos;
        this.categorias = categorias;
    }

    public Novo() {
    }

    public List<Pessoa> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Pessoa> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    
}
