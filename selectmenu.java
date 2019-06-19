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

	private JLabel[] label = new JLabel[5];
	private int state;
	private JLabel[][] setCheck;
	private int ch_select;
	private tower utower;

	public selectmenu(JFrame window, JPanel screen, JLabel coin_screen, hero uhero, tower[] utower) {// 게임 판 생성

		state = 0;
		setCheck = new JLabel[6][2];
		setCheck[0][0] = new JLabel("1번타워");
		setCheck[1][0] = new JLabel("2번타워");
		setCheck[2][0] = new JLabel("3번타워");
		setCheck[3][0] = new JLabel("4번타워");
		setCheck[4][0] = new JLabel("5번타워");
		setCheck[5][0] = new JLabel("6번타워");

		for (int i = 0; i < 6; i++) {
			setCheck[i][1] = new JLabel(String.format("<html>업그레이드 비용 : %04d<br>&nbsp;&nbsp;&nbsp;하시겠습니까? : U</html>", utower[i].get_levelup_cost()));
			setCheck[i][0].setBounds(utower[i].get_X(), utower[i].get_Y() - 15, 50, 15);
			setCheck[i][1].setBounds(utower[i].get_X() - 40, utower[i].get_Y() - 50, 170, 35);
			screen.add(setCheck[i][0], 0);
			screen.add(setCheck[i][1], 0);
			setCheck[i][0].setVisible(false);
			setCheck[i][1].setVisible(false);
		}

		window.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_1) {

					if (state == 0) {
						state = 1;
						setCheck[0][0].setVisible(true);
						setCheck[0][1].setVisible(true);
					}

					else if (state == 1) {
						state = 0;
						setCheck[0][0].setVisible(false);
						setCheck[0][1].setVisible(false);
					} else {
						setCheck[0][0].setVisible(true);
						setCheck[0][1].setVisible(true);
						setCheck[state - 1][0].setVisible(false);
						setCheck[state - 1][1].setVisible(false);

						state = 1;
					}

				}
				if (e.getKeyCode() == KeyEvent.VK_2) {

					if (state == 0) {
						state = 2;
						setCheck[1][0].setVisible(true);
						setCheck[1][1].setVisible(true);
					} else if (state == 2) {
						state = 0;
						setCheck[1][0].setVisible(false);
						setCheck[1][1].setVisible(false);
					} else {
						setCheck[1][0].setVisible(true);
						setCheck[1][1].setVisible(true);
						setCheck[state - 1][0].setVisible(false);
						setCheck[state - 1][1].setVisible(false);
						state = 2;
						// state변경하기
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_3) {
					if (state == 0) {
						state = 3;
						setCheck[2][0].setVisible(true);
						setCheck[2][1].setVisible(true);
					} else if (state == 3) {
						state = 0;
						setCheck[2][0].setVisible(false);
						setCheck[2][1].setVisible(false);
					} else {
						setCheck[2][0].setVisible(true);
						setCheck[2][1].setVisible(true);
						setCheck[state - 1][0].setVisible(false);
						setCheck[state - 1][1].setVisible(false);
						state = 3;
						// state 변경하기
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_4) {
					if (state == 0) {
						state = 4;
						setCheck[3][0].setVisible(true);
						setCheck[3][1].setVisible(true);
					} else if (state == 4) {
						state = 0;
						setCheck[3][0].setVisible(false);
						setCheck[3][1].setVisible(false);
					} else {
						setCheck[3][0].setVisible(true);
						setCheck[3][1].setVisible(true);
						setCheck[state - 1][0].setVisible(false);
						setCheck[state - 1][1].setVisible(false);
						state = 4;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_5) {
					if (state == 0) {
						state = 5;
						setCheck[4][0].setVisible(true);
						setCheck[4][1].setVisible(true);
					} else if (state == 5) {
						state = 0;
						setCheck[4][0].setVisible(false);
						setCheck[4][1].setVisible(false);
					} else {
						setCheck[4][0].setVisible(true);
						setCheck[4][1].setVisible(true);
						setCheck[state - 1][0].setVisible(false);
						setCheck[state - 1][1].setVisible(false);
						state = 5;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_6) {
					if (state == 0) {
						state = 6;
						setCheck[5][0].setVisible(true);
						setCheck[5][1].setVisible(true);
					} else if (state == 6) {
						state = 0;
						setCheck[5][0].setVisible(false);
						setCheck[5][1].setVisible(false);
					} else {
						setCheck[5][0].setVisible(true);
						setCheck[5][1].setVisible(true);
						setCheck[state - 1][0].setVisible(false);
						setCheck[state - 1][1].setVisible(false);
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

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

}
