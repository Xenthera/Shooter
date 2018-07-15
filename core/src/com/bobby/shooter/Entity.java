package com.bobby.shooter;

/*
Base entity class
    Abstract Draw()
    Update()
    Vec2 Position

 */

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Entity {

    public Vector2 position;
    Vector2 velocity;
    Vector2 acceleration;
    World world;
    float rotation = 0f;


    public Entity(World world){
        this.world = world;
        this.world.addEntity(this);
        position = new Vector2(0,0);
        velocity = new Vector2(0,0);
        acceleration = new Vector2(0,0);
    }

    public void draw(Graphics g){
        //Implemented by subclasses
    }

    public void draw(SpriteBatch batch){
        //Implemented by subclasses
    }

    public void update(float dt){
        //Implemented by subclasses
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setPosition(int x, int y){
        this.position.x = x;
        this.position.y = y;
    }

}
