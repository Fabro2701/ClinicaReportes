package comission;

import org.json.JSONObject;

import model.Sale;

public class ComissionsManager {
	public static Parser parser = new Parser();
	Evaluator evaluator;
	public ComissionsManager() {
	}
	public double getComissions(Sale sale) {
		return sale.subtotal* evaluator.getNext(sale) / 100.0;
	}
	public void update(JSONObject program) {
		evaluator = new Evaluator(program);
	}
}
