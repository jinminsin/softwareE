package menu;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import central.*;
import java.awt.*;
import item.*;
import character.gateway;
import character.hero;
import character.tower;

public class selectmenu {
	private int state;
	private JLabel[][] setChoice;

	public selectmenu(JFrame window, JPanel screen, JLabel coin_screen, hero uhero, tower[] utower) {// 게임 판 생성

		state = 0;
		setChoice = new JLabel[6][2];
		setChoice[0][0] = new JLabel("1번타워");
		setChoice[1][0] = new JLabel("2번타워");
		setChoice[2][0] = new JLabel("3번타워");
		setChoice[3][0] = new JLabel("4번타워");
		setChoice[4][0] = new JLabel("5번타워");
		setChoice[5][0] = new JLabel("6번타워");

		for (int i = 0; i < 6; i++) {
			setChoice[i][1] = new JLabel(String.format("<html>업그레이드 비용 : %04d<br>&nbsp;&nbsp;&nbsp;하시겠습니까? : U</html>", utower[i].get_levelup_cost()));
			setChoice[i][0].setBounds(utower[i].get_X(), utower[i].get_Y() - 15, 50, 15);
			setChoice[i][1].setBounds(utower[i].get_X() - 40, utower[i].get_Y() - 50, 170, 35);
			screen.add(setChoice[i][0], 1);
			screen.add(setChoice[i][1], 1);
			setChoice[i][0].setVisible(false);
			setChoice[i][1].setVisible(false);
		}

		window.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_1) {

					if (state == 0) {
						state = 1;
						setChoice[0][0].setVisible(true);
						setChoice[0][1].setVisible(true);
					}

					else if (state == 1) {
						state = 0;
						setChoice[0][0].setVisible(false);
						setChoice[0][1].setVisible(false);
					} else {
						setChoice[0][0].setVisible(true);
						setChoice[0][1].setVisible(true);
						setChoice[state - 1][0].setVisible(false);
						setChoice[state - 1][1].setVisible(false);

						state = 1;
					}

				}
				if (e.getKeyCode() == KeyEvent.VK_2) {

					if (state == 0) {
						state = 2;
						setChoice[1][0].setVisible(true);
						setChoice[1][1].setVisible(true);
					} else if (state == 2) {
						state = 0;
						setChoice[1][0].setVisible(false);
						setChoice[1][1].setVisible(false);
					} else {
						setChoice[1][0].setVisible(true);
						setChoice[1][1].setVisible(true);
						setChoice[state - 1][0].setVisible(false);
						setChoice[state - 1][1].setVisible(false);
						state = 2;
						// state변경하기
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_3) {
					if (state == 0) {
						state = 3;
						setChoice[2][0].setVisible(true);
						setChoice[2][1].setVisible(true);
					} else if (state == 3) {
						state = 0;
						setChoice[2][0].setVisible(false);
						setChoice[2][1].setVisible(false);
					} else {
						setChoice[2][0].setVisible(true);
						setChoice[2][1].setVisible(true);
						setChoice[state - 1][0].setVisible(false);
						setChoice[state - 1][1].setVisible(false);
						state = 3;
						// state 변경하기
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_4) {
					if (state == 0) {
						state = 4;
						setChoice[3][0].setVisible(true);
						setChoice[3][1].setVisible(true);
					} else if (state == 4) {
						state = 0;
						setChoice[3][0].setVisible(false);
						setChoice[3][1].setVisible(false);
					} else {
						setChoice[3][0].setVisible(true);
						setChoice[3][1].setVisible(true);
						setChoice[state - 1][0].setVisible(false);
						setChoice[state - 1][1].setVisible(false);
						state = 4;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_5) {
					if (state == 0) {
						state = 5;
						setChoice[4][0].setVisible(true);
						setChoice[4][1].setVisible(true);
					} else if (state == 5) {
						state = 0;
						setChoice[4][0].setVisible(false);
						setChoice[4][1].setVisible(false);
					} else {
						setChoice[4][0].setVisible(true);
						setChoice[4][1].setVisible(true);
						setChoice[state - 1][0].setVisible(false);
						setChoice[state - 1][1].setVisible(false);
						state = 5;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_6) {
					if (state == 0) {
						state = 6;
						setChoice[5][0].setVisible(true);
						setChoice[5][1].setVisible(true);
					} else if (state == 6) {
						state = 0;
						setChoice[5][0].setVisible(false);
						setChoice[5][1].setVisible(false);
					} else {
						setChoice[5][0].setVisible(true);
						setChoice[5][1].setVisible(true);
						setChoice[state - 1][0].setVisible(false);
						setChoice[state - 1][1].setVisible(false);
						state = 6;
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_U) {
					if (state != 0) {
						synchronized (uhero) {
							if (utower[state - 1].get_levelup_cost() <= uhero.get_coin()) {
								uhero.set_coin(uhero.get_coin() - utower[state - 1].get_levelup_cost());
								utower[state - 1].levelup(state);
								coin_screen.setText(String.format("COIN     :  %09d", uhero.get_coin()));
							}
						}
					}
				}
			}
		});

	}

}
