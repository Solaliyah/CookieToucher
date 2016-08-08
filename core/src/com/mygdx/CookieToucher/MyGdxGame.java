package com.mygdx.cookietoucher;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.cookietoucher.actors.RandomSweetsActor;
import com.mygdx.cookietoucher.actors.TouchedCookieActor;
import com.mygdx.cookietoucher.actors.UIButtonActor;

public class MyGdxGame extends ApplicationAdapter implements ApplicationListener, GestureDetector.GestureListener {
	SpriteBatch batch;
	Texture sakuramochi;
	private BitmapFont font;
	ShapeRenderer shapeRenderer;
	Stage stage;
	private OrthographicCamera camera;
	private FPSLogger fpsLogger = null;
	Setting setting;
	TouchedCookieActor touchedCookieActor;
	Array<String> actorArray;
	Sidebar sidebar;
	@Override
	public void create () {
		actorArray = new Array<String>();
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		font.setColor(Color.BLUE);
		font.getData().setScale(2);
		setting = new Setting();
		sakuramochi = new Texture("sakuramochi.png");

		stage = new Stage(new FitViewport(Setting.LOGICAL_WIDTH, Setting.LOGICAL_HEIGHT));
		Matrix4 cameraMatrix = stage.getViewport().getCamera().combined;
		batch.setProjectionMatrix(cameraMatrix);
		shapeRenderer.setProjectionMatrix(cameraMatrix);

		touchedCookieActor = new TouchedCookieActor();

		stage.addActor(touchedCookieActor);
		actorArray.add("touchedCookieActor");

		fpsLogger = new FPSLogger();

		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(new GestureDetector(this));
		Gdx.input.setInputProcessor(multiplexer);

		Group uIGroup = new Group();
		{

			uIGroup.setPosition(0, 0);
			stage.addActor(uIGroup);
			actorArray.add("uIGroup");
			{
				UIButtonActor uiFrameActor = new UIButtonActor(stage);
				uiFrameActor.setTouchable(Touchable.enabled);
				uIGroup.addActor(uiFrameActor);
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
		font.draw(batch, String.valueOf(setting.getChash()), 0, Setting.LOGICAL_HEIGHT - 32);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		sakuramochi.dispose();
		shapeRenderer.dispose();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		Gdx.app.log("Touch", "touchDown" + String.valueOf(x) + " : " + String.valueOf(y));
		RandomSweetsActor randomSweetsActor = new RandomSweetsActor(Setting.LOGICAL_WIDTH - 128, Setting.LOGICAL_HEIGHT - 128);
		randomSweetsActor.setTouchable(Touchable.enabled);
		stage.addActor(randomSweetsActor);
		actorArray.add("randomSweetsActor");

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
