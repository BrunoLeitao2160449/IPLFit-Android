package com.support.android.iplfit.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SearchView;

import com.support.android.iplfit.Adapater.Adapter_OnClickAdiciona;
import com.support.android.iplfit.Adapater.Adapter_OnClickDetalhes;
import com.support.android.iplfit.Class.Alimento;
import com.support.android.iplfit.Class.AlimentosRefeicao;
import com.support.android.iplfit.Class.MeusAlimentos;
import com.support.android.iplfit.MyDividerItemDecoration;
import com.support.android.iplfit.R;

import java.util.ArrayList;
import java.util.List;

//import android.support.v7.widget.SearchView;

public class DiaDetalhesActivity extends AppCompatActivity {

    private AlimentosRefeicao alimentosRefeicao;
    private List<Alimento> alimentosRefeicaoList;
    private RecyclerView recyclerViewAlimentosPorRefeicao;
    private Adapter_OnClickDetalhes mAdapter_OnClickDetalhes;

    private MeusAlimentos meusAlimentos;
    private List<Alimento> meusAlimentosList;
    private RecyclerView recyclerViewInserirAlimentoARefeicao;
    private Adapter_OnClickAdiciona mAdapter_OnClickAdiciona;

    private SearchView searchView;

    final Context context = this;

    public static final String EXTRA_REFEICAO = "REFEICAO_NAME";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_dia);
        this.setTitle(EXTRA_REFEICAO);

        Intent intent = getIntent();
        final String refeicaoName = intent.getStringExtra(EXTRA_REFEICAO);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(refeicaoName);

        alimentosRefeicao = new AlimentosRefeicao();
        alimentosRefeicaoList = alimentosRefeicao.getAlimentosRefeicao();
        recyclerViewAlimentosPorRefeicao = findViewById(R.id.recyclerview_AlimentosPorRefeicao);
        mAdapter_OnClickDetalhes = new Adapter_OnClickDetalhes(alimentosRefeicaoList);

        RecyclerView.LayoutManager LayoutManagerAlimentosPorRefeicao = new LinearLayoutManager(getApplicationContext());
        recyclerViewAlimentosPorRefeicao.setLayoutManager(LayoutManagerAlimentosPorRefeicao);
        //recyclerViewAlimentosPorRefeicao.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAlimentosPorRefeicao.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerViewAlimentosPorRefeicao.setAdapter(mAdapter_OnClickDetalhes);

    }

    public void InserirAlimentoARefeicaoDialogBox(final View view)  {

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
        View mView = layoutInflaterAndroid.inflate(R.layout.inserir_alimento_refeicao_dialogbox, null);
        AlertDialog.Builder DialogBoxInserirAlimentoRefeicao = new AlertDialog.Builder(context);
        DialogBoxInserirAlimentoRefeicao.setView(mView);

        DialogBoxInserirAlimentoRefeicao
                .setCancelable(false)
                .setPositiveButton("Inserir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        Snackbar.make(view, "Alimento inserido na refeição", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                })

                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });


        AlertDialog alertDialogBox = DialogBoxInserirAlimentoRefeicao.create();

        meusAlimentos = new MeusAlimentos();
        meusAlimentosList = meusAlimentos.getMeusAlimentos();

        recyclerViewInserirAlimentoARefeicao = mView.findViewById(R.id.recyclerviewAdicionarAlimentoRefeicao);
        mAdapter_OnClickAdiciona = new Adapter_OnClickAdiciona(meusAlimentosList);

        RecyclerView.LayoutManager LayoutManagerMeusAlimentos = new LinearLayoutManager(getApplicationContext());
        recyclerViewInserirAlimentoARefeicao.setLayoutManager(LayoutManagerMeusAlimentos);
        //recyclerViewInserirAlimentoARefeicao.setItemAnimator(new DefaultItemAnimator());
        recyclerViewInserirAlimentoARefeicao.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerViewInserirAlimentoARefeicao.setAdapter(mAdapter_OnClickAdiciona);

        alertDialogBox.show();

        searchView = mView.findViewById(R.id.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                ArrayList<Alimento> newList = new ArrayList<>();
                for(Alimento alimento : meusAlimentosList){
                    String nome = alimento.getNome().toLowerCase();
                    if(nome.contains(newText))
                        newList.add(alimento);
                }
                mAdapter_OnClickAdiciona.setFilter(newList);
                return true;
            }
        });
    }
}