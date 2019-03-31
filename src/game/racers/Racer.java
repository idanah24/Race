package game.racers;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Set;

import game.arenas.Arena;
import utilities.Fate;
import utilities.Mishap;
import utilities.Point;
import utilities.state.Active;
import utilities.state.Broken;
import utilities.state.Completed;
import utilities.state.Disabled;
import utilities.state.IState;

/**
 * This is the Racer class, an abstract class
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 */

@SuppressWarnings("deprecation")
public abstract class Racer extends Observable implements Runnable, Cloneable,IRacer,Comparable<Racer> {

	/**
	 * The following are the class data members
	 */
	private int serialNumber;
	private String name;
	private Point currentLocation;
	private Point finish;
	private Arena arena;
	private double maxSpeed;
	private double acceleration;
	private double currentSpeed;
	private double failureProbability;
	private IState state;
	
	private Mishap malfunction;
	private Hashtable<String,Object> attributes;
	
	private static int numOfRacers = 0;		//Keeping track of serial numbers
	private static final double DEFAULT_PROBABILITY = 0.05;
	
	/**
	 * This is the Racer class constructor
	 * @param name
	 * @param maxSpeed
	 * @param acceleration
	 * @param color
	 */
	public Racer(String name, double maxSpeed, double acceleration){
		this.setSerialNumber();
		this.setName(name);
		this.setCurrentLocation(new Point(0, 0));
		this.setFinish(null);
		this.arena = null;
		this.setMaxSpeed(maxSpeed);
		this.setAcceleration(acceleration);
		this.setCurrentSpeed(0);
		this.setFailureProbability(DEFAULT_PROBABILITY);
		this.setMalfunction(null);
		this.attributes = new Hashtable<String,Object>();
	}
	
	/**
	 * The following are the class getters & setters
	 * @return
	 */
	public int getSerialNumber() {
		return serialNumber;
	}
	
	public boolean setSerialNumber() {
		Racer.numOfRacers++;
		this.serialNumber = Racer.numOfRacers;
		return true;
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		if(name == null)
			name = this.getDefaultName();
		this.name = name;
		return true;
	}
	
	public Point getCurrentLocation() {
		return currentLocation;
	}

	public boolean setCurrentLocation(Point currentLocation) {
		this.currentLocation = currentLocation;
		return true;
	}

	public Point getFinish() {
		return finish;
	}

	public boolean setFinish(Point finish) {
		this.finish = finish;
		return true;
	}

	public Arena getArena() {
		return arena;
	}

