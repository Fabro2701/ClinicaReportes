package model;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import comission.ComissionsManager;
import exceptions.FileException;
import model.person.Doctor;

public class ReportsCreator {
	protected List<Sale>sales;
	protected DoctorManager doctorManager;
	protected ComissionsManager comissionsManager;
	public ReportsCreator(DoctorManager doctorManager, ComissionsManager comissionsManager) {
		sales = new ArrayList<Sale>();
		this.doctorManager = doctorManager;
		this.comissionsManager = comissionsManager;
	}
	public void addFiles(File files[]) throws FileException {
		for(File file:files) {
			addSales(file);
		}
	}
	private void addSales(File file) throws FileException  {
		try {
			FileInputStream fis = new FileInputStream(file);
			HSSFWorkbook wb=new HSSFWorkbook(fis);   
			//creating a Sheet object to retrieve the object  
			HSSFSheet sheet=wb.getSheetAt(0); 
			FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();  

			for(Row row: sheet)     //iteration over row using for each loop  
			{  
				if(row.getRowNum()<=8)continue;
				Sale sale = new Sale();
				int i=0;
				for(Cell cell: row) {  	
					switch(formulaEvaluator.evaluateInCell(cell).getCellType())  
					{  
					case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type  
						if(i==14) {
							sale.subtotal = cell.getNumericCellValue();
						}
						break;  
					case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
						sale.data.add(cell.getStringCellValue());
						break;  
					}  
					i++;
				}  
				this.sales.add(sale);
			}  
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileException("Un Problema leyendo "+file.getAbsolutePath());
		}  
		
		
	}
	
	public void generate(String destination, JFrame frame) {
		for(Doctor doctor:doctorManager) {
			Report report = new Report(doctor);
			report.extract(sales);
			report.setCalculations(comissionsManager);
			report.save(destination);
			sales.removeAll(report.ownSales);
		}
		if(sales.size() > 0) {
			informUnknown(frame);
		}
	}
	private void informUnknown(JFrame frame) {
		if(frame == null)System.err.println("No frame assigned");
		List<String>doctors = new ArrayList<String>();
		for(Sale sale:sales) {
			String cmp = sale.data.get(Sale.attributes.get("CMP"));
			if(!doctors.contains(cmp)) {
				JOptionPane.showMessageDialog(frame, "CMP: "+cmp+" no declarado");
				doctors.add(cmp);
			}
		}
	}
}
