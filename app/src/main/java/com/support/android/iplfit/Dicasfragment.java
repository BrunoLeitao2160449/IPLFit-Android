package com.support.android.iplfit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.support.android.iplfit.Class.Dica;
import com.support.android.iplfit.Class.GestorDicas;

import java.util.List;

public class Dicasfragment extends Fragment {

    private RecyclerView recyclerView;
    private Adapter_Dicas adapter_dicas;
    private GestorDicas gestorDicas = new GestorDicas();
    private List<Dica> dicasListAlimentacao;
    private List<Dica> dicasListSaude;
    private List<Dica> dicasListDesporto;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dicas, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleViewDicas);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        dicasListAlimentacao = gestorDicas.getDicasAlimentacao();

        adapter_dicas = new Adapter_Dicas(dicasListAlimentacao);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(adapter_dicas);
    }

    public void alterarAdapater(int position) {
        switch (position) {
            case 0:
                dicasListAlimentacao = gestorDicas.getDicasAlimentacao();
                adapter_dicas = new Adapter_Dicas(dicasListAlimentacao);
                recyclerView.setAdapter(adapter_dicas);
                break;
            case 1:
                dicasListDesporto = gestorDicas.getDicasDesporto();
                adapter_dicas = new Adapter_Dicas(dicasListDesporto);
                recyclerView.setAdapter(adapter_dicas);
                break;
            case 2:
                dicasListSaude = gestorDicas.getDicasSaude();
                adapter_dicas = new Adapter_Dicas(dicasListSaude);
                recyclerView.setAdapter(adapter_dicas);
                break;
        }
    }

    public static class Adapter_Dicas extends RecyclerView.Adapter<Adapter_Dicas.ViewHolder> {

        private List<Dica> dicasList;

        public static class ViewHolder extends RecyclerView.ViewHolder{

            public final View mView;
            public final TextView titulo;
            public final TextView texto;

            public ViewHolder(View view){
                super(view);
                mView = view;
                titulo = view.findViewById(R.id.titulo);
                texto = view.findViewById(R.id.texto);
            }
        }

        public Adapter_Dicas(List<Dica> dicasList) {
            this.dicasList = dicasList;
        }

        @Override
        public Adapter_Dicas.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_dicas, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final Dica dica = dicasList.get(position);
            holder.titulo.setText(dica.getTitulo());
            holder.texto.setText(dica.getTexto());
        }

        @Override
        public int getItemCount() {
            return dicasList.size();
        }
    }

}
