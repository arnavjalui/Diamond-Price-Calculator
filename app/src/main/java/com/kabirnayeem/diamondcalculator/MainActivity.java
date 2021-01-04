package com.kabirnayeem.diamondcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText rapo;
    EditText usd;
    EditText carat;
    EditText back;
    Button getPriceButton;
    Button takeScreenshotButton;
    Button copyToClipboardButton;
    TextView diamPrice;
    TextView diamPricePerCarat;
    Double discPrice;
    Double backPc;
    DiamondPriceModel diamondPriceModel = new DiamondPriceModel();
    String a, b, c, d;

    {
        discPrice = 0.0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        
        getPriceButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getPrice();
            }
        });

        takeScreenshotButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Screenshot functionality to be added",
                        Toast.LENGTH_SHORT).show();
            }
        });

        copyToClipboardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Utils.copyResultToClipboard(diamondPriceModel, MainActivity.this);
            }
        });

    }

    private void initViews() {
        back = findViewById(R.id.back);
        takeScreenshotButton = findViewById(R.id.takeSS);
        getPriceButton = findViewById(R.id.button);
        diamPricePerCarat = findViewById(R.id.diamPricePerCarat);
        diamPrice = findViewById(R.id.diamPrice);
        rapo = findViewById(R.id.rapo);
        diamPricePerCarat = findViewById(R.id.diamPricePerCarat);
        usd = findViewById(R.id.usd);
        carat = findViewById(R.id.carat);
        back = findViewById(R.id.back);
        rapo = findViewById(R.id.rapo);
        diamPrice = findViewById(R.id.diamPrice);
        usd = findViewById(R.id.usd);
        copyToClipboardButton = findViewById(R.id.copyBtn);
        carat = findViewById(R.id.carat);
        diamPrice = findViewById(R.id.diamPrice);
        diamPricePerCarat = findViewById(R.id.diamPricePerCarat);
    }

    public void getPrice() {

        a = rapo.getText().toString();
        b = usd.getText().toString();
        c = carat.getText().toString();
        d = back.getText().toString();

        diamPrice.setText(String.format("₹ %s", diamondPriceModel.getDiscPrice()));
        diamPricePerCarat.setText(String.format("₹ %s/carat", diamondPriceModel.getPricePerCarat()));

        if (a.matches("") || b.matches("") || c.matches("") || d.matches("")) {
            Toast.makeText(this, "Please fill all inputs", Toast.LENGTH_LONG).show();
            diamondPriceModel.setDiscPrice(0.0);
            diamondPriceModel.setPricePerCarat(0.0);
            diamPrice.setText(R.string.rs00);
            diamPricePerCarat.setText(R.string.rs00pc);

        } else {
            diamondPriceModel.setRapoRate(Double.parseDouble(a));
            diamondPriceModel.setUsdRate(Double.parseDouble(b));
            diamondPriceModel.setCaratWt(Double.parseDouble(c));
            diamondPriceModel.setBackPc(Double.parseDouble(d));
            diamondPriceModel.setDiscPrice(0.0);
            diamondPriceModel.setPricePerCarat(0.0);

            if (backPc > 100) {
                Toast.makeText(this, "Back should be less than 100%", Toast.LENGTH_LONG).show();
                diamondPriceModel.setDiscPrice(0.0);
                diamondPriceModel.setPricePerCarat(0.0);
                diamPrice.setText(R.string.rs00);
                diamPricePerCarat.setText(R.string.rs00pc);
            } else {
                diamondPriceModel.calcPrice();
            }
        }
    }

}