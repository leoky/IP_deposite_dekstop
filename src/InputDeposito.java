
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

    Connection conn = null;
    PreparedStatement stmt;
    ResultSet rs = null;
    double nilai, bunga, tax, gross, taxp, nett, total;
    String type = "ARO";
    Boolean input = Boolean.TRUE;

    public InputDeposito() {
        initComponents();
        this.conn = Deposito.getConnection();
        Deposito.a("ARO");
        getData();
        AddListener();
        Show_JTable();
    }
//

    public ArrayList<DepositoTable> getList() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<DepositoTable> depositoList = new ArrayList<>();
        String find = "", type = "";
        if (jComboBox1.getSelectedItem().equals("ARO/TT")) {
            type = "%";
        } else {
            type = (String) jComboBox1.getSelectedItem();
        }
        if (jDateChooser3.getDate() == null) {
            find = "%";
        } else {
            find = s.format(jDateChooser3.getDate());
        }
        try {
            DepositoTable dep;
            stmt = conn.prepareStatement("select * from deposito where dep_tgl_jto like '" + find + "' and dep_type like '" + type
                    + "' order by dep_id desc;");
            rs = stmt.executeQuery();
            while (rs.next()) {
                dep = new DepositoTable(
                        rs.getInt("dep_id"),
                        rs.getString("dep_bank"),
                        rs.getString("dep_tgl_jto"),
                        rs.getString("dep_tgl_perpanjangan"),
                        rs.getString("dep_company"),
                        rs.getString("dep_no_bilyet_1"),
                        rs.getString("dep_no_bilyet_2"),
                        rs.getString("dep_type"),
                        rs.getDouble("dep_nilai"),
                        rs.getDouble("dep_bunga"),
                        rs.getInt("dep_hari"),
                        rs.getDouble("dep_gross"),
                        rs.getDouble("dep_tax"),
                        rs.getDouble("dep_nett"),
                        rs.getDouble("dep_pokokdanBunga"),
                        rs.getString("dep_nama"),
                        rs.getInt("dep_pencairan"));
                depositoList.add(dep);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
        return depositoList;
    }
    //

    public void Show_JTable() {
        ArrayList<DepositoTable> list = getList();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[17];
        for (int i = 0; i < list.size(); i++) {
            row[0] = model.getRowCount() + 1;
            row[1] = list.get(i).getBank();
            row[2] = list.get(i).getTgl_jto();
            row[3] = list.get(i).getTgl_perpanjangan();
            row[4] = list.get(i).getCompany();
            row[5] = list.get(i).getBilyet1();
            row[6] = list.get(i).getBilyet2();
            row[7] = list.get(i).getType();
            row[8] = list.get(i).getNilai();
            row[9] = list.get(i).getBunga();
            row[10] = list.get(i).getHari();
            row[11] = list.get(i).getGross();
            row[12] = list.get(i).getTax();
            row[13] = list.get(i).getNett();
            row[14] = list.get(i).getPokokdanBunga();
            row[15] = list.get(i).getNama();
            row[16] = list.get(i).getPencairan();
            model.addRow(row);
        }
    }
//    

    //all method
    //db
    private void AddListener() {
        jDateChooser1.addPropertyChangeListener(
                new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) throws NullPointerException {
                if (jDateChooser2.getDate() != null) {
                    if (jDateChooser2.getDate().after(jDateChooser1.getDate())) {
                        jLabel22.setText(Float.toString(ChronoUnit.DAYS.between(jDateChooser1.getCalendar().toInstant(), jDateChooser2.getCalendar().toInstant())));
                    } else {
                        jLabel22.setText("0");
                    }
                };
            }
        });
        jDateChooser2.addPropertyChangeListener(
                new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (jDateChooser1.getDate() != null) {
                    if (jDateChooser2.getDate().after(jDateChooser1.getDate())) {
                        jLabel22.setText(String.format("%d", (ChronoUnit.DAYS.between(jDateChooser1.getCalendar().toInstant(), jDateChooser2.getCalendar().toInstant()))));
                    } else {
                        JOptionPane.showMessageDialog(null, "Please input valid date");
                        jDateChooser2.setCalendar(null);
                        jLabel22.setText("0");
                    }
                };
            }
        });
    }

    private void setUpdate(Date a, Date b, Boolean input) throws SQLException {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

        stmt = conn.prepareStatement("INSERT INTO DEPOSITO(dep_bank,dep_tgl_jto,dep_tgl_perpanjangan,"
                + "dep_company,dep_no_bilyet_1,dep_no_bilyet_2,"
                + "dep_type,dep_nilai,dep_bunga,"
                + "dep_hari,dep_gross,dep_tax,"
                + "dep_nett,dep_pokokdanBunga,dep_nama,dep_pencairan)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        stmt.setString(1, tfBank.getText());
        stmt.setString(2, s.format(a));
        stmt.setString(3, s.format(b));
        stmt.setString(4, (String) cbCompany.getSelectedItem());
        stmt.setString(5, tfBil1.getText());
        stmt.setString(6, tfBil2.getText());
        stmt.setString(7, (String) cbType.getSelectedItem());
        stmt.setDouble(8, nilai);
        stmt.setDouble(9, (int) bunga);
        stmt.setInt(10, (int) ChronoUnit.DAYS.between(jDateChooser1.getCalendar().toInstant(), jDateChooser2.getCalendar().toInstant()));
        stmt.setDouble(11, gross);
        stmt.setDouble(12, tax);
        stmt.setDouble(13, nett);
        stmt.setDouble(14, total);
        stmt.setString(15, tfNama.getText());
        stmt.setBoolean(16, input);
        stmt.executeUpdate();
        System.out.println("sucess");
        Deposito.a(type);
        getData();
    }

    private void getData() {
        tfBank.setText(Deposito.bank);
        tfBil1.setText(Deposito.bilyet1);
        tfBil2.setText(Deposito.bilyet2);
        if (type == "ARO") {
            tfNilai.setText(String.format("Rp.%,.2f", Deposito.total));
        } else {
            tfNilai.setText(String.format("Rp.%,.2f", Deposito.nilai));
        }
        tfBunga.setText("" + (int) Deposito.bunga);
        tfTax.setText("10");
        tfNama.setText(Deposito.nama);
        jLabel16.setText("0");
        jLabel17.setText("0");
        jLabel18.setText("0");
        jLabel24.setText("0");
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
        taxp = Double.parseDouble(tfTax.getText());
        gross = (nilai * (bunga / 100.0) * (hari / 365.0));
        tax = gross * taxp / 100.0;
        nett = gross - tax;
        if (cbType.getSelectedItem().equals("ARO")) {
            total = nilai + nett;
        } else {
            total = 0;
        }
//        jLabel16.setText(String.format("Rp %f02", gross));
        jLabel16.setText(String.format("Rp.%,.2f", gross));
        jLabel24.setText(String.format("Rp.%,.2f", tax));
        jLabel17.setText(String.format("Rp.%,.2f", nett));
        jLabel18.setText(String.format("Rp.%,.2f", total));

        System.out.println(nilai + " kali " + bunga + " hari: " + hari + " tax : " + tax);
    }

    //method to check empty
    public void checkEmpty() {

    }

    //end all method
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jButton7 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(3436, 619));

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

        jLabel16.setText("0");

        jLabel17.setText("0");

        jLabel18.setText("0");

        jDateChooser2.setDateFormatString("yyyy-MM-dd ");

        jLabel19.setText("%");

        jLabel20.setText("%");

        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ARO", "TT" }));
        cbType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTypeActionPerformed(evt);
            }
        });

        jButton1.setText("INPUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jLabel21.setText("HARI");

        jLabel22.setText("0");

        jButton2.setText("CALCULATE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel23.setText("TAX");

        jLabel24.setText("0");

        jCheckBox1.setText("CAIRKAN?");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO", "BANK", "TGL_JTO", "TGL_PERP", "COMPANY", "NO BILYE 1", "NO BILYET 2", "TYPE", "NILAI", "BUNGA", "HARI", "GROSS", "TAX", "NETT", "POKOKDANBUNGA", "NAMA", "PENCAIRAN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("SEARCH");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("REFRESH");

        jButton5.setText("EDIT");

        jButton6.setText("LOG OUT");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ARO/TT", "ARO", "TT" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton7.setText("DELETE");

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(363, 363, 363)
                        .addComponent(jLabel15)
                        .addGap(38, 38, 38)
                        .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(484, 484, 484)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel14))
                                        .addGap(110, 110, 110)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbCompany, 0, 230, Short.MAX_VALUE)
                                            .addComponent(tfBil1)
                                            .addComponent(tfBil2)
                                            .addComponent(cbType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(164, 164, 164)
                                        .addComponent(jLabel22)))
                                .addGap(106, 106, 106)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2)
                                    .addComponent(jCheckBox1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel23))
                                        .addGap(25, 25, 25)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel18)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfBank)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(106, 106, 106)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(67, 67, 67)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(tfNilai, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tfBunga, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfTax, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel20))))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jComboBox1))
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                .addGap(101, 101, 101)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfBank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                            .addComponent(jLabel16))))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel23)
                                .addComponent(jLabel21)
                                .addComponent(jLabel22))
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel18))))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfBil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jCheckBox1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfBil2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(34, 34, 34))
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
        if (tfNilai.getText().length() >= 3) {
            if (tfNilai.getText().substring(0, 3).equals("Rp.")) {
                tfNilai.setText(setSymbolToDigit(tfNilai.getText()));
            } else {
                System.out.println("fs");
            }
        }
    }//GEN-LAST:event_tfNilaiFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        nilai = Deposito.nilai;
        try {
            Count();
            if (!jLabel18.isVisible()) {
                total = 0;
            }
            Date a = jDateChooser1.getDate();
            Date e = jDateChooser2.getDate();
            int check = JOptionPane.showConfirmDialog(this, "APAKAH MAU ANDA YAKIN ?");
            if (check == JOptionPane.YES_OPTION) {
                setUpdate(a, e, input);
                Show_JTable();
            } else if (check == JOptionPane.NO_OPTION) {

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
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

    private void cbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTypeActionPerformed
        if (cbType.getSelectedItem().equals("TT")) {
            type = "TT";
            Deposito.a(type);
            getData();
            tfNilai.setText(String.format("Rp.%,.2f", Deposito.nilai));
            jLabel13.setVisible(false);
            jLabel18.setVisible(false);

        } else if (cbType.getSelectedItem().equals("ARO")) {
            type = "ARO";
            Deposito.a(type);
            getData();
            tfNilai.setText(String.format("Rp.%,.2f", Deposito.total));
            jLabel13.setVisible(true);
            jLabel18.setVisible(true);

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTypeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Count();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            input = Boolean.TRUE;
        } else {
            input = Boolean.FALSE;
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Show_JTable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        Show_JTable();
    }//GEN-LAST:event_jComboBox1ActionPerformed
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(InputDeposito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(InputDeposito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(InputDeposito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(InputDeposito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                InputDeposito inputDeposito = new InputDeposito();
////                new InputDeposito().setVisible(true);
//                inputDeposito.setLocationRelativeTo(null);
//                inputDeposito.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbCompany;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField tfBank;
    private javax.swing.JTextField tfBil1;
    private javax.swing.JTextField tfBil2;
    private javax.swing.JTextField tfBunga;
    private javax.swing.JTextField tfNama;
    private javax.swing.JTextField tfNilai;
    private javax.swing.JTextField tfTax;
    // End of variables declaration//GEN-END:variables
}
