package com.support.android.iplfit.Activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.support.android.iplfit.Class.Dica;
import com.support.android.iplfit.Class.GestorDicas;
import com.support.android.iplfit.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;

public class DefinicoesActivity extends AppCompatActivity {

    private MqttAndroidClient client;
    private TextView subTitulo;
    private TextView subConteudo;
    private Vibrator vibrator;
    private Ringtone ringtone;

    private Switch switchSaude;
    private Switch switchAlimentacao;
    private Switch switchDesporto;

    private GestorDicas gestorDicas;
    private Dica dica;

    private List<Dica> ListaDicasAlimentacao;
    private List<Dica> ListaDicasDesporto;
    private List<Dica> ListaDicasSaude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definicoes);

        subTitulo = findViewById(R.id.titulo_view);
        subConteudo = findViewById(R.id.conteudo_view);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        switchSaude = findViewById(R.id.switchSaude);
        switchAlimentacao = findViewById(R.id.switchAlimentacao);
        switchDesporto = findViewById(R.id.switchDesporto);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), uri);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setTitle("Definiçoes");

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), "tcp://5.196.27.244:1883", clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(DefinicoesActivity.this, "connected", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(DefinicoesActivity.this, "connection failed", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String mensagem = new String(message.getPayload());
                String titulo = mensagem.substring(0, mensagem.indexOf("\n"));
                String conteudo = mensagem.substring(mensagem.indexOf("\n"));

                dica = new Dica(titulo, conteudo);
                gestorDicas = new GestorDicas();

                if(topic.equals("Saúde")){
                    ListaDicasAlimentacao = gestorDicas.getDicasAlimentacao();
                    ListaDicasAlimentacao.add(dica);
                }else if (topic.equals("Receitas")){
                    ListaDicasDesporto = gestorDicas.getDicasDesporto();
                    ListaDicasDesporto.add(dica);
                }else if (topic.equals("Desporto")){
                    ListaDicasSaude = gestorDicas.getDicasSaude();
                    ListaDicasSaude.add(dica);
                }

                subTitulo.setText(titulo);
                subConteudo.setText(conteudo);
                notificacao(titulo);
                vibrator.vibrate(500);
                ringtone.play();
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });

        switchSaude.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton View, boolean isChecked) {
                String canal = "Saúde";
                setSubscricao(canal);

                if(!isChecked){
                    Snackbar.make(View, "Cancelou a subscrição de dicas sobre Saúde", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                else
                {
                    Snackbar.make(View, "Sobscreveu dicas sobre Saúde", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });

        switchAlimentacao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton View, boolean isChecked) {
                String canal = "Receitas";
                setSubscricao(canal);

                if(!isChecked){
                    Snackbar.make(View, "Cancelou a subscrição de dicas sobre Receitas", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                else
                {
                    Snackbar.make(View, "Sobscreveu dicas sobre Receitas", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });

        switchDesporto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton View, boolean isChecked) {
                String canal = "Desporto";
                setSubscricao(canal);

                if(!isChecked){
                    Snackbar.make(View, "Cancelou a subscrição de dicas sobre Desporto", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
                else
                {
                    Snackbar.make(View, "Sobscreveu dicas sobre Desporto", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
    }

    private void setSubscricao(String canal){
        String topic = canal;
        try{
            client.subscribe(topic, 1);
        }
        catch (MqttException e){
            e.printStackTrace();
        }
    }

    private void notificacao(String titulo){
        Intent intent = new Intent();
        PendingIntent pIntent = PendingIntent.getActivity(DefinicoesActivity.this, 0, intent, 0);
        Notification noti = new Notification.Builder(DefinicoesActivity.this)
                .setTicker(titulo)
                .setContentTitle(titulo)
                //.setContentText(conteudo)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent).getNotification();
        noti.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0,noti);
    }
}
