package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tank;

public class Hud implements Screen{
    final MyGdxGame game;
    private Texture menubg,cont,save,exit,menu;
    public Hud(MyGdxGame game){
        this.game=game;
        menubg=new Texture("menubg.png");
        cont=new Texture("continue.png");
        exit=new Texture("exit2.png");
        save=new Texture("Store.png");
        menu=new Texture("pause2.png");
    }

    @Override
    public void show() {

    }

    public void render(float delta) {
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
//        game.batch.draw(new Texture("TS_LP.png"),0,0,800,450);
        game.batch.draw(menubg,360,120,100,170);
        game.batch.draw(cont,375,240,70,30);
        game.batch.draw(exit,375,200,70,30);
        game.batch.draw(save,375,160,70,30);
        game.batch.draw(menu,340,120,160,250);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        menubg.dispose();
        cont.dispose();
        exit.dispose();
        save.dispose();
        menu.dispose();
    }
}
