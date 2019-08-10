package dbz.rulechecks.beans;

@FunctionalInterface
public interface Rule {
	public boolean validate(BT3Character character);
}
