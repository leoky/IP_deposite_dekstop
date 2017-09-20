
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Leonardy
 */
public class InputDeposito extends javax.swing.JFrame {

    Connection conn;
    PreparedStatement stmt;
    ResultSet rs = null;
    Double nilai, bunga, tax, gross, nett;

    public InputDeposito() {
        initComponents();
        getConnection();
    }

    //all method
    //db
    private Connection getConnection() {
        try {
            conn = DriverManager.getConnection(DataBase.JDBC_URL, DataBase.JDBC_USERNAME, DataBase.JDBC_PASSWORD);
            System.out.println("HAS CONNECTED");
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(InputDeposito.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("CANNOT CONNECT");
            return null;
        }
    }

    //method to number
    public String setSymbolToDigit(String input) {
        String a1 = input.substring(3);
        String b1 = a1.replace(",", "");
        return b1;
    }

    //method to add currnecy symbol
    public String setToSymbol(String input) {
        Double d = new Double(input);
        return String.format("Rp.%,.2f", d);
    }

    public void Count() {
        long hari = ChronoUnit.DAYS.between(jDateChooser1.getCalendar().toInstant(), jDateChooser2.getCalendar().toInstant());
        nilai = Double.parseDouble(setSymbolToDigit(tfNilai.getText()));
        bunga = Double.parseDouble(tfBunga.getText());
        tax = Double.parseDouble(tfTax.getText());
        gross = (nilai * bunga / 100 * hari / 365) + nilai;
        tax = gross * tax / 100;
        nett = gross - tax;
        System.out.println("nilai :" + nilai + " tax: " + tax + " gross ; " + gross + " nett : " + nett);

    }

    //method to check empty
    public void checkEmpty(){
        
    }
    
    //end all method
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfBank = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbCompany = new javax.swing.JComboBox<>();
        tfBil1 = new javax.swing.JTextField();
        tfBil2 = new javax.swing.JTextField();
        tfNilai = new javax.swing.JTextField();
        tfBunga = new javax.swing.JTextField();
        tfTax = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cbType = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        tfNama = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("INPUT DEPSOTO");

        jLabel2.setText("NAMA BANK");

        jLabel3.setText("TANGGAL JATUH TEMPO");

        jLabel4.setText("TANGGAL PERPANJANG");

        jLabel5.setText("COMPANY");

        jLabel6.setText("NO BILYET 1");

        jLabel7.setText("NO BILYET 2");

        jLabel8.setText("NILAI");

        jLabel9.setText("BUNGA");

        jLabel10.setText("TAX");

        jLabel11.setText("GROSS");

        jLabel12.setText("NETT");

        jLabel13.setText("POKOK + BUNGA");

        jLabel14.setText("TYPE");

        jLabel15.setText("ATAS NAMA");

        cbCompany.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PRIBADI", "PERUSAHAAN" }));

        tfBil1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfBil1FocusLost(evt);
            }
        });

        tfBil2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfBil2FocusLost(evt);
            }
        });
        tfBil2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfBil2ActionPerformed(evt);
            }
        });

        tfNilai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfNilaiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNilaiFocusLost(evt);
            }
        });

        jLabel16.setText("jLabel16");

        jLabel17.setText("jLabel17");

        jLabel18.setText("jLabel18");

        jDateChooser2.setDateFormatString("yyyy-MM-dd ");

        jLabel19.setText("%");

        jLabel20.setText("%");

        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ARO", "TT" }));

        jButton1.setText("INPUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel14)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfBank)
                            .addComponent(cbCompany, 0, 230, Short.MAX_VALUE)
                            .addComponent(tfBil1)
                            .addComponent(tfBil2)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel18))
                            .addComponent(jLabel17)
                            .addComponent(jLabel16)
                            .addComponent(tfNilai, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tfBunga, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                    .addComponent(tfTax, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(363, 363, 363)
                        .addComponent(jLabel15)
                        .addGap(38, 38, 38)
                        .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(463, 463, 463)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(jLabel1))))
                .addContainerGap(181, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfBank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(cbCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(55, 55, 55))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfBil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(tfBil2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(tfNilai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(tfBunga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(tfTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel16))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel17))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel18))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfBil2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfBil2ActionPerformed
        long a = ChronoUnit.DAYS.between(jDateChooser1.getCalendar().toInstant(), jDateChooser2.getCalendar().toInstant());
        tfBil2.setText("" + a);
    }//GEN-LAST:event_tfBil2ActionPerformed
