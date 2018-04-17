import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Truck {

	// initialises variables
	private int width;
	private int height;
	private int maxBoxes;
	private int noOfBoxes = 0;
	private int currentStackIndex = 0;
	private int currentStackWidth = 0;
	private List<Stack<Box>> boxes = new ArrayList<Stack<Box>>();
	
	// constructs a truck, given a width, height and max number of boxes
	public Truck(int width, int height, int maxBoxes){
		this.width = width;
		this.height = height;
		this.maxBoxes = maxBoxes;
	}
	
	// various getters and setters
	public int getTruckWidth(){
		return width;
	}
	
	public int getTruckHeight(){
		return height;
	}
	
	public int getMaxBoxes(){
		return maxBoxes;
	}
	
	public int getNoOfBoxes(){
		return noOfBoxes;
	}
	
	public Stack<Box> getCurrentStack(){
		return boxes.get(currentStackIndex);
	}
	
	public Stack<Box> getFinalStack(){
		return boxes.get(boxes.size() -1);
	}
	
	public int getCurrentStackWidth(){
		return currentStackWidth;
	}
	
	public void setCurrentStack(int stackIndex){
		currentStackIndex = stackIndex;
	}
	
	public List<Stack<Box>> getBoxes(){
		return boxes;
	}
	
	// adds a box to the current stack, creates stack if none created
	public void addBox(Box box){
		if(boxes.isEmpty()){
			Stack<Box> boxStack = new Stack<Box>();
			boxStack.push(box);
			boxes.add(boxStack);
			currentStackWidth = box.getBoxWidth();
		}
		else if(boxes.get(currentStackIndex).isEmpty()){
			boxes.get(currentStackIndex).push(box);
			currentStackWidth += box.getBoxWidth();
		}
		else{
			boxes.get(currentStackIndex).push(box);
		}
		noOfBoxes++;
	}
	
	// given a stack, gets the height of it 
	public int getHeightOfStack(Stack<Box> stack){
		int stackHeight = 0;
		for(Box b: stack){
			stackHeight += b.getBoxHeight();
		}
		return stackHeight;
	}
	
	// creates a new stack
	public void newStack(){
		boxes.add(new Stack<Box>());
		currentStackIndex = boxes.size() - 1;
	}
}
