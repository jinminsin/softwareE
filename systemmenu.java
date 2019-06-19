package menu;

import java.awt.event.*;
import javax.swing.*;

import central.game_screen;
import central.round;
import character.monster;

import java.awt.*;

public class systemmenu extends JPanel {
	private boolean state = false;
	private int check;
	private Font font = new Font("나눔고딕", 1, 15);//
	private JLabel[] setChoice;
	private boolean game = true;

	public systemmenu(JFrame window, JPanel screen, round uround) {// 시스템메뉴 패널
		setChoice = new JLabel[3];

		setChoice[0] = new JLabel("다시시작 ◀");
		setChoice[1] = new JLabel("일시정지");
		setChoice[2] = new JLabel("게임종료");

		window.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					state = !state;
					for (int i = 0; i < 3; i++)
						setChoice[i].setVisible(state);
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (state) {
						check++;
						if (check > 2)
							check = 0;

						if (check == 0) {
							setChoice[0].setText("다시시작 ◀");
							setChoice[1].setText("일시정지");
							setChoice[2].setText("게임종료");

						} else if (check == 1) {
							setChoice[0].setText("다시시작");
							setChoice[1].setText("일시정지 ◀");
							setChoice[2].setText("게임종료");

						} else {
							setChoice[0].setText("다시시작");
							setChoice[1].setText("일시정지");
							setChoice[2].setText("게임종료 ◀");

						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					if (state) {
						check--;
						if (check < 0)
							check = 2;

						if (check == 0) {
							setChoice[0].setText("다시시작 ◀");
							setChoice[1].setText("일시정지");
							setChoice[2].setText("게임종료");

						} else if (check == 1) {
							setChoice[0].setText("다시시작");
							setChoice[1].setText("일시정지 ◀");
							setChoice[2].setText("게임종료");

						} else {
							setChoice[0].setText("다시시작");
							setChoice[1].setText("일시정지");
							setChoice[2].setText("게임종료 ◀");

						}
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (state) {
						if (check == 0) {
							uround.set_round(0);
						}
						if (check == 1) {
							if (game)
								game = false;
							else
								game = true;
						}
						if (check == 2) {
							System.exit(1);

						}
					}
				}
			}

		});

		for (int i = 0; i < setChoice.length; i++) {
			setChoice[i].setFont(font);
			setChoice[i].setBounds(400, 0 + 15 * i, 300, 50);// 패널위치정함
			screen.add(setChoice[i]);// 스크린에 붙임
			setChoice[i].setVisible(state);
		}
		// setChoice -> systemmenu panel에 넣기

	}

	public boolean get_game() {
		return game;
	}
}
