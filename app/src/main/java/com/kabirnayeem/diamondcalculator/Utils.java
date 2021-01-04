package com.kabirnayeem.diamondcalculator;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class Utils {
    public static void takeScreenshot(Context context) {

//        /*Capture screenshot*/
//        Date now = new Date();
//        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
//        try {


            // image naming and path  to include sd card  appending name you choose for file
            // create bitmap screen capture
//            View v1 = getWindow().getDecorView().getRootView();
//            v1.setDrawingCacheEnabled(true);
//            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
//            v1.setDrawingCacheEnabled(false);
//
//            ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
//            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
//            File imageFile = new File(directory, "UniqueFileName" + ".jpg");
//
//            FileOutputStream outputStream = new FileOutputStream(imageFile);
//            int quality = 100;
//            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
//            outputStream.flush();
//            outputStream.close();
//            /*Screenshot capture success*/
//            Toast.makeText(context, "Screenshot Captured", Toast.LENGTH_SHORT).show();
//        } catch (Throwable e) {
//            /*Screenshot error*/
//            e.printStackTrace();
//        }
    }

    public static void copyResultToClipboard(DiamondPriceModel diamondPriceModel, Context context) {
        if (diamondPriceModel.getDiscPrice() == 0.0 || diamondPriceModel.getPricePerCarat() == 0.0) {
            Toast.makeText(context, "No calculation performed", Toast.LENGTH_SHORT).show();
        } else {
            String clpdt = "Rapaport Price: " + diamondPriceModel.getRapoRate() + "\n";
            clpdt += "USD Rate: " + diamondPriceModel.getUsdRate() + "\n";
            clpdt += "Carat Wt.: " + diamondPriceModel.getCaratWt() + "\n";
            clpdt += "Back(Discount): " + diamondPriceModel.getBackPc() + "%\n\n";
            clpdt += "Price: \u20B9" + diamondPriceModel.getDiscPrice() + "/-\n";
            clpdt += "(\u20B9" + diamondPriceModel.getPricePerCarat() + "/- per carat)";

            android.text.ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//            ClipData clipData = ClipData.newPlainText("label", clpdt);
            clipboard.setText(clpdt);
            Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show();
        }
    }
}
