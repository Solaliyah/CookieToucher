package com.mygdx.cookietoucher;

import java.util.ArrayList;

/**
 * Created by SolarisD on 2016/08/04.
 */
public class Setting {
    // ゲーム内スクリーンの論理座標
    public final static int LOGICAL_WIDTH = 800;
    public final static int LOGICAL_HEIGHT = 1280;
    //　サウンドのオンオフ
    private static boolean isSoundEnabled = true;
    //　スコアの値
    private static long chash = 0;
    //　購入したItem TODO アイテムは６４個まで
    static boolean  boughtItem[] = new boolean[64];
    ScoreCalculate scoreCalculate;

    static ArrayList<ShopItem> ItemList;

    public Setting(){
        scoreCalculate = new ScoreCalculate();
    }

    public void iniItemList(){
        ItemList = new ArrayList<ShopItem>();
        ItemList.add(new ShopItem(0, 10, "ITEM_A"));
        ItemList.add(new ShopItem(1, 12, "ITEM_B"));
        ItemList.add(new ShopItem(2, 14, "ITEM_C"));
        ItemList.add(new ShopItem(3, 16, "ITEM_D"));
        ItemList.add(new ShopItem(4, 18, "ITEM_E"));
        ItemList.add(new ShopItem(5, 20, "ITEM_F"));
        ItemList.add(new ShopItem(6, 22, "ITEM_G"));
        ItemList.add(new ShopItem(7, 24, "ITEM_H"));
        ItemList.add(new ShopItem(8, 26, "ITEM_I"));
        ItemList.add(new ShopItem(9, 28, "ITEM_J"));
        ItemList.add(new ShopItem(10, 30, "ITEM_K"));
        ItemList.add(new ShopItem(11, 32, "ITEM_L"));
        ItemList.add(new ShopItem(12, 34, "ITEM_M"));
        ItemList.add(new ShopItem(13, 36, "ITEM_N"));
        ItemList.add(new ShopItem(14, 38, "ITEM_O"));
        ItemList.add(new ShopItem(15, 40, "ITEM_P"));
    }

    public boolean getIsSoundEnable(){
        return isSoundEnabled;
    }
    public void setIsSoundEnable(boolean setSoundEnable){
        isSoundEnabled = setSoundEnable;
    }

    public long getChash(){
        return chash;
    }

    public void setChashDebug(long chash){
        this.chash = chash;
    }

    public void addChash(long addChash){
        this.chash += scoreCalculate.AddChashCalculate( addChash);
    }

    public void subChash(long chash){
        this.chash -= chash;
    }

}
