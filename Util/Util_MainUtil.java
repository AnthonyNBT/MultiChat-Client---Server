package Util;

import java.util.Arrays;
import Calculator.Calculator;
import Convertor.Convertor;

public class Util_MainUtil {
	private Calculator calculate;
	private Convertor convert;
	
	public Util_MainUtil() {
		this.calculate = new Calculator();
		this.convert = new Convertor();
	}
	
	public String calculator(String message) {
		String result = "Sai cu phap ; Cu phap dung: /Cal <add/sub/mul/div/pow> Num1 Num2";
		String[] operation = {"add", "sub", "mul", "div", "pow"};
		String[] handle = message.split(" ");
		
		if (handle.length == 4) {
			if (Arrays.asList(operation).contains(handle[1])) {
				double num1;
				double num2;
				try {
					num1 = Double.parseDouble(handle[2]);
					num2 = Double.parseDouble(handle[3]);
					
					result = calculate.calculatorAll(handle[1], num1, num2);
				} catch (NumberFormatException ex) { }
			}
		}
		return result;
	}
	
	public String convertor(String message) {
		String result = "Sai cu phap ; Cu phap dung: /Cov <dec/bin/hex/oct> <dec/bin/hex/oct> Num";
		String[] operation = {"dec", "bin", "hex", "oct"};
		String[] handle = message.split(" ");
		
		if (handle.length == 4) {
			if (Arrays.asList(operation).contains(handle[1]) && Arrays.asList(operation).contains(handle[2])) {
				if (handle[1].equals(handle[2])) {
					result = "Convertor Duplicate";
				}
				else {
					result = convert.convertorAll(handle[1], handle[2], handle[3]);		
				}
			}
		}
		return result;
	}
	
	public String capital(String message) {
		String result = "Sai cu phap ; Cu phap dung: /Cap <upper/lower> String";
		String[] operation = {"upper", "lower", "count"};
		String[] handle = message.split(" ");
		
		if (Arrays.asList(operation).contains(handle[1])) {
			if (handle[1].equals("upper")) {
				result = message.substring(11).toUpperCase();
			}
			else if (handle[1].equals("lower")){
				result = message.substring(11).toLowerCase();	
			}
			else {
				result = "" + message.substring(11).length();
			}
		}

		return result;
	}
	
	public String help() {
		return "Command prefix - '/'"
				+ "\n\t Cal - Calculator Ham dung de tinh toan"
				+ "\n\t\t add - Phep cong"
				+ "\n\t\t sub - Phep tru"
				+ "\n\t\t mul - Phep nhan"
				+ "\n\t\t div - Phep chia"
				+ "\n\t\t pow - Phep lay mu"
				+ "\n\t Cov - Converter Ham dung de chuyen doi co so"
				+ "\n\t\t dec - So thap phan"
				+ "\n\t\t bin - So nhi phan"
				+ "\n\t\t hex - So thap luc phan"
				+ "\n\t\t oct - So bat phan"
				+ "\n\t Cap - Capital ham dung de xy ly chuoi"
				+ "\n\t\t upper - UpperCase chuoi"
				+ "\n\t\t lower - LowerCase chuoi"
				+ "\n\t\t count - Do dai chuoi";
	}
}
