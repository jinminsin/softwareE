package character;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class hero {
	private ImageIcon icon;// 이미지
	private JLabel icongraphic;
	private JLabel[] HP_bar;
	private JLabel[] MP_bar;
	private JLabel[] ATTACK_;
	private JLabel[] ARMOR_;
	// 이미지, HP,MP 바

	private JLabel[] recovery_;

	private String name;// 이름
	private int MAX_HP;
	private int HP;
	private int MAX_MP;
	private int MP;
	private int attack;
	private int attack_range;
	private int defense;// 방어력
	private int recovery_time;// 부활시간
	private int recovery_mount;
	private int coin;// 재화
	private int score;// 점수

	private int x, y;// setter 필요 x
	private boolean life;

	private Font font = new Font("나눔고딕", Font.BOLD | Font.ITALIC, 15);

	public hero(JPanel screen, String name, int x, int y) {
		icon = new ImageIcon("hero.png");
		icongraphic = new JLabel(icon);
		this.name = name;
		this.x = x;
		this.y = y;
		icongraphic.setBounds(x, y, 30, 50);

		HP = 100;
		MAX_HP = HP;
		MP = 100;
		MAX_MP = MP;

		HP_bar = new JLabel[3];
		MP_bar = new JLabel[3];
		recovery_ = new JLabel[2];
		for (int i = 0; i < 3; i++) {
			HP_bar[i] = new JLabel();
			MP_bar[i] = new JLabel();
		}
		HP_bar[0].setBounds(50, 560, (int) ((float) HP / (float) MAX_HP * 100), 20);
		MP_bar[0].setBounds(50, 590, (int) ((float) MP / (float) MAX_MP * 100), 20);
		HP_bar[0].setBackground(Color.RED);
		MP_bar[0].setBackground(Color.BLUE);
		HP_bar[0].setOpaque(true);
		MP_bar[0].setOpaque(true);

		HP_bar[1].setText("HP");
		HP_bar[2].setText(Integer.toString(HP));
		HP_bar[1].setFont(font);
		HP_bar[2].setFont(font);
		HP_bar[1].setBounds(20, 560, 30, 20);
		HP_bar[2].setBounds(160, 560, 60, 20);

		MP_bar[1].setText("MP");
		MP_bar[2].setText(Integer.toString(MP));
		MP_bar[1].setFont(font);
		MP_bar[2].setFont(font);
		MP_bar[1].setBounds(20, 590, 30, 20);
		MP_bar[2].setBounds(160, 590, 60, 20);

		attack = 50;
		defense = 10;
		attack_range = 300;

		ATTACK_ = new JLabel[2];
		ARMOR_ = new JLabel[2];

		ATTACK_[0] = new JLabel("공격력 :");
		ATTACK_[1] = new JLabel(Integer.toString(attack));

		ARMOR_[0] = new JLabel("방어력 :");
		ARMOR_[1] = new JLabel(Integer.toString(defense));

		ATTACK_[0].setBounds(220, 560, 60, 20);
		ATTACK_[1].setBounds(282, 560, 100, 20);
		ATTACK_[0].setFont(font);
		ATTACK_[1].setFont(font);

		ARMOR_[0].setBounds(220, 590, 60, 20);
		ARMOR_[1].setBounds(282, 590, 100, 20);
		ARMOR_[0].setFont(font);
		ARMOR_[1].setFont(font);

		recovery_time = 6;

		recovery_[0] = new JLabel();
		recovery_[1] = new JLabel();
		recovery_[0].setFont(new Font("나눔고딕", Font.BOLD, 20));
		recovery_[1].setFont(new Font("나눔고딕", Font.BOLD, 30));
		recovery_[0].setBounds(10, 390, 100, 30);
		recovery_[1].setBounds(115, 380, 100, 50);

		coin = 0;
		score = 0;
		life = true;

		screen.add(icongraphic, 0);
		for (int i = 0; i < 3; i++) {
			screen.add(HP_bar[i], 0);
			screen.add(MP_bar[i], 0);
		}
		for (int i = 0; i < 2; i++) {
			screen.add(ATTACK_[i], 0);
			screen.add(ARMOR_[i], 0);
		}
		screen.add(recovery_[0]);
		screen.add(recovery_[1]);
	}

	public void damaged(int damage) {
		if (HP - damage <= 0) {
			set_HP(0);
			recovery();
			life = false;
		} else
			set_HP(HP - damage);
		HP_bar[2].setText(Integer.toString(HP));
		HP_bar[0].setSize((int) ((float) HP / (float) MAX_HP * 100), 20);

	}

	public void recovery() {
		icongraphic.setVisible(false);
		recovery_[0].setText("부활 중 :");
		recovery_[1].setText(Integer.toString(recovery_time));
	}

	public void recovery_timer() {
		recovery_time--;

		recovery_[1].setText(Integer.toString(recovery_time));

		if (recovery_time == 0) {
			icongraphic.setVisible(true);
			set_HP(MAX_HP);
			recovery_mount++;
			recovery_time = 5 * (recovery_mount + 1) + 1;
			HP_bar[2].setText(Integer.toString(HP));
			HP_bar[0].setSize((int) ((float) HP / (float) MAX_HP * 100), 20);
			recovery_[0].setText("");
			recovery_[1].setText("");
			life = true;
		}
	}

	public String get_name() {
		return name;
	}

	public boolean get_life() {
		return life;
	}

	public void set_HP(int HP) {
		this.HP = HP;
		HP_bar[2].setText(Integer.toString(HP));
		HP_bar[0].setSize((int) ((float) HP / (float) MAX_HP * 100), 20);
	}

	public void set_MP(int MP) {
		this.MP = MP;
		MP_bar[2].setText(Integer.toString(MP));
		MP_bar[0].setSize((int) ((float) MP / (float) MAX_MP * 100), 20);
	}

	public void set_attack(int attack) {
		this.attack = attack;
	}

	public void set_attack_range(int range) {
		this.attack_range = range;
	}

	public void set_defense(int defense) {
		this.defense = defense;
	}

	public void set_recovery_time(int time) {
		this.recovery_time = time;
	}

	public void set_coin(int coin) {
		this.coin = coin;
	}

	public void set_score(int score) {
		this.score = score;
	}

	public int get_HP() {
		return HP;
	}

	public int get_MP() {
		return MP;
	}

	public int get_attack() {
		return attack;
	}

	public int get_attack_range() {
		return attack_range;
	}

	public int get_defense() {
		return defense;
	}

	public int get_recovery_time() {
		return recovery_time;
	}

	public int get_coin() {
		return coin;
	}

	public int get_score() {
		return score;
	}

	public int get_X() {
		return x;
	}

	public int get_Y() {
		return y;
	}

}
