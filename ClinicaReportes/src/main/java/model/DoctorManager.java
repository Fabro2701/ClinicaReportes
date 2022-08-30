package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import exceptions.FileException;
import exceptions.IllegalDBModificationException;
import model.person.Doctor;

public class DoctorManager extends ArrayList<Doctor> {
	public DoctorManager() {
		super();
	}
	public void loadFromFile(String filename) throws FileException, JSONException, IllegalDBModificationException {
		JSONArray arr = null;
		try {
			arr = new JSONArray(new JSONTokener(new FileInputStream(filename)));
			for(int i=0;i<arr.length();i++) {
				this.addDoctor(new Doctor(arr.getJSONObject(i)));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FileException("Problemas leyendo el archivo: "+filename);
		}
		
		
	}
	public void saveToFile(String filename) throws FileException {
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(filename));
			out.write(this.toJSON().toString(4));
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileException("Problemas guardando el archivo: "+filename);
		}
		
	}
	public void addDoctor(Doctor doctor) throws IllegalDBModificationException {
		if(this.contains(doctor)) {
			throw new IllegalDBModificationException("Un médico ya está registrado con el CMP: "+doctor.getCMP());
		}
		this.add(doctor);
		System.out.println("Doctor added:{ "+doctor+" }");
	}
	public void removeDoctor(Doctor doctor) throws IllegalDBModificationException {
		if(!this.contains(doctor)) {
			throw new IllegalDBModificationException("No hay ningún médico registrado con el CMP: "+doctor.getCMP());
		}
		this.remove(doctor);
		System.out.println("Doctor removed: "+doctor+" }");
	}
	public JSONArray toJSON() {
		JSONArray arr = new JSONArray();
		for(Doctor doctor:this) {
			arr.put(doctor.toJSON());
		}
		return arr;
	}
	
}
