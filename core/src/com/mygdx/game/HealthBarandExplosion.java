package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HealthBarandExplosion {
    public static final float FRAME_LENGTH= 0.2f;
    public static final int OFFSET= 8;
    public static final int SIZE = 32;

    private static Animation an= null;
    float x,y;
    float statetime;
    public boolean remove = false;
    public HealthBarandExplosion(float x, float y){
        this.x= x - OFFSET;
        this.y= y- OFFSET;
        statetime=0;
        if (an == null){
            an = new Animation(FRAME_LENGTH, TextureRegion.split(new Texture("explosion.png"), SIZE, SIZE));

        }
    }
    public void update(float delta){
        statetime += delta;
        if (an.isAnimationFinished(statetime))
            remove=true;
    }
    public void render(SpriteBatch batch){
        batch.draw((Texture) an.getKeyFrame(statetime),x,y);
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }


}
