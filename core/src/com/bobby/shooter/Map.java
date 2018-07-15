package com.bobby.shooter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Map extends Entity{

    /*Map Class
        ArrayList or int[] of sprite tiles
        position?
        width, height
        draw()
     */

    int width = 50;
    int height = 50;
    int tileSize = 50;



    public Map(World world){
        super(world);
    }

    public void draw(Graphics g){

        g.set(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.setColor((i + j) % 2 == 0 ? new Color(0.15f,0.15f,0.15f,1.0f) : new Color(0.25f,0.25f,0.25f,1.0f));
                g.rect(i * tileSize, j * tileSize, tileSize, tileSize);
            }
        }

    }

    public void draw(SpriteBatch batch){

    }

}
