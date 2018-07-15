package com.bobby.shooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Player extends Entity implements InputProcessor{

    private float speed = 350; //Pixels per second?
    float turretAngle = 0;
    ProjectileSpawner ps;
    float counter = 0f;
    Texture textureSheet;


    public Player(World world){
        super(world);
        velocity = new Vector2(0,0);
        ps = new ProjectileSpawner(world, this);
        textureSheet = new Texture("MulticolorTanks.png");
    }

    @Override
    public void draw(Graphics g) {

        Vector3 screenMouse = new Vector3(Gdx.input.getX(),  Gdx.input.getY(), 0);
        Vector3 worldSpaceMouse = world.camera.unproject(screenMouse);

        float xDiff = worldSpaceMouse.x - this.position.x;
        float yDiff = worldSpaceMouse.y - this.position.y;
        turretAngle = (float)Math.atan2(yDiff, xDiff);//(float)Math.toRadians(Math.sin(counter * 0.003) * 3600);//

    }

    public void draw(SpriteBatch batch){
        batch.draw(textureSheet, this.position.x-16, this.position.y-16, 16,16,32,32,
                2.5f, 2.5f, this.rotation - 90, 0, 0, 32, 32, false, true);

        batch.draw(textureSheet, this.position.x - 16, this.position.y - 16, 16, 16, 32,
                32, 2.5f, 2.5f, (float)Math.toDegrees(turretAngle) , 0, 32, 32,
                32, false, true);
    }

    @Override
    public void update(float dt) {
        counter+=4;



        //System.out.println(moveVector.x);

        Vector2 moveVector = new Vector2();

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            moveVector.y = -1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            moveVector.y = 1f;
        }
//        if(Gdx.input.isKeyPressed(Input.Keys.A)){
//            moveVector.x = -1;
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.D)){
//            moveVector.x = 1;
//        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            this.rotation -= 2;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            this.rotation += 2;
        }

        moveVector.rotate(this.rotation);

        moveVector.nor(); // Normalize to correct for diagonal speed increase

        moveVector.scl(this.speed);



        this.velocity = moveVector;

        this.position.x += (this.velocity.x * dt);
        this.position.y += (this.velocity.y * dt);

        ps.setPosition((int)(Math.cos(turretAngle) * 47.5 + this.position.x), (int)(Math.sin(turretAngle) * 47.5 + this.position.y));

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            ps.fire(turretAngle, 1500);
        }

    }


    /**
     * Called when a key was pressed
     *
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    /**
     * Called when a key was released
     *
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    /**
     * Called when a key was typed
     *
     * @param character The character
     * @return whether the input was processed
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * Called when a finger or the mouse was dragged.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.  @return whether the input was processed
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     * Called when the mouse was moved without any buttons being pressed. Will not be called on iOS.
     *
     * @param screenX
     * @param screenY
     * @return whether the input was processed
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     * Called when the mouse wheel was scrolled. Will not be called on iOS.
     *
     * @param amount the scroll amount, -1 or 1 depending on the direction the wheel was scrolled.
     * @return whether the input was processed.
     */
    @Override
    public boolean scrolled(int amount) {
        this.world.camera.zoom += (0.06 * (this.world.camera.zoom)) * amount;//((0.04 * amount) * this.world.camera.zoom);
        this.world.camera.zoom = (float)Math.max(0.15, Math.min(this.world.camera.zoom, 10));
        System.out.println(this.world.camera.zoom);
        return true;
    }
}
