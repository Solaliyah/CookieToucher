package com.mygdx.cookietoucher.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.cookietoucher.Setting;

/**
 * Created by SolarisD on 2016/08/04.
 */
public class TouchedCookieActor extends Actor{
    Texture cookie = new Texture("sweets_cookie.png");
    // TODO Soundのdispose処理
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("click.ogg"));
    Setting setting;


    public TouchedCookieActor(){
        setX(Setting.LOGICAL_WIDTH / 2 - cookie.getWidth() / 2 - 64);
        setY(Setting.LOGICAL_HEIGHT - 512);

        setting = new Setting();
        setBounds(getX(), getY(), cookie.getWidth(), cookie.getHeight());
        addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                sound.play();
                setting.addChash(1);
                return true;
            }

        });
    }

    @Override
    public void draw(Batch batch, float alpha){
            batch.draw(cookie, getX(), getY());
    }

    @Override
    public void act(float delta){
        super.act(delta);
    }
}
