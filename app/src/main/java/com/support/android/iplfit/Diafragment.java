package com.support.android.iplfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.support.android.iplfit.Activities.DiaDetalhesActivity;

public class Diafragment extends Fragment {

    TextView peq_almoco;
    TextView lancheManha;
    TextView almoco;
    TextView lancheTarde;
    TextView jantar;
    TextView lancheNoite;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View View = inflater.inflate(R.layout.fragment_dia, container, false);

        peq_almoco = View.findViewById(R.id.tv_peqAlmoco);
        lancheManha = View.findViewById(R.id.tv_lancheManha);
        almoco = View.findViewById(R.id.tv_almoco);
        lancheTarde = View.findViewById(R.id.tv_lancheTarde);
        jantar = View.findViewById(R.id.tv_jantar);
        lancheNoite = View.findViewById(R.id.tv_lancheNoite);

        peq_almoco.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DiaDetalhesActivity.class);
                intent.putExtra("REFEICAO_NAME", "Pequeno Almoço");
                startActivity(intent);
            }
        });

        lancheManha.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DiaDetalhesActivity.class);
                intent.putExtra("REFEICAO_NAME", "Lanche da Manhã");
                startActivity(intent);
            }
        });

        almoco.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DiaDetalhesActivity.class);
                intent.putExtra("REFEICAO_NAME", "Almoço");
                startActivity(intent);
            }
        });

        lancheTarde.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DiaDetalhesActivity.class);
                intent.putExtra("REFEICAO_NAME", "Lanche da Tarde");
                startActivity(intent);
            }
        });

        jantar.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DiaDetalhesActivity.class);
                intent.putExtra("REFEICAO_NAME", "Jantar");
                startActivity(intent);
            }
        });

        lancheNoite.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DiaDetalhesActivity.class);
                intent.putExtra("REFEICAO_NAME", "Lanche da Noite");
                startActivity(intent);
            }
        });

        return View;
    }
}

