package com.example.campominado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TelaJogo extends AppCompatActivity implements OnCellClickListener {
    public static final long TIMER_LENGTH = 999000L;    // 999 seconds in milliseconds
    public static final int BOMB_COUNT = 1;
    public static final int GRID_SIZE = 8;

    private MineGridRecyclerAdapter mineGridRecyclerAdapter;
    private TextView timer;
    private TextView flag;
    private TextView flagsLeft;
    private BombMineGame BombMine;
    private CountDownTimer countDownTimer;
    private int secondsElapsed;
    private boolean timerStarted;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_jogo);

        RecyclerView grid = findViewById(R.id.grid_campo_minado);
        grid.setLayoutManager(new GridLayoutManager(this, 8));

        timer = findViewById(R.id.activity_main_timer);
        timerStarted = false;
        countDownTimer = new CountDownTimer(TIMER_LENGTH, 1000) {
            public void onTick(long millisUntilFinished) {
                secondsElapsed += 1;
                timer.setText(String.format("%03d", secondsElapsed));
            }

            public void onFinish() {
                BombMine.outOfTime();
                Toast.makeText(getApplicationContext(), "Game Over: Timer Expired", Toast.LENGTH_SHORT).show();
                BombMine.getMineGrid().revealAllBombs();
                mineGridRecyclerAdapter.setCells(BombMine.getMineGrid().getCells());
            }
        };

        flagsLeft = findViewById(R.id.activity_main_flagsleft);

        BombMine = new BombMineGame(GRID_SIZE, BOMB_COUNT);
        flagsLeft.setText(String.format("%03d", BombMine.getNumberBombs() - BombMine.getFlagCount()));
        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(BombMine.getMineGrid().getCells(), this);
        grid.setAdapter(mineGridRecyclerAdapter);

        TextView bomb = findViewById(R.id.activity_main_bomb);
        bomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BombMine = new BombMineGame(GRID_SIZE, BOMB_COUNT);
                mineGridRecyclerAdapter.setCells(BombMine.getMineGrid().getCells());
                timerStarted = false;
                countDownTimer.cancel();
                secondsElapsed = 0;
                timer.setText(R.string.default_count);
                flagsLeft.setText(String.format("%03d", BombMine.getNumberBombs() - BombMine.getFlagCount()));
            }
        });

        flag = findViewById(R.id.activity_main_flag);
        flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BombMine.toggleMode();
                if (BombMine.isFlagMode()) {
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(0xFFFFFFFF);
                    border.setStroke(1, 0xFF000000);
                    flag.setBackground(border);
                } else {
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(0xFFFFFFFF);
                    flag.setBackground(border);
                }
            }
        });
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void cellClick(Cell cell) {
        BombMine.handleCellClick(cell);

        flagsLeft.setText(String.format("%03d", BombMine.getNumberBombs() - BombMine.getFlagCount()));

        if (!timerStarted) {
            countDownTimer.start();
            timerStarted = true;
        }

        if (BombMine.isGameOver()) {
            countDownTimer.cancel();
            Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
            BombMine.getMineGrid().revealAllBombs();
        }

        if (BombMine.isGameWon()) {
            countDownTimer.cancel();
            Toast.makeText(getApplicationContext(), "Game Won", Toast.LENGTH_SHORT).show();
            BombMine.getMineGrid().revealAllBombs();
        }

        mineGridRecyclerAdapter.setCells(BombMine.getMineGrid().getCells());
    }
}