	public boolean setArena(Arena arena) {
		this.arena = arena;
		this.addObserver(this.arena);
		return true;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public boolean setMaxSpeed(double maxSpeed) {
		if (maxSpeed <= 0)
			return false;
		this.maxSpeed = maxSpeed;
		return true;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public boolean setAcceleration(double acceleration) {
		if(acceleration <= 0)
			return false;
		this.acceleration = acceleration;
		return true;
	}

	public double getCurrentSpeed() {
		return currentSpeed;
	}

	public boolean setCurrentSpeed(double currentSpeed) {
		if(currentSpeed < 0)
			return false;
		if(currentSpeed > this.maxSpeed)		//Making sure set dosen't exceed speed limit
			currentSpeed = this.maxSpeed;
		this.currentSpeed = currentSpeed;
		return true;
	}

	public double getFailureProbability() {
		return failureProbability;
	}

	public boolean setFailureProbability(double failureProbability) {
		if(failureProbability < 0)
			return false;
		this.failureProbability = failureProbability;
		return true;
	}

	public Mishap getMalfunction() {
		return malfunction;
	}

	public void setMalfunction(Mishap malfunction) {
		this.malfunction = malfunction;
	}

	/**
	 * The following are class methods
	 */
	/**
	 * This function initializes a race for a racer
	 * @param arena
	 * @param start
	 * @param finish
	 */
	public void initRace(Arena arena, Point start, Point finish) {
		this.setArena(arena);
		this.setCurrentLocation(start);
		this.setFinish(finish);
		this.state = new Active();
	}
	
	/**
	 * This method moves a racer forward and increasing it's speed
	 * @param friction
	 * @return Point of a new location after movement
	 */
	public Point move(double friction) {
		double addition = this.acceleration * friction;
		if(this.malfunction == null) {			//If there is no mishap
			if(Fate.breakDown(this.failureProbability) == true) {		//Rolling for a new mishap
				this.malfunction = Fate.generateMishap();	//Generating a new mishap
				System.out.println(this.name + " had a new mishap " + this.malfunction);
			}
		}
		if(this.malfunction != null) {		//If there's a mishap
			if(this.malfunction.isFixable() == true && this.malfunction.getTurnsToFix() == 0) {		//If mishap has ended
				this.malfunction = null;
				this.setState(new Active());
				this.notifyObservers(this.state);
				this.arena.update(this, this.state);
			}
			else if(!(this.malfunction.isFixable())) {
				this.setState(new Disabled());
				this.notifyObservers(this.state);
				this.arena.update(this, this.state);
			}
			else if(this.malfunction.isFixable() == true && this.malfunction.getTurnsToFix() > 0) {
				addition *= this.malfunction.getReductionFactor();
				this.malfunction.nextTurn();
				this.setState(new Broken());
				this.notifyObservers(this.state);
				this.arena.update(this, this.state);
			}
		}
		
		if(!(this.arena.getDisabledRacers().contains(this))) {		//Moving only if racer isn't disabled
			this.setCurrentSpeed(this.currentSpeed + (addition));	//Increasing speed
			if(this.currentLocation.getX() + this.currentSpeed>=this.arena.getLength()) {
				this.currentLocation.setX(this.arena.getLength());
				this.state = new Completed();
				this.notifyObservers(this.state);
				this.arena.update(this, this.state);
			}
			else
				this.currentLocation.setX(this.currentLocation.getX() + this.currentSpeed);	//Moving forward
		}
		return this.currentLocation;
		
	}
	
	/**
	 * This method returns a string describing basic racer details
	 */
	public String describeBasics() {
		return "[" + this.className() + "] Name: " + this.name  + ", Serial Number: " + this.serialNumber + ", Max Speed: " + this.maxSpeed + ", Acceleration: "+ this.acceleration;
	}
	
	/**
	 * This method returns a string describing basic and specific racer details
	 */
	public String describeRacer() {
		return this.describeBasics() + this.describeSpecific();
	}
	
	/**
	 * This method prints out a racer's details
	 */
	public void introduce() {
		System.out.println(this.describeRacer());
	}
	
	/**
	 * This method generates a default racer name
	 * @return
	 */
	public String getDefaultName() {
		return this.className() + " #" + this.getSerialNumber();
	}
	
	/**
	 * The following are all abstract methods
	 */
	public String describeSpecific() {
		String s=", ";
		Set<String> keys = attributes.keySet();
		for(String key : keys) {
			s+=key;
			s+=": ";
			s+=attributes.get(key);
			s+=", ";
		}
		return s;
	}

	public abstract String className();
	
	
	public void run() {
		while(!(this.move(this.arena.getFRICTION())).hasCrossed(finish)) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			if(this.arena.getDisabledRacers().contains(this)) {
				return;
			}
				
		}
	}
	public Hashtable<String,Object> getAttributes() {
		return this.attributes;
	}
	
	public void addAttribute(String key,Object value) {
		if(!attributes.containsKey(key)) {
			attributes.put(key, new ArrayList<Object>());
		}
		@SuppressWarnings("unchecked")
		ArrayList<Object> a = (ArrayList<Object>)attributes.get(key);
		a.add(value);
	}
	
	@Override
	public Racer clone() {
		Racer clonedRacer = null;
		try {
			clonedRacer = (Racer) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
		clonedRacer.setSerialNumber();
		clonedRacer.setName(clonedRacer.getDefaultName());
		clonedRacer.setCurrentLocation(new Point(this.currentLocation));
		if(this.finish != null)
			clonedRacer.setFinish(new Point(this.finish));
		clonedRacer.attributes = new Hashtable<String,Object>();
		Set<String> keys = this.attributes.keySet();
		for(String key : keys) {
			clonedRacer.attributes.put(key, this.attributes.get(key));
		}
		return clonedRacer;
	}
	
	/**
	 * sets the State of the racer
	 * @param state - the state of the racer(Active,Disabled,Broken,Completed)
	 */
	public void setState(IState state) {
		this.state = state;
	}
	
	/**
	 * Implementing the Comperable Interface for sorting the racers
	 */
	@Override
	public int compareTo(Racer racer) {
		return (int)(this.getCurrentLocation().getX() - racer.getCurrentLocation().getX());
	}
}