package com.mygdx.cookietoucher;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Solaliyah on 2016/08/07.
 */
public class Sidebar {
    Stage stage;

    int sidebarStates;

    int menuNum;

    Sidebar(Stage stage){
        this.stage = stage;
        menuNum = 5;
        sidebarStates = 1;
    }

    public int Update(int TouchedButtonNum){
        UpdateSidebar(TouchedButtonNum);

        return sidebarStates;
    }

    private void UpdateSidebar(int TouchedButtonNum){
        sidebarStates = sidebarStates*10;
    }
}