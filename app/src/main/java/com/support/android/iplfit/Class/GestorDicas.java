package com.support.android.iplfit.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nelsu on 20/12/2017.
 */

public class GestorDicas {

    private ArrayList<Dica> ListaDicasAlimentacao;
    private ArrayList<Dica> ListaDicasDesporto;
    private ArrayList<Dica> ListaDicasSaude;

    public GestorDicas() {
        ListaDicasAlimentacao = new ArrayList<>();
        DicasAlimentacao();
        ListaDicasDesporto = new ArrayList<>();
        DicasDesporto();
        ListaDicasSaude = new ArrayList<>();
        DicasSaude();
    }

    public void DicasAlimentacao(){
        ListaDicasAlimentacao.add(new Dica("Fruta","Coma pelo menos 3 peças de fruta por dia"));
        ListaDicasAlimentacao.add(new Dica("Fruta","Coma pelo menos 3 peças de fruta por dia"));
        ListaDicasAlimentacao.add(new Dica("Fruta","Coma pelo menos 3 peças de fruta por dia"));
    }

    public void DicasDesporto(){
        ListaDicasDesporto = new ArrayList<>();
        ListaDicasDesporto.add(new Dica("Caminhe", "Caminhe pelo menos meia hora por dia"));
    }

    public void DicasSaude(){
        ListaDicasSaude = new ArrayList<>();
        ListaDicasSaude.add(new Dica("Análises","Faça análises ao sangue com regularidade"));
    }

    public List<Dica>getDicasAlimentacao(){
        return ListaDicasAlimentacao;
    }

    public List<Dica>getDicasDesporto(){
        return ListaDicasDesporto;
    }

    public List<Dica>getDicasSaude(){
        return ListaDicasSaude;
    }

}
