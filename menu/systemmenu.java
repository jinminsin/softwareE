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
	private Font font = new Font("�������", 1, 15);//
	private JLabel[] setChoice;
	private boolean game = true;

	public systemmenu(JFrame window, JPanel screen, round uround) {// �ý��۸޴� �г�
		setChoice = new JLabel[3];

		setChoice[0] = new JLabel("�ٽý��� ��");
		setChoice[1] = new JLabel("�Ͻ�����");
		setChoice[2] = new JLabel("��������");

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
							setChoice[0].setText("�ٽý��� ��");
							setChoice[1].setText("�Ͻ�����");
							setChoice[2].setText("��������");

						} else if (check == 1) {
							setChoice[0].setText("�ٽý���");
							setChoice[1].setText("�Ͻ����� ��");
							setChoice[2].setText("��������");

						} else {
							setChoice[0].setText("�ٽý���");
							setChoice[1].setText("�Ͻ�����");
							setChoice[2].setText("�������� ��");

						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					if (state) {
						check--;
						if (check < 0)
							check = 2;

						if (check == 0) {
							setChoice[0].setText("�ٽý��� ��");
							setChoice[1].setText("�Ͻ�����");
							setChoice[2].setText("��������");

						} else if (check == 1) {
							setChoice[0].setText("�ٽý���");
							setChoice[1].setText("�Ͻ����� ��");
							setChoice[2].setText("��������");

						} else {
							setChoice[0].setText("�ٽý���");
							setChoice[1].setText("�Ͻ�����");
							setChoice[2].setText("�������� ��");

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
			setChoice[i].setBounds(400, 0 + 15 * i, 300, 50);// �г���ġ����
			screen.add(setChoice[i]);// ��ũ���� ����
			setChoice[i].setVisible(state);
		}
		// setChoice -> systemmenu panel�� �ֱ�

	}

	public boolean get_game() {
		return game;
	}
}
