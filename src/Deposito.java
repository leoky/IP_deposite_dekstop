
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Deposito {

    public static int id;
    public static String bank;
    public static String tgl_jto;
    public static String tgl_perpanjangan;
    public static String company;
    public static String bilyet1;
    public static String bilyet2;
    public static String type;
    public static double nilai;
    public static double bunga;
    public static int hari;
    public static double gross;
    public static double tax;
    public static double nett;
    public static double pokokdanBunga;
    public static String nama;
    public static Boolean status;
    public static double total;
    public static Date date;

    public static Connection conn;
    public static PreparedStatement stmt;
    public static ResultSet rs = null;

    public static Connection getConnection() {
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

    public static void a(String type) {
        try {
            getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM DEPOSITO  where dep_type ='" + type + "'order by dep_id desc limit 1");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Deposito.bank = rs.getString("dep_bank");
//                Deposito.date = rs.getDate("dep_tgl_perpanjangan");
                Deposito.bilyet1 = rs.getString("dep_no_bilyet_1");
                Deposito.bilyet2 = rs.getString("dep_no_bilyet_2");
                Deposito.nilai = rs.getDouble("dep_nilai");
                Deposito.bunga = rs.getDouble("dep_bunga");
                Deposito.tax = rs.getDouble("dep_tax");
                Deposito.total = rs.getDouble("dep_pokokdanBunga");
                Deposito.nama = rs.getString("dep_nama");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
