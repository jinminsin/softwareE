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
         lightning = new skill("라이트닝","F1",100, 500, 30, 10);
         metheo = new skill("메테오","F2",150, 700, 50, 30);
         repair = new skill("리커버리","F3",0, 300, 30, 30);
         shop_ = new JPanel(new GridLayout(3, 2));
         shop_item = new JPanel[6];
         for (int i = 0; i < 6; i++) {
            shop_item[i] = new JPanel(new GridLayout(4, 1, 2, 2));
            shop_item[i].setSize(220, 110);
            shop_item[i].setBorder(new LineBorder(Color.BLACK, 2));
         }
         shop_.setBounds(350, 400, 440, 220);// 위치

         name[0] = new JLabel("무기 강화 : Q"); // Q
         name[1] = new JLabel("방어구 강화 : W"); // W
         name[2] = new JLabel("게이트웨이 강화 : E"); // E
         name[3] = new JLabel("게이트웨이 리커버리 : R"); // R
         name[4] = new JLabel("라이트닝 강화 : T"); // T
         name[5] = new JLabel("메테오 강화 : Y"); // Y

         level[0] = new JLabel(String.format("Lv : %02d, 비용 : %d", uweapon.get_level(), uweapon.get_weapon_cost())); // Q
         level[1] = new JLabel(String.format("Lv : %02d, 비용 : %d", uarmor.get_level(), uarmor.get_armor_cost())); // W
         level[2] = new JLabel(
               String.format("Lv : %02d, 비용 : %d", ugateway.get_level(), ugateway.get_levelup_cost())); // E
         level[3] = new JLabel(String.format("Lv : %02d, 비용 : %d", repair.get_level(), repair.get_cost())); // R
         level[4] = new JLabel(String.format("Lv : %02d, 비용 : %d", lightning.get_level(), lightning.get_cost())); // T
         level[5] = new JLabel(String.format("Lv : %02d, 비용 : %d", metheo.get_level(), metheo.get_cost())); // Y

         information[0] = new JLabel(String.format("무기를 강화")); // Q
         information[1] = new JLabel(String.format("방어구를 강화")); // W
         information[2] = new JLabel(String.format("문 업그레이드")); // E
         information[3] = new JLabel(String.format("문 체력 회복")); // R
         information[4] = new JLabel(String.format("영웅 범위 내 말소")); // T
         information[5] = new JLabel(String.format("전 지역 말소")); // Y

         etc[0] = new JLabel(String.format("추가 공격력 + %02d", uweapon.get_attack())); // Q
         etc[1] = new JLabel(String.format("추가 방어력 + %02d", uarmor.get_defense())); // W
         etc[2] = new JLabel(String.format("Gateway HP + %03d", ugateway.get_MAX_HP() - 2000)); // E
         etc[3] = new JLabel(String.format("추가회복력 + %02d, MP 소모 : %d", repair.get_attack(), repair.get_MP_cost())); // R
         etc[4] = new JLabel(
               String.format("추가공격력 + %03d, MP 소모 : %d", lightning.get_attack(), lightning.get_MP_cost())); // T
         etc[5] = new JLabel(String.format("추가공격력 + %03d, MP 소모 : %d", metheo.get_attack(), metheo.get_MP_cost())); // Y

         for (int i = 0; i < 6; i++) {
            name[i].setHorizontalAlignment(JLabel.CENTER);
            name[i].setFont(new Font("나눔고딕", Font.BOLD, 12));
            level[i].setHorizontalAlignment(JLabel.CENTER);
            level[i].setFont(new Font("나눔고딕", Font.BOLD, 12));
            information[i].setHorizontalAlignment(JLabel.CENTER);
            information[i].setFont(new Font("나눔고딕", Font.BOLD, 12));
            etc[i].setHorizontalAlignment(JLabel.CENTER);
            etc[i].setFont(new Font("나눔고딕", Font.BOLD, 12));
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
