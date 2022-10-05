package com.example.campominado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        bomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BombMine = new BombMineGame(8);
                mineGridRecyclerAdapter.setCells(BombMine.getMineGrid().getCells());
            }
        });
    }

    public void cellClick(Cell cell) {
        TextView tentativas = findViewById(R.id.tentativas);

        if (BombMine.ganhouJogo(cell)) {

        }

        int index = BombMine.getMineGrid().getCells().indexOf(cell);
        BombMine.getMineGrid().getCells().get(index).setRevelado(true);
        int numTentativas = (Integer.parseInt(tentativas.getText().toString())) + 1;
        tentativas.setText(String.valueOf(numTentativas));

        mineGridRecyclerAdapter.setCells(BombMine.getMineGrid().getCells());
    }
}