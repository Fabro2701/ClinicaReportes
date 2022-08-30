package model.person;

import org.json.JSONObject;

public class Doctor extends Person implements Comparable<Doctor>{

	protected String CMP;
	public Doctor(String fullname, String CMP) {
		super(fullname);
		this.CMP = CMP;
		// TODO Auto-generated constructor stub
	}
	public Doctor(JSONObject jsonObject) {
		this(jsonObject.getString("fullname"),jsonObject.getString("CMP"));
	}
	public String toString() {
		return "Doctor: "+super.toString()+", CMP: "+CMP;
	}
	@Override
	public int compareTo(Doctor o) {
		return CMP.compareTo(o.CMP);
	}
	public String getCMP() {
		return CMP;
	}
	public void setCMP(String CMP) {
		this.CMP = CMP;
	}
	@Override
	public boolean equals(Object doctor) {
		return this.equals((Doctor)doctor);
	}
	public boolean equals(Doctor doctor) {
		return this.CMP.equals(doctor.CMP);
	}
	public JSONObject toJSON(){
		return new JSONObject().put("CMP", CMP).put("fullname", fullname);
	}
}
