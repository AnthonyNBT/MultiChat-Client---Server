package Calculator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Util.Util_MultiChat_Panel;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import Util.Util_MainUtil;


public class Calculator_Server extends JFrame implements Runnable{

	private JPanel contentPane;
	private int connectPort;
	private Thread thread;
	private Calculator_Server thisCalculatorServer;
	private ServerSocket serverSocket;
	private Util_MainUtil mainUtil;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator_Server frame = new Calculator_Server();
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
	public Calculator_Server() {
		this.connectPort = 4633;
		this.thisCalculatorServer = this;
		this.mainUtil = new Util_MainUtil();
		
		setTitle("Calculator_Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Start Server");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					serverSocket = new ServerSocket(connectPort);
					JOptionPane.showMessageDialog(null, "Server chay tai port: " + connectPort, "Thong bao", JOptionPane.DEFAULT_OPTION);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Server chay tai port: " + connectPort + " bi loi", "Loi", JOptionPane.ERROR_MESSAGE);
				}
				thread = new Thread(thisCalculatorServer);
				thread.start();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(10, 59, 416, 53);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Calculator Server");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 416, 39);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void run() {
		try {
			Socket connectSocketClient = serverSocket.accept();		
			while (true) {
				if(connectSocketClient !=null) {
					DataInputStream dataInputStream = new DataInputStream(connectSocketClient.getInputStream());
					String message = dataInputStream.readUTF();
					if(message != null && message.length() > 0) {
						DataOutputStream dataOutputStream = new DataOutputStream(connectSocketClient.getOutputStream());
						String resultCal = mainUtil.calculator(message);
						dataOutputStream.writeUTF(resultCal);
					}
				}
				Thread.sleep(100);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
