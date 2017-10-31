package edu.uga.cs.rentaride.persistence.impl;

import edu.uga.cs.rentaride.persistence.Persistable;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;

public abstract class Persistence implements Persistable{
	
	private static PersistenceLayer persistencvalayer;
	private long id;

	
	public Persistence() {
		this.id = -1;
	
	}
	
	public Persistence(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	
	public void setId(long id) {
		
		this.id = id;
	}
	
	
	public boolean isPersistent() {
		return id >= 0;
	}
	
	public static PersistenceLayer getPersistencvalayer() {
		return persistencvalayer;
	}

	public static void setPersistencvalayer(PersistenceLayer persistencvalayer) {
		Persistence.persistencvalayer = persistencvalayer;
	}

}
