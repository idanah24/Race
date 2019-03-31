package utilities.state;

import java.sql.Timestamp;

import game.racers.Racer;

public class Broken implements IState {
	@Override
	public void action(Racer racer) {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		float timeFromStart = currentTime.getTime() - racer.getArena().getTime().getTime();
		timeFromStart *=0.001;//Change from miliseconds to seconds
		
		if(!racer.getArena().getBrokenRacers().contains((Racer)racer))
			racer.getArena().getBrokenRacers().add(((Racer)racer));
		racer.getArena().getActiveRacers().remove(((Racer)racer));
		
		System.out.println(racer.getName() + " was broken " + timeFromStart + " seconds from the start");
	}

}
