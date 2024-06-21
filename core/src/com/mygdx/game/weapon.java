package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.sun.tools.sjavac.JavacState;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class weapon extends GameObj implements Serializable {
    private Vector2 velocity = new Vector2();
    public boolean destr=false;
//    ANY_ACCESS_MODIFIER static final long serialVersionUID = 42L;
    private final float damage=20;
    private boolean collision=false;
    long d_s=-1,f_s;
    public boolean destructed= false;
    private ArrayList<String> arrt=new ArrayList<>();
    private float speed=500,gravity=50f;
    boolean shoot;
    TiledMapTileLayer ground;
    Texture destruction= new Texture("destruct.jpg");
    private final Tank Master;
    private int ui;
    public weapon(Tank m ,Sprite t,TiledMapTileLayer gl){
        super(t,gl);
        Master=m;
        setSize(25,25);
        arrt.add("Weapon1 ");
        arrt.add("Weapon 2");
        arrt.add("Weapon 3:");
    }
    public void setangleandpower(float angle,float power){
        Iterator iter= this.arrt.iterator();
        while (iter.hasNext()){
            if (iter.next().equals("Weapon 1")){
                this.ui=5;
            }
        }
        this.velocity.x= (float) (power*Math.sin(angle));
        this.velocity.y=(float) (power*Math.cos(angle));
        if(shoot){
            setPosition(Master.getX(), Master.getY());
            System.out.println("updated");
        }
    }

    public  void destructed(){
        if (!destructed){
            this.setTexture(destruction);
            this.setSize(100,100);
            Damage(Master);
            Damage(Master.opponent);
            destr=true;
        }
    }
    public static double calculateDamage(float x, float y, float d1, float d2, int h){
        int healthfactor;
        double distanceg=(Math.sqrt(Math.pow(x-d1,2)+Math.pow(y-d2,2)))*h;
        return distanceg;

    }
    public void Damage(Tank m){
        float disx=m.getX() - getX();
        float disy=m.getY() - getY();
        calculateDamage(m.getX(),m.getY(),10,15,200);
        if (Math.abs(disx)<50 && Math.abs(disy) < 30){
            m.health-= damage*15*(1/Math.abs(disx));
        }
    }
    public void shoot(float delta){
        collision=collidesLeft()||collidesRight()||collidesBottom();
        if(!collision){
            velocity.y-=gravity*delta;
//            velocity.x-=0.1*delta;
            setY(getY()+ velocity.y*delta);
            setX(getX()+ velocity.x*delta);}
        else {
            destructed();
            destructed=true;
//            d_s=java.lang.System.currentTimeMillis();
        }
    }
    @Override
    public void draw(Batch b){
        if(shoot){
            shoot(Gdx.graphics.getDeltaTime());
        }
        else {
//            System.out.println("shott not true");
        }
        super.draw(b);
    }

}
