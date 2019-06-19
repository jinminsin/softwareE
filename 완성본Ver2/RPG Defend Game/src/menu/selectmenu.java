package menu;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import central.*;
import java.awt.*;
import item.*;
import character.gateway;
import character.hero;
import character.tower;

public class selectmenu {
	private int state;
	private JPanel[] choice_;
	private JLabel[][] setChoice;

	public selectmenu(JFrame window, JPanel screen, JLabel coin_screen, hero uhero, tower[] utower) {// 게임 판 생성

		state = 0;
		choice_ = new JPanel[6];
		for (int i = 0; i < 6; i++) {
			choice_[i] = new JPanel(null);
			choice_[i].setBounds(utower[i].get_X() - 80, utower[i].get_Y() - 60, 170, 60);
		}
		setChoice = new JLabel[6][2];
		setChoice[0][0] = new JLabel("<1번타워>");
		setChoice[1][0] = new JLabel("<2번타워>");
		setChoice[2][0] = new JLabel("<3번타워>");
		setChoice[3][0] = new JLabel("<4번타워>");
		setChoice[4][0] = new JLabel("<5번타워>");
		setChoice[5][0] = new JLabel("<6번타워>");

		for (int i = 0; i < 6; i++) {
			setChoice[i][1] = new JLabel(String.format("<html>업그레이드 비용 : %03d<br>&nbsp;&nbsp;&nbsp;하시겠습니까? : U키</html>",
					utower[i].get_levelup_cost()));
			setChoice[i][0].setBounds(2, 2, 170, 20);
			setChoice[i][1].setBounds(2, 23, 170, 35);
			setChoice[i][0].setFont(new Font("나눔고딕",0,12));
			setChoice[i][1].setFont(new Font("나눔고딕",0,12));
			setChoice[i][0].setHorizontalAlignment(JLabel.CENTER);
			setChoice[i][1].setHorizontalAlignment(JLabel.CENTER);
			choice_[i].add(setChoice[i][0]);
			choice_[i].add(setChoice[i][1]);
			choice_[i].setOpaque(true);
			choice_[i].setBorder(new LineBorder(Color.BLACK));
			screen.add(choice_[i], 1);
			choice_[i].setVisible(false);
		}

		window.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_1) {

					if (state == 0) {
						state = 1;
						choice_[0].setVisible(true);
					}

					else if (state == 1) {
						state = 0;
						choice_[0].setVisible(false);
					} else {
						choice_[0].setVisible(true);
						choice_[state - 1].setVisible(false);

						state = 1;
					}

				}
				if (e.getKeyCode() == KeyEvent.VK_2) {

					if (state == 0) {
						state = 2;
						choice_[1].setVisible(true);
					} else if (state == 2) {
						state = 0;
						choice_[1].setVisible(false);
					} else {
						choice_[1].setVisible(true);
						choice_[state - 1].setVisible(false);
						state = 2;
						// state변경하기
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_3) {
					if (state == 0) {
						state = 3;
						choice_[2].setVisible(true);
					} else if (state == 3) {
						state = 0;
						choice_[2].setVisible(false);
					} else {
						choice_[2].setVisible(true);
						choice_[state - 1].setVisible(false);
						state = 3;
						// state변경하기
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_4) {
					if (state == 0) {
						state = 4;
						choice_[3].setVisible(true);
					} else if (state == 4) {
						state = 0;
						choice_[3].setVisible(false);
					} else {
						choice_[3].setVisible(true);
						choice_[state - 1].setVisible(false);
						state = 4;
						// state변경하기
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_5) {
					if (state == 0) {
						state = 5;
						choice_[4].setVisible(true);
					} else if (state == 5) {
						state = 0;
						choice_[4].setVisible(false);
					} else {
						choice_[4].setVisible(true);
						choice_[state - 1].setVisible(false);
						state = 5;
						// state변경하기
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_6) {
					if (state == 0) {
						state = 6;
						choice_[5].setVisible(true);
					} else if (state == 6) {
						state = 6;
						choice_[5].setVisible(false);
					} else {
						choice_[5].setVisible(true);
						choice_[state - 1].setVisible(false);
						state = 6;
						// state변경하기
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_U) {
					if (state != 0) {
						synchronized (uhero) {
							if (utower[state - 1].get_levelup_cost() <= uhero.get_coin()) {
								uhero.set_coin(uhero.get_coin() - utower[state - 1].get_levelup_cost());
								utower[state - 1].levelup(state);
								coin_screen.setText(String.format("COIN     :  %09d", uhero.get_coin()));
								setChoice[state - 1][1] = new JLabel(
										String.format("<html>업그레이드 비용 : %03d<br>&nbsp;&nbsp;&nbsp;하시겠습니까? : U키</html>",
												utower[state - 1].get_levelup_cost()));
								choice_[state - 1].setVisible(false);
								state = 0;
							}
						}
					}
				}
			}
		});

	}

}
