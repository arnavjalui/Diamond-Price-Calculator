package ml.arnavjalui.diamondcalculator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText rapo,usd,carat,back;
    Button btn, takeSS;
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


        takeSS = (Button) findViewById(R.id.takeSS);
        takeSS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                takeScreenshot();
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


    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/Pictures/Screenshots/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            Toast.makeText(MainActivity.this, "Screenshot Captured", Toast.LENGTH_SHORT).show();
            openScreenshot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }
}