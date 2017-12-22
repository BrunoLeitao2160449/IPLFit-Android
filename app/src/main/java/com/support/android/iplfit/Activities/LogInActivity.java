package com.support.android.iplfit.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.support.android.iplfit.R;

public class LogInActivity extends AppCompatActivity {

    private static String nome = "nome";
    private static String password = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_log_in);
    }

    public void Logar(View view) {

        EditText LoginNome = findViewById(R.id.loginName);
        EditText LoginPass = findViewById(R.id.loginPass);

        nome = LoginNome.getText().toString();
        password = LoginPass.getText().toString();

        Intent logar = new Intent(getApplicationContext(), MainActivity.class);
        logar.putExtra("nome", nome);
        startActivity(logar);

        this.finish();
    }

    public void AbrirRegisto(View view) {
        Intent registo = new Intent(this, RegistoActivity.class);
        startActivity(registo);
    }

    public void AbrirRecuperarPassword(View view) {
        Intent recuperarPassw = new Intent(this, RecuperarPasswordActivity.class);
        startActivity(recuperarPassw);
    }
}
