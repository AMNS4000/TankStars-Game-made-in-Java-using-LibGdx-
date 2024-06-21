package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Json;

import java.io.*;

public class Tank extends GameObj implements InputProcessor, Serializable {
// movement velcoity
    public boolean turn;
    private int numberser=0;
    private Sprite healthbar=new Sprite(new Texture("health.png"));
    private Sprite bar_out1=new Sprite(new Texture("health.png"));
    private Sprite bar_out2=new Sprite(bar_out1);
    private Sprite fuelbar=new Sprite(new Texture("health.png"));

    boolean lost;

    public MyGdxGame game;
    private Vector2 velocity = new Vector2(),c_p=new Vector2(),c_a=new Vector2();
    private boolean collisionY=false,collisionX=false;
    public boolean shoot=false;
    private float fuel=400;
    public float health=100;
    public  weapon w1;
    public Tank opponent;
    private float speed=60 , gravity = 1.5f;
//    Sprite t;
    private float power=200,angle=46;
    TiledMapTileLayer ground;
    public String name;

    public int getNumberser() {
        return numberser;
    }

    public void setNumberser(int numberser) {
        this.numberser = numberser;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public Tank(Sprite t, int p_no, TiledMapTileLayer gl) {
        super(t,gl);
//        game=g;
        this.ground=gl;
        w1 = new weapon(this, new Sprite(new Texture("nuke.png")), ground);
        if(p_no==1){ // intitla values for player 1
            setPosition(200,600);
            setSize(50,50);
            name = "P1";
            fuelbar.setPosition(100,100);
            turn=true;
        }
        else if (p_no==2) {  // initial values for player 2
            setPosition(900,400);
            setSize(50,50);
            angle=-45;
            name="P2";
            turn=false;
            fuelbar.setPosition(450,100);
            w1.rotate(180);
        }
    }

    public void setOpponent(Tank op){  
        this.opponent=op;  //getting opponent player to give signals such as if turn is over
    }
    private boolean isblocked(float x, float y){   // takes coordinates and check if the tile there have a property blo
        Cell cell = ground.getCell((int) (x / ground.getTileWidth()), (int) (y / ground.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }
    public boolean collidesBottom() {
        boolean collides = false;
        for(float step = 0; step < getWidth(); step += ground.getTileWidth()/2){
            if(collides = isblocked(getX()+step, getY())) {
                break;}}
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
    public void aim(){
        if (Gdx.input.isKeyPressed(Input.Keys.J)){
            angle-=0.01;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.L)){
            angle+=0.01;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.K)){
            power-=0.5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.I)){
            power+=0.5;
        }
    }
    public void checklost(){
        if (health<0){
            if(!lost){
                setTexture(new Texture("RIP.png"));
            }
            lost = true;
        }
    }
    public void update(float delta){
//        System.out.println(get);
        velocity.y -=gravity*delta;
//        set;
        if(velocity.y < speed  && !collisionX)
            velocity.y-=speed;


        float oldx= getX(),oldy=getY();
        float tile_wdt=ground.getTileWidth();
        float tile_hgt= ground.getTileHeight();
        setX(getX()+(velocity.x*delta));
//        System.out.println("velocity.x "+ getX());
        if(velocity.x < 0){
            collisionX=collidesLeft();
            fuel-=1;
        } else if (velocity.x > 0) {
            collisionX=collidesRight();
            fuel-=1;
        }
        setY(getY()+velocity.y*delta);
        if(velocity.y<0){    //going down
            collisionY=collidesBottom();}
        if(collisionY  && !collisionX){
            setY(oldy);
            velocity.y=0;
        }
        else if(collisionX && collisionY){
            if(collisionX) {
                velocity.y=30;
                setX(oldx);
                setY(getY()+velocity.y*delta);
                velocity.y=0;
            }
            else {
                setX(getX()+(velocity.x*delta));
            }
        }
    }
    public void updatehlth(){
        healthbar.setSize((health/2),15);
        healthbar.setPosition(getX(),getY()+75);
    }

    @Override
    public void draw(Batch b){
        updatehlth();
        healthbar.draw(b);
        if(fuel>0){
            update(Gdx.graphics.getDeltaTime());}
        if (turn){
            aim();
        }
        checklost();
        if(w1!=null){
            w1.draw(b);}
        else{
            System.out.println(name+" w1 is null");
        }
        if(w1!=null && w1.destr){
            changeturn();
        }else{
//            System.out.println( name +"      "+(w1==null) +"      "+w1.destructed);
        }
//        if(w1!=null && w1.destructed){
//            w1=null;
//        }
//        System.out.println(name+"   w1 destructed = "+w1.destructed);
        super.draw(b);
//        System.out.println("Fuel : "+fuel+"name : "+this.name);

//
//        System.out.println("done");
//        super.draw(b);
//        b.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.A:
//                System.out.println("A pressed "+velocity.x);
                velocity.x= -speed;
//                System.out.println("A pressed "+velocity.x);
                break;
            case Input.Keys.D:
                velocity.x= +speed;
                break;
            case Input.Keys.ENTER:
                if(w1!=null && !w1.shoot) {
//                    System.out.println(angle + "      " + power);
                    w1 = new weapon(this, new Sprite(new Texture("nuke.png")), ground);
//                w1.setangleandpower(45f,50f);

//                    else {
////                        System.out.println(w1+"   w1 destructed = "+w1.destructed);
//                    }
                    w1.shoot = true;
                    w1.setangleandpower(angle, power);

//                    System.out.println(((Tank)(Gdx.input.getInputProcessor())).name);
                }
                break;
        }
        return false;
    }

    private void changeturn(){
        if(opponent!=null ){
//            System.out.println("Turn changed");
            Gdx.input.setInputProcessor(opponent);
            this.turn=false;
            opponent.turn=true;
            opponent.refuel();
        }
        else{
//            System.out.println("null");
        }
    }
    private void refuel() {
        fuel=400;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.A:
                velocity.x=0;
                break;
            case Input.Keys.D:
                velocity.x=0;
                break;
        }
        return false;
    }
    public void serialize() throws IOException{
        ObjectOutputStream out=null;
        try{
            int g=numberser%6;
            String y=String.valueOf(g);
            out=new ObjectOutputStream(new FileOutputStream(y));
            out.writeObject(this);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            out.close();
        }
    }
    public void deserialize(String h) throws IOException, ClassNotFoundException{
        ObjectInputStream in=null;
        try{
            in=new ObjectInputStream(new FileInputStream(h));
            Tank o=(Tank) in.readObject();
        }
        finally{
            in.close();
        }
    }
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

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}