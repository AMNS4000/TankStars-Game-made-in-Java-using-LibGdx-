package com.mygdx.game;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TankSer implements Serializable {
    private static Map<String, TankSer> map=new HashMap<String, TankSer>();
    transient Tank a;
    private boolean turn;
    private float fuel;
    private float health;
    private float power;
    private float angle;
    private int numberser;
    private TankSer(Tank a) {
        this.a = a;
        this.turn=a.turn;
        this.health=a.health;
        this.fuel=a.getFuel();
        this.angle=a.getAngle();
        this.power=a.getPower();
        this.numberser=a.getNumberser();
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
    public void serialize() throws IOException {
        ObjectOutputStream out=null;
        try{
            int g=(this.numberser)%6;
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
            TankSer o=(TankSer) in.readObject();
        }
        finally{
            in.close();
        }
    }
}
