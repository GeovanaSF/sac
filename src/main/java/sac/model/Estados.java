/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.model;

import java.util.List;
import sac.domain.Estado;

/**
 *
 * @author geova
 */
public class Estados {
    public List<Estado> estados;

    public Estados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }    
}
