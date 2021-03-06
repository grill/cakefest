package com.cakefest.core;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionDetection implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		Object oa = contact.getFixtureA().getUserData();
		Object ob = contact.getFixtureB().getUserData();
		
		if(oa instanceof Android && ob instanceof Krapfen) {
			if(!CakeFestGame.kills.contains((Krapfen) ob)) {
				CakeFestGame.counter++;
			}
			CakeFestGame.kills.add(((Krapfen) ob));
			//((Android)oa).fat();
		} else if(ob instanceof Android && oa instanceof Krapfen) {
			if(!CakeFestGame.kills.contains((Krapfen) oa)) {
				CakeFestGame.counter++;
			}
			CakeFestGame.kills.add((Krapfen) oa);
			//CakeFestGame.counter++;
			//((Android)oa).fat();
		} else if(oa instanceof Krapfen && ob instanceof CakeFestGame) {
			if(!CakeFestGame.kills.contains((Krapfen) oa)) {
				CakeFestGame.dcounter--;
			}
			CakeFestGame.kills.add((Krapfen) oa);
		} else if(oa instanceof CakeFestGame && ob instanceof Krapfen) {
			if(!CakeFestGame.kills.contains((Krapfen) ob)) {
				CakeFestGame.dcounter--;
			}
			CakeFestGame.kills.add((Krapfen) ob);
		}
	}

	@Override
	public void endContact(Contact contact) {
		/*Object oa = contact.getFixtureA().getUserData();
		Object ob = contact.getFixtureB().getUserData();
		
		if(oa instanceof Android && ob instanceof Krapfen) {
			CakeFestGame.kills.add(((Krapfen) ob));
			//CakeFestGame.counter++;
			//((Android)oa).fat();
		} else if(ob instanceof Android && oa instanceof Krapfen) {
			CakeFestGame.kills.add((Krapfen) oa);
			//CakeFestGame.counter++;
			//((Android)oa).fat();
		} else if(oa instanceof Krapfen && ob instanceof CakeFestGame) {
			CakeFestGame.kills.add((Krapfen) ob);
		} else if(ob instanceof CakeFestGame && oa instanceof Krapfen) {
			CakeFestGame.kills.add((Krapfen) ob);
		}*/
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
