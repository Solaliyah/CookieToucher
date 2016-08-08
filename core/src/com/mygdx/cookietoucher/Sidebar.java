package com.mygdx.cookietoucher;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.HashMap;

/**
 * Created by SolarisD on 2016/08/06.
 */
public class Sidebar {
    Stage stage;

    static final int SIDEBAR_CLOSE = 1;
    static final int SIDEBAR_OPEN = 11;
    static final int SIDEBAR_SHOP = 111;
    static final int SIDEBAR_EQUIP = 112;
    static final int SIDEBAR_SUPPOTER = 113;
    static final int SIDEBAR_ACHIEVEMENT = 114;
    static final int SIDEBAR_OPTION = 115;
    static final int SIDEBAR_SHOP_AITEM = 1111;
    static final int SIDEBAR_SHOP_BITEM = 1112;
    static final int SIDEBAR_SHOP_CITEM = 1113;
    static final int SIDEBAR_SHOP_DITEM = 1114;
    static final int SIDEBAR_ACHIEVEMENT_FISHGUIDE = 1141;
    static final int SIDEBAR_ACHIEVEMENT_ACHIEVE = 1142;
    private int sidebarStates = SIDEBAR_CLOSE;
    private int menuNum;

    HashMap<Integer, Integer> sidebarStatesToMenuNum;

    public Sidebar (Stage stage){
        this.stage = stage;
        menuNum = 5;
        sidebarStatesToMenuNum = new HashMap<Integer, Integer>();

        sidebarStatesToMenuNum.put(SIDEBAR_CLOSE, 1);
        sidebarStatesToMenuNum.put(SIDEBAR_OPEN, 5);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP, 4);
        sidebarStatesToMenuNum.put(SIDEBAR_EQUIP, 1);
        sidebarStatesToMenuNum.put(SIDEBAR_SUPPOTER, 1);
        sidebarStatesToMenuNum.put(SIDEBAR_ACHIEVEMENT, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_OPTION, 1);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_AITEM, 1);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_BITEM, 1);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_CITEM, 1);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_DITEM, 1);
        sidebarStatesToMenuNum.put(SIDEBAR_ACHIEVEMENT_FISHGUIDE, 1);
        sidebarStatesToMenuNum.put(SIDEBAR_ACHIEVEMENT_ACHIEVE, 1);

    }

    public int Updata(int touchedButtonNum){

        if(CheckTouchedButtonNum(touchedButtonNum) == false)
            return sidebarStatesToMenuNum.get(sidebarStates);

        if(touchedButtonNum == 0){
            if( sidebarStates != 1) {
                sidebarStates = sidebarStates / 10;
                return sidebarStatesToMenuNum.get(sidebarStates);
            }
            sidebarStates = SIDEBAR_OPEN;
            return sidebarStatesToMenuNum.get(sidebarStates);
        }

        Gdx.app.log("sidebarStatesA", String.valueOf(sidebarStates));

        switch (sidebarStates) {
            case SIDEBAR_SHOP_AITEM:
                if(touchedButtonNum == 1) {
                    BuyItem(touchedButtonNum);
                    sidebarStates = SIDEBAR_CLOSE;
                    return sidebarStatesToMenuNum.get(sidebarStates);

                }
                break;
            default:
                break;
        }
        sidebarStates = sidebarStates * 10 + touchedButtonNum;
        Gdx.app.log("sidebarStatesB", String.valueOf(sidebarStates));
        return sidebarStatesToMenuNum.get(sidebarStates);
    }

    private boolean CheckTouchedButtonNum(int touchedButtonNum){
        if(touchedButtonNum <= sidebarStatesToMenuNum.get(sidebarStates))
            return true;
        return false;
    }

    private void BuyItem(int touchedButtonNum){
        Setting.boughtItem[0] = true;
    }

    public int getSidebarStates(){
        return sidebarStates;
    }

    public int getMenuNum(){
        return menuNum;
    }
}
