package model;

import java.util.ArrayList;
import java.util.List;

import model.person.Doctor;

public class Report {
	Doctor doctor;
	List<Sale>ownSales;
	List<Double>calculations;
	public static List<String>attributes;
	static {
		attributes = new ArrayList<String>();
		attributes.add("Rubro");
	}
	public static List<Modifier>modifiers;
	
	private Report() {
		ownSales = new ArrayList<Sale>();
		calculations = new ArrayList<Double>();
	}

	public Report(Doctor doctor) {
		this();
		this.doctor = doctor;
	}
	public class Modifier{
		String num;
		double factor;
		public Modifier(String num, String factor) {
			this.num = num;
			this.factor = Double.parseDouble(factor);
		}
		public boolean evaluate(Sale sale) {
			if(num.equals("default"))return true;
			return Double.parseDouble(num) < sale.subtotal;
		}
		public Double getResult(Sale sale) {
			return factor*sale.subtotal;
		}
	}
	public void extract(List<Sale>sales) {
		for(Sale sale:sales) {
			String cmp = sale.data.get(Sale.attributes.get("CMP"));
			if(cmp.equals(doctor.getCMP())) {
				this.ownSales.add(sale);
			}
		}
		
	}

	public void setCalculations() {
		for(Sale sale:this.ownSales) {
			for(Modifier modifier:Report.modifiers) {
				if(modifier.evaluate(sale)) {
					this.calculations.add(modifier.getResult(sale));
					break;
				}
			}
		}
		
	}
	public void save(String destination) {
		for(Sale sale:this.ownSales) {
			for(String att:Report.attributes) {
				sale.data.get(Sale.attributes.get(att));
			}
		}
		double sum=0.0;
		for(double v:this.calculations) {
			sum += v;
		}
		
		
	}

}
