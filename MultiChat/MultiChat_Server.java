package MultiChat;

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
import javax.swing.JTabbedPane;
import java.awt.Panel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class MultiChat_Server extends JFrame implements Runnable {

	private JPanel contentPane;
	private JTextField txtPortConnect;
	private JTabbedPane tabbedPane;
	private ServerSocket serverSocket;
	private Thread thread;
	private MultiChat_Server thisChatServer;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultiChat_Server frame = new MultiChat_Server();
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
	public MultiChat_Server() {
		thisChatServer = this;
		setTitle("MultiChat_Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblNewLabel = new JLabel("PortConnect:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		txtPortConnect = new JTextField();
		txtPortConnect.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPortConnect.setText("2612");
		panel.add(txtPortConnect);
		txtPortConnect.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtPortConnect.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nhap day du thong tin", "Canh bao", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int connectPort = Integer.parseInt(txtPortConnect.getText());
				try {
					serverSocket = new ServerSocket(connectPort);
					JOptionPane.showMessageDialog(null, "Server chay tai port: " + connectPort, "Thong bao", JOptionPane.DEFAULT_OPTION);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Server chay tai port: " + connectPort + " bi loi", "Loi", JOptionPane.ERROR_MESSAGE);
				}
				thread = new Thread(thisChatServer);
				thread.start();
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(btnNewButton);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket connectSocketClient = serverSocket.accept();

				if (connectSocketClient != null) {
					DataInputStream dataInputStream = new DataInputStream(connectSocketClient.getInputStream());
					String startedClientLine = dataInputStream.readUTF();
					String clientName = "Stranger";
					if (startedClientLine.startsWith("ClientName")) {
						clientName  = startedClientLine.split(":")[1];
					}
					Util_MultiChat_Panel clientChatPanel = new Util_MultiChat_Panel(connectSocketClient, "Admin", clientName);
					tabbedPane.add(clientName, clientChatPanel);
					clientChatPanel.addMessageResultPanel("Thong bao: " + clientName + " da ket noi den he thong");
					clientChatPanel.updateUI();
				}
				
				Thread.sleep(100);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
