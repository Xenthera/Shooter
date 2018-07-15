package com.bobby.shooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

import java.util.Stack;

public class Graphics extends ShapeRenderer {
    enum mode {
        CENTERED, CORNER
    }
    enum fillType {
        FILLED, LINE
    }
    private mode ellipseMode = mode.CENTERED;


    Stack<Matrix4> transformStack;

    public Graphics(){
        super();
        transformStack = new Stack<Matrix4>();
    }

    public void pushTransform(){
        Matrix4 matrix = getTransformMatrix();
        transformStack.push(matrix);
        setTransformMatrix(new Matrix4(matrix));
    }

    public void popTransform(){
        Matrix4 matrix = transformStack.pop();
        setTransformMatrix(matrix);
    }


    @Override
    public void ellipse(float x, float y, float width, float height) {
        if(this.ellipseMode == mode.CENTERED) {
            super.ellipse(x - (width / 2), y - (height / 2), width, height);
        }else if(this.ellipseMode == mode.CORNER){
            super.ellipse(x, y, width, height);
        }
    }

    public void setEllipseMode(mode m){
        this.ellipseMode = m;
    }
}
