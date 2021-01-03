package ml.arnavjalui.diamondcalculator;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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
    Button btn, takeSS, copyBtn;
    TextView diamPrice,diamPricePerCarat;
    Double discPrice=0.0, pricePerCarat=0.0, rapoRate, usdRate, caratWt, backPc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                validateFields();
            }
        });

        takeSS = findViewById(R.id.takeSS);
        takeSS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ssButtonPressed();
            }
        });

        copyBtn = findViewById(R.id.copyBtn);
        copyBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                copyBtnPressed();
            }
        });
    }

    public void validateFields() {
        String a,b,c,d;
        /*Get rapo rate*/
        rapo = findViewById(R.id.rapo);
        a = rapo.getText().toString();
        /*Get USD*/
        usd =  findViewById(R.id.usd);
        b = usd.getText().toString();
        /*Get Carat*/
        carat =  findViewById(R.id.carat);
        c = carat.getText().toString();
        /*Get Back Rate*/
        back = findViewById(R.id.back);
        d = back.getText().toString();

        if (a.matches("") || b.matches("") || c.matches("") || d.matches("")) {
            Toast.makeText(this, "Please fill all inputs", Toast.LENGTH_LONG).show();
            discPrice=0.0;
            pricePerCarat=0.0;
            /*Show diamond price*/
            diamPrice = findViewById(R.id.diamPrice);
            diamPrice.setText(R.string.rs00);
            /*Show diamond price per carat*/
            diamPricePerCarat =  findViewById(R.id.diamPricePerCarat);
            diamPricePerCarat.setText(R.string.rs00pc);

        } else {
            rapoRate = Double.parseDouble(a);
            usdRate = Double.parseDouble(b);
            caratWt = Double.parseDouble(c);
            backPc = Double.parseDouble(d);
            discPrice=0.0;
            pricePerCarat=0.0;
            if (backPc > 100) {
                Toast.makeText(this, "Back should be less than 100%", Toast.LENGTH_LONG).show();
                discPrice=0.0;
                pricePerCarat=0.0;
                /*Show diamond price*/
                diamPrice = findViewById(R.id.diamPrice);
                diamPrice.setText(R.string.rs00);
                /*Show diamond price per carat*/
                diamPricePerCarat = findViewById(R.id.diamPricePerCarat);
                diamPricePerCarat.setText(R.string.rs00pc);
            } else {
                calcPrice(rapoRate, usdRate, caratWt, backPc);
            }
        }
    }

    public void calcPrice(Double rapo, Double usd, Double carat, Double back) {
        // TODO: Add menu item for developer info and disclaimer
        // TODO: Add menu item for sharing app

        double price = rapo * usd * carat;
        discPrice = price * (100-back) / 100;
        pricePerCarat = discPrice / carat;

        /*Round off the discPrice to 2 decimal places*/
        DecimalFormat df = new DecimalFormat("#.##");
        discPrice = Double.valueOf(df.format(discPrice));
        pricePerCarat = Double.valueOf(df.format(pricePerCarat));

        /*Show diamond price*/
        diamPrice = findViewById(R.id.diamPrice);
        diamPrice.setText(String.format("₹ %s", discPrice));
        /*Show diamond price per carat*/
        diamPricePerCarat = findViewById(R.id.diamPricePerCarat);
        diamPricePerCarat.setText(String.format("₹ %s/carat", pricePerCarat));

    }

    private void ssButtonPressed() {
        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck!= PackageManager.PERMISSION_GRANTED) {
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
            takeScreenshot();
        }
    }

    private void takeScreenshot() {
        /*Capture screenshot*/
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
            /*Screenshot capture success*/
            Toast.makeText(MainActivity.this, "Screenshot Captured", Toast.LENGTH_SHORT).show();
        } catch (Throwable e) {
            /*Screenshot error*/
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 786) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                /*Permission granted*/
                takeScreenshot();
            } else {
                /*Permission denied*/
                Toast.makeText(this, "File access required. Please ALLOW permission.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void copyBtnPressed() {
        if (discPrice==0.0 || pricePerCarat==0.0) {
            Toast.makeText(this, "No calculation performed", Toast.LENGTH_SHORT).show();
        } else {
            String clpdt = "Rapaport Price: " + rapoRate + "\n";
            clpdt += "USD Rate: " + usdRate + "\n";
            clpdt += "Carat Wt.: " + caratWt + "\n";
            clpdt += "Back(Discount): " + backPc + "%\n\n";
            clpdt += "Price: \u20B9" + discPrice + "/-\n";
            clpdt += "(\u20B9" + pricePerCarat + "/- per carat)";

            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", clpdt);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
        }
    }
}