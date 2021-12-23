package Menu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import MultiChat.*;
import Calculator.*;
import Convertor.*;
import javax.swing.ImageIcon;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 457);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("MultiChat");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("MultiChat_Server");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MultiChat_Server fame = new MultiChat_Server();
				fame.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("MultiChat_Client");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MultiChat_Client fame = new MultiChat_Client();
				fame.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Calculator");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Calculator_Server");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calculator_Server fame = new Calculator_Server();
				fame.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Calculator_Client");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calculator_Client fame = new Calculator_Client();
				fame.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Convertor");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Convertor_Server");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Convertor_Server fame = new Convertor_Server();
				fame.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Convertor_Client");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Convertor_Client fame = new Convertor_Client();
				fame.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nguy\u1EC5n B\u00ECnh Th\u0103ng - 51900831");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 10, 635, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblTrnThThanh = new JLabel("Tr\u1EA7n Th\u1ECB Ph\u01B0\u01A1ng Thanh - 51900832");
		lblTrnThThanh.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrnThThanh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTrnThThanh.setBounds(10, 50, 635, 30);
		contentPane.add(lblTrnThThanh);
		
		JLabel lblNewLabel_1_1 = new JLabel("T\u0103ng Ch\u1EA5n H\u01B0ng - 51900797");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(10, 90, 635, 30);
		contentPane.add(lblNewLabel_1_1);
		
		JTextArea txtrCnKhi = new JTextArea();
		txtrCnKhi.setEditable(false);
		txtrCnKhi.setText("1/. C\u1EA7n kh\u1EDFi \u0111\u1ED9ng Server tr\u01B0\u1EDBc khi ch\u1EA1y Client \u1EDF t\u1EA5t c\u1EA3 c\u00E1c m\u1EE5c\r\n2/. MultiChat c\u00F3 th\u1EC3 th\u1EF1c hi\u1EC7n Chat c\u00F9ng l\u00FAc 1 Server v\u1EDBi nhi\u1EC1u Client\r\n3/. C\u00F3 th\u1EC3 th\u1EF1c hi\u1EC7n c\u00E1c bi\u1EC3u th\u1EE9c bi\u1EBFn \u0111\u1ED5i tr\u1EF1c ti\u1EBFp trong c\u1EEDa s\u1ED5 Chat th\u00F4ng qua\r\n\tCommand Prefix: \"/\"\r\n\t\tC\u00FA ph\u00E1p:\r\n\t\t\tD\u00F9ng \u0111\u1EC3 th\u1EF1c hi\u1EC7n c\u00E1c ph\u00E9p t\u00EDnh (C\u1ED9ng, tr\u1EEB, nh\u00E2n, chia, m\u0169):\r\n\t\t\t\t\"/Cal <add/sub/mul/div/pow> num1 num2\"\r\n\t\t\tD\u00F9ng \u0111\u1EC3 chuy\u1EC3n \u0111\u1ED5i c\u01A1 s\u1ED1 gi\u1EEFa c\u00E1c h\u1EC7 v\u1EDBi nhau:\r\n\t\t\t\t\"/Cov <dec/bin/hex/oct> <dec/bin/hex/oct> numInt\"\r\n\t\t\tD\u00F9ng \u0111\u1EC3 thao t\u00E1c v\u1EDBi chu\u1ED7i:\r\n\t\t\t\t\"/Cap <upper/lower/count> str\"\r\n\t\t\tD\u00F9ng \u0111\u1EC3 hi\u1EC7n tr\u1EE3 gi\u00FAp v\u1EC1 d\u00F2ng l\u1EC7nh:\r\n\t\t\t\t\"/Help\"\r\n4/. H\u1EC7 th\u1ED1ng ti\u1EC7n \u00EDch c\u00F3 th\u1EC3 d\u00F9ng \u1EDF c\u1EA3 2 ph\u00EDa Client v\u00E0 Server\r\n5/. Ngo\u00E0i ra c\u00F3 th\u1EC3 s\u1EED d\u1EE5ng ti\u1EC7n \u00EDch th\u00F4ng qua GUI Calculator_<Server/Client> \r\n\tho\u1EB7c Convertor_<Server/Client>");
		txtrCnKhi.setBounds(10, 130, 635, 258);
		contentPane.add(txtrCnKhi);
	}
}
