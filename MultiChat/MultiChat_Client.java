package MultiChat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Util.Util_MultiChat_Panel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class MultiChat_Client extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtPortConnect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultiChat_Client frame = new MultiChat_Client();
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
	public MultiChat_Client() {
		JFrame thisFrame = this;
		setTitle("MultiChat_Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 6, 0, 0));
		
		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblName);
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setLabelFor(txtName);
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtName.setColumns(10);
		panel_1.add(txtName);
		
		JLabel lblNewLabel_1 = new JLabel("IP Address:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblNewLabel_1);
		
		txtAddress = new JTextField();
		txtAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setLabelFor(txtAddress);
		txtAddress.setText("localhost");
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtAddress.setColumns(10);
		panel_1.add(txtAddress);
		
		JLabel lblNewLabel_2 = new JLabel("Port:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lblNewLabel_2);
		
		txtPortConnect = new JTextField();
		txtPortConnect.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setLabelFor(txtPortConnect);
		txtPortConnect.setText("2612");
		txtPortConnect.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPortConnect.setColumns(10);
		panel_1.add(txtPortConnect);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtName.getText().equals("") || txtAddress.getText().equals("") || txtPortConnect.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nhap day du thong tin", "Canh bao", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String clientName = txtName.getText();
				String connectAddress = txtAddress.getText();
				int connectPort = Integer.parseInt(txtPortConnect.getText());
				try {
					Socket connectServerSocket = new Socket(connectAddress, connectPort);
					
					if(connectServerSocket != null) {
						Util_MultiChat_Panel chatPanel = new Util_MultiChat_Panel(connectServerSocket, clientName, "Admin");
						thisFrame.getContentPane().add(chatPanel);
						chatPanel.addMessageResultPanel("Ban da co the nhan tin");
						chatPanel.updateUI();
					}
					
					DataOutputStream dataOutputStream = new DataOutputStream(connectServerSocket.getOutputStream());
					dataOutputStream.writeUTF("ClientName:" + clientName);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Loi " + ex, "Canh bao", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(btnConnect);
	}
}
