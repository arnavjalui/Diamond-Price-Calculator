package com.kabirnayeem.diamondcalculator;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    EditText rapo;
    EditText usd;
    EditText carat;
    EditText back;
    Button btn;
    Button takeSS;
    Button copyBtn;
    TextView diamPrice;
    TextView diamPricePerCarat;
    Double discPrice;
    Double pricePerCarat = 0.0;
    Double rapoRate;
    Double usdRate;
    Double caratWt;
    Double backPc;
    DiamondPriceModel diamondPriceModel = new DiamondPriceModel();

    {
        discPrice = 0.0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        diamPricePerCarat = findViewById(R.id.diamPricePerCarat);
        diamPrice = findViewById(R.id.diamPrice);
        diamPrice.setText(String.format("₹ %s", diamondPriceModel.getDiscPrice()));
        diamPricePerCarat.setText(String.format("₹ %s/carat", diamondPriceModel.getPricePerCarat()));


        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                validateFields();
            }
        });

        rapo = findViewById(R.id.rapo);
        usd = findViewById(R.id.usd);
        carat = findViewById(R.id.carat);
        back = findViewById(R.id.back);


        takeSS = findViewById(R.id.takeSS);
        takeSS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ssButtonPressed();
            }
        });

        copyBtn = findViewById(R.id.copyBtn);
        copyBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Utils.copyResultToClipboard(diamondPriceModel, MainActivity.this);
            }
        });

    }

    public void validateFields() {
        String a, b, c, d;
        /*Get rapo rate*/
        rapo = findViewById(R.id.rapo);
        a = rapo.getText().toString();
        /*Get USD*/
        usd = findViewById(R.id.usd);
        b = usd.getText().toString();
        /*Get Carat*/
        carat = findViewById(R.id.carat);
        c = carat.getText().toString();
        /*Get Back Rate*/
        back = findViewById(R.id.back);
        d = back.getText().toString();

        if (a.matches("") || b.matches("") || c.matches("") || d.matches("")) {
            Toast.makeText(this, "Please fill all inputs", Toast.LENGTH_LONG).show();
            discPrice = 0.0;
            pricePerCarat = 0.0;
            /*Show diamond price*/
            diamPrice = findViewById(R.id.diamPrice);
            diamPrice.setText(R.string.rs00);
            /*Show diamond price per carat*/
            diamPricePerCarat = findViewById(R.id.diamPricePerCarat);
            diamPricePerCarat.setText(R.string.rs00pc);

        } else {
            rapoRate = Double.parseDouble(a);
            usdRate = Double.parseDouble(b);
            caratWt = Double.parseDouble(c);
            backPc = Double.parseDouble(d);
            discPrice = 0.0;
            pricePerCarat = 0.0;
            if (backPc > 100) {
                Toast.makeText(this, "Back should be less than 100%", Toast.LENGTH_LONG).show();
                discPrice = 0.0;
                pricePerCarat = 0.0;
                /*Show diamond price*/
                diamPrice = findViewById(R.id.diamPrice);
                diamPrice.setText(R.string.rs00);
                /*Show diamond price per carat*/
                diamPricePerCarat = findViewById(R.id.diamPricePerCarat);
                diamPricePerCarat.setText(R.string.rs00pc);
            } else {
                diamondPriceModel.calcPrice();
            }
        }
    }


    private void ssButtonPressed() {
        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            /*Permission not available*/
            /*Check if permission can be requested*/
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                /*Request Permission*/
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 786);
            } else {
                /*Cannot request permission*/
                Toast.makeText(MainActivity.this, "Permission not available", Toast.LENGTH_SHORT).show();
            }
        } else {
            /*Permission available*/
            Utils.takeScreenshot(MainActivity.this);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 786) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                /*Permission granted*/
                Utils.takeScreenshot(MainActivity.this);
            } else {
                /*Permission denied*/
                Toast.makeText(this, "File access required. Please ALLOW permission.", Toast.LENGTH_LONG).show();
            }
        }
    }
}