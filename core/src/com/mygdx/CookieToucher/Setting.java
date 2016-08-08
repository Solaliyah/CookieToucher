package com.mygdx.cookietoucher;

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

    public Setting(){
        scoreCalculate = new ScoreCalculate();
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
