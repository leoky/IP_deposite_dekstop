
public class DepositoTable {

    private int id;
    private String bank;
    private String tgl_jto;
    private String tgl_perpanjangan;
    private String company;
    private String bilyet1;
    private String bilyet2;
    private String type;
    private double nilai;
    private double bunga;
    private int hari;
    private double gross;
    private double tax;
    private double nett;
    private double pokokdanBunga;
    private String nama;
    private int pencairan;

    public DepositoTable(int id, String bank, String tgl_jto, String tgl_perpanjangan, String company, String bilyet1, String bilyet2, String type, double nilai, double bunga, int hari, double gross, double tax, double nett, double pokokdanBunga, String nama, int pencairan) {
        this.id = id;
        this.bank = bank;
        this.tgl_jto = tgl_jto;
        this.tgl_perpanjangan = tgl_perpanjangan;
        this.company = company;
        this.bilyet1 = bilyet1;
        this.bilyet2 = bilyet2;
        this.type = type;
        this.nilai = nilai;
        this.bunga = bunga;
        this.hari = hari;
        this.gross = gross;
        this.tax = tax;
        this.nett = nett;
        this.pokokdanBunga = pokokdanBunga;
        this.nama = nama;
        this.pencairan = pencairan;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getTgl_jto() {
        return tgl_jto;
    }

    public void setTgl_jto(String tgl_jto) {
        this.tgl_jto = tgl_jto;
    }

    public String getTgl_perpanjangan() {
        return tgl_perpanjangan;
    }

    public void setTgl_perpanjangan(String tgl_perpanjangan) {
        this.tgl_perpanjangan = tgl_perpanjangan;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBilyet1() {
        return bilyet1;
    }

    public void setBilyet1(String bilyet1) {
        this.bilyet1 = bilyet1;
    }

    public String getBilyet2() {
        return bilyet2;
    }

    public void setBilyet2(String bilyet2) {
        this.bilyet2 = bilyet2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    public double getBunga() {
        return bunga;
    }

    public void setBunga(double bunga) {
        this.bunga = bunga;
    }

    public int getHari() {
        return hari;
    }

    public void setHari(int hari) {
        this.hari = hari;
    }

    public double getGross() {
        return gross;
    }

    public void setGross(double gross) {
        this.gross = gross;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getNett() {
        return nett;
    }

    public void setNett(double nett) {
        this.nett = nett;
    }

    public double getPokokdanBunga() {
        return pokokdanBunga;
    }

    public void setPokokdanBunga(double pokokdanBunga) {
        this.pokokdanBunga = pokokdanBunga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getPencairan() {
        return pencairan;
    }

    public void setPencairan(int pencairan) {
        this.pencairan = pencairan;
    }

    

}
