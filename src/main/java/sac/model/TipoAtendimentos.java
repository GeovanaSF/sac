/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sac.model;

import java.util.List;
import sac.domain.TipoAtendimento;

/**
 *
 * @author geova
 */
public class TipoAtendimentos {
    private List<TipoAtendimento> tipoAtendimentos;

    public TipoAtendimentos(List<TipoAtendimento> tipoAtendimentos) {
        this.tipoAtendimentos = tipoAtendimentos;
    }

    public List<TipoAtendimento> getTipoAtendimentos() {
        return tipoAtendimentos;
    }

    public void setTipoAtendimentos(List<TipoAtendimento> tipoAtendimentos) {
        this.tipoAtendimentos = tipoAtendimentos;
    }
    
    
}
