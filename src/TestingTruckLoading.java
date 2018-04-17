import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestingTruckLoading {

	
	public static void main(String args[]){
		// creates a truck problem and a list of boxes
		TruckLoadingProblem t = new TruckLoadingProblem();
		List<Box> boxList = new ArrayList<Box>();
		
		Random rn = new Random();
		
		int boxNumber = 100;
		
		// given a box number, adds that many boxes of random height/widths
		for(int i = 0; i < boxNumber; i++){
			boxList.add(new Box(rn.nextInt(100), rn.nextInt(100)));
		}
		
		// populates a list of trucks of number boxNumber
		t.populateTrucks(boxNumber, 100, 100, 5);
		
		// gets the time
		long nf1 = System.nanoTime();
		
		for(int j = 0; j < boxList.size(); j++){
			t.nextFit(boxList.get(j));
		}
		
		long nf2 = System.nanoTime();
		
		// prints trucks used and time taken
		System.out.println("NextFit Trucks used: " + t.nextFitTrucksUsed());
		System.out.println("NextFit Time: " + (nf2 - nf1));
		
		// populates a list of trucks of number boxNumber
		t.populateTrucks(boxNumber, 100, 100, 5);
		
		// gets the time
		long ff1 = System.nanoTime();
		
		for(int i = 0; i < boxList.size(); i++){
			t.firstFit(boxList.get(i));
		}
		
		long ff2 = System.nanoTime();
		
		// prints trucks used and time taken
		System.out.println("\nFirstFit Trucks used: " + t.firstFitTrucksUsed());
		System.out.println("FirstFit Time: " + (ff2 - ff1));
	}
}
