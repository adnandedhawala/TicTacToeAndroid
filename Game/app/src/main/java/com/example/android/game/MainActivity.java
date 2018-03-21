package com.example.android.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] buttons =new Button[3][3];

    private boolean player1Turn= true;
    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textView_player1;
    private TextView textView_player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_player1=(TextView)findViewById(R.id.tvPlayer1);
        textView_player2=(TextView)findViewById(R.id.tvPlayer2);

        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                String buttonId = "button_"+i+j;
                int resId=getResources().getIdentifier(buttonId,"id",getPackageName());
                buttons[i][j]=(Button)findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button resetButton=(Button) findViewById(R.id.btnReset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().equals("")){
            return;
        }
        if (player1Turn){
            ((Button)v).setText("X");
        }
        else{
            ((Button)v).setText("O");
        }
        roundCount++;
        if (checkForWin()){
            if(player1Turn){
                player1Wins();
            }else {
                player2Wins();
            }
        }
        else if (roundCount==9){
            draw();
        }
        else {
            player1Turn=!player1Turn;
        }

    }
    private boolean checkForWin(){
        String[][] field= new String[3][3];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                field[i][j]=buttons[i][j].getText().toString().trim();
            }
        }
        for (int i=0;i<3;i++){
            if (field[i][0].equals(field[i][1])&&field[i][0].equals(field[i][2])&& !(field[i][0].equals(""))){
                return true;
            }
        }
        for (int i=0;i<3;i++){
            if (field[0][i].equals(field[1][i])&&field[0][i].equals(field[2][i])&& !(field[0][i].equals(""))){
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])&&field[0][0].equals(field[2][2])&& !(field[0][0].equals(""))){
            return true;
        }

        if (field[0][2].equals(field[1][1])&&field[2][0].equals(field[1][1])&& !(field[0][2].equals(""))){
            return true;
        }
        return false;
    }
    private void player1Wins(){
        player1Points++;
        Toast.makeText(this,"Player1 wins!",Toast.LENGTH_SHORT).show();
        UpdatePoints();
        resetBoard();
    }
    private void player2Wins(){
        player2Points++;
        Toast.makeText(this,"Player2 wins!",Toast.LENGTH_SHORT).show();
        UpdatePoints();
        resetBoard();
    }
    private void draw(){
        Toast.makeText(this,"Draw!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void UpdatePoints(){
        textView_player1.setText("Player 1: "+player1Points);
        textView_player2.setText("Player 2: "+player2Points);

    }
    private  void resetBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        roundCount=0;
        player1Turn=true;
    }

    private void resetGame(){
        resetBoard();
        player1Points=0;
        player2Points=0;
        UpdatePoints();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount",roundCount);
        outState.putInt("player1Score",player1Points);
        outState.putInt("Player2Score",player2Points);
        outState.putBoolean("player1Turn",player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount=savedInstanceState.getInt("roundCount");
        player1Points=savedInstanceState.getInt("player1Score");
        player2Points=savedInstanceState.getInt("player2Score");
        player1Turn=savedInstanceState.getBoolean("player1Turn");
    }
}
