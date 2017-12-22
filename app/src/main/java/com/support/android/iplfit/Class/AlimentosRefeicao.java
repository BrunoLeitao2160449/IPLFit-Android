package com.support.android.iplfit.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nelsu on 17/12/2017.
 */

public class AlimentosRefeicao {

    private ArrayList<Alimento> ListaAlimentosRefeicao;

    public AlimentosRefeicao() {
        ListaAlimentosRefeicao = new ArrayList<>();
        InitailizeData();
    }

    private void InitailizeData(){
        ListaAlimentosRefeicao.add(new Alimento("Feijao"));
        ListaAlimentosRefeicao.add(new Alimento("Arroz"));
        ListaAlimentosRefeicao.add(new Alimento("Frango"));
    }

    public List<Alimento> getAlimentosRefeicao(){
        return ListaAlimentosRefeicao;
    }

}
