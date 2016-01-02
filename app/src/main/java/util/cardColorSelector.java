package util;

import android.support.annotation.ColorRes;

import java.util.ArrayList;

import cwoj.tk.cwoj.R;

/**
 * Copyright 2016(c) Comet Corporation.
 * Created by asus1 on 2016/1/1.
 */
public class cardColorSelector {

    private ArrayList<colorPair> pairs;

    public cardColorSelector() {
        pairs = new ArrayList<>();
        pairs.add(new colorPair(R.color.cardColor0, R.color.cardColor0Pressed));
//        pairs.add(new colorPair(R.color.cardColor1, R.color.cardColor1Pressed));
//        pairs.add(new colorPair(R.color.cardColor2, R.color.cardColor2Pressed));
//        pairs.add(new colorPair(R.color.cardColor3, R.color.cardColor3Pressed));
//        pairs.add(new colorPair(R.color.cardColor4, R.color.cardColor4Pressed));
//        pairs.add(new colorPair(R.color.cardColor5, R.color.cardColor5Pressed));
//        pairs.add(new colorPair(R.color.cardColor6, R.color.cardColor6Pressed));
//        pairs.add(new colorPair(R.color.cardColor7, R.color.cardColor7Pressed));
    }

    public colorPair getRandomColor(){
        int rand = (int) (Math.random()*100);
        rand %= pairs.size();
        return pairs.get(rand);
    }

    public colorPair getColorByIndex(int index){
        index %= pairs.size();
        return pairs.get(index);
    }

    public class colorPair {
        private int colorNormal;
        private int colorPressed;

        public colorPair(@ColorRes int colorNormal, @ColorRes int colorPressed) {
            this.colorNormal = colorNormal;
            this.colorPressed = colorPressed;
        }

        public int getColorNormal() {
            return colorNormal;
        }

        public int getColorPressed() {
            return colorPressed;
        }
    }
}
