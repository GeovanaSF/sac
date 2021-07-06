/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.model;

/**
 *
 * @author 
 */
public enum SituacaoAtendimento {
    Aberto(1), Finalizado(2);

    private final int valor;

    SituacaoAtendimento(int valorOpcao) {
        valor = valorOpcao;
    }

    public int getValor() {
        return valor;
    }
}
