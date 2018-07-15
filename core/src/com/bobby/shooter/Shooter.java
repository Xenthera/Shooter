package com.bobby.shooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Shooter extends ApplicationAdapter {

	private Graphics g;

	private World world;

	private SpriteBatch batch;
	Texture texture;

	OrthographicCamera camera;


	//private Player player;


	@Override
	public void create () {
	    g = new Graphics();
        batch = new SpriteBatch();


        //player = new Player(world);
        //world.setPlayerEntity(player);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true);
        //camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();


        world = new World(camera);

        texture = new Texture("TankSpriteSheet.png");


	}

	@Override
	public void render () {

	    /////////////// Update ///////////////
        float dt = Gdx.graphics.getDeltaTime();
        world.update(dt);
        camera.update();

	    /////////////// Render ///////////////
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        g.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);


		g.setAutoShapeType(true);
		g.begin();
		world.draw(g);
		g.end();

        batch.begin();
        world.draw(batch);
        batch.end();
	}
	
	@Override
	public void dispose () {
        g.dispose();
	}

	public void resize(int w, int h){

	}
}
