package item;

import java.awt.GridLayout;

import javax.swing.*;

public class skill {
	// ��ų
	private JPanel bskill_;
	private JLabel[] skill_;

	private int attack;// ��ų Ÿ��
	private int level;
	private int cost;
	private int attack_coefficient;
	private int cost_coefficient;
	private int MP_cost;// MP�Ҹ�
	private int cool_time;// ���� ���� �ð�
	private int timer;

	public skill(JPanel screen, String name, String key, int attack, int cost, int MP_cost, int cool_time, int x, int y) {
		bskill_ = new JPanel(new GridLayout(3, 1));
		bskill_.setBounds(x, y, 70, 60);
		skill_ = new JLabel[3];
		level = 0;
		this.attack = 0;
		this.attack_coefficient = attack;
		this.cost_coefficient = cost;
		this.cost = cost;
		this.MP_cost = MP_cost;
		this.cool_time = cool_time;
		skill_[0] = new JLabel(name);
		skill_[1] = new JLabel(key);
		skill_[2] = new JLabel("��� ����");
		for(int i=0;i<3;i++) {
			skill_[i].setHorizontalAlignment(JLabel.CENTER);
			bskill_.add(skill_[i]);
		}
		
		screen.add(bskill_);
		bskill_.setVisible(false);
	}

	public void levelup() {
		level++;
		attack += attack_coefficient;
		cost += cost_coefficient;

		if (level == 1) {
			bskill_.setVisible(true);			
		}
	}
	
	public void cool_down()
	{
		timer--;
		
		if(timer == 0)
			skill_[2].setText("��� ����");
		else
			skill_[2].setText("��Ÿ�� : "+Integer.toString(timer));
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

	public void set_timer()
	{
		timer = cool_time;
	}
	
	public int get_timer()
	{
		return timer;
	}
}
