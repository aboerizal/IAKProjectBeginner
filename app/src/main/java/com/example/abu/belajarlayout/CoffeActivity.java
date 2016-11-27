package com.example.abu.belajarlayout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CoffeActivity extends AppCompatActivity {
    EditText mTextNama;
    Button mButtonOrder, mButtonMin, mButtonPlus, mButtonReset;
    RadioGroup mRadioGroup;
    RadioButton mRadioItem1, mRadioItem2;
    Context mContext ;
    TextView mTextHarga, mTextQty;
    int harga = 0;
    int qty = 0;
    //spinner
    Spinner mSpinnerMenu;
    List<String> mListMenu = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = getApplicationContext();
        mTextNama = (EditText) findViewById(R.id.mTextNama);
        mButtonOrder = (Button) findViewById(R.id.mButtonOrder);
        mButtonReset = (Button) findViewById(R.id.mButtonReset);
        mTextHarga = (TextView) findViewById(R.id.mTextHarga);
        mTextQty = (TextView) findViewById(R.id.mTextHarga);
        mButtonPlus = (Button) findViewById(R.id.mButtonPlus);
        mButtonMin = (Button) findViewById(R.id.mButtonMin);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mRadioItem1 = (RadioButton) findViewById(R.id.mRadioItem1);
        mRadioItem2 = (RadioButton) findViewById(R.id.mRadioItem2);
        mRadioItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Item 1 diklik", Toast.LENGTH_SHORT).show();
            }
        });
        mRadioItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Item 2 diklik", Toast.LENGTH_SHORT).show();
            }
        });

//spinner
        mSpinnerMenu = (Spinner) findViewById(R.id.mSpinnerMenu);
        mListMenu.add("--------");
        mListMenu.add("Martabak");
        mListMenu.add("Piscok Bakar");
        mListMenu.add("Ice Cream Sandwich");
        mListMenu.add("Lumpia Basah");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mListMenu);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerMenu.setAdapter(dataAdapter);

//            mButtonOrder.setOnClickListener(new View.OnClickListener());{
//        public void onClickOrder(View view){
//            Toast.makeText(getApplicationContext(),"Hi, nama saya "+mTextNama.getText(),Toast.LENGTH_LONG).show();
//            mTextHarga.setText(mTextNama.getText());
//        }

    }

    public void onClickOrder(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, "arieridwansyah@gmail.com");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, mTextNama.getText());
        emailIntent.putExtra(Intent.EXTRA_TEXT,
                "Saya Pesan "
                + mSpinnerMenu.getSelectedItem()
                + "Sebanyak"
                + mTextQty.getText()
                + "Seharga"
                + mTextHarga.getText());
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        }catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickPlus(View view) {
        harga = harga + 5;
        qty = qty + 1;
        mTextQty.setText(qty + "");
        mTextHarga.setText("$" + harga);
    }

    public void onClickMin(View view) {
        if (harga != 0) {
            harga = harga - 5;
            qty = qty - 1;
            mTextQty.setText(qty + "");
            mTextHarga.setText("$" + harga);
        } else {
            harga = 0;
            qty = 0;
            mTextQty.setText(qty + "");
            mTextHarga.setText("$" + harga);
        }
    }

    public void onClickReset(View view){
        harga = 0;
        qty = 0;
        mTextNama.setText("");
        mTextHarga.setText("");
        mTextQty.setText(qty+"");
   }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
