package com.mygdx.game;

/**
 * Created by SolarisD on 2016/08/04.
 */
public class Setting {
    // ゲーム内スクリーンの論理座標
    public final static int LOGICAL_WIDTH = 800;
    public final static int LOGICAL_HEIGHT = 1280;
    //　サウンドのオンオフ
    private static boolean isSoundEnabled = true;

    public boolean getIsSoundEnable(){
        return isSoundEnabled;
    }
    public void setIsSoundEnable(boolean setSoundEnable){
        isSoundEnabled = setSoundEnable;
    }

}
