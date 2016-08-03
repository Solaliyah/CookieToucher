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
import com.badlogic.gdx.math.MathUtils;
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
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Iterator;

public class MyGdxGame extends ApplicationAdapter implements ApplicationListener, GestureDetector.GestureListener {
	SpriteBatch batch;
	Texture sakuramochi;
	ShapeRenderer shapeRenderer;
	private TextureAtlas textureAtlas;
	private float elapsedTime = 0;
	private Sprite button;
	private Vector3 touchPoint;
	public static int LOGICAL_WIDTH = 720;
	public static int LOGICAL_HEIGHT = 1280;
	Stage stage;
	private FPSLogger fpsLogger = null;
	Vector2 touchVector;
	long chash;
	BitmapFont font;


	public class sakuramochiActor extends Actor{
		Texture texture = new Texture("sakuramochi.png");
		double actorTheta = 0;
		int r = 128;

		public sakuramochiActor(float x,float y){
			setX(x);
			setY(y);
		}

		@Override
		public void draw(Batch batch, float alpha){
			batch.draw(texture, getX(), getY());
		}

		@Override
		public void act(float delta){
			actorTheta += 0.1;
			setX(getX() + ((float)Math.sin(actorTheta) - (float)Math.sin(actorTheta - 0.1)) * r);
			setY(getY() +((float)Math.cos(actorTheta) - (float)Math.cos(actorTheta - 0.1)) * r);
			if(actorTheta %6 == 0){
				Gdx.app.log(String.valueOf(actorTheta), "a");
			}
		}
	}
	public class UIFrameActor extends Actor{
		Texture ButtonFrame = new Texture("Button.png");
		public boolean started = false;

		public UIFrameActor(int x,int y){
			setBounds(x, y, ButtonFrame.getWidth(), ButtonFrame.getHeight());
			addListener(new InputListener(){
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
					if(started == false)
						started = true;
					else
						started = false;
					Gdx.app.log("A",String.valueOf(started));
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
			if(started){
				setX(0);
			}else{
				setX(LOGICAL_WIDTH - ButtonFrame.getWidth());
			}
		}
	}

	public class CookeiActor extends Actor{
		Texture cookey = new Texture("sakuramochi.png");
		public CookeiActor(int x,int y){
			setX(x);
			setY(y);
			setBounds(getX(), getY(), cookey.getWidth(), cookey.getHeight());
			addListener(new InputListener(){
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
					chash++;
					sakuramochiActor sakuramochiActor = new sakuramochiActor(MathUtils.random(LOGICAL_WIDTH), MathUtils.random(LOGICAL_HEIGHT));
					sakuramochiActor.setTouchable(Touchable.enabled);
					stage.addActor(sakuramochiActor);
					return true;
				}

			});
		}

		@Override
		public void draw(Batch batch, float alpha){
			batch.draw(cookey, this.getX(), this.getY());
		}

		@Override
		public void act(float delta){
			for(Iterator<Action> iter = this.getActions().iterator(); iter.hasNext();){
				iter.next().act(delta);
			}
		}
	}

	public class BackGroundActor extends Actor{
		Texture ButtonFrame = new Texture("BackGround.jpeg");
		public boolean started = false;

		public BackGroundActor(){
			setX(0);
			setY(0);
		}

		@Override
		public void draw(Batch batch, float alpha){
			batch.draw(ButtonFrame, getX(), getY());
		}

		@Override
		public void act(float delta){

		}
	}


	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		font.setColor(Color.BLUE);
		font.getData().setScale(12);
		sakuramochi = new Texture("sakuramochi.png");
		touchVector = new Vector2();
		stage = new Stage(new FitViewport(LOGICAL_WIDTH, LOGICAL_HEIGHT));
		Matrix4 cameraMatrix = stage.getViewport().getCamera().combined;
		batch.setProjectionMatrix(cameraMatrix);
		shapeRenderer.setProjectionMatrix(cameraMatrix);

		chash = 0;

		BackGroundActor backGroundActor = new BackGroundActor();

		CookeiActor cookeiActor = new CookeiActor(256, 256);

		stage.addActor(backGroundActor);
		stage.addActor(cookeiActor);


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
				UIFrameActor uiFrameActor1 = new UIFrameActor(720 - 128 + 40, 1280 - 128 - 30);
				uiFrameActor1.setTouchable(Touchable.enabled);
				uIGroup.addActor(uiFrameActor1);
			}
			{
				UIFrameActor uiFrameActor2 = new UIFrameActor(720 - 128 + 40, 1280 - 128 * 2 - 30);
				uiFrameActor2.setTouchable(Touchable.enabled);
				uIGroup.addActor(uiFrameActor2);
			}
			{
				UIFrameActor uiFrameActor3 = new UIFrameActor(720 - 128 + 40, 1280 - 128 * 3 - 30);
				uiFrameActor3.setTouchable(Touchable.enabled);
				uIGroup.addActor(uiFrameActor3);
			}
			{
				UIFrameActor uiFrameActor4 = new UIFrameActor(720 - 128 + 40, 1280 - 128 * 4 - 30);
				uiFrameActor4.setTouchable(Touchable.enabled);
				uIGroup.addActor(uiFrameActor4);
			}
			{
				UIFrameActor uiFrameActor5 = new UIFrameActor(720 - 128 + 40, 1280 - 128 * 5 - 30);
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
		font.draw(batch, String.valueOf(chash) , 0, LOGICAL_HEIGHT);
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
		stage.getViewport().unproject(touchVector.set(x, y));
		Gdx.app.log("Touch", "touchDown" + String.valueOf(touchVector.x));
		sakuramochiActor sakuramochiActor = new sakuramochiActor(touchVector.x, touchVector.y);
		sakuramochiActor.setTouchable(Touchable.enabled);
		stage.addActor(sakuramochiActor);
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