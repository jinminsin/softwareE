package menu;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import central.round;
import java.awt.*;

public class systemmenu extends JPanel {
	private JPanel choice_;
	private boolean state = false;
	private int check;
	private Font font = new Font("�������", 1, 15);
	private JLabel[] setChoice;
	private boolean game = true;

	public systemmenu(JFrame window, JPanel screen, round uround) {// �ý��۸޴� �г�
		choice_ = new JPanel(new GridLayout(3, 1, 2, 0));
		choice_.setBounds(200, 150, 400, 300);
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
						choice_.setVisible(state);
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
			setChoice[i].setHorizontalAlignment(JLabel.CENTER);
			choice_.add(setChoice[i]);
		}
		choice_.setOpaque(true);
		choice_.setBorder(new LineBorder(Color.BLACK));
		screen.add(choice_, 0);// ��ũ���� ����
		choice_.setVisible(state);
		
		// setChoice -> systemmenu panel�� �ֱ�

	}

	public boolean get_game() {
		return game;
	}
}
