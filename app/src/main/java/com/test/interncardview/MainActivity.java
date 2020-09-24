package com.test.interncardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView memberCard, cropCard, priceCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        memberCard = (CardView) findViewById(R.id.cardview_member);
        cropCard = (CardView) findViewById(R.id.cardview_crops);
        priceCard = (CardView) findViewById(R.id.cardview_priceslist);

        memberCard.setOnClickListener(this);
        cropCard.setOnClickListener(this);
        priceCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.cardview_member : i = new Intent(this,Member.class);startActivity(i); break;
            case R.id.cardview_crops : i = new Intent(this,CropList.class);startActivity(i); break;
            case R.id.cardview_priceslist : i = new Intent(this,Prices.class);startActivity(i); break;
            default:break;
        }
    }
}