package Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Arrays;

import javax.swing.JTextArea;

import Util.Util_MainUtil;

public class Util_OutputThread extends Thread {
	Socket socket;
	JTextArea txaResult;
	DataInputStream dataInputStream;
	String send;
	String receive;
	Util_MainUtil mainUtil;
	
	public Util_OutputThread(Socket socket, JTextArea txaResult, String send, String receive) {
		super();
		this.socket = socket;
		this.txaResult = txaResult;
		this.send = send;
		this.receive = receive;
		this.mainUtil = new Util_MainUtil();
		try {
			dataInputStream = new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(true) {
			try {
				if(socket !=null) {
					String message = "";
					if((message = dataInputStream.readUTF()) != null && message.length() > 0) {
						String choose = message.substring(0, 4);
						DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
						if (!receive.equals("Admin")) {
							switch (choose) {
							case "/Cal":
								String resultCal = mainUtil.calculator(message);
								dataOutputStream.writeUTF("Calculator Result : " + resultCal);
								break;
							case "/Cov":
								String resultCov = mainUtil.convertor(message);
								dataOutputStream.writeUTF("Convertor Result : " + resultCov);
								break;
							case "/Cap":
								String resultCap = mainUtil.capital(message);
								dataOutputStream.writeUTF("Capital Result : " + resultCap);
								break;
							case "/Hel":
								if (message.substring(1, 5).equals("Help")) {
									String resultHelp = mainUtil.help();
									dataOutputStream.writeUTF("Help Result : " + resultHelp);
									break;									
								}
							default:
								txaResult.setText(txaResult.getText() + "\n" + receive +": " + message);
							}
						}
						else {
							txaResult.setText(txaResult.getText() + "\n" + receive +": " + message);
						}
					}
					sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
