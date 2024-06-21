package com.mygdx.game.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class LoadGameScreen implements Screen {
    MyGdxGame game;
    Texture bg,emptyslot,filledslot;
    public LoadGameScreen(MyGdxGame game){
        this.game=game;
        bg=new Texture("OIP2.jpeg");
        emptyslot=new Texture("LG6.png");
        filledslot=new Texture("lG4.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(bg,0,0,800,400);
        game.batch.draw(filledslot,87,200,150,125);
        game.batch.draw(emptyslot,87,50,150,125);
        game.batch.draw(emptyslot,318,200,150,125);
        game.batch.draw(emptyslot,318,50,150,125);
        game.batch.draw(emptyslot,561,200,150,125);
        game.batch.draw(emptyslot,561,50,150,125);
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setScreen(new menuscreen(game));
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
        bg.dispose();
        emptyslot.dispose();
        filledslot.dispose();

    }
}
