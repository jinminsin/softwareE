package central;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import character.*;
import item.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class game_screen implements Runnable {
	// ���� ȭ��(���� �̵�, ���� ����)
	private JLabel notice;
	private JLabel skip_notice;
	private ArrayList<monster> umonster;// ����
	private hero uhero;// ����
	private tower[] utower;// Ÿ��
	private gateway ugateway;// ����
	private round uround;
	private JPanel screen;
	private JLabel[][] map;
	private ImageIcon[][] mapicon;
	private JLabel coin_screen;
	private JLabel score_screen;

	private boolean restart = false;

	private Thread summon, attack;
	private ArrayList<Thread> summon_ = new ArrayList<Thread>();
	private shop ushop;
	private short_key ukey;
	private menu.selectmenu sel_menu;
	private menu.systemmenu sys_menu;

	public game_screen(JFrame window, String name, int choice)// ���� ���ӵ�
	{
		screen = new JPanel(null);// ������

		notice = new JLabel();
		notice.setBounds(50, 40, 700, 50);
		notice.setFont(new Font("�������", Font.ITALIC, 20));// ����
		notice.setForeground(Color.RED);
		notice.setText(name + "��, �������� �븮�� ��Ÿ���� ���͸� ó���� �ּ���!");

		skip_notice = new JLabel();
		skip_notice.setBounds(10, 90, 150, 60);
		skip_notice.setFont(new Font("�������", Font.BOLD | Font.ITALIC, 20));
		skip_notice.setForeground(Color.RED);

		map = new JLabel[16][11];// ��
		mapicon = new ImageIcon[16][11];// �� �̹���

		umonster = new ArrayList<monster>();// ����
		utower = new tower[6];// Ÿ��
		uhero = new hero(screen, name, 105, 210);// ����
		ugateway = new gateway(screen, 55, 210);// ����Ʈ����
		uround = new round(screen, choice);// ���� ����
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 0)
					utower[i * 3 + j] = new tower(screen, i * 3 + j + 1, 320 + 200 * j, 130);
				else
					utower[i * 3 + j] = new tower(screen, i * 3 + j + 1, 200 + 200 * j, 290);
			}
		}

		coin_screen = new JLabel(String.format("COIN     :  %09d", uhero.get_coin()));
		coin_screen.setBounds(20, 520, 220, 30);
		coin_screen.setFont(new Font("�������", Font.BOLD | Font.ITALIC, 20));
		score_screen = new JLabel(String.format("SCORE  :  %09d", uhero.get_score()));
		score_screen.setBounds(20, 490, 220, 30);
		score_screen.setFont(new Font("�������", Font.BOLD | Font.ITALIC, 20));

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (j < 5 || j > 5) {
					if (i == 0) {
						mapicon[i][j] = new ImageIcon("wall.png");
						map[i][j] = new JLabel(mapicon[i][j]);
					} else if ((j == 1 || j == 9) && (i == 4 || i == 8 || i == 12)) {
						mapicon[i][j] = new ImageIcon("tower_street.png");
						map[i][j] = new JLabel(mapicon[i][j]);
					} else {
						mapicon[i][j] = new ImageIcon("earth.png");
						map[i][j] = new JLabel(mapicon[i][j]);
					}
				} else {
					if (i != 0) {
						mapicon[i][j] = new ImageIcon("street.png");
						map[i][j] = new JLabel(mapicon[i][j]);
					} else {
						mapicon[i][j] = new ImageIcon("blank.png");
						map[i][j] = new JLabel(mapicon[i][j]);
					}
				}

				map[i][j].setBounds(15 * (8 - j) + 50 * i, 150 + 20 * j, 50, 20);
				screen.add(map[i][j]);
			}
		}
		screen.add(notice);
		screen.add(coin_screen);
		screen.add(score_screen);
		screen.add(skip_notice);
		screen.setBackground(Color.WHITE);
		window.add(screen);
		ushop = new shop();
		sys_menu = new menu.systemmenu(window, screen, uround);
		sel_menu = new menu.selectmenu(window, screen, coin_screen, uhero, utower);
		ukey = new short_key(window);
		summon = (new Thread(new summon_monster()));
		summon_.add(summon);
		attack = (new Thread(new attack_monster()));
		window.setVisible(true);
		window.requestFocus();
	}

	public void run()// ����
	{// ���� : ���� �̵�
		int time = 0;
		summon.start();
		attack.start();
		while (uround.get_round() != 0 && ugateway.get_HP() != 0 && uround.get_round() < uround.get_clear()) {
			while (sys_menu.get_game() == false) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			time++;
			synchronized (umonster) {
				for (int i = 0; i < umonster.size(); i++) {
					if (time == 5)
						umonster.get(i).move(true, ugateway, uhero, ushop);
					else
						umonster.get(i).move(false, ugateway, uhero, ushop);
				}
			}
			if (time == 5) {
				uround.set_time(uround.get_time() - 1);// 1�ʸ��� Ÿ�̸� ����
				uround.total_time(1);

				for (int i = 0; i < summon_.size(); i++) {
					if (!summon_.get(i).isAlive()) {
						summon_.remove(i);
						i--;
					}
				}

				if (uhero.get_HP() != 100 && uhero.get_HP() != 0)
					uhero.set_HP(uhero.get_HP() + 1);
				if (uhero.get_MP() != 100)
					uhero.set_MP(uhero.get_MP() + 1);

				if (ushop.lightning.get_level() > 0 && ushop.lightning.get_timer() != 0) {
					ushop.lightning.cool_down();
					if (ushop.lightning.get_timer() <= 5)
						notice.setText("");

				}

				if (ushop.metheo.get_level() > 0 && ushop.metheo.get_timer() != 0) {
					ushop.metheo.cool_down();
					if (ushop.metheo.get_timer() <= 25)
						notice.setText("");
				}

				if (ushop.repair.get_level() > 0 && ushop.repair.get_timer() != 0) {
					ushop.repair.cool_down();
					if (ushop.repair.get_timer() <= 25)
						notice.setText("");
				}

				if (uhero.get_life() == false)
					uhero.recovery_timer();

				if (uround.get_total_time() == 10)
					notice.setText("");

				if (uround.get_time() == 30 && uround.get_round() + 1 <= uround.get_clear())
					skip_notice.setText("<html>sŰ��<br>&nbsp;&nbsp;&nbsp;&nbsp;��ŵ ����!</html>");

				if (uround.get_time() == 20 && uround.get_round() + 1 <= uround.get_clear())
					skip_notice.setText("");

				if (uround.get_time() <= 0 && uround.get_round() + 1 <= uround.get_clear()) {
					uround.round_clear();
					summon = (new Thread(new summon_monster()));
					summon_.add(summon);
					summon.start();
				}
				time = 0;
			}

			try {
				Thread.sleep(200);// ���ϸ��̼��� ���� �̵� ����ȭ
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		try {
			attack.join();
			for (int i = 0; i < summon_.size(); i++)
				summon_.get(i).join();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (uround.get_round() == 0) {
			restart = true;
			screen.removeAll();
			screen.repaint();
			return;
		}

		if (uround.get_clear() < uround.get_round()) {
			screen.removeAll();
			screen.repaint();
			notice.setFont(new Font("�������", 3, 30));
			notice.setBounds(50, 200, 700, 200);
			notice.setText("YOU VICTORY!");
			screen.add(notice);
		}
		if (ugateway.get_HP() == 0) {
			screen.removeAll();
			screen.repaint();
			notice.setFont(new Font("�������", 3, 30));
			notice.setBounds(50, 200, 700, 200);
			notice.setText("YOU LOSE!");
			screen.add(notice);
		}
	}

	// Ŭ���� ����
	// ������ Ÿ�� �ð��� �°� ������ ����
	public class summon_monster implements Runnable {
		private int count;
		// ���� ��ȯ

		public summon_monster() {
			count = 1;
		}

		public void run() {
			while (count != 21 && uround.get_round() != 0 && ugateway.get_HP() != 0
					&& uround.get_round() < uround.get_clear()) {
				while (sys_menu.get_game() == false) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				synchronized (umonster) {
					if (count % 20 == 0)
						umonster.add(
								new monster(screen, uround.get_buff_coefficient(), 'b', uround.get_round(), 805, 210));
					else if (count % 10 == 0)
						umonster.add(
								new monster(screen, uround.get_buff_coefficient(), 'n', uround.get_round(), 805, 210));
					else
						umonster.add(
								new monster(screen, uround.get_buff_coefficient(), 'm', uround.get_round(), 805, 210));
				}
				count++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public class attack_monster implements Runnable {
		private int target;

		// ���� : ���� -> ����
		public void run()// ������ ����
		{
			while (uround.get_round() != 0 && ugateway.get_HP() != 0 && uround.get_round() < uround.get_clear()) {
				// 1�ʸ��� ���� ����
				// ���� -> �� ���� ���� ����
				// Ÿ�� -> �����Ÿ� �� ���� �켱���� ���� ���� ����
				while (sys_menu.get_game() == false) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				synchronized (umonster) {
					if (!umonster.isEmpty()) {
						if (uhero.get_life()
								&& uhero.get_X() + uhero.get_attack_range() + 30 > umonster.get(0).get_X()) {
							umonster.get(0).damaged(screen,
									(uhero.get_attack() + ushop.uweapon.get_attack() <= umonster.get(0).get_defense())
											? 1
											: (uhero.get_attack() + ushop.uweapon.get_attack()
													- umonster.get(0).get_defense()));

							if (umonster.get(0).get_HP() == 0) {
								synchronized (uhero) {
									uhero.set_coin(uhero.get_coin() + umonster.get(0).get_drop_coin());
									uhero.set_score(uhero.get_score() + umonster.get(0).get_drop_coin());
									coin_screen.setText(String.format("COIN     :  %09d", uhero.get_coin()));
									score_screen.setText(String.format("SCORE  :  %09d", uhero.get_score()));
									umonster.remove(0);
								}
							}
						}
					}

					for (int i = 0; i < 6; i++) {
						if (utower[i].get_level() > 0) {// ����
							if (!umonster.isEmpty()) {
								if ((target = utower[i].targeting(umonster)) != -1) {

									umonster.get(target).damaged(screen,
											(utower[i].get_attack() <= umonster.get(target).get_defense()) ? 1
													: utower[i].get_attack() - umonster.get(target).get_defense());

									if (umonster.get(target).get_HP() == 0) {
										synchronized (uhero) {
											uhero.set_coin(uhero.get_coin() + umonster.get(target).get_drop_coin());
											uhero.set_score(uhero.get_score() + umonster.get(target).get_drop_coin());
											coin_screen.setText(String.format("COIN     :  %09d", uhero.get_coin()));
											score_screen.setText(String.format("SCORE  :  %09d", uhero.get_score()));
											umonster.remove(target);
										}
									}
								}
							}
						}
					}
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public class short_key {
		// ���� : ����Ű �۵�(��ų, ����)
		// ��ų 1, ���� ����, ��ų 2, ��ü ����(�� ���� ����) -> ��ų �� �ߵ� ����, ���� ����
		// ��ų 3, ������ ��ų -> HP�� 0 �̻��̶�� �ߵ�
		// �нú� ��ų, �� �нú� ���� ��ġ ���� -> ���� �� �ǰݽ� ����
		public short_key(JFrame window) {
			synchronized (uhero) {
				window.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						if (e.getKeyCode() == KeyEvent.VK_S) {// skip
							if (uround.get_time() <= 30 && uround.get_round() + 1 <= uround.get_clear()) {
								skip_notice.setText("");
								uround.round_clear();
								summon = (new Thread(new summon_monster()));
								summon_.add(summon);
								summon.start();
							}

						}
						if (e.getKeyCode() == KeyEvent.VK_Q) {
							if (uhero.get_coin() >= ushop.uweapon.get_weapon_cost()) {
								uhero.set_coin(uhero.get_coin() - ushop.uweapon.get_weapon_cost());
								ushop.uweapon.levelup();
								coin_screen.setText(String.format("COIN     :  %09d", uhero.get_coin()));
								ushop.level[0].setText(String.format("Lv : %02d, ��� : %02d", ushop.uweapon.get_level(),
										ushop.uweapon.get_weapon_cost()));
								ushop.etc[0].setText(String.format("�߰� ���ݷ� + %02d", ushop.uweapon.get_attack()));
							}
						}
						if (e.getKeyCode() == KeyEvent.VK_W) {
							if (uhero.get_coin() >= ushop.uarmor.get_armor_cost()) {
								uhero.set_coin(uhero.get_coin() - ushop.uarmor.get_armor_cost());
								ushop.uarmor.levelup();
								coin_screen.setText(String.format("COIN     :  %09d", uhero.get_coin()));
								ushop.level[1].setText(String.format("Lv : %02d, ��� : %d", ushop.uarmor.get_level(),
										ushop.uarmor.get_armor_cost()));
								ushop.etc[1].setText(String.format("�߰� ���� + %02d", ushop.uarmor.get_defense()));
							}
						}

						if (e.getKeyCode() == KeyEvent.VK_E) {
							if (uhero.get_coin() >= ugateway.get_levelup_cost()) {
								uhero.set_coin(uhero.get_coin() - ugateway.get_levelup_cost());
								ugateway.levelup();
								coin_screen.setText(String.format("COIN     :  %09d", uhero.get_coin()));
								ushop.level[2].setText(String.format("Lv : %02d, ��� : %d", ugateway.get_level(),
										ugateway.get_levelup_cost()));
								ushop.etc[2].setText(String.format("Gateway HP + %03d", ugateway.get_MAX_HP() - 2000));
							}
						}

						if (e.getKeyCode() == KeyEvent.VK_R) {
							if (uhero.get_coin() >= ushop.repair.get_cost()) {
								uhero.set_coin(uhero.get_coin() - ushop.repair.get_cost());
								ushop.repair.levelup();
								coin_screen.setText(String.format("COIN     :  %09d", uhero.get_coin()));
								ushop.level[3].setText(String.format("Lv : %02d, ��� : %d", ushop.repair.get_level(),
										ushop.repair.get_cost()));
								ushop.etc[3].setText(String.format("�߰�ȸ���� + %02d, MP �Ҹ� : %d",
										ushop.repair.get_attack(), ushop.repair.get_MP_cost()));
							}
						} // ������

						if (e.getKeyCode() == KeyEvent.VK_T) {
							if (uhero.get_coin() >= ushop.lightning.get_cost()) {
								uhero.set_coin(uhero.get_coin() - ushop.lightning.get_cost());
								ushop.lightning.levelup();
								coin_screen.setText(String.format("COIN     :  %09d", uhero.get_coin()));
								ushop.level[4].setText(String.format("Lv : %02d, ��� : %d", ushop.lightning.get_level(),
										ushop.lightning.get_cost()));
								ushop.etc[4].setText(String.format("�߰����ݷ� + %03d, MP �Ҹ� : %d",
										ushop.lightning.get_attack(), ushop.lightning.get_MP_cost()));
							}
						} // ����Ʈ��

						if (e.getKeyCode() == KeyEvent.VK_Y) {
							if (uhero.get_coin() >= ushop.metheo.get_cost()) {
								uhero.set_coin(uhero.get_coin() - ushop.metheo.get_cost());
								ushop.metheo.levelup();
								coin_screen.setText(String.format("COIN     :  %09d", uhero.get_coin()));
								ushop.level[5].setText(String.format("Lv : %02d, ��� : %d", ushop.metheo.get_level(),
										ushop.metheo.get_cost()));
								ushop.etc[5].setText(String.format("�߰����ݷ� + %03d, MP �Ҹ� : %d",
										ushop.metheo.get_attack(), ushop.metheo.get_MP_cost()));
							}
						} // ���׿�

						if (e.getKeyCode() == KeyEvent.VK_F1) {
							if (ushop.lightning.get_level() > 0) {
								if (ushop.lightning.get_MP_cost() <= uhero.get_MP()
										&& ushop.lightning.get_timer() == 0) {
									synchronized (umonster) {
										notice.setText("����Ʈ�� �ߵ�!");
										uhero.set_MP(uhero.get_MP() - ushop.lightning.get_MP_cost());
										ushop.lightning.set_timer();
										if (!umonster.isEmpty()) {
											for (int i = 0; i < umonster.size(); i++) {
												if (uhero.get_life() && uhero.get_X() + uhero.get_attack_range()
														+ 30 > umonster.get(i).get_X()) {
													umonster.get(i).damaged(screen,
															(uhero.get_attack() + ushop.uweapon.get_attack()
																	+ ushop.lightning.get_attack() <= umonster.get(i)
																			.get_defense())
																					? 1
																					: (uhero.get_attack()
																							+ ushop.uweapon.get_attack()
																							+ ushop.lightning
																									.get_attack()
																							- umonster.get(i)
																									.get_defense()));

													if (umonster.get(i).get_HP() == 0) {
														synchronized (uhero) {
															uhero.set_coin(
																	uhero.get_coin() + umonster.get(i).get_drop_coin());
															uhero.set_score(uhero.get_score()
																	+ umonster.get(i).get_drop_coin());
															coin_screen.setText(String.format("COIN     :  %09d",
																	uhero.get_coin()));
															score_screen.setText(
																	String.format("SCORE  :  %09d", uhero.get_score()));
															umonster.remove(i);
															i--;
														}
													}
												}
											}
										}
									}

								}
							}
						} // ����Ʈ�� ��ų ����

						if (e.getKeyCode() == KeyEvent.VK_F2) {
							if (ushop.metheo.get_level() > 0) {
								if (ushop.metheo.get_MP_cost() <= uhero.get_MP() && ushop.metheo.get_timer() == 0) {
									synchronized (umonster) {
										notice.setText("���׿� �ߵ�!");
										uhero.set_MP(uhero.get_MP() - ushop.metheo.get_MP_cost());
										ushop.metheo.set_timer();
										if (!umonster.isEmpty()) {
											for (int i = 0; i < umonster.size(); i++) {
												umonster.get(i).damaged(screen,
														(uhero.get_attack() + ushop.uweapon.get_attack() + ushop.metheo
																.get_attack() <= umonster.get(i).get_defense())
																		? 1
																		: (uhero.get_attack()
																				+ ushop.uweapon.get_attack()
																				+ ushop.metheo.get_attack()
																				- umonster.get(i).get_defense()));

												if (umonster.get(i).get_HP() == 0) {
													synchronized (uhero) {
														uhero.set_coin(
																uhero.get_coin() + umonster.get(i).get_drop_coin());
														uhero.set_score(
																uhero.get_score() + umonster.get(i).get_drop_coin());
														coin_screen.setText(
																String.format("COIN     :  %09d", uhero.get_coin()));
														score_screen.setText(
																String.format("SCORE  :  %09d", uhero.get_score()));
														umonster.remove(i);
														i--;
													}
												}
											}
										}
									}
								}
							}
						} // ���׿� ��ų ����

						if (e.getKeyCode() == KeyEvent.VK_F3) {
							if (ushop.repair.get_level() > 0) {
								if (ushop.repair.get_MP_cost() <= uhero.get_MP() && ushop.repair.get_timer() == 0) {
									if (ugateway.get_HP() == ugateway.get_MAX_HP()) {
										notice.setText("����Ʈ���̰� ȸ���Ǿ����ϴ�.");
										uhero.set_MP(uhero.get_MP() - ushop.repair.get_MP_cost());
										ushop.repair.set_timer();
										ugateway.repair(uhero.get_attack() + ushop.uweapon.get_attack()
												+ ushop.repair.get_attack());
									}
								}

							}
						}
					}

				});
			}
		}
	}

	public class shop {
		private weapon uweapon;
		private armor uarmor;
		private skill lightning;
		private skill metheo;
		private skill repair;
		private JLabel[] name = new JLabel[6];
		private JLabel[] level = new JLabel[6];
		private JLabel[] information = new JLabel[6];
		private JLabel[] etc = new JLabel[6];
		private JPanel shop_;
		private JPanel[] shop_item;

		public shop() {
			uweapon = new weapon();
			uarmor = new armor();
			lightning = new skill(screen, "����Ʈ��", "F1", 100, 500, 30, 10, 10, 430);
			metheo = new skill(screen, "���׿�", "F2", 150, 700, 50, 30, 90, 430);
			repair = new skill(screen, "��Ŀ����", "F3", 50, 300, 30, 30, 170, 430);
			shop_ = new JPanel(new GridLayout(3, 2));
			shop_item = new JPanel[6];
			for (int i = 0; i < 6; i++) {
				shop_item[i] = new JPanel(new GridLayout(4, 1, 2, 2));
				shop_item[i].setSize(220, 110);
				shop_item[i].setBorder(new LineBorder(Color.BLACK, 2));
			}
			shop_.setBounds(350, 400, 440, 220);// ��ġ

			name[0] = new JLabel("���� ��ȭ : Q"); // Q
			name[1] = new JLabel("�� ��ȭ : W"); // W
			name[2] = new JLabel("����Ʈ���� ��ȭ : E"); // E
			name[3] = new JLabel("����Ʈ���� ��Ŀ���� : R"); // R
			name[4] = new JLabel("����Ʈ�� ��ȭ : T"); // T
			name[5] = new JLabel("���׿� ��ȭ : Y"); // Y

			level[0] = new JLabel(String.format("Lv : %02d, ��� : %d", uweapon.get_level(), uweapon.get_weapon_cost())); // Q
			level[1] = new JLabel(String.format("Lv : %02d, ��� : %d", uarmor.get_level(), uarmor.get_armor_cost())); // W
			level[2] = new JLabel(
					String.format("Lv : %02d, ��� : %d", ugateway.get_level(), ugateway.get_levelup_cost())); // E
			level[3] = new JLabel(String.format("Lv : %02d, ��� : %d", repair.get_level(), repair.get_cost())); // R
			level[4] = new JLabel(String.format("Lv : %02d, ��� : %d", lightning.get_level(), lightning.get_cost())); // T
			level[5] = new JLabel(String.format("Lv : %02d, ��� : %d", metheo.get_level(), metheo.get_cost())); // Y

			information[0] = new JLabel(String.format("���⸦ ��ȭ")); // Q
			information[1] = new JLabel(String.format("���� ��ȭ")); // W
			information[2] = new JLabel(String.format("�� ���׷��̵�")); // E
			information[3] = new JLabel(String.format("�� ü�� ȸ��")); // R
			information[4] = new JLabel(String.format("���� ���� �� ����")); // T
			information[5] = new JLabel(String.format("�� ���� ����")); // Y

			etc[0] = new JLabel(String.format("�߰� ���ݷ� + %02d", uweapon.get_attack())); // Q
			etc[1] = new JLabel(String.format("�߰� ���� + %02d", uarmor.get_defense())); // W
			etc[2] = new JLabel(String.format("Gateway HP + %03d", ugateway.get_MAX_HP() - 2000)); // E
			etc[3] = new JLabel(String.format("�߰�ȸ���� + %02d, MP �Ҹ� : %d", repair.get_attack(), repair.get_MP_cost())); // R
			etc[4] = new JLabel(
					String.format("�߰����ݷ� + %03d, MP �Ҹ� : %d", lightning.get_attack(), lightning.get_MP_cost())); // T
			etc[5] = new JLabel(String.format("�߰����ݷ� + %03d, MP �Ҹ� : %d", metheo.get_attack(), metheo.get_MP_cost())); // Y

			for (int i = 0; i < 6; i++) {
				name[i].setHorizontalAlignment(JLabel.CENTER);
				name[i].setFont(new Font("�������", Font.BOLD, 12));
				level[i].setHorizontalAlignment(JLabel.CENTER);
				level[i].setFont(new Font("�������", Font.BOLD, 12));
				information[i].setHorizontalAlignment(JLabel.CENTER);
				information[i].setFont(new Font("�������", Font.BOLD, 12));
				etc[i].setHorizontalAlignment(JLabel.CENTER);
				etc[i].setFont(new Font("�������", Font.BOLD, 12));
			}

			for (int i = 0; i < 6; i++) {
				shop_item[i].add(name[i]);
				shop_item[i].add(level[i]);
				shop_item[i].add(information[i]);
				shop_item[i].add(etc[i]);
				shop_.add(shop_item[i]);
			}

			screen.add(shop_);
		}// damage, cost, mpcost, cooltime

		public weapon get_weapon() {
			return uweapon;
		}

		public armor get_armor() {
			return uarmor;
		}

	}
	public JPanel get_screen() {
		// TODO Auto-generated method stub
		return screen;
	}
	public boolean get_restart() {
		// TODO Auto-generated method stub
		return restart;
	}
}