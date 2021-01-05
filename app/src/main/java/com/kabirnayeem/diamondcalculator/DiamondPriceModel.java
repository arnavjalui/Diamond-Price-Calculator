package com.kabirnayeem.diamondcalculator;

import android.util.Log;

import java.text.DecimalFormat;

public class DiamondPriceModel {

    // Used to "model" the data in the application.

    private static final String TAG = "DiamondPriceModel";

    private final double rapoRate;
    private final double usdRate;
    private final double caratWt;
    private final double backPc;
    private double discPrice = 0.0;
    private double pricePerCarat = 0.0;

    public DiamondPriceModel(double rapoRate,
                             double usdRate,
                             double caratWt,
                             double backPc) {
        this.rapoRate = rapoRate;
        this.usdRate = usdRate;
        this.caratWt = caratWt;
        this.backPc = backPc;
    }

    public void calcPrice() {
        // Calculates the prices of the Diamond using certain formula.
        double price = rapoRate * usdRate * caratWt;
        discPrice = price * (100 - backPc) / 100;
        pricePerCarat = discPrice / caratWt;

        // Formats decimal numbers.
        DecimalFormat df = new DecimalFormat("#.##");
        discPrice = Double.parseDouble(df.format(discPrice));
        pricePerCarat = Double.parseDouble(df.format(pricePerCarat));
    }

    public double getRapoRate() {
        return rapoRate;
    }


    public double getUsdRate() {
        return usdRate;
    }


    public double getCaratWt() {
        return caratWt;
    }


    public double getBackPc() {
        return backPc;
    }


    public double getDiscPrice() {
        return discPrice;
    }


    public double getPricePerCarat() {
        return pricePerCarat;
    }


    @Override
    public String toString() {
        String string = "DiamondPriceModel{" +
                "rapoRate=" + rapoRate +
                ", usdRate=" + usdRate +
                ", caratWt=" + caratWt +
                ", backPc=" + backPc +
                ", discPrice=" + discPrice +
                ", pricePerCarat=" + pricePerCarat +
                '}';
        Log.d(TAG, "toString: " + string);
        return string;
    }
}
