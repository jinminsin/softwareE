package central;

import java.awt.*;

import javax.swing.*;

public class round {
	// 전체 라운드 변화 시스템
	private JLabel[] level_screen;
	private JLabel[] round_screen;
	private JLabel[] time_screen;
	private JLabel[] total_time_screen;
	private Font font = new Font("나눔고딕", Font.BOLD, 16);

	private int level;// 난이도
	private float buff_coefficient;// 버프계수
	private int round;// 라운드
	private int clear;
	private int time;
	private int total_time;

	public round(JPanel screen, int choice) {
		level_screen = new JLabel[2];
		round_screen = new JLabel[2];
		time_screen = new JLabel[2];
		total_time_screen = new JLabel[2];
		level_screen[0] = new JLabel("LEVEL");
		level_screen[0].setBounds(600, 20, 100, 20);
		level_screen[0].setFont(font);
		level_screen[1] = new JLabel();
		level_screen[1].setBounds(700, 20, 100, 20);
		level_screen[1].setFont(font);
		round_screen[0] = new JLabel("ROUND");
		round_screen[0].setBounds(600, 40, 100, 20);
		round_screen[0].setFont(font);
		round_screen[1] = new JLabel();
		round_screen[1].setBounds(700, 40, 100, 20);
		round_screen[1].setFont(font);
		time_screen[0] = new JLabel("TIME");
		time_screen[0].setBounds(600, 60, 100, 30);
		time_screen[0].setFont(font);
		time_screen[1] = new JLabel();
		time_screen[1].setBounds(700, 60, 100, 30);
		time_screen[1].setFont(font);
		total_time_screen[0] = new JLabel("TOTAL PLAY TIME :");
		total_time_screen[0].setBounds(10, 20, 160, 30);
		total_time_screen[0].setFont(font);
		total_time_screen[1] = new JLabel();
		total_time_screen[1].setBounds(172, 20, 200, 30);
		total_time_screen[1].setFont(font);
		set_level(choice);
		set_time(60);
		set_round(1);
		total_time(0);
		for (int i = 0; i < 2; i++) {
			screen.add(level_screen[i]);
			screen.add(round_screen[i]);
			screen.add(time_screen[i]);
			screen.add(total_time_screen[i]);
		}
	}

	public void apply_buff() {
		if (level == 0) {
			level_screen[1].setText(": EASY");
			set_buff_coefficient(0.7F);
			clear = 20;
		} else if (level == 1) {
			level_screen[1].setText(": NORMAL");
			set_buff_coefficient(1.0F);
			clear = 50;
		} else if (level == 2) {
			level_screen[1].setText(": HARD");
			set_buff_coefficient(1.5F);
			clear = 70;
		} else {
			level_screen[1].setText(": KOREAN");
			set_buff_coefficient(2.0F);
			clear = 100;
		}

	}

	public void round_clear() {
		set_round(round + 1);
		set_time(60);
	}

	public void set_level(int level) {
		this.level = level;
		apply_buff();
	}

	public int get_level() {
		return level;
	}

	public void set_buff_coefficient(float buff_coefficient) {
		this.buff_coefficient = buff_coefficient;
	}

	public float get_buff_coefficient() {
		return buff_coefficient;
	}

	public void set_round(int round) {
		this.round = round;
		round_screen[1].setText(String.format(": %03d", round));
	}

	public int get_round() {
		return round;
	}

	public void set_time(int time) {
		this.time = time;
		time_screen[1].setText(String.format(": %02d:%02d", time / 60, time % 60));
	}

	public int get_time() {
		return time;
	}
	
	public int get_total_time()
	{
		return total_time;
	}
	
	public void total_time(int time)
	{
		total_time+=time;
		int hour = total_time/60/60;
		int minute = (total_time-hour*60*60)/60;
		int second = (total_time-hour*60*60-minute*60);
		total_time_screen[1].setText(String.format("%02d:%02d:%02d", hour, minute, second));
	}
	
	public int get_clear()
	{
		return clear;
	}
	
}
