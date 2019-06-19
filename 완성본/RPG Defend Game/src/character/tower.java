package character;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import javafx.scene.layout.Border;

public class tower {
	private ImageIcon icon;// 이미지
	private JLabel icongraphic;
	private JLabel Level_bar;

	private int level;
	private int levelup_cost;// 레벨업 필요재화
	private int attack;
	private int attack_range;

	private int x, y;// setter 필요x
	private Font font = new Font("나눔고딕", 0, 12);

	public tower(JPanel screen, int number, int x, int y) {
		icon = new ImageIcon("tower.png");
		icongraphic = new JLabel(icon);
		icongraphic.setBounds(x, y, 30, 50);
		Level_bar = new JLabel(String.format("<html>[%d]번 타워<br>설치 필요</html>", number));
		Level_bar.setBounds(x - 70, y, 60, 40);
		Level_bar.setFont(font);
		Level_bar.setHorizontalAlignment(JLabel.CENTER);
		Level_bar.setBackground(Color.WHITE);
		Level_bar.setBorder(new LineBorder(Color.BLACK));
		Level_bar.setOpaque(true);
		icongraphic.setVisible(false);

		level = 0;
		levelup_cost = 300;
		attack = 0;
		attack_range = 0;

		this.x = x;
		this.y = y;

		screen.add(icongraphic, 1);
		screen.add(Level_bar, 1);
	}

	public void levelup(int number) {
		level++;
		levelup_cost = 300 + level * 500;
		attack = level * 30;
		attack_range = 300;
		Level_bar.setText(String.format("<html>[%d]번 타워<br>LV : %02d</html>", number, level));

		if (level == 1)
			icongraphic.setVisible(true);
	}

	public int targeting(ArrayList<monster> umonster) {
		int target = 0;
		int range = 0;
		for (int i = 0; i < umonster.size(); i++) {
			range = 0;
			range += (x-umonster.get(i).get_X()) * (x-umonster.get(i).get_X());
			range += (y-umonster.get(i).get_Y()) * (y-umonster.get(i).get_Y());

			if (range < attack_range * attack_range)// 범위에 들어감.
			{
				if (target != i) {
					if (umonster.get(target).get_type() == 'm' && umonster.get(i).get_type() == 'n') {// 일반 -> 보스 우선
						target = i;
					} else if (umonster.get(target).get_type() == 'm' && umonster.get(i).get_type() == 'n') {// 일반 ->
																												// 네임드
																												// 우선
						target = i;
					} else if (umonster.get(target).get_type() == 'n' && umonster.get(i).get_type() == 'b') {// 네임드 ->
																												// 보스 우선
						target = i;
					}
				}
			}
		}

		if (target == 0)// 타겟이 0일 때, 이것이 정상적으로 맞는 타겟인가?
		{
			range = 0;
			range += (x-umonster.get(target).get_X()) * (x-umonster.get(target).get_X());
			range += (y-umonster.get(target).get_Y()) * (y-umonster.get(target).get_Y());
			
			if (range < attack_range * attack_range)// 범위에 들어감.
			{
				return target;
			}
			else
			{
				return -1;
			}
		}

		return target;
	}

	public void set_level(int level) {
		this.level = level;
	}

	public void set_levelup_cost(int cost) {
		this.levelup_cost = cost;
	}

	public void set_attack(int attack) {
		this.attack = attack;
	}

	public void set_attack_range(int range) {
		this.attack_range = range;
	}

	public int get_level() {
		return level;
	}

	public int get_levelup_cost() {
		return levelup_cost;
	}

	public int get_attack() {
		return attack;
	}

	public int get_attack_range() {
		return attack_range;
	}

	public int get_X() {
		return x;

	}

	public int get_Y() {
		return y;
	}
}
