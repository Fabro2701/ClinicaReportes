package model;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import comission.ComissionsManager;
import model.person.Doctor;

public class Report {
	Doctor doctor;
	List<Sale>ownSales;
	List<Double>calculations;
	public static List<String>attributes;
	static {
		attributes = new ArrayList<String>();
		attributes.add("CMP");
		attributes.add("Fecha");
		attributes.add("Paciente");
		attributes.add("Rubro");
		attributes.add("Cía. Aseguradora");
		attributes.add("Tipo Adm.");
		attributes.add("Historia Clínica");
		attributes.add("Tarifario");
	}
	
	private Report() {
		ownSales = new ArrayList<Sale>();
		calculations = new ArrayList<Double>();
	}

	public Report(Doctor doctor) {
		this();
		this.doctor = doctor;
	}
	
	public void extract(List<Sale>sales) {
		for(Sale sale:sales) {
			String cmp = sale.data.get(Sale.attributes.get("CMP"));
			if(cmp.equals(doctor.getCMP())) {
				this.ownSales.add(sale);
			}
		}
		sales.removeAll(this.ownSales);
	}

	public void setCalculations(ComissionsManager comissionsManager) {
		for(Sale sale:this.ownSales) {
			this.calculations.add(comissionsManager.getComissions(sale));
		}
	}
	public void save(String destination) {
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("resumen");
         
     
 
        int rowCount = 1;
        int columnCount = 0;
        
        //header
        Row row = sheet.createRow(rowCount);
        for(String att:Report.attributes) {
        	Cell cell = row.createCell(++columnCount);
			cell.setCellValue(att);
        }
        
        //Sale details
        columnCount = 0;
        for(Sale sale:this.ownSales) {
        	row = sheet.createRow(++rowCount);
        	columnCount = 0;
			for(String att:Report.attributes) {
				Cell cell = row.createCell(++columnCount);
				cell.setCellValue(sale.data.get(Sale.attributes.get(att)));
			}
		}
        
        //Sale subtotal
        rowCount = 1;
        for(Sale sale:this.ownSales) {
        	row = sheet.getRow(++rowCount);
			Cell cell = row.createCell(columnCount);
			cell.setCellValue(sale.subtotal);
		}

        //subtotal comission
        double sum=0.0;
        rowCount = 1;
        columnCount++;
		for(double v:this.calculations) {
			row = sheet.getRow(++rowCount);
			Cell cell = row.createCell(columnCount);
			cell.setCellValue(v);
			sum += v;
		}
		
		row = sheet.createRow(++rowCount);
		Cell cell = row.createCell(++columnCount);
		cell.setCellValue(sum);
		
//        for (Object[] aBook : bookData) {
//            Row row = sheet.createRow(++rowCount);
//             
//            columnCount = 0;
//             
//            for (Object field : aBook) {
//                Cell cell = row.createCell(++columnCount);
//                if (field instanceof String) {
//                    cell.setCellValue((String) field);
//                } else if (field instanceof Integer) {
//                    cell.setCellValue((Integer) field);
//                }
//            }
//             
//        }
		
		
		FileOutputStream outputStream;
		try {
			File file = new File(destination+this.doctor.getFullname()+this.doctor.getCMP()+".xlsx");
			
			outputStream = new FileOutputStream(file);
	        workbook.write(outputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}

}
