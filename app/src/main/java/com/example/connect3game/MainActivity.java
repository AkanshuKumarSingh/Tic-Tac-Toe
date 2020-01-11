package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int count = 0;
    int activePlayer = 0;
    int gameState[] = {2,2,2,2,2,2,2,2,2};
    int winingPosition[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;

    public  void dropIn(View view){
        String winner = (activePlayer == 0)?"Yellow":"Red";
        ImageView counter = (ImageView)view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameActive == true) {
            count++;
            counter.setTranslationY(-1500);
            counter.setScaleY(0.1f);
            counter.setScaleX(0.1f);
            Log.d("HI", "dropIn:" + counter.getTag());
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer++;
                gameState[tappedCounter] = 0;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer--;
                gameState[tappedCounter] = 1;

            }
            counter.animate().translationYBy(1500).scaleYBy(1).scaleXBy(1).setDuration(300);
            for (int arr[] : winingPosition) {

                if (gameState[arr[0]] == gameState[arr[1]] && gameState[arr[1]] == gameState[arr[2]] && gameState[arr[0]] != 2) {
                    Log.d("yup", "dropIn: player : " + (gameState[arr[0]] + 1) + " wins");
                    gameActive = false;
//                  Toast.makeText(this, "player : " + winner + " has won", Toast.LENGTH_SHORT).show();
                    Button playAgainButton = findViewById(R.id.palyagainbutton);
                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    winnerTextView.setText("player : " + winner + " has won");
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                    break;
                }

            }
            if(count == 9){
                Button playAgainButton = findViewById(R.id.palyagainbutton);
                TextView winnerTextView = findViewById(R.id.winnerTextView);
                winnerTextView.setText("NoOne has won");
                winnerTextView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }
    }

    public void playAgain(View view){

        Button playAgainButton = findViewById(R.id.palyagainbutton);
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView counter = (ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
         activePlayer = 0;
         for(int j=0;j<gameState.length;j++){
             gameState[j] = 2;
         }
         gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
