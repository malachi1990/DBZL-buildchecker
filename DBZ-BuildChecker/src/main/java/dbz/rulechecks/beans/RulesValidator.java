package dbz.rulechecks.beans;

import java.util.ArrayList;
import java.util.List;

public class RulesValidator {

	private List<String> bannedAIs; 
	private List<String> restrictedCharacters; 
	private List<Potara> bannedPotaras; 
	private List<String> powerBodyRestrictions;
	private List<BT3Character> lifeDrainer;
	
	public RulesValidator(){
		setupRules();
	}
	
	
	private void setupRules(){
		if(bannedAIs == null || bannedAIs.isEmpty()){
			bannedAIs = new ArrayList<>();
			bannedAIs.add("Recoome");
			bannedAIs.add("Yajirobe");
			bannedAIs.add("Ginyu");
			bannedAIs.add("Chiaotzu");
			bannedAIs.add("Recoome");
			
			//list of restricted characters
			restrictedCharacters = new ArrayList<>();
			restrictedCharacters.add("Cooler");
			restrictedCharacters.add("Meta-Cooler");
			restrictedCharacters.add("Garlic Jr.");
			restrictedCharacters.add("Arale");
			restrictedCharacters.add("Grandpa Gohan");
			restrictedCharacters.add("Cell");
			restrictedCharacters.add("Android 13");
			restrictedCharacters.add("Yajirobe");
			restrictedCharacters.add("Majin-Buu");
			restrictedCharacters.add("Kid Buu");
			restrictedCharacters.add("Goten");
			restrictedCharacters.add("Frieza");
			restrictedCharacters.add("Supreme Kai");

			
			bannedPotaras = new ArrayList<>();
			//checking eternal life in its own method, because several character have special logic with respect to it.
			bannedPotaras.add(new Potara(4, "Eternal Life"));
			bannedPotaras.add(new Potara(1, "Spiritual Control"));
			bannedPotaras.add(new Potara(1, "Dragon Spirit"));
			bannedPotaras.add(new Potara(3, "Secret Measures"));
			bannedPotaras.add(new Potara(2, "Kibito's Secret Art"));
			bannedPotaras.add(new Potara(1, "Dragon Heart"));
			
			powerBodyRestrictions = new ArrayList<>();
			powerBodyRestrictions.add("Dr Gero");
			powerBodyRestrictions.add("Babidi");
			powerBodyRestrictions.add("Android 19.");
			powerBodyRestrictions.add("Arale");
			powerBodyRestrictions.add("Cell");
			powerBodyRestrictions.add("Kid Buu");

			lifeDrainer = new ArrayList<>();
			lifeDrainer.add(new BT3Character("Dr. Gero", 0));
		}
		
	}
	
	public boolean validate(String teamName, List<Character> team) {
		boolean legal = true;
		
		
		return legal;

	}

	public boolean checkPotaraPoints(List<Potara> potaras) {
		boolean legal = true;
		int points = 0;

		for (Potara build : potaras) {
			points += build.getPoints();
		}
		if (points > 7) {
			legal = false;
		}
		return legal;
	}

	/**
	 * checks most of the healing restrictions. for characters with additional
	 * restrictions, such as Cell or Android 13, the checks for eternal life are
	 * in their team specific checks.
	 * 
	 * @param character
	 * @return
	 */

	public boolean checkHealingRestrictions(BT3Character character) {
		boolean legal = true;
		boolean isRestrictedCharacter = true;
		
		/*
		 * Order of ops: 
		 * 
		 * 1. Check if the character is a character who is restricted.
		 * 2. If so, check the build for restricted potaras/builds
		 */
		
		for(String name : restrictedCharacters){
			if(character.getName().equalsIgnoreCase(name)){
				isRestrictedCharacter = true;
			}
		}
		if(isRestrictedCharacter){
			for (Potara build : character.getBuild()) {
					//check against the restricted potaras
					for(Potara banned : bannedPotaras)
						if (build.getName().equalsIgnoreCase(banned.getName())) {
							legal = false;
							break;
					}
					//check against the restricted AIs
					for(String banned : bannedAIs)
						if (build.getName().equalsIgnoreCase(banned)) {
							legal = false;
							break;
					}
			}
			//handling special cases here
			if(character.getName().equalsIgnoreCase("Cell")){
				legal = checkTransformingCharacters(character);
			}
		}

		return legal;
	}

	public boolean checkPowerBody(BT3Character character){
		boolean legal = true;
		for(String name : powerBodyRestrictions){
			if(character.getName().equalsIgnoreCase(name)){
				legal = false;
				break;
			}
		}
		
		return legal;
	}
	
	private boolean checkTransformingCharacters(BT3Character transformer){
		boolean legal = true;
		boolean hasEternalLife = false;
		boolean hasBrolysRing = false;
		
		for(Potara build : transformer.getBuild()){
			if (build.getName().equalsIgnoreCase("Eternal Life")) {
				hasEternalLife = true;
			}
			if (build.getName().equalsIgnoreCase("Broly's Ring")) {
				hasBrolysRing = true;
			}
		}
		
		if(transformer.getTransformationState() == 3){
			if (hasEternalLife) {
				legal = true;
			}
		//perfect cell needs broly's ring to legally have eternal life
		} else if(transformer.getTransformationState() == 2){
			if(hasEternalLife && hasBrolysRing){
				legal = true;
			} else{
				legal = false;
			}
			//ie if Cell is in an imperfect form
		} else{
			if(hasEternalLife){
				legal = false;
			}
		}
		return legal;
		
	}
	
	
	//team specific checks
	public boolean checkAndroids(List<BT3Character> team){
		boolean legal = true;
		
		for(BT3Character player : team){
			if(player.getName().equalsIgnoreCase("Cell")){
				legal = checkTransformingCharacters(player);
			}
		}
		
		return legal;
	}
	
	public boolean checkMuscle(List<BT3Character> team){
		boolean legal = true;
		for(BT3Character player : team){
			if(player.getName().equalsIgnoreCase("Android 13")){
				legal = false;
			}
		}
		return legal;
	}
	
}
