package comission;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import exceptions.ParsingException;
import model.Sale;



public class Evaluator {
	
	ArrayList<JSONObject>_statements;
	
	/**
	 * Reads a AST in JSON format
	 * @param program
	 */
	public Evaluator(JSONObject program){
		
		_statements = new ArrayList<JSONObject>();
		for(Object o:program.getJSONArray("body")) {
			_statements.add((JSONObject)o);
		}
	}
	
	public double getNext(Sale sale) {
		for(int i=0; i<this._statements.size();i++) {
			JSONObject query = this._statements.get(i);
			boolean result = this._evaluate(query, sale);
			if(result)return query.getDouble("pctg");
		}
		return 0.0;
	}
	
	private boolean _evaluate(JSONObject query, Sale sale) {
		
		boolean b = true;
		JSONArray arr = query.getJSONArray("conditions");
		for(int i=0;i<arr.length();i++) {
			if(!this._evaluateCondition(arr.getJSONObject(i), sale)) {
				return false;
			}
		}
				
		return b;
		
	}
	
	private boolean _evaluateCondition(JSONObject condition, Sale sale) {
		String left = condition.getJSONObject("left").getString("value");
		String op = condition.getString("operation");
		JSONObject right = condition.getJSONObject("right");
		
		
		if(right.getString("type").equals("string")) {
			return sale.data.get(Sale.attributes.get(left)).equals(right.getString("value"));
		}
		else {//always subtotal
			double r = right.getDouble("value");
			if(sale.subtotal>=0) {
				if(op.equals("<")) {
					return sale.subtotal < r;
				}
				else {
					return sale.subtotal > r;
				}
			}
			else {
				if(op.equals("<")) {
					return -sale.subtotal < r;
				}
				else {
					return -sale.subtotal > r;
				}
			}
		}
	}

	public static void main(String args[]) throws ParsingException {
		String test1 = "1.4(\"Rubro\" = \"Servicios\")(\"Subtotal\" < 55).\n"
				     + "2.4(\"Subtotal\" > 55).";
		Parser parser = new Parser();
		JSONObject program = parser.parse(test1);
		System.out.println(program.toString(4));
		
		Evaluator evaluator = new Evaluator(program);
		
		
		Sale sale = new Sale();
		for(int i =0;i<20;i++) {
			sale.data.add("a");
		}
		sale.data.set(Sale.attributes.get("Rubro"), "Servicios");
		sale.subtotal = 40.2;
		System.out.println("Resulting: "+evaluator.getNext(sale));
	}
}
