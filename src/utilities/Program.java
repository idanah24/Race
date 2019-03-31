package utilities;
import java.util.Scanner;
import utilities.Fate;
import factory.CarRaceBuilder;
import game.racers.air.*;
import game.racers.decorator.ColoredRacer;
import game.racers.decorator.WheeledRacer;
import game.racers.land.*;
import game.racers.naval.*;
import utilities.EnumContainer.Color;
import game.arenas.Arena;
import game.arenas.air.AerialArena;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.arenas.land.LandArena;
import game.arenas.naval.NavalArena;
import game.racers.Racer;
public class Program {
	
	private static Scanner input = new Scanner(System.in);
	private static Arena activeArena = null;
	
	public static void main(String [] args) {
		int option;
		Fate.setSeed(305437774);
		System.out.println("Welcome to race simulator!");
		do {
			printMenu();
			option = input.nextInt();
			
			switch(option) {
			case 1: createArena();
			break;
			
			case 2: addRacer();
			break;
			
			case 3: designRacer(null);
			break;
			
			case 4: cloneRacer();
			break;
			
			case 5: createCarRace();
			break;
			
			case 6: startRace();
			break;
			
			}
		}while(option != 7);
		System.out.println("GoodBye!");
	}
	
	public static void printMenu() {
		System.out.println("Choose option from menu: ");
		System.out.println("1. Create Arena");
		System.out.println("2. Add Racer");
		System.out.println("3. Design Racer");
		System.out.println("4. Clone Racer");
		System.out.println("5. Create Car Race");
		System.out.println("6. Start Race");
		System.out.println("7. Exit");
	}
	
	public static void createArena() {
		System.out.println("Choose arena type:");
		System.out.println("1. Aerial Arena");
		System.out.println("2. Land Arena");
		System.out.println("3. Naval Arena");
		System.out.println("Click 0 to go back to menu");
		
		int n = input.nextInt();
		
		switch(n) {
		case 1: activeArena = new AerialArena();
				System.out.println("Aerial Arena created");
				return;
		
		case 2: activeArena = new LandArena();
				System.out.println("Land Arena created");
				return;
		
		case 3: activeArena = new NavalArena();
				System.out.println("Naval Arena created");
				return;
		
		case 0:	return;

		}
	}
	
	public static void addRacer() {
		if(activeArena == null) {
			System.out.println("Create Arena first!");
			return;
		}
		Racer newRacer = null;
		System.out.println("Choose racer type:");
		System.out.println("1. Airplane");
		System.out.println("2. Helicopter");
		System.out.println("3. Car");
		System.out.println("4. Bicycle");
		System.out.println("5. Horse");
		System.out.println("6. SpeedBoat");
		System.out.println("7. RowBoat");
		System.out.println("Click 0 to go back to menu");
		int option = input.nextInt();

		switch(option) {
		case 1: newRacer = new Airplane();
		break;
		case 2: newRacer = new Helicopter();
		break;
		case 3: newRacer = new Car();
		break;
		case 4: newRacer = new Bicycle();
		break;
		case 5: newRacer = new Horse();
		break;
		case 6: newRacer = new SpeedBoat();
		break;
		case 7: newRacer = new RowBoat();
		break;
		case 0: return;
		
		}
			try {
				activeArena.addRacer(newRacer);
			} catch (RacerTypeException | RacerLimitException e) {
				e.printStackTrace();
				System.out.println("Racer was not added");
				return;
			}
			System.out.println("Racer added to arena");
	}
	
