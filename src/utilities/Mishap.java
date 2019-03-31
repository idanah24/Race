package utilities;
/**
 * This is the Mishap class
 * this class represents a malfunction in a racer
 * @author Idan Aharon, Itay Fridman
 * 			 305437774 305360653
 * @see Fate, Racer
 */
public class Mishap {
	
	/**
	 * These are the class data members
	 */
	private boolean fixable;
	private double reductionFactor;
	private int turnsToFix;
	
	/**
	 * This is the class constructor
	 * @param fixable
	 * @param turnsToFix
	 * @param reductionFactor
	 */
	Mishap(boolean fixable, int turnsToFix, double reductionFactor){
		this.setFixable(fixable);
		this.setTurnsToFix(turnsToFix);
		this.setReductionFactor(reductionFactor);
	}
	
	/**
	 * This method reduces turnsToFix if a mishap is fixable
	 */
	public void nextTurn() {
		if(this.isFixable())
			this.setTurnsToFix(turnsToFix-1);
	}
	
	/**
	 * This method returns a string describing the mishap
	 */
	@Override
	public String toString() {
		return "(" + this.fixable + ", " + this.turnsToFix + ", " + String.format("%.2f" ,reductionFactor) + ")";
	}
	/**
	 * The following are the class getters & setters
	 */
	public boolean isFixable() {
		return fixable;
	}

	public boolean setFixable(boolean fixable) {
		this.fixable = fixable;
		return true;
	}

	public double getReductionFactor() {
		return reductionFactor;
	}

	public boolean setReductionFactor(double reductionFactor) {
		this.reductionFactor = reductionFactor;
		return true;
	}

	public int getTurnsToFix() {
		return turnsToFix;
	}

	public boolean setTurnsToFix(int turnsToFix) {
		if(turnsToFix < 0)
			return false;
		this.turnsToFix = turnsToFix;
		return true;
	}
}