package com.group2.blackjack.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.group2.blackjack.Callbacks.GameOverCallback;
import com.group2.blackjack.Entities.Card;
import com.group2.blackjack.Entities.CardJV;
import com.group2.blackjack.Entities.Table;
import com.group2.blackjack.Entities.TableJV;
import com.group2.blackjack.Enums.EndGameState;
import com.group2.blackjack.Game.Game;
import com.group2.blackjack.Game.GameJV;
import com.group2.blackjack.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static java.sql.DriverManager.println;

/**
 * Created by root on 23.11.17.
 */

public class MainActivityJV extends AppCompatActivity implements GameOverCallback {

    private Button splitButton;
    private Button hitButton;
    private TextView balance;
    private GameJV game;
    private Button startButton;
    private Button standButton;
    private RelativeLayout cardLayout;
    private RelativeLayout dealerLayout;
    private int numbersOfPlayerHits = 0;
    private ImageView backView;
    private SeekBar seekBar;
    private TextView betText;
    private float currBet = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splitButton = (Button) findViewById(R.id.splitButton);
        hitButton = (Button) findViewById(R.id.hitButton);
        standButton = (Button) findViewById(R.id.standButton);
        balance = (TextView) findViewById(R.id.balanceText);
        startButton = (Button) findViewById(R.id.startButton);
        standButton = (Button) findViewById(R.id.standButton);
        cardLayout = (RelativeLayout) findViewById(R.id.playerCardsLayout);
        dealerLayout = (RelativeLayout) findViewById(R.id.dealerCardsLayout);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        betText = (TextView) findViewById(R.id.betText);

        buttonAction();

        game = new GameJV(balance, this);

        game.initGame();

        //game.startRound()
        //continue round
    }

    @Override
    public void endGame(@NotNull EndGameState winner) {
        String cardString = game.getTable().getDealer().get(1).toString();
        int id = getResources().getIdentifier(cardString, "drawable", getPackageName());
        backView.setImageResource(id);

        if(winner == EndGameState.PLAYER){
            System.out.println("Player won-----");
            alertBox("You won!");
        }
        else if(winner == EndGameState.DEALER){
            System.out.println("Dealer won----");
            alertBox("You lost!");
        }
        else{
            System.out.println("Push----");
            alertBox("Push");
        }
    }

    private void  alertBox(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Round over")
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, null) //ALERT IDK WHAT I DID WITH THE NULL
                //.setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void buttonAction() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int maxBet = Integer.parseInt(balance.getText().toString());
                currBet = ( maxBet * ((progress*10) / 100.0f));
                if(progress <= 1) {
                    currBet = ( maxBet * ((progress*1) / 100.0f));
                }
                betText.setText("" + currBet);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });

        splitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), HighscoreActivity.class);
                startActivity(intent);
            }


        });
        standButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!game.getRoundOver()){
                    CardJV card = game.stand();
                    println("Dealerdraw: " + card);
                    int numbersOfDealerHits = 1;
                    if(card != null){
                        numbersOfDealerHits++;
                        setImageToScreen(game.getTable().getDealer(), numbersOfDealerHits, dealerLayout, false);
                    }
                    while(card != null){
                        card = game.stand();
                        System.out.println("Dealerdraw " + card);
                        numbersOfDealerHits++;
                        if (card != null){
                            setImageToScreen(game.getTable().getDealer(), numbersOfDealerHits, dealerLayout, false);
                        }
                    }
                }
            }
        });

        //HIT
        hitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!game.getRoundOver()){
                    CardJV playerDraw = game.playerHit(); // can be null
                    System.out.println(playerDraw);
                    if (playerDraw != null) {
                        numbersOfPlayerHits++;
                        setImageToScreen(game.getTable().getPlayer(), numbersOfPlayerHits, cardLayout, false);
                    }
                }
            }
        });

        //START
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardLayout.removeAllViews();
                dealerLayout.removeAllViews();
                numbersOfPlayerHits = 1;

                game.startRound((int) currBet);
                TableJV table = game.getTable();

                //player cards
                for(int i = 0; i < 1; i++){
                    setImageToScreen(table.getPlayer(), i, cardLayout,false);
                }
                //dealer cards
                setImageToScreen(table.getDealer(), 0, dealerLayout,false);
                setImageToScreen(table.getDealer(), 1, dealerLayout,true);
            }
        });
    }

    private void setImageToScreen(List<CardJV> cards , int i, RelativeLayout layout, boolean showBackground) {
        String cardString = cards.get(i).toString();

        ImageView imgView = new ImageView(this);
        int id = getResources().getIdentifier(cardString, "drawable", getPackageName());

        if(showBackground){
            imgView.setImageResource(R.drawable.b0);
            backView = imgView;
        }
        else {
            imgView.setImageResource(id);
        }

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = (i*70);
        imgView.setLayoutParams(params);

        layout.addView(imgView, i);
    }
}
