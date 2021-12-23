	package Convertor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Convertor_Client extends JFrame {

	private JPanel contentPane;
	private JTextField txtInput;
	private JTextField txtOutput;
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
					Convertor_Client frame = new Convertor_Client();
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
	public Convertor_Client() {
		this.connectPort = 7245;
		this.connectAddress = "localhost";
		this.isConnect = false;
		
		setTitle("Convetor_Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 437, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox cbTypeIn = new JComboBox();
		cbTypeIn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbTypeIn.setModel(new DefaultComboBoxModel(new String[] {"dec", "bin", "hex", "oct"}));
		cbTypeIn.setBounds(77, 80, 120, 21);
		contentPane.add(cbTypeIn);
		
		JComboBox cbTypeOut = new JComboBox();
		cbTypeOut.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbTypeOut.setModel(new DefaultComboBoxModel(new String[] {"dec", "bin", "hex", "oct"}));
		cbTypeOut.setBounds(297, 80, 120, 21);
		contentPane.add(cbTypeOut);
		
		JLabel lblNewLabel = new JLabel("TypeIn");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 82, 57, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblTypeout = new JLabel("TypeOut");
		lblTypeout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTypeout.setBounds(207, 82, 80, 13);
		contentPane.add(lblTypeout);
		
		JLabel label = new JLabel("New label");
		label.setBounds(111, 84, 45, 13);
		contentPane.add(label);
		
		txtInput = new JTextField();
		txtInput.setText("16");
		txtInput.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtInput.setBounds(77, 111, 340, 45);
		contentPane.add(txtInput);
		txtInput.setColumns(10);
		
		txtOutput = new JTextField();
		txtOutput.setBackground(Color.LIGHT_GRAY);
		txtOutput.setEditable(false);
		txtOutput.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtOutput.setColumns(10);
		txtOutput.setBounds(77, 220, 340, 45);
		contentPane.add(txtOutput);
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtInput.getText().trim().equals("") ) {
					txtInput.requestFocus();	
					JOptionPane.showMessageDialog(null, "Nhap day du thong tin Input", "Canh bao", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if (cbTypeIn.getSelectedItem().toString().equals(cbTypeOut.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(null, "Chon 2 kieu chuyen doi du lieu khac nhau", "Canh bao", JOptionPane.WARNING_MESSAGE);
					}
					else {
						String typeIn = cbTypeIn.getSelectedItem().toString();
						String typeOut = cbTypeOut.getSelectedItem().toString();
						
						try {
							int input = Integer.parseInt(txtInput.getText());
							String sendMessage = "/Cov " + typeIn + " " + typeOut + " " + input;
							
							try {					
								DataOutputStream dataOutputStream = new DataOutputStream(connectServerSocket.getOutputStream());
								dataOutputStream.writeUTF(sendMessage);
								String result = "";
								DataInputStream dataInputStream;
								while((dataInputStream = new DataInputStream(connectServerSocket.getInputStream())) == null) {}
								result = dataInputStream.readUTF();				
								txtOutput.setText(result);					
							} catch (IOException ex) {
								ex.printStackTrace();
							}
							
							txtInput.requestFocus();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Nhap Input la so nguyen", "Canh bao", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				
				
			}
		});
		btnConvert.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnConvert.setBounds(77, 165, 340, 45);
		contentPane.add(btnConvert);
		
		JLabel lblInput = new JLabel("Input");
		lblInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInput.setBounds(10, 127, 57, 13);
		contentPane.add(lblInput);
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOutput.setBounds(10, 236, 57, 13);
		contentPane.add(lblOutput);
		
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
		btnConnect.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnConnect.setBounds(10, 10, 407, 45);
		contentPane.add(btnConnect);
	}
}
