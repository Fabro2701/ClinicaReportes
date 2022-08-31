package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import model.person.Doctor;

public class ReportsCreator {
	protected List<Sale>sales;
	protected DoctorManager doctorManager;
	public ReportsCreator(DoctorManager doctorManager) {
		sales = new ArrayList<Sale>();
		this.doctorManager = doctorManager;
	}
	public void addFiles(File files[]) throws IOException {
		for(File file:files) {
			addSales(file);
		}
	}
	private void addSales(File file) throws IOException {
		
		FileInputStream fis=new FileInputStream(file);  
		HSSFWorkbook wb=new HSSFWorkbook(fis);   
		//creating a Sheet object to retrieve the object  
		HSSFSheet sheet=wb.getSheetAt(0); 
		FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();  

		for(Row row: sheet)     //iteration over row using for each loop  
		{  
			if(row.getRowNum()<=9)continue;
			Sale sale = new Sale();
			for(Cell cell: row) {  
				switch(formulaEvaluator.evaluateInCell(cell).getCellType())  
				{  
				case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type  
					sale.subtotal = cell.getNumericCellValue();
					break;  
				case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
					sale.data.add(cell.getStringCellValue());
					break;  
				}  
			}  
			this.sales.add(sale);
		}  
		
	}
	
	public void generate(String destination) {
		for(Doctor doctor:doctorManager) {
			Report report = new Report(doctor);
			report.extract(sales);
			report.setCalculations();
			report.save(destination);
		}
	}
}
