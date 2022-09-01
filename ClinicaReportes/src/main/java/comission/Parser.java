package comission;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.regex.*;

public class Parser {
	private String _string;
	private Tokenizer _tokenizer;
	private JSONObject _lookahead;
	public Parser() {
		_string = new String();
		_tokenizer = new Tokenizer();
	}
	public JSONObject parse(String string){
		_string = string;
		_tokenizer.init(string);
		
		
		this._lookahead = this._tokenizer.getNextToken();
		return this.Program();
	}
	
	
	private JSONObject Program() {
		return new JSONObject().put("type", "Program").put("body", this.StatementList("null"));
	}
	private JSONArray StatementList(String stop) {
		JSONArray arr = new JSONArray();
		
		while(this._lookahead != null && !this._lookahead.getString("type").equals(stop)) {
			arr.put(this.Statement());
		}
		return arr;
	}
	private JSONObject Statement() {
		double value = _eat("NUMBER").getDouble("value");
		JSONArray arr = this.ComissionStatement();
		_eat(".");
		return new JSONObject().put("conditions", arr)
							   .put("pctg", value);
	}
	
	private JSONArray ComissionStatement() {
		JSONArray conditions = new JSONArray();
		while(this._lookahead != null && !this._lookahead.getString("type").equals(".")) {
			conditions.put(this.Condition());
		}
		return conditions;
	}
	private JSONObject Condition() {
		_eat("(");
		JSONObject left = this.Symbol();
		String operation = _eat("OPERATION").getString("value");
		JSONObject right = this.Symbol();
		_eat(")");
		return new JSONObject().put("type", "ConditionExpression")
							   .put("left", left)
							   .put("operation", operation)
							   .put("right", right);
	}
	private JSONObject Symbol() {
		if(this._lookahead.getString("type").equals("STRING")) {
			String aux = _eat("STRING").getString("value");
			return new JSONObject().put("type", "string")
							       .put("value", aux.substring(1, aux.length()-1));
		}
		else {
			return new JSONObject().put("type", "number")
								   .put("value", _eat("NUMBER").get("value"));
		}
	}
	private JSONObject _eat(String type) {
		JSONObject token=_lookahead;
		if(this._lookahead==null) {
			System.err.println("unex end of input");
			return null;
		}
		if(!this._lookahead.getString("type").equals(type)) {
			System.err.println("unexpected "+this._lookahead.getString("type")+" expected "+type);
			return null;
		}
		this._lookahead=_tokenizer.getNextToken();
		return token;
	}
	
	public static void main(String args[]) {

		String e2 = "5.2(\"Rubro\" = \"Servicios\")(\"CMP\" < 5.3).\n"
				  + "1.0(\"Fecha\" > 5).";
		Parser parser = new Parser();
		System.out.println(parser.parse(e2).toString(4));
		
		
		/*Pattern p = Pattern.compile("^\\blet\\b");
		Matcher m = p.matcher(" let x =12;"); 
		System.out.println(m.find());*/
		//System.out.println(m.end());
	}
}
