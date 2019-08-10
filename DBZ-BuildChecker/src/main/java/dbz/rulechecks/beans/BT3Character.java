package dbz.rulechecks.beans;

import java.util.List;

public class BT3Character {

	private String name;
	private List<Potara> build;

	/*
	 * by default this is 1, but it increments for each transformation. The only
	 * characters it really matters for are cell and frieza, because of healing
	 * restrictions 
	 * 
	 * So frieza would be this:
	 * 
	 * base form: 1
	 * 1st transformation: 2 
	 * 2nd transformation: 3 
	 * final form: 4
	 * final form 100% power: 5
	 * 
	 * Cell: 
	 * base form: 1
	 * imperfect Cell: 2 
	 * Perfect: 3
	 * Super Perfect: 4
	 * 
	 * This field only really matters when analyzing rule exceptions
	 * 	
	 */
	
	private int transformationState = 0;

	public BT3Character(){
		
	}
	
	public BT3Character(String name, int transformedState){
		this.name = name;
		transformationState = transformedState;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Potara> getBuild() {
		return build;
	}

	public void setBuild(List<Potara> potaras) {
		this.build = potaras;
	}

	public int getTransformationState() {
		return transformationState;
	}

	public void setTransformationState(int transformationState) {
		this.transformationState = transformationState;
	}

}
