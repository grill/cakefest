package com.cakefest.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;

public class Android {
	private World world;
	private OrthographicCamera camera;
	private TextureRegion android;
	private Texture texture;
	private Sprite sp;
	private Fixture fix;
	private Body body;
	private Vector2 ve;
	private int size = 128;
	private boolean pressed = false;

	public Android(World world, OrthographicCamera camera) {
		this.world = world;
		this.camera = camera;
		
		texture = new Texture(Gdx.files.internal("data/android.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		android = new TextureRegion(texture, 0, 0, 511, 596);
		sp = new Sprite(android);
		sp.setPosition(0, 0);
		
		sp.setSize(size, size);
		
		initPhysics();
	}

	public void initPhysics() {
        BodyDef bodyDef = new BodyDef();  
        bodyDef.type = BodyType.DynamicBody;  
        bodyDef.position.set(0, -140);  
        bodyDef.gravityScale = 20;
        body = world.createBody(bodyDef);  
        CircleShape dynamicCircle = new CircleShape();  
        dynamicCircle.setRadius(60f);
        dynamicCircle.setPosition(new Vector2(64, 70));
        FixtureDef fixtureDef = new FixtureDef();  
        fixtureDef.shape = dynamicCircle;
        fixtureDef.density = 1.0f;  
        fixtureDef.friction = 0.0f;  
        fixtureDef.restitution = 1;  
        fix = body.createFixture(fixtureDef); 
        fix.setUserData(this);
	}
	
	public void draw(SpriteBatch batch) {
		//batch.draw(android, 0, 0, 511, 596, 400, 400, 1, 1, 0);
		ve = body.getPosition();
		sp.setPosition(ve.x, ve.y);
		sp.setSize(size, size);
		sp.draw(batch);
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT) || Gdx.input.isKeyPressed(Keys.A)) body.setLinearVelocity(new Vector2(-100, 0));
		else if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) || Gdx.input.isKeyPressed(Keys.D)) body.setLinearVelocity(new Vector2(100, 0));
		else body.setLinearVelocity(new Vector2(0,0));
		
	}

	public void dispose() {
		texture.dispose();
	}

	public void fat() {
		size += 5;
	}
}
