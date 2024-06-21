package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;

public class ChooseTankScreen implements Screen {
    MyGdxGame game;
    Texture t1,t2,t3,t4,t5,t6,t7,t8;
    Texture Bg=new Texture("Bg.png");
    int tanknum=1;
    Stage stage;
    public ChooseTankScreen(MyGdxGame game){
        this.game=game;
        stage= new Stage();
        t1=new Texture("helios.png");
        t2=new Texture("pumpkin.png");
        t3=new Texture("toxic.png");
        t4=new Texture("dubstep.png");
        t5=new Texture("pinky.png");
        t6=new Texture("Atomic.png");
        t7=new Texture("t34.png");
        t8=new Texture("mark1.png");
    }
    @Override
    public void show() {

    }

    public void chooseTank(){
        game.batch.draw(Bg,0,0,800,400);
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            if(tanknum==1){
                tanknum=8;
            }else{
                tanknum--;}
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            if(tanknum==8){
                tanknum=1;
            }else{
                tanknum++;
            }
        }
        switch (tanknum){
            case 1:
                game.batch.draw(t1,300,100,200,200);
                break;
            case 2:
                game.batch.draw(t2,300,100,200,200);
                break;
            case 3:
                game.batch.draw(t3,300,100,200,200);
                break;
            case 4:
                game.batch.draw(t4,300,100,200,200);
                break;
            case 5:
                game.batch.draw(t5,300,100,200,200);
                break;
            case 6:
                game.batch.draw(t6,300,100,200,200);
                break;
            case 7:
                game.batch.draw(t7,300,100,200,200);
                break;
            case 8:
                game.batch.draw(t8,300,100,200,200);
                break;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            game.setScreen(new MainScreen(game,t1,t2));
        }
    }
    @Override
    public void render(float delta) {
        game.batch.begin();
        chooseTank();
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
        Bg.dispose();
        t1.dispose();
        t2.dispose();
        t3.dispose();
        t4.dispose();
        t5.dispose();
        t6.dispose();
        t7.dispose();
        t8.dispose();
    }
}
