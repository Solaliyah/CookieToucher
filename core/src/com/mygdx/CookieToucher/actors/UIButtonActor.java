package com.mygdx.cookietoucher.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.cookietoucher.Setting;

/**
 * Created by SolarisD on 2016/08/04.
 */
public class UIButtonActor extends Actor{
    Texture ButtonFrame = new Texture("Button.png");
    // TODO Soundのdispose処理
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("click.ogg"));
    int menuNum;
    Stage stage;

    public UIButtonActor(Stage stage){
        this.stage = stage;
        menuNum = 5;
        setX(Setting.LOGICAL_WIDTH - 128);
        setY(Setting.LOGICAL_HEIGHT - 128 * menuNum);
        setBounds(getX(), getY() - 128 * (5 - menuNum), ButtonFrame.getWidth(), ButtonFrame.getHeight() * menuNum);
        addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                //  TODO stage.getActor()でここでRandomSweetsActorを消したい
                sound.play();
                menuNum--;
                if(menuNum == 0)
                    menuNum = 5;
                return true;
            }

        });
    }

    @Override
    public void draw(Batch batch, float alpha){
        for(int menuNum_i = 0;menuNum_i < menuNum; menuNum_i++)
            batch.draw(ButtonFrame, getX(), getY() + 128 * menuNum_i);
    }

    @Override
    public void act(float delta){
        super.act(delta);
    }
}
