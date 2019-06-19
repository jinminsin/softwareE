package item;

import javax.swing.*;

public class skill {
	// 스킬
	private JLabel[] skill_;

	private int attack;// 스킬 타입
	private int level;
	private int cost;
	private int coefficient;
	private int MP_cost;// MP소모량
	private int cool_time;// 재사용 가능 시간
	private int timer;

	public skill(String name, String key, int attack, int cost, int MP_cost, int cool_time) {
		skill_ = new JLabel[3];
		level = 0;
		this.attack = 0;
		this.coefficient = attack;
		this.cost = cost;
		this.MP_cost = MP_cost;
		this.cool_time = cool_time;
		skill_[0] = new JLabel(name);
		skill_[1] = new JLabel(key);
		skill_[2] = new JLabel("쿨타임 : " + Integer.toString(cool_time));
	}

	public void levelup() {
		level++;
		attack += coefficient;
		cost += cost;
	}

	public int get_cost() {
		return cost;
	}

	public void set_level(int level) {
		this.level = level;
	}

	public void set_MP_cost(int MP_cost) {
		this.MP_cost = MP_cost;
	}

	public int get_level() {
		return level;
	}

	public int get_MP_cost() {
		return MP_cost;
	}

	public int get_attack() {
		return attack;
	}

}
