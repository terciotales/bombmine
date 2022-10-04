package com.example.campominado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TelaJogo extends AppCompatActivity implements OnCellClickListener {
    public static final int GRID_SIZE = 8;

    private MineGridRecyclerAdapter mineGridRecyclerAdapter;
    private BombMineGame BombMine;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_jogo);

        RecyclerView grid = findViewById(R.id.grid_campo_minado);
        grid.setLayoutManager(new GridLayoutManager(this, 8));

        BombMine = new BombMineGame(GRID_SIZE);
        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(BombMine.getMineGrid().getCells(), this);
        grid.setAdapter(mineGridRecyclerAdapter);

        TextView bomb = findViewById(R.id.activity_main_bomb);
        bomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BombMine = new BombMineGame(GRID_SIZE);
                mineGridRecyclerAdapter.setCells(BombMine.getMineGrid().getCells());
            }
        });
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void cellClick(Cell cell) {
        if (BombMine.perdeuJogo()) {
            Toast.makeText(getApplicationContext(), "Perdeu!", Toast.LENGTH_SHORT).show();
        }

        if (BombMine.ganhouJogo()) {
            Toast.makeText(getApplicationContext(), "Ganhou!", Toast.LENGTH_SHORT).show();
        }

        mineGridRecyclerAdapter.setCells(BombMine.getMineGrid().getCells());
    }
}