package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.actors.randomSweetsActor;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter implements ApplicationListener, GestureDetector.GestureListener {
	SpriteBatch batch;
	Texture sakuramochi;
	private BitmapFont font;
	ShapeRenderer shapeRenderer;
	private TextureAtlas textureAtlas;
	private Animation animation;
	private float elapsedTime = 0;
	private Sprite button;
	Sprite sprite;
	private Vector3 touchPoint;
	public static int LOGICAL_WIDTH = 800;
	public static int LOGICAL_HEIGHT = 1280;
	Stage stage;
	private OrthographicCamera camera;
	private FPSLogger fpsLogger = null;
	public class sakuramochiActor extends Actor{
		double actorTheta = 0;
		int r = 128;
		Sprite sakuramochiSprite = new Sprite(new Texture("sakuramochi.png"));
		public sakuramochiActor(float x,float y){
			sakuramochiSprite.setPosition(x, y);
		}

		@Override
		public void draw(Batch batch, float alpha){
			sakuramochiSprite.draw(batch);
		}

		@Override
		public void act(float delta){
			actorTheta += 0.1;
			sakuramochiSprite.rotate(-10.0f);
			sakuramochiSprite.setX(sakuramochiSprite.getX() + ((float)Math.sin(actorTheta) - (float)Math.sin(actorTheta - 0.1)) * r);
			sakuramochiSprite.setY(sakuramochiSprite.getY() +((float)Math.cos(actorTheta) - (float)Math.cos(actorTheta - 0.1)) * r);
		}
	}
	public class UIFrameActor extends Actor{
		Texture ButtonFrame = new Texture("Button.png");
		public boolean started = true;

		public UIFrameActor(int x,int y){
			setX(x);
			setY(y);
			Gdx.app.log(String.valueOf(getX()), String.valueOf(getY()));
			setBounds(x, y, ButtonFrame.getWidth(), ButtonFrame.getHeight());
			addListener(new InputListener(){
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
					if(started == false)
						started = true;
					else
						started = false;
					return true;
				}

			});
		}

		@Override
		public void draw(Batch batch, float alpha){
			batch.draw(ButtonFrame, getX(), getY());
		}

		@Override
		public void act(float delta){
			super.act(delta);
			if(started){
				setX(LOGICAL_WIDTH - 128);
			}else{
				setX(0);
			}
		}
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		font.setColor(Color.BLUE);
		sakuramochi = new Texture("sakuramochi.png");

		sprite = new Sprite(sakuramochi);
		sprite.setRotation(45f);

		stage = new Stage(new FitViewport(LOGICAL_WIDTH,LOGICAL_HEIGHT));
		Matrix4 cameraMatrix = stage.getViewport().getCamera().combined;
		batch.setProjectionMatrix(cameraMatrix);
		shapeRenderer.setProjectionMatrix(cameraMatrix);

		sakuramochiActor sakuramochiActor = new sakuramochiActor(256, 256);
		sakuramochiActor.setTouchable(Touchable.enabled);

		Action action = Actions.rotateBy(600, 10 );
		sakuramochiActor.addAction(action);

		stage.addActor(sakuramochiActor);

		textureAtlas = new TextureAtlas(Gdx.files.internal("atlas.atlas"));
		animation = new Animation(1/4f, textureAtlas.getRegions());
		fpsLogger = new FPSLogger();

		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(new GestureDetector(this));
		Gdx.input.setInputProcessor(multiplexer);

		Group uIGroup = new Group();
		{

			uIGroup.setPosition(0, 0);
			stage.addActor(uIGroup);

			{
				UIFrameActor uiFrameActor1 = new UIFrameActor(LOGICAL_WIDTH - 128, LOGICAL_HEIGHT - 128);
				uiFrameActor1.setTouchable(Touchable.enabled);
				uIGroup.addActor(uiFrameActor1);
			}
			{
				UIFrameActor uiFrameActor2 = new UIFrameActor(LOGICAL_WIDTH - 128, LOGICAL_HEIGHT - 128 * 2);
				uiFrameActor2.setTouchable(Touchable.enabled);
				uIGroup.addActor(uiFrameActor2);
			}
			{
				UIFrameActor uiFrameActor3 = new UIFrameActor(LOGICAL_WIDTH - 128, LOGICAL_HEIGHT - 128 * 3);
				uiFrameActor3.setTouchable(Touchable.enabled);
				uIGroup.addActor(uiFrameActor3);
			}
			{
				UIFrameActor uiFrameActor4 = new UIFrameActor(LOGICAL_WIDTH - 128, LOGICAL_HEIGHT - 128 * 4);
				uiFrameActor4.setTouchable(Touchable.enabled);
				uIGroup.addActor(uiFrameActor4);
			}
			{
				UIFrameActor uiFrameActor5 = new UIFrameActor(LOGICAL_WIDTH - 128, LOGICAL_HEIGHT - 128 * 5);
				uiFrameActor5.setTouchable(Touchable.enabled);
				uIGroup.addActor(uiFrameActor5);
			}
		}
	}

	@Override
	public void resize(int width, int height){
		stage.getViewport().update(width, height);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		batch.begin();
		sprite.draw(batch, 1);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		sakuramochi.dispose();
		shapeRenderer.dispose();
		textureAtlas.dispose();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		Gdx.app.log("Touch", "touchDown");
		randomSweetsActor Actor = new randomSweetsActor(LOGICAL_WIDTH - 128, LOGICAL_HEIGHT - 128);
		Actor.setTouchable(Touchable.enabled);
		stage.addActor(Actor);
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		Gdx.app.log("Touch", "Tap");
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		Gdx.app.log("Touch", "longPress");
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		Gdx.app.log("Touch", "fling");
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		 Gdx.app.log("Touch", "pan");

		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		Gdx.app.log("Touch", "zoom");
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
						 Vector2 pointer1, Vector2 pointer2) {
		Gdx.app.log("Touch", "Pinch");
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int deltaX, int deltaY) {
		Gdx.app.log("Touch", "panStop");
		return false;
	}

	@Override
	public void pinchStop() {
		Gdx.app.log("Touch", "pinchStop");
	}
}
