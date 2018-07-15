package com.bobby.shooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;

public class World {
    /*
    World class
        ArrayList<Entity>
        Draw()
        Update()

     */

    Map map;
    Player player;
    OrthographicCamera camera;
    Entity focusedEntity;

    private ArrayList<Entity> entities;

    public World(OrthographicCamera camera){
        this.entities = new ArrayList<Entity>();
        this.camera = camera;
        this.map = new Map(this);
        this.player = new Player(this);
        Gdx.input.setInputProcessor(this.player);
        this.focusedEntity = player;
    }

    public void setPlayerEntity(Player p){
        this.player = p;
    }

    public void draw(Graphics g){
        for (Entity e : this.entities) {
            e.draw(g);
        }
    }

    public void draw(SpriteBatch batch){
        for (Entity e : this.entities) {
            e.draw(batch);
        }
    }



    public void update(float dt){
        int size = entities.size();
        for (int i = size - 1; i >= 0; i--) {
            entities.get(i).update(dt);
        }

        this.camera.position.x = this.focusedEntity.position.x;
        this.camera.position.y = this.focusedEntity.position.y;

    }

    public void addEntity(Entity e){
        this.entities.add(e);
    }

    public void removeEntity(Entity e){
        this.entities.remove(e);
    }
}
