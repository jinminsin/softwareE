package character;

import central.game_screen.shop;
import java.awt.*;
import javax.swing.*;

public class monster {
	private ImageIcon icon;
	private JLabel[] HP_bar;
	private JLabel icongraphic;
	// ÀÌ¹ÌÁö, HP¹Ù

	private char type;
	private int MAX_HP;
	private int HP;
	private int drop_coin;
	private int attack;
	private int defense;

	private int x, y;
	private int levelup_coefficient;

	private Font font = new Font("³ª´®°íµñ", Font.BOLD, 12);

	public monster(JPanel screen, float buff, char type, int round, int x, int y) {
		set_type(type);
		set_coefficient(round);
		if (type == 'b') {
			icon = new ImageIcon("boss.png");
			icongraphic = new JLabel(icon);
			set_HP((int) (round * levelup_coefficient * 4 * buff));
			attack = round * 10;
			defense = round * 10;
		}
		if (type == 'n') {
			icon = new ImageIcon("named.png");
			icongraphic = new JLabel(icon);
			set_HP((int) (round * levelup_coefficient * 2 * buff));
			attack = round * 5;
			defense = round * 5;
		}
		if (type == 'm') {
			icon = new ImageIcon("monster.png");
			icongraphic = new JLabel(icon);
			set_HP((int) (round * levelup_coefficient * buff));
			attack = round * 2;
			defense = round * 2;
		}
		MAX_HP = HP;

		icongraphic.setBounds(x, y, 30, 50);
		HP_bar = new JLabel[2];
		HP_bar[0] = new JLabel();
		HP_bar[1] = new JLabel(Integer.toString(HP));
		HP_bar[0].setBounds(x, y - 10, (int) ((float) MAX_HP / (float) HP * 30), 10);
		HP_bar[1].setBounds(x, y - 10, 30, 10);
		HP_bar[1].setFont(font);
		HP_bar[0].setBackground(Color.RED);
		HP_bar[1].setForeground(Color.GREEN);
		HP_bar[0].setOpaque(true);
		HP_bar[1].setHorizontalAlignment(JLabel.CENTER);

		set_drop_coin(this.HP);
		this.x = x;
		this.y = y;

		screen.add(icongraphic, 2);
		screen.add(HP_bar[0], 2);
		screen.add(HP_bar[1], 2);
	}

	public void move(boolean turn, gateway ugateway, hero uhero, shop ushop) {
		if (x < uhero.get_X() + 50) {
			if (turn) {
				targeting(ugateway, uhero, ushop);
			}
		} else {
			set_X(x - 8);
			icongraphic.setLocation(x, y);
			HP_bar[0].setLocation(x, y - 10);
			HP_bar[1].setLocation(x, y - 10);
		}
	}

	public void targeting(gateway ugateway, hero uhero, shop ushop) {
		if (uhero.get_HP() == 0)
			attack_gateway(ugateway);
		else
			attack_hero(uhero, ushop);
	}

	public void attack_hero(hero uhero, shop ushop) {
		uhero.damaged((uhero.get_defense() + ushop.get_armor().get_defense() >= attack) ? 1
				: attack - (uhero.get_defense() + ushop.get_armor().get_defense()));
	}

	public void attack_gateway(gateway ugateway) {
		ugateway.damaged((ugateway.get_defense() >= attack) ? 1 : attack - ugateway.get_defense());

	}

	public void damaged(JPanel screen, int damage) {
		if (HP - damage <= 0) {
			set_HP(0);
			remove(screen);
		} else
			set_HP(HP - damage);

		HP_bar[1].setText(Integer.toString(HP));
		HP_bar[0].setSize((int) ((float) HP / (float) MAX_HP * 30), 10);
	}

	public void remove(JPanel screen) {
		screen.remove(icongraphic);
		screen.remove(HP_bar[0]);
		screen.remove(HP_bar[1]);
		screen.repaint();
	}

	public void set_coefficient(int round) {
		levelup_coefficient = 50 + round;
	}

	public void set_type(char type) {
		this.type = type;
	}

	public void set_HP(int HP) {
		this.HP = HP;
	}

	public void set_drop_coin(int drop_coin) {
		this.drop_coin = drop_coin;
	}

	public void set_attack(int attack) {
		this.attack = attack;
	}

	public void set_defense(int defense) {
		this.defense = defense;
	}

	public void set_X(int x) {
		this.x = x;
	}

	public void set_Y(int y) {
		this.y = y;
	}

	public char get_type() {
		return type;
	}

	public int get_HP() {
		return HP;
	}

	public int get_drop_coin() {
		return drop_coin;
	}

	public int get_attack() {
		return attack;
	}

	public int get_defense() {
		return defense;
	}

	public int get_X() {
		return x;
	}

	public int get_Y() {
		return y;
	}
}
