package item;

public class weapon {
	// ¹«±â
	private int level;
	private int cost;
	private int attack;

	public weapon() {
		level = 0;
		cost = 500;
		attack = 0;
	}

	public void levelup() {
		level++;
		attack += 50;
		cost = 500 + level * 500;
	}

	public int get_level() {
		return level;
	}

	public void set_attack(int attack) {
		this.attack = attack;
	}

	public int get_attack() {
		return attack;
	}

	public void set_weapon_cost(int cost) {
		this.cost = cost;
	}

	public int get_weapon_cost() {
		return cost;
	}
}
