package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by SolarisD on 2016/08/03.
 */
public class randomSweetsActor extends Actor {
        float actorTheta = 0;
        float plusTheta;
        int r;
        Sprite spriteTexture;
        public randomSweetsActor(int LOGICAL_WIDTH, int LOGICAL_HEIGHT){
            generateTexture();
            spriteTexture.setPosition(MathUtils.random(LOGICAL_WIDTH), MathUtils.random(LOGICAL_HEIGHT));
            spriteTexture.scale((float)(MathUtils.random(10) * 0.1));
            r = MathUtils.random(128) + 256;
            plusTheta = (float) ( MathUtils.random(10) * 0.1);

        }

        void generateTexture() {
            switch (MathUtils.random(7 - 1)) {
                case 0:
                    Gdx.app.log("default","NOT");
                    spriteTexture = new Sprite(new Texture("sweets_annindoufu.png"));
                    break;
                case 1:
                    spriteTexture = new Sprite(new Texture("kakigoori_ujikintoki_shiratama.png"));
                    break;
                case 2:
                    spriteTexture = new Sprite(new Texture("sweets_cannolo.png"));
                    break;
                case 3:
                    spriteTexture = new Sprite(new Texture("sweets_icecream_sherbet.png"));
                    break;
                case 4:
                    spriteTexture = new Sprite(new Texture("sweets_mizufusen_icecream.png"));
                    break;
                case 5:
                    spriteTexture = new Sprite(new Texture("sweets_wataame.png"));
                    break;
                case 6:
                    spriteTexture = new Sprite(new Texture("valentine_giri_chocolate.png"));
                    break;
                case 7:
                    spriteTexture = new Sprite(new Texture("valentine_honmei_chocolate.png"));
                    break;
                default:
                    Gdx.app.log("default","default");
                    break;
            }
        }

        @Override
        public void draw(Batch batch, float alpha){
            spriteTexture.draw(batch);
        }

        @Override
        public void act(float delta){
            actorTheta += plusTheta;
            spriteTexture.rotate(MathUtils.random(20) - 10);
            spriteTexture.setX(spriteTexture.getX() + ((float)Math.sin(actorTheta) - (float)Math.sin(actorTheta - 0.1)) * r);
            spriteTexture.setY(spriteTexture.getY() + ((float)Math.cos(actorTheta) - (float)Math.cos(actorTheta - 0.1)) * r);
        }
}
