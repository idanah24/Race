package factory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import game.arenas.Arena;
import game.racers.Racer;

/**
 * This is the RaceBuilder class
 * it is used to dynamically load arenas/racers using java reflection
 * @author Idan Aharon, Itay Fridman
 * 			305437774	305360653
 * @see Arena, Racer
 */
/**/
public class RaceBuilder {

	/**
	 * Class is a singleton
	 */
	private static RaceBuilder instance;
	public static RaceBuilder getInstance() {
		if (instance == null) {
			instance = new RaceBuilder();
		}
		return instance;
	}
	
	/**
	 * This method dynamically loads an arena
	 */
	public Arena buildArena(String arenaType, double length, int maxRacers) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		Class<?> c;
		Constructor<?> con;
		c = cl.loadClass(arenaType);
		 con = c.getConstructor(double.class, int.class);
		 return (Arena)con.newInstance(length,maxRacers);
	}
	
	/**
	 * This method dynamically loads a general racer
	 */
	public Racer buildRacer(String racerType, String name, double maxSpeed, double acceleration) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		Class<?> c;
		Constructor<?> con;
		c = cl.loadClass(racerType);
		 con = c.getConstructor(String.class, double.class, double.class);
		 return (Racer)con.newInstance(name, maxSpeed, acceleration);
	}
	
	/**
	 * This method dynamically loads a wheeled racer
	 */
	public Racer buildWheeledRacer(String racerType, String name, double maxSpeed, double acceleration, int numOfWheels) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		Class<?> c;
		Constructor<?> con;
		c = cl.loadClass(racerType);
		 con = c.getConstructor(String.class, double.class, double.class, int.class);
		 return (Racer)con.newInstance(name, maxSpeed, acceleration, numOfWheels);
	}
}