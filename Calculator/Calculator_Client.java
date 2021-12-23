package Calculator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Util.Util_MultiChat_Panel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class Calculator_Client extends JFrame {

	private JPanel contentPane;
	private JTextField txtNum1;
	private JTextField txtNum2;
	private JTextField txtResult;
	private int connectPort;
	private String connectAddress;
	private Socket connectServerSocket;
	private boolean isConnect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator_Client frame = new Calculator_Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Calculator_Client() {
		this.connectPort = 4633;
		this.connectAddress = "localhost";
		this.isConnect = false;
		
		setTitle("Calculator_Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Number 1:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(15, 56, 80, 13);
		contentPane.add(lblNewLabel);
		
		txtNum1 = new JTextField();
		txtNum1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNum1.setBounds(105, 52, 100, 20);
		contentPane.add(txtNum1);
		txtNum1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Number 2:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(215, 56, 80, 13);
		contentPane.add(lblNewLabel_1);
		
		txtNum2 = new JTextField();
		txtNum2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNum2.setColumns(10);
		txtNum2.setBounds(307, 52, 100, 20);
		contentPane.add(txtNum2);
		
		txtResult = new JTextField();
		txtResult.setBackground(Color.LIGHT_GRAY);
		txtResult.setEditable(false);
		txtResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtResult.setBounds(90, 139, 346, 40);
		contentPane.add(txtResult);
		txtResult.setColumns(10);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblResult.setBounds(20, 152, 60, 13);
		contentPane.add(lblResult);
		
		JButton btnAdd = new JButton("+");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle("add");
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.setBounds(10, 79, 80, 50);
		contentPane.add(btnAdd);
		
		JButton btnSub = new JButton("-");
		btnSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle("sub");
			}
		});
		btnSub.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSub.setBounds(95, 79, 80, 50);
		contentPane.add(btnSub);
		
		JButton btnMul = new JButton("*");
		btnMul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle("mul");
			}
		});
		btnMul.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnMul.setBounds(181, 79, 80, 50);
		contentPane.add(btnMul);
		
		JButton btnDiv = new JButton("/");
		btnDiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle("div");
			}
		});
		btnDiv.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDiv.setBounds(267, 79, 80, 50);
		contentPane.add(btnDiv);
		
		JButton btnPow = new JButton("^");
		btnPow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handle("pow");
			}
		});
		btnPow.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPow.setBounds(357, 79, 80, 50);
		contentPane.add(btnPow);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connectServerSocket = new Socket(connectAddress, connectPort);
					JOptionPane.showMessageDialog(null, "Da " + ((isConnect) ? "ngat " : "") + "ket noi den Server", "Thong bao", JOptionPane.INFORMATION_MESSAGE);
					isConnect = !isConnect;
					btnConnect.setText(isConnect ? "Disconect" : "Connect");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Loi " + ex, "Canh bao", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConnect.setBounds(15, 12, 421, 21);
		contentPane.add(btnConnect);
	}
	
	private void handle(String typeIn) {
		if (txtNum1.getText().trim().equals("")) {
			txtNum1.requestFocus();	
			JOptionPane.showMessageDialog(null, "Nhap day du thong tin Num1", "Canh bao", JOptionPane.WARNING_MESSAGE);
		}
		else if (txtNum2.getText().trim().equals("")) {
			txtNum2.requestFocus();	
			JOptionPane.showMessageDialog(null, "Nhap day du thong tin Num2", "Canh bao", JOptionPane.WARNING_MESSAGE);
		}
		else {
			double num1 = Double.parseDouble(txtNum1.getText());
			double num2 = Double.parseDouble(txtNum2.getText());
			
			String sendMessage = "/Cal " + typeIn + " " + num1 + " " + num2;
			
			try {					
				DataOutputStream dataOutputStream = new DataOutputStream(connectServerSocket.getOutputStream());
				dataOutputStream.writeUTF(sendMessage);
				String result = "";
				DataInputStream dataInputStream;
				while((dataInputStream = new DataInputStream(connectServerSocket.getInputStream())) == null) {}
				result = dataInputStream.readUTF();				
				txtResult.setText(result);					
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
			txtNum1.setText("");
			txtNum1.requestFocus();
			txtNum2.setText("");
		}
	}
}
