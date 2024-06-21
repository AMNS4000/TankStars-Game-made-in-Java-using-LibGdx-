package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class MainScreen implements Screen {

    private boolean pause_flag=false;
    private MyGdxGame game;
    Texture texture;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Hud hud;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Texture tank1,tank2;

    private Texture hb1,ho1;

    private Texture pause;

    private Hud pausemenu = new Hud(game);
    public MainScreen(MyGdxGame game,Texture t1,Texture t2){
        this.game=game;
        texture=new Texture("TS_LP.png");
        gamecam=new OrthographicCamera();
        gameport=new StretchViewport(800,480,gamecam);
//        gameport=new S6creenViewport();
//        gameport=new FitViewport(game.V_width,game.V_Height,gamecam);
        hud = new Hud(game);
        hb1=new Texture("health.png");
        ho1=new Texture("hlth_outline.png");
        tank1=t1;
        tank2=t2;
        maploader=new TmxMapLoader();
        map=maploader.load("map2.tmx");
        renderer= new OrthogonalTiledMapRenderer(map);
        pause=new Texture("pause.png");
        gamecam.position.set(+400,+280,0);
    }
    @Override
    public void show() {

    }

    public void update(float dt){
        gamecam.update();
        renderer.setView(gamecam);
    }


    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        renderer.render();
        game.batch.draw(ho1,20,350,100,20);
        game.batch.draw(hb1,20,350,100,20);
        game.batch.draw(ho1,690,350,100,20);
        game.batch.draw(hb1,690,350,100,20);
        game.batch.draw(tank1,160,250,50,50);
        game.batch.draw(tank2,600,220,50,50);
        game.batch.draw(pause,375,360,75,75);
        if(Gdx.input.isKeyPressed(Input.Keys.P)){
            pause_flag=true;
        }
        if(pause_flag==true){
            game.setScreen(new Hud(game));
        }
        game.batch.setProjectionMatrix(gamecam.combined);
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

    }

    @Override
    public void dispose() {
//        texture.dispose();
//        tank1.dispose();
//        tank2.dispose();
//        pause.dispose();
//        ho1.dispose();
//        hb1.dispose();
//        hud.dispose();

    }
}