//TextField price check if lostFOcut
    private void tfNilaiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNilaiFocusLost
        if (!tfNilai.getText().chars().allMatch(Character::isAlphabetic)) {
            tfNilai.setText(setToSymbol(tfNilai.getText()));
        } else {
            System.out.println(tfNilai.getText());
        }
    }//GEN-LAST:event_tfNilaiFocusLost
//TextField price check if gain focus
    private void tfNilaiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNilaiFocusGained
//        try {
        if (tfNilai.getText().substring(0, 3).equals("Rp.")) {
            tfNilai.setText(setSymbolToDigit(tfNilai.getText()));
        } else {
            System.out.println("fs");
        }
    }//GEN-LAST:event_tfNilaiFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Boolean input = Boolean.TRUE;
        try {
            Date a = jDateChooser1.getDate();
            Date e = jDateChooser2.getDate();
            int check = JOptionPane.showConfirmDialog(this, "APAKAH MAU DICAIRKAN ?");
            if (check == JOptionPane.YES_OPTION) {
                input = Boolean.TRUE;
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                
                stmt = conn.prepareStatement("INSERT INTO DEPOSITO(dep_bank,dep_tgl_jto,dep_tgl_perpanjangan,"
                        + "dep_company,dep_no_bilyet_1,dep_no_bilyet_2,"
                        + "dep_type,dep_nilai,dep_bunga,"
                        + "dep_hari,dep_gross,dep_tax,"
                        + "dep_nett,dep_pokokdanBunga,dep_nama,dep_pencairan)"
                        + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                stmt.setString(1, "'" + tfBank.getText() + "'");
                stmt.setString(2, s.format(a));
                stmt.setString(3, s.format(e));
                stmt.setString(4, "'" + cbCompany.getSelectedItem() + "'");
                stmt.setString(5, "'" + tfBil1.getText() + "'");
                stmt.setString(6, "'" + tfBil2.getText() + "'");
                stmt.setString(7, "'" + cbType.getSelectedItem() + "'");
                stmt.setDouble(8, 1);
                stmt.setDouble(9, 1);
                stmt.setInt(10, (int) ChronoUnit.DAYS.between(jDateChooser1.getCalendar().toInstant(), jDateChooser2.getCalendar().toInstant()));
                stmt.setDouble(11, 1);
                stmt.setDouble(12, 1);
                stmt.setDouble(13, 1);
                stmt.setDouble(14, 1);
                stmt.setString(15, tfNama.getText());
                stmt.setBoolean(16, input);
                stmt.executeUpdate();

//                stmt = conn.createStatement();
//                stmt.executeUpdate("INSERT INTO DEPOSITO(dep_bank,dep_tgl_jto,dep_tgl_perpanjangan,"
//                        + "dep_company,dep_no_bilyet_1,dep_no_bilyet_2,"
//                        + "dep_type,dep_nilai,dep_bunga,"
//                        + "dep_hari,dep_gross,dep_tax,"
//                        + "dep_nett,dep_pokokdanBunga,dep_nama,dep_pencairan)"
//                        + "values('1',null,null,'1','1','1','1',1,1,1,1,1,1,1,'1','1')");
                System.out.println("sucess");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed
    //tf bil1 
    private void tfBil1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfBil1FocusLost
        if (tfBil1.getText().length() != 0) {
            if (tfBil1.getText().length() > 12) {
                JOptionPane.showMessageDialog(this, "PLEASE INPUT RIGHT BILL NUMBER");
            }
        }
    }//GEN-LAST:event_tfBil1FocusLost
    //tf bil2
    private void tfBil2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfBil2FocusLost
        if (tfBil2.getText().length() != 0) {
            if (tfBil2.getText().length() > 12) {
                JOptionPane.showMessageDialog(this, "PLEASE INPUT RIGHT BILL NUMBER");
            }
        }
    }//GEN-LAST:event_tfBil2FocusLost
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InputDeposito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputDeposito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputDeposito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputDeposito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InputDeposito inputDeposito = new InputDeposito();
//                new InputDeposito().setVisible(true);
                inputDeposito.setLocationRelativeTo(null);
                inputDeposito.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbCompany;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTextField tfBank;
    private javax.swing.JTextField tfBil1;
    private javax.swing.JTextField tfBil2;
    private javax.swing.JTextField tfBunga;
    private javax.swing.JTextField tfNama;
    private javax.swing.JTextField tfNilai;
    private javax.swing.JTextField tfTax;
    // End of variables declaration//GEN-END:variables
}
