package com.bobby.shooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ProjectileSpawner extends Entity {
    Entity parent;
    Texture projectiles;


    public ProjectileSpawner(World world, Entity parent){
        super(world);
        this.parent = parent;
        projectiles = new Texture("Projectiles.png");

    }

    public void update(float dt){
        this.velocity.x = parent.velocity.x;
        this.velocity.y = parent.velocity.y;
    }


    public void fire(float angle, int speed){
        new Projectile(angle, speed, this.world, this);
    }
}
