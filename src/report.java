import java.util.HashMap;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class report {
public static void main(String[] args) throws JRException{
	JasperReport jasperReport = JasperCompileManager.compileReport("E:/java/IP_deposite_dekstop/src/deposito.jrxml");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), new JREmptyDataSource());
        JasperExportManager.exportReportToPdfFile(jasperPrint, "E:/java/IP_Deposite_dekstip/src/Data deposito.pdf");
        System.out.println("File pdf berhasil dibuat");
        JasperViewer.viewReport(jasperPrint);
		
	}
}