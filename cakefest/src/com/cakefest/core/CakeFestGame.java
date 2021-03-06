package com.cakefest.core;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class CakeFestGame implements ApplicationListener {
    static final float BOX_STEP=1/60f;  
    static final int BOX_VELOCITY_ITERATIONS=6;  
    static final int BOX_POSITION_ITERATIONS=2;  
    static final float WORLD_TO_BOX=0.01f;  
    static final float BOX_WORLD_TO=100f;  
    
    static final int WIDTH = 480;
    static final int HEIGHT = 320;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Android sp;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Rectangle glViewport;
    private Random r;
    ArrayList<Krapfen> list;
    long time;
    long interval = 2000;
    static int counter = 0;
    static int dcounter = 10;
    private Krapfen k;
    private BitmapFont font;
    static ArrayList<Krapfen> kills;

	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		world = new World(new Vector2(0, -100), true);
		camera = new OrthographicCamera(WIDTH, HEIGHT);            
		camera.position.set(WIDTH / 2, HEIGHT / 2, 0);

        glViewport = new Rectangle(0, 0, WIDTH, HEIGHT);
		batch = new SpriteBatch();
		
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		
		sp = new Android(world, camera);
		list = new ArrayList<Krapfen>();
		kills = new ArrayList<Krapfen>();
		//sprite = new Sprite(region);
		//Gdx.app.log("h w", sprite.getHeight() + " w " + sprite.getWidth());
		//sprite.setSize(sprite.getHeight(), sprite.getWidth());
		//sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		//sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);


        BodyDef groundBodyDef =new BodyDef();  
        groundBodyDef.position.set(new Vector2(-100, 10));  
        Body groundBody = world.createBody(groundBodyDef);  
        PolygonShape groundBox = new PolygonShape();  
        groundBox.setAsBox((camera.viewportWidth) * 2, 0, new Vector2(0,-165), 0);
        FixtureDef f = new FixtureDef();
        f.shape = groundBox;
        f.density = 0.0f;
        groundBody.createFixture(f).setUserData(this); 
        debugRenderer = new Box2DDebugRenderer(); 
        r = new Random();
        
        time = System.currentTimeMillis();
        k = new Krapfen(world, camera, 220-r.nextInt(440), r.nextInt(2));
        world.setContactListener(new CollisionDetection());
	}

	@Override
	public void dispose() {
		batch.dispose();
		sp.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport((int) glViewport.x, (int) glViewport.y,
                        (int) glViewport.width, (int) glViewport.height);
		

		for(Krapfen kra : kills) {
			kra.kill();
			list.remove(kra);
		}
		batch.setProjectionMatrix(camera.combined);
        //debugRenderer.render(world, camera.combined);  
        world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
        if(time+interval < System.currentTimeMillis()) {
        	if(interval > 500)
        		interval -= 100;
        	time = System.currentTimeMillis();
        	list.add(new Krapfen(world, camera, 220-r.nextInt((440)), r.nextInt(2)));
        }
		batch.begin();
		if(dcounter < 0) {

			font.draw(batch, "You Lost!!", 0, 0);
		} else {
		for(Krapfen kra : list)
			kra.draw(batch);
		sp.draw(batch);
		font.draw(batch, "P: " + counter, 180, 140);
		font.draw(batch, "L: " + dcounter, 180, 110);
		k.draw(batch);
		}
		batch.end();
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
}
