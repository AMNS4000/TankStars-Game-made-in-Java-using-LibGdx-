package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.Screens.MainScreen;
import com.mygdx.game.Screens.MainScreen2;
import com.mygdx.game.Screens.menuscreen;

public class MyGdxGame extends Game {
	public SpriteBatch batch;

	public static final int V_width=800;
	public static final int V_Height=480;
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainScreen2(this,new Texture("helios.png"),new Texture("pumpkin.png")));
	}

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer ;
	@Override
	public void render () {
		super.render();
	}
	@Override
	public void dispose () {
		super.dispose();
	}
}
