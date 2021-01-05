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
        //  Initialises all the views (Proposed by John Shivers)

        // Initialises button
        takeScreenshotButton = findViewById(R.id.takeSS);
        getPriceButton = findViewById(R.id.button);
        copyToClipboardButton = findViewById(R.id.copyBtn);

        // Initialises text fields
        caratTextField = findViewById(R.id.carat);
        rapoTextField = findViewById(R.id.rapo);
        backTextField = findViewById(R.id.back);
        usdTextField = findViewById(R.id.usd);

        // Initialises text views
        resultPriceTextView = findViewById(R.id.resultPriceTextView);
        resultPricePerCaratTextView = findViewById(R.id.resultPricePerCaratTextView);
    }

    public void getPrice() {
        /* After the get price button is pressed
        It calculates the price
        It then manipulates the view to show the new found price
        on the screen
         */

        // Gets the string text from the edit text fields
        String rapoRateText = rapoTextField.getText().toString();
        String usdText = usdTextField.getText().toString();
        String caratText = caratTextField.getText().toString();
        String backText = backTextField.getText().toString();

        // Makes the String text into double to pass them to the DiamondPriceModel class
        // for data manipulation, analysis and findings.
        try {
            rapoRate = Double.parseDouble(rapoRateText);
            usdRate = Double.parseDouble(usdText);
            caratWt = Double.parseDouble(caratText);
            back = Double.parseDouble(backText);
        } catch (Exception exception) {
            Log.d(TAG, "getPrice: " + exception);
        }

        // Creates a new instance of [DiamondPriceModel] which is provided with
        // Rapo Rate, USD conversion rate, Carat per watt and Back Price.
        diamondPriceModel = new DiamondPriceModel(rapoRate, usdRate, caratWt, backPc);

        // Calculates the price
        diamondPriceModel.calcPrice();

        // Shows the new found value on screen
        manipulateViews();

    }

    private void manipulateViews() {
        // Manipulates view to show certain text on the screen
        resultPriceTextView.setText(String.format("₹ %s", diamondPriceModel.getDiscPrice()));
        resultPricePerCaratTextView.setText(String.format("₹ %s", diamondPriceModel.getPricePerCarat()));
    }
}

