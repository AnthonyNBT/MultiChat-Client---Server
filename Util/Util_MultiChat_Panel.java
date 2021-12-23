package Util;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class Util_MultiChat_Panel extends JPanel {
	private JTextField txtMess;
	private JButton btnSend;
	private JTextArea txaResult;
	
	private Socket socket;
	private String send;
	private String receive;
	private Util_MainUtil mainUtil;

	/**
	 * Create the panel.
	 */
	public Util_MultiChat_Panel(Socket socket, String send, String receive) {
		this.socket = socket;
		this.send = send;
		this.receive = receive;
		this.mainUtil = new Util_MainUtil();
				
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 570, 230);
		add(scrollPane);
		
		txaResult = new JTextArea();
		txaResult.setBounds(10, 10, 570, 230);
		scrollPane.setViewportView(txaResult);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 246, 570, 41);
		add(panel);
		panel.setLayout(null);
		
		txtMess = new JTextField();
		txtMess.setBounds(0, 0, 450, 40);
		panel.add(txtMess);
		txtMess.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtMess.getText().trim().equals("")) {
					txtMess.setText("");
					txtMess.requestFocus();	
					return;
				}
				
				String sendMessage = txtMess.getText();
				
				try {
					if (send.equals("Admin") && sendMessage.startsWith("/")) {
						String choose = sendMessage.substring(0, 4);
						if (!receive.equals("Admin")) {
							switch (choose) {
							case "/Cal":
								String resultCal = mainUtil.calculator(sendMessage);
								txaResult.setText(txaResult.getText() + "\nCalculator Result : " + resultCal);
								break;
							case "/Cov":
								String resultCov = mainUtil.convertor(sendMessage);
								txaResult.setText(txaResult.getText() + "\nConvertor Result : " + resultCov);
								break;
							case "/Cap":
								String resultCap = mainUtil.capital(sendMessage);
								txaResult.setText(txaResult.getText() + "\nCapital Result : " + resultCap);
								break;
							case "/Hel":
								if (sendMessage.substring(1, 5).equals("Help")) {
									String resultHelp = mainUtil.help();
									txaResult.setText(txaResult.getText() + "\nHelp Result : " + resultHelp);
									break;									
								}
							default:
								txaResult.setText(txaResult.getText() + "\nMe: " + sendMessage);
							}
						}
					}
					else {						
						DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
						dataOutputStream.writeUTF(sendMessage);
						txaResult.setText(txaResult.getText() + "\nMe: " + sendMessage);						
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
				txtMess.setText("");
				txtMess.requestFocus();			
			}
		});
		btnSend.setBounds(450, 0, 120, 40);
		panel.add(btnSend);
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		 try {
			 Util_OutputThread thread = new Util_OutputThread(socket, txaResult, send, receive);
			 thread.start();
		 } catch (Exception e) {
		 	e.printStackTrace();
		 }
	}
	
	public void addMessageResultPanel(String message) {
		txaResult.append(message);
	}
}
