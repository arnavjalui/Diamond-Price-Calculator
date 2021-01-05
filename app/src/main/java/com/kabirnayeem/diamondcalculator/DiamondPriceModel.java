package com.kabirnayeem.diamondcalculator;

import android.util.Log;

import java.text.DecimalFormat;

public class DiamondPriceModel {

    private static final String TAG = "DiamondPriceModel";

    private double rapoRate;
    private double usdRate;
    private double caratWt;
    private double backPc;
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

    public DiamondPriceModel() {
    }

    public void calcPrice() {
        double price = rapoRate * usdRate * caratWt;
        Log.d(TAG, "calcPrice: price is" + price);
        discPrice = price * (100 - backPc) / 100;
        Log.d(TAG, "calcPrice: discPrice is" + discPrice);
        pricePerCarat = discPrice / caratWt;
        Log.d(TAG, "calcPrice: pricePerCarat" + pricePerCarat);
        DecimalFormat df = new DecimalFormat("#.##");
        discPrice = Double.parseDouble(df.format(discPrice));
        pricePerCarat = Double.parseDouble(df.format(pricePerCarat));
    }

    public double getRapoRate() {
        return rapoRate;
    }

    public void setRapoRate(double rapoRate) {
        this.rapoRate = rapoRate;
    }

    public double getUsdRate() {
        return usdRate;
    }

    public void setUsdRate(double usdRate) {
        this.usdRate = usdRate;
    }

    public double getCaratWt() {
        return caratWt;
    }

    public void setCaratWt(double caratWt) {
        this.caratWt = caratWt;
    }

    public double getBackPc() {
        return backPc;
    }

    public void setBackPc(double backPc) {
        this.backPc = backPc;
    }

    public double getDiscPrice() {
        return discPrice;
    }

    public void setDiscPrice(double discPrice) {
        this.discPrice = discPrice;
    }

    public double getPricePerCarat() {
        return pricePerCarat;
    }

    public void setPricePerCarat(double pricePerCarat) {
        this.pricePerCarat = pricePerCarat;
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
