package com.support.android.iplfit.Adapater;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.support.android.iplfit.Class.Alimento;
import com.support.android.iplfit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nelsu on 28/11/2017.
 */

public class Adapter_OnClickDetalhes extends RecyclerView.Adapter<Adapter_OnClickDetalhes.ViewHolder> implements Filterable {

    private List<Alimento> alimentosList;

    public Adapter_OnClickDetalhes(List<Alimento> alimentosList){
        this.alimentosList = alimentosList;
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder{
        public final View mView;

        TextView nome;

        public ViewHolder(View view){
            super(view);

            mView = view;
            nome = view.findViewById(R.id.nome_comida);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_meus_alimentos, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Alimento alimento = alimentosList.get(position);
        holder.nome.setText(alimento.getNome());
        //set imagem se houver
        
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Context context = v.getContext();
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
                View mView = layoutInflaterAndroid.inflate(R.layout.detalhes_meu_alimento_dialogbox, null);
                AlertDialog.Builder DialogBoxDetalhesMeuAlimento = new AlertDialog.Builder(context);
                DialogBoxDetalhesMeuAlimento.setView(mView);

                final EditText NomeComida = mView.findViewById(R.id.et_NomeComida);
                final EditText CaloriasComida = mView.findViewById(R.id.et_CaloriasComida);
                final EditText ProteinasComida = mView.findViewById(R.id.et_ProteinasComida);
                final EditText GordurasComida = mView.findViewById(R.id.et_GordurasComida);
                final EditText HidratosComida = mView.findViewById(R.id.et_HidratosComida);

                NomeComida.setText(alimento.getNome());

                DialogBoxDetalhesMeuAlimento
                        .setCancelable(false)
                        .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                Snackbar.make(v, "Alimento Guardado", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                
                            }
                        })

                        .setNegativeButton("Cancelar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });

                AlertDialog alertDialogBox = DialogBoxDetalhesMeuAlimento.create();
                alertDialogBox.show();
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                final Context context = v.getContext();
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
                View mView = layoutInflaterAndroid.inflate(R.layout.apagar_alimento_dialogbox, null);
                AlertDialog.Builder DialogBoxApagarAlimento = new AlertDialog.Builder(context);
                DialogBoxApagarAlimento.setView(mView);

                DialogBoxApagarAlimento
                        .setCancelable(false)
                        .setPositiveButton("Sim",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        Snackbar.make(v, "Alimento Removido da Refeição", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                            }
                        })
                        .setNegativeButton("Não",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });

                AlertDialog alertDialogBox = DialogBoxApagarAlimento.create();
                alertDialogBox.show();

                return false;
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
