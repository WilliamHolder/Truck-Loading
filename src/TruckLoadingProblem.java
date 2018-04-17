import java.util.ArrayList;
import java.util.List;

public class TruckLoadingProblem {

	// Initialises some variables
	private int currentHeight = 0;
	private int currentWidth = 0;
	private List<Truck> trucks = new ArrayList<Truck>();
	private int currentTruckIndex = 0;
	private int nextFitTrucksUsed = 0;
	private int firstFitTrucksUsed = 0;

	// Next Fit algorithm
	public void nextFit(Box box) {
		Truck currentTruck = trucks.get(currentTruckIndex);

		// Checks validity of parameter
		if (box == null) {
			throw new IllegalArgumentException("Invalid Box");
		}

		// If the truck is empty, add a box
		if (currentTruck.getBoxes().isEmpty()) {
			currentTruck.addBox(box);
			currentHeight = box.getBoxHeight();
			currentWidth = box.getBoxWidth();
			nextFitTrucksUsed++;
		}

		// if number of boxes exceeds max boxes for truck, move to next truck, add to that
		else if ((currentTruck.getNoOfBoxes() + 1) > currentTruck.getMaxBoxes()) {
			currentTruckIndex++;
			nextFitTrucksUsed++;
			trucks.get(currentTruckIndex).addBox(box);
			currentHeight = box.getBoxHeight();
			currentWidth = box.getBoxWidth();
		}

		// if new box wider than below OR adding the new box will exceed truck height
		else if (box.getBoxWidth() > currentTruck.getCurrentStack().peek().getBoxWidth()
				|| currentHeight + box.getBoxHeight() > currentTruck.getTruckHeight()) {
			// if room for a new stack in truck, add one
			if (currentWidth + box.getBoxWidth() <= currentTruck.getTruckWidth()) {
				currentTruck.newStack();
				currentTruck.addBox(box);
				currentWidth += box.getBoxWidth();
				currentHeight = box.getBoxHeight();
			}
			// else, move on to the next truck
			else {
				currentTruckIndex++;
				nextFitTrucksUsed++;
				trucks.get(currentTruckIndex).addBox(box);
				currentHeight = box.getBoxHeight();
				currentWidth = box.getBoxWidth();
			}
		}
		// else, add the box to the truck
		else {
			currentTruck.addBox(box);
			currentHeight += box.getBoxHeight();
		}
	}

	// First Fit algorithm
	public void firstFit(Box box) {

		// checks validity of parameter
		if (box == null) {
			throw new IllegalArgumentException("Invalid Box");
		}

		// loops through the List of trucks
		for (int j = 0; j < trucks.size(); j++) {
			Truck currentTruck = trucks.get(j);
			currentWidth = 0;
			
			// loops through the List of Stacks inside each truck
			for (int i = 0; i < currentTruck.getBoxes().size(); i++) {
				// sets current width and height of truck
				currentWidth += currentTruck.getCurrentStackWidth();
				currentHeight = currentTruck.getHeightOfStack(currentTruck.getBoxes().get(i));
				
				// if box is smaller than one below AND height + current height
				// won't exceed truck AND under the limit of boxes AND isn't placed
				if ((box.getBoxWidth() <= currentTruck.getBoxes().get(i).peek().getBoxWidth())
						&& (currentHeight + box.getBoxHeight() <= currentTruck.getTruckHeight())
						&& (currentTruck.getNoOfBoxes() + 1 <= currentTruck.getMaxBoxes())
						&& (box.isPlaced() == false)) {
					
					// adds box to current stack
					currentTruck.setCurrentStack(i);
					currentTruck.addBox(box);
					box.placed();
				}

				// if currentWidth + box width doesn't exceed truck width AND
				// number of boxes doesn't exceed max boxes AND box isn't placed
				// AND adding it to the stack exceeds truck height
				else if ((currentWidth + box.getBoxWidth() <= currentTruck.getTruckWidth()) && box.isPlaced() == false
						&& currentTruck.getFinalStack() == currentTruck.getBoxes().get(i)
						&& (currentTruck.getNoOfBoxes() + 1 <= currentTruck.getMaxBoxes())) {
					
					// creates a new stack inside the truck, adds box to it
					currentTruck.newStack();
					currentTruck.addBox(box);
					box.placed();
					currentWidth += box.getBoxWidth();
				}
				currentTruckIndex = j;
			}

		}

		// else if box not placed, move to next truck and add it there
		if (box.isPlaced() == false) {
			currentTruckIndex++;
			trucks.get(currentTruckIndex).addBox(box);
			box.placed();
			firstFitTrucksUsed++;
		}
	}

	// gets trucks used for next fit
	public int nextFitTrucksUsed() {
		return nextFitTrucksUsed;
	}
	
	// gets trucks used for first fit
	public int firstFitTrucksUsed() {
		return firstFitTrucksUsed;
	}

	// populates the trucks array, given height,width,maxboxes and the number of trucks
	public void populateTrucks(int num, int height, int width, int max) {
		trucks.clear();
		currentTruckIndex = 0;
		for (int i = 0; i < num; i++) {
			trucks.add(new Truck(width, height, max));
		}
	}
}
