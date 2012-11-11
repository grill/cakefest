package com.cakefest.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Android {
	private World world;
	private OrthographicCamera camera;
	private TextureRegion android;
	private Texture texture;
	private Sprite sp;

	public Android(World world, OrthographicCamera camera) {
		this.world = world;
		this.camera = camera;
		
		texture = new Texture(Gdx.files.internal("data/android.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		android = new TextureRegion(texture, 0, 0, 511, 596);
		sp = new Sprite(android);
		sp.setPosition(0, 0);
		sp.setSize(128, 128);
	}

	public void initPhysics() {
        BodyDef bodyDef = new BodyDef();  
        bodyDef.type = BodyType.DynamicBody;  
        bodyDef.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2);  
        Body body = world.createBody(bodyDef);  
        CircleShape dynamicCircle = new CircleShape();  
        dynamicCircle.setRadius(5f);  
        FixtureDef fixtureDef = new FixtureDef();  
        fixtureDef.shape = dynamicCircle;  
        fixtureDef.density = 1.0f;  
        fixtureDef.friction = 0.0f;  
        fixtureDef.restitution = 1;  
        body.createFixture(fixtureDef);  
	}
	
	public void draw(SpriteBatch batch) {
		//batch.draw(android, 0, 0, 511, 596, 400, 400, 1, 1, 0);
		sp.draw(batch);
	}

	public void dispose() {
		texture.dispose();
	}
}
