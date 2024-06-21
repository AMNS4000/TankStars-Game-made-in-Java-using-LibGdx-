package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;

import java.util.*;
public class Drop{
    private static Map<String, Drop> map=new HashMap<String, Drop>();
    private Texture g;
    private final long x;
    private final long y;
    private final long lastdroptime;
    private Drop(long a, long b, long c){
        this.x=a;
        this.y=b;
        this.lastdroptime=c;
        g=new Texture("nuke.png");
    }
    public static Drop getInstance(long a, long b, long c){
        String key=a+" "+b;
        if (!map.containsKey(key)){
            map.put(key, new Drop(a,b,c));
        }
        return map.get(key);
    }

}
