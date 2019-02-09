package cwiczenie5;

public class Main {

	public static void main(String args[]) throws Exception {
		
		/*
		Bureau bureau = initBureau();
		printBureau(bureau);
		saveBureau(bureau, "mi6.ser");
		Bureau loadedBureau = loadBureau("mi6.ser");
		printBureau(loadedBureau);
		*/
	}

	private static void saveBureau(Bureau bureau, String filename) throws Exception {
		// to be implemented
	}

	private static Bureau loadBureau(String filename) throws Exception {
		// to be implemented
		return null;
	}

	private static Bureau initBureau() {
		Bureau bureau = new Bureau("MI6");
		Agent agent007 = new Agent("James", "Bond", 35, "007");
		bureau.addAgent(agent007);

		Task task = new Task("darkMamba", "infiltrate, spy, destroy");
		agent007.addTask(task);

		return bureau;
	}
	
	private static void printBureau(Bureau bureau) {
		// to be implemented
	}

}
