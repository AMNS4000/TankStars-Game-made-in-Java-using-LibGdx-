package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class menuscreen implements Screen {
    MyGdxGame game;
    Texture menubg;
    int option =1;
    Texture New_game;
    Texture Load_game;
    Texture Exit,logo;
    public menuscreen(MyGdxGame game){
        this.game=game;
        logo= new Texture("logo.png");
        menubg=new Texture("mainmenu.jpg");
        New_game= new Texture("newgame.png");
        Load_game=new Texture("Loadgame.png");
        Exit= new Texture("exit.png");
    }
    @Override
    public void show() {

    }
    public void changeoption(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            if(option>1){
                option--;
            }else if(option==1){
                option=3;
            };
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            if(option<3){
                option++;
            } else if (option==3) {
                option=1;
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        changeoption(delta);
        game.batch.begin();
        game.batch.draw(menubg,0,0,800,400);
        game.batch.draw(logo,300,275,250,125);
        if(option==1){
            game.batch.draw(Exit,190,20,125,50);
            game.batch.draw(Load_game,335,30,150,70);
            game.batch.draw(New_game,510,20,125,50);}
        if(option==2){
            game.batch.draw(Load_game,190,20,125,50);
            game.batch.draw(New_game,335,30,150,70);
            game.batch.draw(Exit,510,20,125,50);}
        if(option==3){
            game.batch.draw(New_game,190,20,125,50);
            game.batch.draw(Exit,335,30,150,70);
            game.batch.draw(Load_game,510,20,125,50);}

        if(Gdx.input.isKeyPressed(66) ){
            if(option==2){
                game.setScreen(new ChooseTankScreen(game));
            } else if (option==1) {
                game.setScreen(new LoadGameScreen(game));
            }
        }
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
        New_game.dispose();
        Load_game.dispose();
        Exit.dispose();
        logo.dispose();
    }
}
