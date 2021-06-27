/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.model;

import java.util.List;
import sac.domain.Estado;
import sac.domain.Produto;

/**
 *
 * @author geova
 */
public class Produtos {
    private List<Produto> produtos;

    public Produtos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
