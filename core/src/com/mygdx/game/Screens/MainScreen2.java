package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tank;
import com.mygdx.game.weapon;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainScreen2 implements Screen {
    //    private boolean pause_flag=false;
    private MyGdxGame game;
//    private boolean turn;
    //    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    //    private Hud hud;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Texture tan2,tan1;

    private weapon w1;


//    private Texture hb1,ho1;

    //    private Texture pause;
    private Tank tank1, tank2;

    //    private Hud pausemenu = new Hud(game);
    public MainScreen2(MyGdxGame game, Texture t1, Texture t2) {
        this.game = game;
        this.tan1=t1;
        this.tan2=t2;
//        if(map.getLayers().get(0)==null){
//            System.out.println("null");
//        }
//        texture=new Texture("TS_LP.png");
        gamecam = new OrthographicCamera();
        gamecam.position.set(+600,+420,0);

//        this.show();
//        pause=new Texture("pause.png");
//        gamecam.position.set(+400,+280,0);
//        System.out.println("okay okay main");
    }

    @Override
    public void show() {
//        maploader=new TmxMapLoader();
        map = new TmxMapLoader().load("ground.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameport = new StretchViewport(800, 480, gamecam);
        tank1=new Tank(new Sprite(tan1),1,(TiledMapTileLayer) map.getLayers().get(1));
        tank2=new Tank(new Sprite(tan2),2,(TiledMapTileLayer) map.getLayers().get(1));
        Gdx.input.setInputProcessor(tank1);
        tank1.setOpponent(tank2);
        tank2.setOpponent(tank1);
//        Gdx.input.setInputProcessor(tank2);
//        for(MapLayer m:map.getLayers()){
//            System.out.println(m.getName());
//        }
//        System.out.println("okay okay");
//        gameport=new ScreenViewport();
//        gameport=new FitViewport(game.V_width,game.V_Height,gamecam);
//        hud = new Hud(game);
//        hb1=new Texture("health.png");
//        ho1=new Texture("hlth_outline.png");

    }

    public void update(float dt) {
        gamecam.update();
        renderer.setView(gamecam);
//        System.out.println("okay");
    }

    public void changeTurn(){
        if(Gdx.input.getInputProcessor()==tank1 && tank2.w1.destructed){
            Gdx.input.setInputProcessor(tank2);
        } else if (Gdx.input.getInputProcessor()==tank2 && tank1.w1.destructed) {
            Gdx.input.setInputProcessor(tank1);
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        renderer.render();
//        game.batch.draw(pause,375,360,75,75);
        tank1.draw(game.batch);
        tank2.draw(game.batch);
//        System.out.println(tank1.health+"      "+tank2.health);
//        if(Gdx.input.isKeyPressed(Input.Keys.P)){
//            pause_flag=true;
//        }
//        if(pause_flag==true){
//            game.setScreen(new Hud(game));
//    }
        game.batch.setProjectionMatrix(gamecam.combined);
//        System.out.println("fint till now");
        game.batch.end();
    }
    @Override
    public void resize(int width, int height) {
        gameport.update(800,480);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
//        dispose();
    }

    @Override
    public void dispose() {
//        texture.dispose();
//        tank1.getTexture().dispose();
//        tank2.getTexture().dispose();
//        pause.dispose();
//        ho1.dispose();
//        hb1.dispose();
//        hud.dispose();
//
    }

//    public void serialize () throws IOException {
//        ObjectOutputStream out=null;
//        try{
//            out = new ObjectOutputStream(new FileOutputStream("out.txt"));
////            out.write(tank1);
////            out.write(tank2);
//        }
//    }
}
