package central;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class windows extends JFrame {
	// ������(800*600)
	private Font font = new Font("�������", 1, 30);// ��Ʈ

	private static game_screen game_;
	private static Thread start_game;
	private static boolean start = true;
	private static int choice;// ������
	private static String name;

	public windows(JFrame window, JPanel game) {// ���� �� ����
		window.setTitle("RPG Defense Game");
		window.setSize(800, 650);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);

		choice = 0;

		JLabel[] setChoice = new JLabel[2];// ������
		setChoice[0] = new JLabel("���ӽ��� ��");
		setChoice[1] = new JLabel("��������");
		game.setLayout(null);
		for (int i = 0; i < setChoice.length; i++) {
			setChoice[i].setFont(font);
			setChoice[i].setBounds(280, 300 + 100 * i, 200, 50);
			game.add(setChoice[i]);
		}
		window.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (choice == 0) {
						window.removeKeyListener(this);
						game.removeAll();
						game.repaint();
						selectLevel(window, game);
					} else if (choice == 1) {
					} else {
						System.exit(1);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					choice++;
					if (choice > 1)
						choice = 0;

					if (choice == 0) {
						setChoice[0].setText("���ӽ��� ��");
						setChoice[1].setText("��������");
					} else {
						setChoice[0].setText("���ӽ���");
						setChoice[1].setText("�������� ��");
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					choice--;
					if (choice < 0)
						choice = 1;

					if (choice == 0) {
						setChoice[0].setText("���ӽ��� ��");
						setChoice[1].setText("��������");
					} else {
						setChoice[0].setText("���ӽ���");
						setChoice[1].setText("�������� ��");
					}
				}
			}

			public void keyReleased(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {

			}
		});

		window.add(game);
		window.setVisible(true);
	}

	void selectLevel(JFrame window, JPanel game) {// ���̵� ����
		JLabel[] setChoice = new JLabel[4];// ������ ��ü�� ������ 4���� �����
		setChoice[0] = new JLabel("Level EASY ��");
		setChoice[1] = new JLabel("Level NORMAL");
		setChoice[2] = new JLabel("Level HARD");
		setChoice[3] = new JLabel("Level KOREAN");
		for (int i = 0; i < setChoice.length; i++) {
			setChoice[i].setFont(font);
			setChoice[i].setBounds(250, 100 + 100 * i, 300, 50);
			game.add(setChoice[i]);
		}

		window.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					window.removeKeyListener(this);
					game.removeAll();
					game.repaint();
					ready(window, game);
					return;
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					choice++;
					if (choice > 3)
						choice = 0;
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					choice--;
					if (choice < 0)
						choice = 3;
				}

				if (choice == 0) {
					setChoice[0].setText("Level EASY ��");
					setChoice[1].setText("Level NORMAL");
					setChoice[2].setText("Level HARD");
					setChoice[3].setText("Level KOREAN");
				} else if (choice == 1) {
					setChoice[0].setText("Level EASY");
					setChoice[1].setText("Level NORMAL ��");
					setChoice[2].setText("Level HARD");
					setChoice[3].setText("Level KOREAN");
				} else if (choice == 2) {
					setChoice[0].setText("Level EASY");
					setChoice[1].setText("Level NORMAL");
					setChoice[2].setText("Level HARD ��");
					setChoice[3].setText("Level KOREAN");
				} else {
					setChoice[0].setText("Level EASY");
					setChoice[1].setText("Level NORMAL");
					setChoice[2].setText("Level HARD");
					setChoice[3].setText("Level KOREAN ��");
				}
			}
		});
	}

	public void ready(JFrame window, JPanel game)// �غ�(ĳ���͸� ����)
	{
		JLabel command = new JLabel("�������� �̸��� �����ΰ���?");
		JLabel error_command = new JLabel();
		JTextField input_name = new JTextField();// �̸� ����
		font = new Font("�������", 1, 15);
		command.setFont(font);
		command.setBounds(300, 200, 300, 50);
		game.add(command);
		input_name.setBounds(300, 250, 200, 30);
		input_name.setFont(font);
		game.add(input_name);
		error_command.setFont(font);
		error_command.setBounds(300, 300, 400, 50);
		error_command.setForeground(Color.RED);
		game.add(error_command);
		input_name.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (input_name.getText().length() > 15) {
					error_command.setText("������! �̸��� �ִ� 15�ڱ����� �����ϴ�ϴ�!");
					try {
						input_name.setText(input_name.getText(0, 15));
					} catch (Exception k) {
					}
				}

				if (input_name.getText().length() <= 14) {
					error_command.setText("");
				}

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					input_name.removeKeyListener(this);
					game.removeAll();
					game.repaint();
					window.remove(game);
					window.repaint();
					name = input_name.getText();
					game_ = new game_screen(window, name, choice);
					start_game = new Thread(game_);
					start_game.start();
				}
			}
		});
	}

	public static void main(String args[]) {
		JFrame window = new JFrame();// ��ü ���� ��
		JPanel game = new JPanel();// �����ϴ� â

		new windows(window, game);

		while (start_game == null)
			;
		
		try {
			while (true) {
				start_game.join();
				if (game_.get_restart() == true) {
					window.remove(game_.get_screen()); 
					window.repaint();
					game_ = new game_screen(window, name, choice);
					start_game = new Thread(game_);
					start_game.start();
				} else
					break;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}