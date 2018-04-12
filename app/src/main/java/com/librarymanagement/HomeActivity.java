package com.librarymanagement;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeActivity extends AppCompatActivity {

    CardView cardLearning , cardLocation , cardPayments , cardContactUs , cardFAQ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fetchIds();
    }

    private void fetchIds() {

        cardLearning = (CardView) findViewById(R.id.card_materials);
        cardLocation = (CardView) findViewById(R.id.card_location);
        cardPayments = (CardView) findViewById(R.id.card_payments);
        cardContactUs = (CardView) findViewById(R.id.card_contactus);
        cardFAQ = (CardView) findViewById(R.id.card_faq);


        cardLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext() , MaterialLearningActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);



            }
        });


        cardLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext() , MaterialLearningActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

        cardPayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String paytmURL= "https://www.paytm.com";

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(paytmURL));
                startActivity(intent);
            }
        });

        cardContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        cardFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }
}
