package dbz.rulechecks.beans;

public class Potara {
	private int points;
	private String name;
	private PotaraType type; //type of potara -- blue, yellow, AI
	
	public Potara(){
		
	}
	
	public Potara(final int points, final String name){
		this.points = points;
		this.name = name;
	}
	
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
