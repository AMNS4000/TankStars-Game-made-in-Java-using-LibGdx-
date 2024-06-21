package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;


//        fuelar.setSize(50-(fuel/8),15);

//private Sprite healthbar=new Sprite(new Texture("health.png"));
//
//private Sprite fuelar=new Sprite(new Texture("health2.png"));
//private Sprite healthbar_otline=new Sprite(new Texture("hlth_outline.png"));
//private Sprite fuelbar_otline=new Sprite(new Texture("hlth_outline.png"));

public class GameObj extends Sprite  implements Serializable {
    private Vector2 velocity = new Vector2();
    private boolean collisionY=false,collisionX=false;
    private float speed=100,gravity=1.5f;
    TiledMapTileLayer ground;
    public GameObj(Sprite t,TiledMapTileLayer gl){
        super(t);
        this.ground=gl;
    }
    private boolean isblocked(float x, float y){
        TiledMapTileLayer.Cell cell = ground.getCell((int) (x / ground.getTileWidth()), (int) (y / ground.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");

    }
    public boolean collidesBottom() {
        boolean collides = false;
        for(float step = 0; step < getWidth(); step += ground.getTileWidth()/2){
            if(collides = isblocked(getX()+step, getY())) {
                break;}}
//            }System.out.println(isblocked(getX()+step, getY()+getWidth()));}
        return collides;
    }


    public boolean collidesRight() {
        boolean collides = false;
        for(float step = 0; step < getHeight(); step += ground.getTileHeight() / 2)
            if(collides = isblocked(getX() + getWidth(), getY() + step))
                break;

        return collides;
    }

    public boolean collidesLeft() {
        boolean collides = false;
        for(float step = 0; step < getHeight(); step += ground.getTileHeight() / 2){
            if(collides = isblocked(getX(), getY() + step))
                break;}
        return collides;
    }

}
