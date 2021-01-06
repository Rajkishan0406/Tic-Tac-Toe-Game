package com.example.fragmenttester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.Random;

public class Activity_Player extends AppCompatActivity {

    private int ActivePlayer=1;
    ArrayList<Integer> Player1= new ArrayList<Integer>();// hold player 1 data
    ArrayList<Integer> Player2= new ArrayList<Integer>();// hold player 2 data
    int attempt = 0;
    private int end =0;
    TextView play1,play2,point1,point2,again;
    private LottieAnimationView celebration;

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent  = new Intent(Activity_Player.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__player);

        celebration = findViewById(R.id.celebration_time);
        celebration.setVisibility(View.INVISIBLE);

        play1 = findViewById(R.id.player1);
        play2 = findViewById(R.id.player2);
        point1 = findViewById(R.id.points1);
        point2 = findViewById(R.id.points2);

        Intent intent = getIntent();
        String res1 = intent.getStringExtra("one");
        String res2 = intent.getStringExtra("two");

        point1.setText(res1);
        point2.setText(res2);

        again = findViewById(R.id.again);
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Player.this,Activity_Player.class);
                intent.putExtra("one",point1.getText().toString());
                intent.putExtra("two",point2.getText().toString());
                startActivity(intent);
            }
        });

    }
    public void BuClick(View view) {
        Button buSelected= (Button) view;
        int CellID=0;
        switch ((buSelected.getId())){

            case R.id.btn1:
                CellID=1;
                break;

            case R.id.btn2:
                CellID=2;
                break;

            case R.id.btn3:
                CellID=3;
                break;

            case R.id.btn4:
                CellID=4;
                break;

            case R.id.btn5:
                CellID=5;
                break;

            case R.id.btn6:
                CellID=6;
                break;

            case R.id.btn7:
                CellID=7;
                break;

            case R.id.btn8:
                CellID=8;
                break;

            case R.id.btn9:
                CellID=9;
                break;
        }
        PlayGame(CellID, buSelected);
    }

    void PlayGame(int CellID,Button buSelected) {

        if (end == 0) {
            if (ActivePlayer == 1) {
                buSelected.setText("X");
                buSelected.setTextColor(Color.GREEN);
                Player1.add(CellID);
                ActivePlayer = 2;
                attempt++;

                buSelected.setEnabled(false);
                CheckWiner();
                //AutoPlay();
            }
            else if (ActivePlayer == 2) {
                buSelected.setText("O");
                buSelected.setTextColor(Color.YELLOW);
                Player2.add(CellID);
                ActivePlayer = 1;
                attempt++;
                buSelected.setEnabled(false);
                CheckWiner();
            }

        }
    }
    void CheckWiner(){
        int Winer=-1;
        //row 1
        if (Player1.contains(1) && Player1.contains(2)  && Player1.contains(3))  {
            Winer=1 ;
        }
        if (Player2.contains(1) && Player2.contains(2)  && Player2.contains(3))  {
            Winer=2 ;
        }

        //row 2
        if (Player1.contains(4) && Player1.contains(5)  && Player1.contains(6))  {
            Winer=1 ;
        }
        if (Player2.contains(4) && Player2.contains(5)  && Player2.contains(6))  {
            Winer=2 ;
        }

        //row 3
        if (Player1.contains(7) && Player1.contains(8)  && Player1.contains(9))  {
            Winer=1 ;
        }
        if (Player2.contains(7) && Player2.contains(8)  && Player2.contains(9))  {
            Winer=2 ;
        }


        //col 1
        if (Player1.contains(1) && Player1.contains(4)  && Player1.contains(7))  {
            Winer=1 ;
        }
        if (Player2.contains(1) && Player2.contains(4)  && Player2.contains(7))  {
            Winer=2 ;
        }

        //col 2
        if (Player1.contains(2) && Player1.contains(5)  && Player1.contains(8))  {
            Winer=1 ;
        }
        if (Player2.contains(2) && Player2.contains(5)  && Player2.contains(8))  {
            Winer=2 ;
        }


        //col 3
        if (Player1.contains(3) && Player1.contains(6)  && Player1.contains(9))  {
            Winer=1 ;
        }
        if (Player2.contains(3) && Player2.contains(6)  && Player2.contains(9))  {
            Winer=2 ;
        }

        //dialognal 1
        if (Player1.contains(1) && Player1.contains(5)  && Player1.contains(9))  {
            Winer=1 ;
        }
        if (Player2.contains(1) && Player2.contains(5)  && Player2.contains(9))  {
            Winer=2 ;
        }

        //dialognal 2
        if (Player1.contains(3) && Player1.contains(5)  && Player1.contains(7))  {
            Winer=1 ;
        }
        if (Player2.contains(3) && Player2.contains(5)  && Player2.contains(7))  {
            Winer=2 ;
        }


        if ( Winer !=-1){
            // We have winer

            if (Winer==1){
                end=1;
                showanimation();
                Toast.makeText(this," Player 1 is winner ",Toast.LENGTH_SHORT).show();
                String number = point1.getText().toString();
                int x = Integer.parseInt(number);
                x = x+2;
                number = String.valueOf(x);
                point1.setText(number);
            }

            if (Winer==2){
                end=1;
                showanimation();
                Toast.makeText(this,"  PLayer 2 is winner  ",Toast.LENGTH_SHORT).show();
                String number = point2.getText().toString();
                int V = Integer.parseInt(number);
                V = V+2;
                number = String.valueOf(V);
                point2.setText(number);
            }

        }
        else{
            if(attempt == 9) {
                end=1;
                Toast.makeText(this, " Match is tied ", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void showanimation() {
        celebration.setVisibility(View.VISIBLE);
        celebration.playAnimation();
        Handler mhandler = new Handler();
        mhandler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 600);
    }

    void AutoPlay(){

        ArrayList<Integer> EmptyCells= new ArrayList<Integer>(); // all un selected cells
        //Find empty cells

        for (int cellID=1; cellID<10;cellID++){
            if (!( Player1.contains(cellID) || Player2.contains(cellID))){
                EmptyCells.add(cellID);
            }
        }

        Random r= new Random();
        int  RandIndex=r.nextInt(EmptyCells.size()- 0)+ 0; // if size =3 , select (0,1,2)
        int CellID=EmptyCells.get(RandIndex);

        Button buSelected;
        switch (CellID){

            case 1 :
                buSelected=(Button) findViewById(R.id.btn1);
                break;

            case 2:
                buSelected=(Button) findViewById(R.id.btn2);
                break;

            case 3:
                buSelected=(Button) findViewById(R.id.btn3);
                break;

            case 4:
                buSelected=(Button) findViewById(R.id.btn4);
                break;

            case 5:
                buSelected=(Button) findViewById(R.id.btn5);
                break;

            case 6:
                buSelected=(Button) findViewById(R.id.btn6);
                break;

            case 7:
                buSelected=(Button) findViewById(R.id.btn7);
                break;

            case 8:
                buSelected=(Button) findViewById(R.id.btn8);
                break;

            case 9:
                buSelected=(Button) findViewById(R.id.btn9);
                break;
            default:
                buSelected=(Button) findViewById(R.id.btn1);
                break;

        }
        PlayGame(CellID, buSelected);
    }
}