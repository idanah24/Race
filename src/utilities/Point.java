package utilities;

/**
 * This class is a utility for the race game
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 */
public class Point {
	/**
	 * These are the class fields
	 * Contains x & y to represent a point and constant variables that hold limits
	 */
	private double x;
	private double y;
	final static int MIN_X = 0;
	final static int MAX_X = 10000000;
	final static int MIN_Y = 0;
	final static int MAX_Y = 800;
	
	/**
	 * This is Point class constructor
	 * @param a
	 * @param b
	 */
	public Point(double a, double b) {
		if(!(this.setX(a)))
			this.x = 0;
		if(!(this.setY(b)))
			this.y = 0;
	}
	
	/**
	 * This is Point class copy constructor
	 * @param other
	 */
	public Point(Point other) {
		this.x = other.getX();
		this.y = other.getY();
	}
	
	/**
	 * The following are class getters & setters
	 * 
	 */
	public double getX() {
		return x;
	}

	public boolean setX(double x) {
		if(x < MIN_X || x > MAX_X) {
				return false;
		}	
		else {
			this.x = x;
			return true;
		}

	}

	public double getY() {
		return y;
	}

	public boolean setY(double y) {
		if(y < MIN_Y || y > MAX_Y) {
			return false;
		}
		else {
			this.y = y;
			return true;
		}
	}
	
	/**
	 * This function checks if a point is before or after another point
	 * According to X value only
	 * @param finish
	 * @return true: if the point is past finish point. false: otherwise
	 */
	public boolean hasCrossed(Point finish) {
		return this.x >= finish.getX();
	}
	
	/**
	 * These functions are the overrides java Object class methods
	 */
	@Override
	public boolean equals(Object other) {
		if(other instanceof Point) {
			return this.x == ((Point)other).x && this.y == ((Point)other).y;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}