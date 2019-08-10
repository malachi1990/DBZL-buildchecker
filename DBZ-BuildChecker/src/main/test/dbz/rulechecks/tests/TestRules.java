package dbz.rulechecks.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import dbz.rulechecks.beans.BT3Character;
import dbz.rulechecks.beans.Potara;
import dbz.rulechecks.beans.RulesValidator;

public class TestRules {

	private static List<Potara> healingBuild;
	private static List<Potara> powerBodyBuild;
	private static RulesValidator rules;
	
	@BeforeClass
	public static void setup(){
		healingBuild = new ArrayList<>();
		healingBuild.add(new Potara(4, "Eternal Life"));
		healingBuild.add(new Potara(1, "Quick fast Attack"));
		healingBuild.add(new Potara(1, "Attack + 1"));
		healingBuild.add(new Potara(1, "savior"));
		
		powerBodyBuild = new ArrayList<>();
		powerBodyBuild.add(new Potara(4, "Power Body"));
		powerBodyBuild.add(new Potara(1, "Quick fast Attack"));
		powerBodyBuild.add(new Potara(1, "Attack + 1"));
		powerBodyBuild.add(new Potara(1, "savior"));
		rules = new RulesValidator();
	}
	
	
	
	@Test
	public void testPointLimit(){
		assertTrue(rules.checkPotaraPoints(healingBuild));
	}
	
	@Test
	public void testeternalLifeRule(){
		BT3Character cooler = new BT3Character("Cooler", 0);
		cooler.setBuild(healingBuild);
		assertFalse(rules.checkHealingRestrictions(cooler));
	}
	
	@Test
	public void testPowerBodyRule(){
		BT3Character arale = new BT3Character("Arale", 0);
		arale.setBuild(powerBodyBuild);
		assertFalse(rules.checkPowerBody(arale));
	}
	
	@Test
	public void testSuperPerfectCell(){
		BT3Character cell = new BT3Character("Cell", 3);
		cell.setBuild(healingBuild);
		assertTrue(rules.checkHealingRestrictions(cell));
	
	}
	
	@Test
	public void testPerfectCell(){
		BT3Character cell = new BT3Character("Cell", 2);
		List<Potara> healBuild = new ArrayList<>();
		healBuild.add(new Potara(4, "Eternal Life"));
		healBuild.add(new Potara(1, "Quick fast Attack"));
		healBuild.add(new Potara(1, "Attack + 1"));
		healBuild.add(new Potara(1, "Broly's Ring"));
		cell.setBuild(healBuild);
		assertTrue(rules.checkHealingRestrictions(cell));
	
	}
	
	@Test
	public void testPerfectCell2(){
		BT3Character cell = new BT3Character("Cell", 2);
		cell.setBuild(healingBuild);
		assertFalse(rules.checkHealingRestrictions(cell));
	
	}
	
}
