package com.example.tiktok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    int active_player = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    boolean gameActive = true;

    public void dropCoin(View view) {
        ImageView counter = (ImageView) view;


        Log.i("Tag", counter.getTag().toString());


        if (gameState[Integer.parseInt((counter.getTag().toString()))] == 2 && gameActive) {
            gameState[Integer.parseInt((counter.getTag().toString()))] = active_player;
            counter.setTranslationY(-1000);
            if (active_player == 0) {
                counter.setImageResource(R.drawable.yellow);
                active_player = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                active_player = 0;
            }
            counter.animate().translationYBy(1000).setDuration(400);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {
                    String winner;
                    gameActive = false;
                    if (active_player == 0)
                        winner = "Red";
                    else
                        winner = "Yellow";
//                    Toast.makeText(this, winner + " has won", Toast.LENGTH_LONG).show();
                    Button playAgainButton = (Button) findViewById(R.id.button);
                    TextView winnerText = (TextView) findViewById(R.id.winnerTextView);

                    winnerText.setText(winner+" has won.");
                    winnerText.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);

                }
            }
        }

    }


    public void playAgainNow(View view){
        Button playAgainButton = (Button) findViewById(R.id.button);
        TextView winnerText = (TextView) findViewById(R.id.winnerTextView);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        winnerText.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        for(int i = 0; i<gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        active_player = 0;
        gameActive = true;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}