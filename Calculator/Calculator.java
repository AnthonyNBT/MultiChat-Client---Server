package Calculator;

public class Calculator {
	private String add(double a, double b) {
		return "" + (a + b);
	}
	
	private String sub(double a, double b) {
		return "" + (a - b);
	}
	
	private String mul(double a, double b) {
		return "" + (a * b);
	}
	
	private String div(double a, double b) {
		if (b == 0.0) {
			return "Khong the chia cho 0";
		}
		return "" + (a / b);
	}
	
	private String pow(double a, double b) {
		return "" + Math.pow(a, b);
	}
	
	public String calculatorAll(String typeIn, double a, double b) {
		switch(typeIn) {
			case "add":
				return add(a, b);
			case "sub":
				return sub(a, b);
			case "mul":
				return mul(a, b);
			case "div":
				return div(a, b);
			case "pow":
				return pow(a, b);
		}
		return "Tinh toan loi";
	}
}
