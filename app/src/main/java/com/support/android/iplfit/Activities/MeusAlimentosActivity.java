package com.support.android.iplfit.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.support.android.iplfit.Adapater.Adapter_OnClickDetalhes;
import com.support.android.iplfit.Class.Alimento;
import com.support.android.iplfit.Class.MeusAlimentos;
import com.support.android.iplfit.MyDividerItemDecoration;
import com.support.android.iplfit.R;

import java.util.ArrayList;
import java.util.List;

//import android.widget.SearchView;

public class MeusAlimentosActivity extends AppCompatActivity {

    private MeusAlimentos meusAlimentos;
    private List<Alimento> alimentosList;
    private RecyclerView recyclerView;
    private Adapter_OnClickDetalhes mAdapter_OnClickDetalhes;

    private SearchView search;

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_alimentos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setTitle("Meus Alimentos");

        meusAlimentos = new MeusAlimentos();
        alimentosList = meusAlimentos.getMeusAlimentos();

        recyclerView = findViewById(R.id.recyclerview_meusAlimentos);

        mAdapter_OnClickDetalhes = new Adapter_OnClickDetalhes(alimentosList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));

        recyclerView.setAdapter(mAdapter_OnClickDetalhes);

        search = findViewById(R.id.searchView);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                ArrayList<Alimento> newList = new ArrayList<>();
                for(Alimento alimento : alimentosList){
                    String nome = alimento.getNome().toLowerCase();
                    if(nome.contains(newText))
                        newList.add(alimento);
                }
                mAdapter_OnClickDetalhes.setFilter(newList);
                return true;
            }
        });
    }

    public void InserirMeuAlimentoDialogBox(final View view) {

            LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
            View mView = layoutInflaterAndroid.inflate(R.layout.inserir_novo_alimento_dialogbox, null);
            AlertDialog.Builder DialogBoxInserirNovoAlimento = new AlertDialog.Builder(context);
            DialogBoxInserirNovoAlimento.setView(mView);

            final EditText NomeComida = mView.findViewById(R.id.et_NomeComida);
            final EditText CaloriasComida = mView.findViewById(R.id.et_CaloriasComida);
            final EditText ProteinasComida = mView.findViewById(R.id.et_ProteinasComida);
            final EditText GordurasComida = mView.findViewById(R.id.et_GordurasComida);
            final EditText HidratosComida = mView.findViewById(R.id.et_HidratosComida);

            DialogBoxInserirNovoAlimento
                    .setCancelable(false)
                    .setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogBox, int id) {
                            Snackbar.make(view, "Novo alimento adicionado", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }
                    })

                    .setNegativeButton("Cancelar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogBox, int id) {
                                    dialogBox.cancel();
                                }
                            });

            AlertDialog alertDialogBox = DialogBoxInserirNovoAlimento.create();
            alertDialogBox.show();
        }
}