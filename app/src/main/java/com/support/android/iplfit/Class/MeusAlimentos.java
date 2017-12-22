package com.support.android.iplfit.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nelsu on 28/11/2017.
 */

public class MeusAlimentos {

    private ArrayList<Alimento> ListaMeusAlimentos;

    public MeusAlimentos() {
        ListaMeusAlimentos = new ArrayList<>();
        initializeData();
    }

    private void initializeData(){
        ListaMeusAlimentos = new ArrayList<>();
        ListaMeusAlimentos.add(new Alimento("Arroz"));
        ListaMeusAlimentos.add(new Alimento("Pão"));
        ListaMeusAlimentos.add(new Alimento("Frango"));
        ListaMeusAlimentos.add(new Alimento("Queijo"));
        ListaMeusAlimentos.add(new Alimento("Noz"));
        ListaMeusAlimentos.add(new Alimento("Bacalhau"));
        ListaMeusAlimentos.add(new Alimento("Chouriço"));
        ListaMeusAlimentos.add(new Alimento("Maçã"));
        ListaMeusAlimentos.add(new Alimento("Massa"));
        ListaMeusAlimentos.add(new Alimento("Croissant"));
    }

    public List<Alimento> getMeusAlimentos()
    {
        return ListaMeusAlimentos;
    }
}
