package character;

import java.awt.*;

import javax.swing.*;

public class gateway {
	private ImageIcon icon;
	private JLabel[] HP_bar;
	private JLabel icongraphic;
	// ÀÌ¹ÌÁö, HP¹Ù

	private int level;
	private int levelup_cost;
	private int HP;
	private int MAX_HP;
	private int defense;
	// °´Ã¼ ½ºÆå

	private int x, y;
	// °´Ã¼ À§Ä¡(Á¶Àý ÇÊ¿ä x)

	private Font font = new Font("³ª´®°íµñ", 3, 12);
	// HP¹Ù ÆùÆ®

	public gateway(JPanel screen, int x, int y) {
		icon = new ImageIcon("gateway.png");
		icongraphic = new JLabel(icon);
		icongraphic.setBounds(x, y, 30, 50);
		level = 1;
		HP = 2000;
		levelup_cost = 2000;
		MAX_HP = HP;
		HP_bar = new JLabel[2];

		for (int i = 0; i < 2; i++)
			HP_bar[i] = new JLabel();

		HP_bar[1].setText(Integer.toString(HP));
		HP_bar[0].setBounds(x - 5, y - 10, 40, 10);
		HP_bar[1].setBounds(x - 5, y - 10, 40, 10);
		HP_bar[1].setFont(font);
		HP_bar[0].setBackground(Color.RED);
		HP_bar[1].setForeground(Color.GREEN);
		HP_bar[0].setOpaque(true);
		HP_bar[1].setHorizontalAlignment(JLabel.CENTER);
		defense = 50;
		this.x = x;
		this.y = y;
		screen.add(icongraphic, 0);
		screen.add(HP_bar[0], 0);
		screen.add(HP_bar[1], 0);
	}

	public void levelup() {
		MAX_HP += 500;
		set_HP(HP + 500);
		HP_bar[1].setText(Integer.toString(HP));
		HP_bar[0].setSize((int) ((float) HP / (float) MAX_HP * 40), 10);
		set_defense(50 + 5 * level);
		level++;
	}

	public void repair(int healing) {
		if (HP + healing > MAX_HP)
			HP = MAX_HP;
		else
			HP += healing;
		
		HP_bar[1].setText(Integer.toString(HP));
		HP_bar[0].setSize((int) ((float) HP / (float) MAX_HP * 40), 10);
	}

	public int get_levelup_cost() {
		return levelup_cost;
	}

	public void damaged(int damage) {
		if (HP - damage < 0)
			set_HP(0);
		else
			set_HP(HP - damage);
		HP_bar[1].setText(Integer.toString(HP));
		HP_bar[0].setSize((int) ((float) HP / (float) MAX_HP * 40), 10);
	}

	public int get_MAX_HP() {
		return MAX_HP;
	}

	public void set_level(int level) {
		this.level = level;
	}

	public void set_HP(int HP) {
		this.HP = HP;
	}

	public void set_defense(int defense) {
		this.defense = defense;
	}

	public int get_level() {
		return level;
	}

	public int get_HP() {
		return HP;
	}

	public int get_defense() {
		return defense;
	}
}
