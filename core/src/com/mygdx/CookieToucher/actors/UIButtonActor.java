package com.mygdx.cookietoucher.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragScrollListener;
import com.mygdx.cookietoucher.Setting;
import com.mygdx.cookietoucher.Sidebar;

/**
 * Created by SolarisD on 2016/08/04.
 */
public class UIButtonActor extends Actor{
    Texture BackButtonFrame = new Texture("BackButton.png");
    Texture ButtonFrame = new Texture("Button.png");
    // TODO Soundのdispose処理
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("click.ogg"));
    int menuNum;
    int menuPageNum;
    Stage stage;
    Sidebar sidebar;
    BitmapFont font;
    float touchUpBufferX;
    float touchUpBufferY;
    public UIButtonActor(Stage stage){
        this.stage = stage;
        menuNum = 1;
        menuPageNum = 1;
        font = new BitmapFont();
        font.getData().setScale(3);
        sidebar = new Sidebar(stage);
        setX(Setting.LOGICAL_WIDTH - 128);
        setY(Setting.LOGICAL_HEIGHT - 128);
        setBounds(getX(), getY(), 128, 128);
        touchUpBufferX = 0;
        touchUpBufferY = 0;
        addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                touchUpBufferX = x;
                touchUpBufferY = y;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(touchUpBufferX != x || touchUpBufferY != y)
                    return;
                sound.play();
                menuNum = sidebar.Updata((int) (((Setting.LOGICAL_HEIGHT - (getY() + y)) / 128)));
                setBounds(getX(), Setting.LOGICAL_HEIGHT - 128 * menuNum, 128, 128 * menuNum);
                Gdx.app.log("touchUp",String.valueOf(menuNum));
            }
        });
    }

    @Override
    public void draw(Batch batch, float alpha){
        if(sidebar.getSidebarStates() != 1)
            batch.draw(BackButtonFrame, getX(), Setting.LOGICAL_HEIGHT - 128);
        else{
            batch.draw(ButtonFrame, getX(), Setting.LOGICAL_HEIGHT - 128);
        }
        for(int menuNum_i = 1;menuNum_i < menuNum; menuNum_i++)
            batch.draw(ButtonFrame, getX(), 128 * ( 9 - menuNum_i));


        switch (sidebar.getSidebarStates()){
            case 1:
                font.getData().setScale(2);
                font.draw(batch,"Menu",getX(), 128 * 10 - 64);
                break;
            case 11:
                font.getData().setScale(2);
                font.draw(batch,"SHOP",getX(), 128 * 9 - 64);
                font.draw(batch,"EQUIP",getX(), 128 * 8 - 64);
                font.draw(batch,"SUPPORTER",getX(), 128 * 7 - 64);
                font.draw(batch,"ACHIEVEMENT",getX(), 128 * 6 - 64);
                break;
            case 111:
                font.getData().setScale(4);
                font.draw(batch,"A",getX(), 128 * 9 - 64);
                font.draw(batch,"B",getX(), 128 * 8 - 64);
                font.draw(batch,"C",getX(), 128 * 7 - 64);
                font.draw(batch,"D",getX(), 128 * 6 - 64);
                font.draw(batch,"E",getX(), 128 * 5 - 64);
                font.draw(batch,"F",getX(), 128 * 4 - 64);
                font.draw(batch,"G",getX(), 128 * 3 - 64);
                font.draw(batch,"H",getX(), 128 * 2 - 64);
                break;
            case 1119:
                font.getData().setScale(4);
                font.draw(batch,"I",getX(), 128 * 9 - 64);
                font.draw(batch,"J",getX(), 128 * 8 - 64);
                font.draw(batch,"K",getX(), 128 * 7 - 64);
                font.draw(batch,"L",getX(), 128 * 6 - 64);
                font.draw(batch,"M",getX(), 128 * 5 - 64);
                font.draw(batch,"N",getX(), 128 * 4 - 64);
                font.draw(batch,"O",getX(), 128 * 3 - 64);
                font.draw(batch,"P",getX(), 128 * 2 - 64);
                break;
            default:
                break;
        }
        font.getData().setScale(3);

        font.draw(batch, String.valueOf(sidebar.getSidebarStates()), Setting.LOGICAL_WIDTH- 256, Setting.LOGICAL_HEIGHT - 64);
    }

    @Override
    public void act(float delta){
        super.act(delta);
    }
}
