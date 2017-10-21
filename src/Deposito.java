
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Deposito {

    static int id;
    static String bank;
    static String tgl_jto;
    static String tgl_perpanjangan;
    static String company;
    static String bilyet1;
    static String bilyet2;
    static String type;
    static double nilai;
    static double bunga;
    static int hari;
    static double gross;
    static double tax;
    static double nett;
    static double pokokdanBunga;
    static String nama;
    static Boolean status;
    static double total;
    static Date date;

    static Connection conn;
    static PreparedStatement stmt;
    static ResultSet rs = null;

    private static Connection getConnection() {
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
            
            stmt = conn.prepareStatement("SELECT * FROM DEPOSITO  where dep_type ='" + type + "'order by dep_tgl_perpanjangan desc limit 1");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Deposito.bank = rs.getString("dep_bank");
                Deposito.date = rs.getDate("dep_tgl_perpanjangan");
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
