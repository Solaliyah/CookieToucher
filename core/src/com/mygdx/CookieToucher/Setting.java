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

    public boolean getIsSoundEnable(){
        return isSoundEnabled;
    }
    public void setIsSoundEnable(boolean setSoundEnable){
        isSoundEnabled = setSoundEnable;
    }

    public long getChash(){
        return chash;
    }
    public void setChash(long chash){
        this.chash = chash;
    }

    public void addChash(long chash){
        this.chash += chash;
    }

    public void subChash(long chash){
        this.chash -= chash;
    }

}
