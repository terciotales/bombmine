package com.example.campominado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TelaJogo extends AppCompatActivity implements OnCellClickListener {
    private MineGridRecyclerAdapter mineGridRecyclerAdapter;
    private BombMineGame BombMine;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_jogo);

        RecyclerView grid = findViewById(R.id.grid_campo_minado);
        grid.setLayoutManager(new GridLayoutManager(this, 8));

        BombMine = new BombMineGame(8);
        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(BombMine.getMineGrid().getCells(), this);
        grid.setAdapter(mineGridRecyclerAdapter);

        TextView bomb = findViewById(R.id.activity_main_bomb);
        TextView tentativas = findViewById(R.id.tentativas);

        bomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BombMine = new BombMineGame(8);
                mineGridRecyclerAdapter.setCells(BombMine.getMineGrid().getCells());
                tentativas.setText("0");
            }
        });
    }

    public void cellClick(Cell cell) {
        TextView tentativas = findViewById(R.id.tentativas);

        if (BombMine.ganhouJogo(cell)) {
            int numTentativas = (Integer.parseInt(tentativas.getText().toString())) + 1;
            tentativas.setText(String.valueOf(numTentativas));
            Intent intent = new Intent(TelaJogo.this, TelaFinal.class);
            intent.putExtra("tentativas", tentativas.getText().toString());
            startActivity(intent);
            finish();
        } else {
            int numTentativas = (Integer.parseInt(tentativas.getText().toString())) + 1;
            tentativas.setText(String.valueOf(numTentativas));
        }

        int index = BombMine.getMineGrid().getCells().indexOf(cell);
        BombMine.getMineGrid().getCells().get(index).setRevelado(true);

        mineGridRecyclerAdapter.setCells(BombMine.getMineGrid().getCells());
    }
}