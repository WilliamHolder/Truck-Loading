
public class Box {

	// initialises variables
	private int width;
	private int height;
	private boolean placed = false;
	
	// given a width and height, creates a box
	public Box(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	//various getters
	public int getBoxWidth(){
		return width;
	}
	
	public int getBoxHeight(){
		return height;
	}
	
	//if called, box has been placed
	public void placed(){
		placed = true;
	}
	
	public boolean isPlaced(){
		return placed;
	}
	
	@Override
	public String toString(){
		return "Width: " + width + ", Height: " + height;
	}
}
