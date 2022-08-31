package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

public class Sale {
	public List<String>data;
	public double subtotal;
	public static HashMap<String, Integer>attributes;
	static {
		attributes = new HashMap<String, Integer>();
		attributes.put("Rubro", 0);
		attributes.put("Aseguradora", 1);
		attributes.put("CMP", 2);
		attributes.put("Fecha", 3);
		attributes.put("Tipo Adm", 5);
		attributes.put("Historia Clinica", 6);
		attributes.put("Paciente", 7);
		//attributes.put("Subtotal", 14);
	}
	public Sale() {
		data = new ArrayList<String>();
	}
	

}
