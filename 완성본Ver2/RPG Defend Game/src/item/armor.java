package item;

public class armor {
	// ¹æ¾î±¸
	private int level;
	private int defense;
	private int cost;

	public armor() {
		level = 0;
		cost = 500;
		defense = 0;

	}

	public void levelup() {
		level++;
		defense += 10;
		cost = 500 + level * 500;
	}

	public void set_level(int level) {
		this.level = level;
	}

	public int get_level() {
		return level;
	}

	public void set_defense(int defense) {
		this.defense = defense;
	}

	public int get_defense() {
		return defense;
	}

	public void set_armor_cost(int cost) {
		this.cost = cost;
	}

	public int get_armor_cost() {
		return cost;
	}
}
