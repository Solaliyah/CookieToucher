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
    static final int SIDEBAR_SHOP_EITEM = 1115;
    static final int SIDEBAR_SHOP_FITEM = 1116;
    static final int SIDEBAR_SHOP_GITEM = 1117;
    static final int SIDEBAR_SHOP_HITEM = 1118;
    static final int SIDEBAR_SHOP_NEXTPAGE = 1119;
    static final int SIDEBAR_SHOP_IITEM = 11191;
    static final int SIDEBAR_SHOP_JITEM = 11192;
    static final int SIDEBAR_SHOP_KITEM = 11193;
    static final int SIDEBAR_SHOP_LITEM = 11194;
    static final int SIDEBAR_SHOP_MITEM = 11195;
    static final int SIDEBAR_SHOP_NITEM = 11196;
    static final int SIDEBAR_SHOP_OITEM = 11197;
    static final int SIDEBAR_SHOP_PITEM = 11198;
    static final int SIDEBAR_ACHIEVEMENT_FISHGUIDE = 1141;
    static final int SIDEBAR_ACHIEVEMENT_ACHIEVE = 1142;
    private int sidebarStates = SIDEBAR_CLOSE;
    private int menuNum;
    HashMap<Integer, Integer> sidebarStatesToMenuNum;
    Setting setting;

    public Sidebar (Stage stage){
        this.stage = stage;
        menuNum = 5;
        sidebarStatesToMenuNum = new HashMap<Integer, Integer>();

        sidebarStatesToMenuNum.put(SIDEBAR_CLOSE, 1);
        sidebarStatesToMenuNum.put(SIDEBAR_OPEN, 5);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP, 10);
        sidebarStatesToMenuNum.put(SIDEBAR_EQUIP, 1);
        sidebarStatesToMenuNum.put(SIDEBAR_SUPPOTER, 1);
        sidebarStatesToMenuNum.put(SIDEBAR_ACHIEVEMENT, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_OPTION, 1);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_AITEM, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_BITEM, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_CITEM, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_DITEM, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_EITEM, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_FITEM, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_GITEM, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_HITEM, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_NEXTPAGE, 10);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_IITEM, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_JITEM, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_KITEM, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_LITEM, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_MITEM, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_NITEM, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_OITEM, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_SHOP_PITEM, 2);
        sidebarStatesToMenuNum.put(SIDEBAR_ACHIEVEMENT_FISHGUIDE, 1);
        sidebarStatesToMenuNum.put(SIDEBAR_ACHIEVEMENT_ACHIEVE, 1);

        setting = new Setting();
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
            case SIDEBAR_SHOP_NEXTPAGE:
                if(touchedButtonNum == 9) {
                    sidebarStates = SIDEBAR_SHOP;
                    return sidebarStatesToMenuNum.get(sidebarStates);
                }
                break;
            default:
                if(sidebarStates / 10 == SIDEBAR_SHOP) {
                    if (touchedButtonNum == 1) {
                        BuyItem(touchedButtonNum);
                        sidebarStates = SIDEBAR_CLOSE;
                        return sidebarStatesToMenuNum.get(sidebarStates);
                    }
                }
                if(sidebarStates / 100 == SIDEBAR_SHOP) {
                    if (touchedButtonNum == 1) {
                        BuyItem(touchedButtonNum);
                        sidebarStates = SIDEBAR_CLOSE;
                        return sidebarStatesToMenuNum.get(sidebarStates);
                    }
                }
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

    private boolean BuyItem(int touchedButtonNum){
        int resultSelectItem = -1;
        if(SIDEBAR_SHOP_AITEM <= sidebarStates  && sidebarStates <= SIDEBAR_SHOP_HITEM){
            resultSelectItem = sidebarStates % 10 - 1;
        }
        if(SIDEBAR_SHOP_IITEM <= sidebarStates  && sidebarStates <= SIDEBAR_SHOP_PITEM){
            resultSelectItem = sidebarStates % 10 - 1 + 8;
        }
        if(Setting.ItemList.get(resultSelectItem).cost <= setting.getChash()) {
            setting.subChash(Setting.ItemList.get(resultSelectItem).cost);
            Setting.boughtItem[resultSelectItem] = true;
            Gdx.app.log("resultSelectItem", String.valueOf(resultSelectItem));
            return true;
        }

            return false;
    }

    public int getSidebarStates(){
        return sidebarStates;
    }

    public int getMenuNum(){
        return menuNum;
    }
}
