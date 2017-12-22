package com.support.android.iplfit.Adapater;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.support.android.iplfit.Class.Alimento;
import com.support.android.iplfit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nelsu on 17/12/2017.
 */

public class Adapter_OnClickAdiciona extends RecyclerView.Adapter<Adapter_OnClickAdiciona.ViewHolder> implements Filterable {

    private List<Alimento> alimentosList;

    public static  class ViewHolder extends RecyclerView.ViewHolder{
        public final View mView;

        TextView nome;

        public ViewHolder(View view){
            super(view);
            mView = view;

            nome = view.findViewById(R.id.nome_comida);
        }
    }

    public Adapter_OnClickAdiciona(List<Alimento> alimentosList){
        this.alimentosList = alimentosList;
    }

    @Override
    public Adapter_OnClickAdiciona.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_meus_alimentos, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Alimento alimento = alimentosList.get(position);
        holder.nome.setText(alimento.getNome());
        //set imagem se houver

        holder.mView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Snackbar.make(v, "Serve para selecionar o alimento", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return alimentosList.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public void setFilter(ArrayList<Alimento> newList){
        alimentosList = new ArrayList<>();
        alimentosList.addAll(newList);
        notifyDataSetChanged();
    }
}
