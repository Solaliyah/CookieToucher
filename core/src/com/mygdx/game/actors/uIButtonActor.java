package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.Setting;

/**
 * Created by SolarisD on 2016/08/04.
 */
public class UIButtonActor extends Actor{
    Texture ButtonFrame = new Texture("Button.png");
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("click.ogg"));
    int menuNum;


    public UIButtonActor(){
        menuNum = 5;
        setX(Setting.LOGICAL_WIDTH - 128);
        setY(Setting.LOGICAL_HEIGHT - 128 * menuNum);
        Gdx.app.log(String.valueOf(getX()), String.valueOf(getY()));
        setBounds(getX(), getY() - 128 * (5 - menuNum), ButtonFrame.getWidth(), ButtonFrame.getHeight() * menuNum);
        addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
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
