package utilities;

import java.util.ArrayList;
import java.util.Collections;

import game.racers.Racer;

public class DaemonThread extends Thread {
	private ArrayList<Racer> racers;
	public DaemonThread(ArrayList<Racer> racers) {
		super();
		this.setDaemon(true);
		this.racers = racers;
	}
	
	public void run() {
		Collections.sort(racers);
	}
}
