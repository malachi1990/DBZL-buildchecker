package dbz.rulechecks.beans;

public enum RestrictedPotaras {
	ATTACK_PLUS_2(1, "Attack + 2/Defense down 1"), 
	SUPER_PLUS_2(1, "Super+2/Ki down 1"), 
	KI_PLUS_2(1, "Ki+2/Super down 1"),
	DEFENSE_PLUS_2(1, "Defense+2/Attack down 1"),
	DEFENSE_PLUS_3(2, "Defense+3/Attack down 2"),
	STYLE_OF_THE_STRONG(4, "Style of the Strong"),
	ETERNAL_LIFE(4, "Eternal Life");
	
	
	private Potara restricted;
	
	private RestrictedPotaras(int points, String name){
		restricted = new Potara (points, name);
	}
	
	
	public Potara getRestrictedPotara(){
		return restricted;
	}
}
