package com.kabirnayeem.diamondcalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    EditText rapoTextField;
    EditText usdTextField;
    EditText caratTextField;
    EditText backTextField;
    Button getPriceButton;
    Button takeScreenshotButton;
    Button copyToClipboardButton;
    TextView resultPriceTextView;
    TextView resultPricePerCaratTextView;
    DiamondPriceModel diamondPriceModel;
    double backPc;
    double rapoRate = 0.0;
    double usdRate = 0.0;
    double caratWt = 0.0;
    double back = 0.0;

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

        takeScreenshotButton.setVisibility(View.GONE);
        copyToClipboardButton.setVisibility(View.GONE);

    }

    private void initViews() {
        backTextField = findViewById(R.id.back);
        takeScreenshotButton = findViewById(R.id.takeSS);
        getPriceButton = findViewById(R.id.button);
        resultPricePerCaratTextView = findViewById(R.id.resultPricePerCaratTextView);
        resultPriceTextView = findViewById(R.id.resultPriceTextView);
        rapoTextField = findViewById(R.id.rapo);
        resultPricePerCaratTextView = findViewById(R.id.resultPricePerCaratTextView);
        usdTextField = findViewById(R.id.usd);
        caratTextField = findViewById(R.id.carat);
        backTextField = findViewById(R.id.back);
        rapoTextField = findViewById(R.id.rapo);
        resultPriceTextView = findViewById(R.id.resultPriceTextView);
        usdTextField = findViewById(R.id.usd);
        copyToClipboardButton = findViewById(R.id.copyBtn);
        caratTextField = findViewById(R.id.carat);
        resultPriceTextView = findViewById(R.id.resultPriceTextView);
        resultPricePerCaratTextView = findViewById(R.id.resultPricePerCaratTextView);
    }

    public void getPrice() {


        String rapoRateText = rapoTextField.getText().toString();
        String usdText = usdTextField.getText().toString();
        String caratText = caratTextField.getText().toString();
        String backText = backTextField.getText().toString();

        try {
            rapoRate = Double.parseDouble(rapoRateText);
            usdRate = Double.parseDouble(usdText);
            caratWt = Double.parseDouble(caratText);
            back = Double.parseDouble(backText);
        } catch (Exception exception) {
            Log.d(TAG, "getPrice: " + exception);
        }

        Log.d(TAG, String.format("getPrice: \n raporRateText: %s" +
                        "\n usdRate %s \n" +
                        "caratWt %s \n back %s",
                rapoRate, usdRate, caratWt, backTextField));

        diamondPriceModel = new DiamondPriceModel(rapoRate, usdRate, caratWt, backPc);
        diamondPriceModel.calcPrice();
        resultPriceTextView.setText(String.format("₹ %s", diamondPriceModel.getDiscPrice()));
        resultPricePerCaratTextView.setText(String.format("₹ %s", diamondPriceModel.getPricePerCarat()));

        Log.d(TAG, "getPrice: \n" + diamondPriceModel.toString());
    }
}

