package com.example.campominado;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TelaFinal extends AppCompatActivity {

    TextView tentativas;
    SoundPool snd;
    MediaPlayer musica;
    Button voltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_final);

        tentativas = findViewById(R.id.tentativas_final);
        voltar = findViewById(R.id.button_start);

        Intent dadosRecebidos = getIntent();
        String numTentativas = dadosRecebidos.getStringExtra("tentativas");
        String tentativas_string = "Número de tentativas: " + numTentativas;

        tentativas.setText(tentativas_string);

        snd = new SoundPool(4, AudioManager.STREAM_MUSIC, 8);
        musica = MediaPlayer.create(TelaFinal.this, R.raw.vitoria);
        musica.start();

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaFinal.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
