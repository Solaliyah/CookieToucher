package com.mygdx.cookietoucher;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by SolarisD on 2016/08/09.
 */
public class ShopItem {
    int myNum;
    int cost;
    String name;
    String explanation;
    String explanation2;
    String explanation3;
    Texture ItemTexture;

    ShopItem(int myNum, int cost, String name){
        this.myNum = myNum;
        this.cost = cost;
        this.name = name;
    }
}
