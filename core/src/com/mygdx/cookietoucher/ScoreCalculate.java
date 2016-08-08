package com.mygdx.cookietoucher;

/**
 * Created by SolarisD on 2016/08/08.
 */
public class ScoreCalculate {

    ScoreCalculate(){

    }

    public long AddChashCalculate (long Chash){
        long resultChash = Chash;

        if(Setting.boughtItem[0] == true)
            resultChash *= 2;

        return resultChash;
    }

}
