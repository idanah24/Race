package game.arenas;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.IRacer;
import game.racers.Racer;
import utilities.DaemonThread;
import utilities.Point;
import utilities.state.IState;
/**
 * This is the Arena class
 * @author Idan Aharon, Itay Fridman
 * 			305437774	305360653
 * @see AerialArena, NavalArena, LandArena
 */

@SuppressWarnings("deprecation")
public abstract class Arena implements Observer {

	/**
	 * These are Arena class data members
	 */
	protected ArrayList<Racer> activeRacers;
	private ArrayList<Racer> completedRacers;
	private ArrayList<Racer> brokenRacers;
	private ArrayList<Racer> disabledRacers;
	private final double FRICTION;
	private final int MAX_RACERS;
	private final static int MIN_Y_GAP = 10;
	private double length;
	
	private ExecutorService threadPool;
	protected ArrayList<Racer> allRacers;
	private ArrayList<JLabel> imageRacers;
	private Timestamp timestamp;
	private DaemonThread daemon;
	
	public final static String ICON_PATH = "/game/gui/icons/";
	
	/**
	 * This is the Arena class constructor
	 * @param length
	 * @param maxRacers
	 * @param friction
	 */
	public Arena(double length, int maxRacers, double friction) {
		this.activeRacers = new ArrayList<Racer>();
		this.completedRacers = new ArrayList<Racer>();
		this.brokenRacers = new ArrayList<Racer>();
		this.disabledRacers = new ArrayList<Racer>();
		this.allRacers = new ArrayList<Racer>();
		this.imageRacers = new ArrayList<JLabel>();
		
		this.FRICTION = friction;
		this.MAX_RACERS = maxRacers;
		this.setLength(length);
		this.timestamp = null;
		this.daemon = new DaemonThread(this.allRacers);
	}
	
	/**
	 * This is Arena class copy constructor
	 * @param other
	 */
	public Arena(Arena other) {
		this.activeRacers = new ArrayList<Racer>(other.activeRacers);
		this.completedRacers = new ArrayList<Racer>(other.completedRacers);
		this.brokenRacers = new ArrayList<Racer>(other.brokenRacers);
		this.disabledRacers = new ArrayList<Racer>(other.disabledRacers);
		this.allRacers = new ArrayList<Racer>(other.allRacers);
		this.imageRacers = new ArrayList<JLabel>(other.imageRacers);

		this.FRICTION = other.getFRICTION();
		this.MAX_RACERS = other.getMAX_RACERS();
		this.setLength(other.getLength());
		this.timestamp = null;
		this.daemon = new DaemonThread(this.allRacers);
	}
	
	
	
	/**
	 * These are Arena class getters & setters
	 * @return
	 */
	public double getFRICTION() {
		return FRICTION;
	}

	public int getMAX_RACERS() {
		return MAX_RACERS;
	}

	public static int getMinYGap() {
		return MIN_Y_GAP;
	}

	public double getLength() {
		return length;
	}

	public boolean setLength(double length) {
		if(length <= 0)
			return false;
		this.length = length;
		return true;
	}
	
	public ArrayList<Racer> getActiveRacers() {
		return this.activeRacers;
	}
	
	/**
	 * This method returns the number of current active racers
	 */
	public int getNumOfRacers() {
		return this.activeRacers.size();
	}
	
	public ArrayList<Racer> getCompletedRacers(){
		return this.completedRacers;
	}
	
	public ArrayList<Racer> getBrokenRacers(){
		return this.brokenRacers;
	}
	
	public ArrayList<Racer> getAllRacers(){
		return this.allRacers;
	}
	
	public ArrayList<Racer> getDisabledRacers(){
		return this.disabledRacers;
	}
	
	public ArrayList<JLabel> getImageRacers() {
		return imageRacers;
	}
	
	public ExecutorService getThreadPool() {
		return this.threadPool;
	}
	
	public void addRacer(IRacer newRacer) throws RacerTypeException, RacerLimitException{
		Racer racer = (Racer)newRacer;
		racer.setCurrentLocation(new Point(0,MIN_Y_GAP*activeRacers.size()));
	}
	
	/**
	 * This function initializes race for each active racer
	 */
	public void initRace() {
		int y = 0;
		for(int i = 0; i < this.activeRacers.size(); i++) {
			this.activeRacers.get(i).initRace(this, new Point(0, y), new Point(this.length, y));
			y += MIN_Y_GAP;
		}
	}
	
	/**
	 * This method creates a thread pool of all racers and starts a race
	 */
	public void startRace() throws InterruptedException {
		this.timestamp = new Timestamp(System.currentTimeMillis());
		this.threadPool = Executors.newFixedThreadPool(this.activeRacers.size());
		for(int i = 0; i<this.activeRacers.size(); i++)
			threadPool.execute(this.activeRacers.get(i));
		this.daemon.run();
		this.threadPool.shutdown();
		this.threadPool.awaitTermination(10, TimeUnit.SECONDS);

	}
	/**
	 * This function checks if there are any more active racers
	 * @return true: if active racers array is empty. false: otherwise
	 */
	public boolean raceFinished() {
		return (this.activeRacers.isEmpty() && this.brokenRacers.isEmpty());
	}
	
	
	/**
	 * This function prints out the completed racers array
	 */
	public void showResults() {
		for(int i = 0; i < this.completedRacers.size(); i++) {
			System.out.println("#" + (i+1) + " -> " + this.completedRacers.get(i).describeRacer());
		}
			
	}
	
	
	
	/**
	 * This methods handles notifications from racers to the arena
	 */
	@Override
	public synchronized void update(Observable racer, Object s) {
		IState state = (IState)s;
		state.action((Racer)racer);
	}
	
	/**
	 * Abstract methods to be implemented by all inheriting classes
	 */
	public abstract String className();
	
	public Timestamp getTime() {
		return this.timestamp;
	}
}
