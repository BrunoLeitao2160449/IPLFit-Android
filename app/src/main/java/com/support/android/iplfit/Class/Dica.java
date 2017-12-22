package com.support.android.iplfit.Class;

/**
 * Created by nelsu on 20/12/2017.
 */

public class Dica {

    String titulo;
    String texto;

    public Dica(String titulo, String texto) {

        this.titulo = titulo;
        this.texto = texto;
    }

    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public String getTexto() {

        return texto;
    }

    public void setTexto(String texto) {

        this.texto = texto;
    }
}
