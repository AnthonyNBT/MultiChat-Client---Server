package Convertor;

public class Convertor {
	private String dec2hex(int num) {
		return Integer.toHexString(num);
	}
	
	private String dec2bin(int num) {
		return Integer.toBinaryString(num);
	}
	
	private String dec2oct(int num) {
		return Integer.toOctalString(num);
	}
	
	private String hex2dec(String num) {
		try {
			return "" + Integer.parseInt(num, 16);
		} catch (Exception e) {
			return "null";
		}
		
	}
	
	private String bin2dec(String num) {
		return "" + Integer.parseInt(num, 2);
	}
	
	private String oct2dec(String num) {
		return "" + Integer.parseInt(num, 8);
	}
	
	public String convertorAll(String typeIn, String typeOut, String num) {
		String result = "";
		switch (typeIn) {
			case "dec":
				result = num;
				break;
			case "hex":
				result = hex2dec(num);
				break;
			case "bin":
				result = bin2dec(num);
				break;
			case "oct":
				result = oct2dec(num);
				break;
		}
		
		int tmp;
		
		try {
			tmp = Integer.parseInt(result);			
		} catch (Exception e) {
			return "Input loi";
		}
		
		switch (typeOut) {
			case "dec":
				break;
			case "hex":
				result = dec2hex(tmp);
				break;
			case "bin":
				result = dec2bin(tmp);
				break;
			case "oct":
				result = dec2oct(tmp);
				break;
		}
		
		return result;
	}
}