	public static void startRace() {
		if(activeArena == null) {
			System.out.println("Create Arena first!");
			return;
		}
		if(activeArena.getActiveRacers().size() == 0) {
			System.out.println("Add at least one racer first");
			return;
		}
		
		activeArena.initRace();
		try {
			activeArena.startRace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Race finished! results:");
		activeArena.showResults();
		activeArena = null;
	}
	
	public static Racer chooseRacer() {
		if(activeArena == null) {
			System.out.println("Create Arena first!");
			return null;
		}
		if(activeArena.getActiveRacers().size() == 0) {
			System.out.println("No racers in arena");
			return null;
		}		
		System.out.println("Choose racer by number: (Click 0 to go back)");
		
		for(int i = 0; i<activeArena.getActiveRacers().size(); i++)
			System.out.println((i+1) + ". " + activeArena.getActiveRacers().get(i).getName());
		
		int option;
		
		do {
			option = input.nextInt();
			if(option == 0)
				return null;
			if(((option - 1) < 0) || ((option - 1) > (activeArena.getActiveRacers().size()) - 1) )
				System.out.println("Invalid input! Try again.");
		}while(((option - 1) < 0) || ((option - 1) > (activeArena.getActiveRacers().size()) - 1));
		
		return activeArena.getActiveRacers().get(option - 1);
		
		
	}
	
	public static void designRacer(Racer clonedRacer) {
		Racer choice;
		if(clonedRacer != null)
			choice = clonedRacer;
		else {
			choice = chooseRacer();
			if(choice == null)
				return;
		}
		
		System.out.println("You are now designing the racer: " + choice.getName());
		
		System.out.println("Choose design options: (Click 0 to go back)");
		System.out.println("1. Add wheels decoration");
		System.out.println("2. Add color decoration");
		System.out.println("3. Add customized attribute");

		int option = input.nextInt();
		
		switch(option) {
		case 1:	addWheels(choice);
		break;
		case 2: addColor(choice);
		break;
		case 3: addCustomize(choice);
		break;
		case 0: System.out.println("No changes in attribute were made");
		return;
		}
		System.out.println("Racer edited!");
	}
	
	public static void addWheels(Racer racer) {
		System.out.println("Enter number of wheels:");
		int numOfWheels;
		do {
			numOfWheels = input.nextInt();
			if(numOfWheels <= 0)
				System.out.println("Invalid number of wheels! Try again.");
		}while(numOfWheels <= 0);
		new WheeledRacer(racer, numOfWheels);
	}
	
	public static void addColor(Racer racer) {
		System.out.println("Choose color:");
		System.out.println("1. Black");
		System.out.println("2. Blue");
		System.out.println("3. Green");
		System.out.println("4. Red");
		System.out.println("5. Yellow");
		
		Color color = Color.BLACK;
		int choice;
		do {
			choice = input.nextInt();
			if(choice <= 0 || choice >= 6)
				System.out.println("Invalid input! Try again.");
		}while(choice <= 0 || choice >= 6);
		
		switch(choice) {
		case 1: color = Color.BLACK;
		break;
		case 2: color = Color.BLUE;
		break;
		case 3: color = Color.GREEN;
		break;
		case 4: color = Color.RED;
		break;
		case 5: color = Color.YELLOW;
		break;
		}
		new ColoredRacer(racer, color);
	}
	
	public static void addCustomize(Racer racer) {
		System.out.println("Enter attribute name and value:");
		String key = input.next();
		Object value = input.next();
		racer.addAttribute(key, value);		
	}
	
	public static void cloneRacer() {
		if(activeArena != null && (activeArena.getActiveRacers().size() + 1 > activeArena.getMAX_RACERS())) {
			System.out.println("Arena is full!");
			return;
		}
		Racer source = chooseRacer();
		if(source == null)
			return;
			
		Racer clone = source.clone();
		designRacer(clone);
		try {
			activeArena.addRacer(clone);
		} catch (RacerTypeException | RacerLimitException e) {
			e.printStackTrace();
		}
	}
	
	public static void createCarRace() {
		System.out.println("Enter number of cars:");
		int numOfCars;
		do {
			numOfCars = input.nextInt();
			if(numOfCars <= 0)
				System.out.println("Invalid input! Try again.");
		}while(numOfCars <= 0);
		CarRaceBuilder builder = new CarRaceBuilder(numOfCars);
		activeArena = builder.getRace();
		System.out.println("Car race created and ready!");
	}
	
}