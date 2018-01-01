package ml.arnavjalui.diamondcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText rapo,usd,carat,back;
    Button btn;
    TextView diamPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calcPrice();
            }
        });


    }

    public void calcPrice() {
        // TODO: Check if 4 input fields are empty(Toast if yes:Calculate if no)
        // TODO: Back % not more than 100
        // TODO: Add menu for developer info and disclaimer
        /*Get rapo rate*/
        rapo = (EditText) findViewById(R.id.rapo);
        String a = rapo.getText().toString();
        Double rapo = Double.parseDouble(a);

        /*Get USD*/
        usd = (EditText) findViewById(R.id.usd);
        a = usd.getText().toString();
        Double usd = Double.parseDouble(a);

        /*Get Carat*/
        carat = (EditText) findViewById(R.id.carat);
        a = carat.getText().toString();
        Double carat = Double.parseDouble(a);

        /*Get Back Rate*/
        back = (EditText) findViewById(R.id.back);
        a = back.getText().toString();
        Double back= Double.parseDouble(a);


        Double price = rapo * usd * carat;
        Double discPrice = price * (100-back) / 100;


        Log.v("Price is ", "Rs. "+ discPrice);

        /*Round off the discPrice to 2 decimal places*/
        DecimalFormat df = new DecimalFormat("#.##");
        discPrice = Double.valueOf(df.format(discPrice));

        /*Show diamond price*/
        diamPrice = (TextView) findViewById(R.id.diamPrice);
        diamPrice.setText("\u20B9 " + discPrice);
    }
}