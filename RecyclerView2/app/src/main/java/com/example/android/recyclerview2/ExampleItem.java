package com.example.android.recyclerview2;

import android.widget.TextView;

/**
 * Created by Lenovo on 3/18/2018.
 */

public class ExampleItem {
    private int mImgResource;
    private String line1;
    private String line2;

    public ExampleItem(int mImgResource, String line1, String line2) {
        this.mImgResource = mImgResource;
        this.line1 = line1;
        this.line2 = line2;
    }

    public void changeText1(String text){
        line1 =text;
    }

    public int getmImgResource() {
        return mImgResource;
    }
    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }
}
