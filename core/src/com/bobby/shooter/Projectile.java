package com.bobby.shooter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Entity {

    int lifeTime = 150;
    float angle = 0;
    float speed = 0;
    Vector2 velocity;
    Vector2 originatorVelocity;
    ProjectileSpawner originator;


    public Projectile(float angle, float speed, World world, ProjectileSpawner originator){
        super(world);
        this.angle = angle;
        this.speed = speed;
        this.position.x = originator.position.x;
        this.position.y = originator.position.y;
        this.velocity = new Vector2(0, 0);
        this.originatorVelocity = new Vector2(originator.velocity.x,originator.velocity.y);

        this.originator = originator;

    }

    @Override
    public void update(float dt) {

        this.velocity.x = 1;
        this.velocity.y = 1;

        this.velocity.x = (float)Math.cos(this.angle) * this.speed;
        this.velocity.y = (float)Math.sin(this.angle) * this.speed;





        this.position.x += ((this.velocity.x) + originatorVelocity.x) * dt;
        this.position.y += ((this.velocity.y) + originatorVelocity.y) * dt;
        this.lifeTime -= 1;
        if(this.lifeTime <= 0){
            this.world.removeEntity(this);
        }
    }

    @Override
    public void draw(Graphics g) {

    }

    public void draw(SpriteBatch batch){
        batch.draw(originator.projectiles, this.position.x - 8, this.position.y - 8, 8, 8, 16,16,1,1,(float)Math.toDegrees(this.angle), 0, 0, 16, 16, false, true);
    }

}
