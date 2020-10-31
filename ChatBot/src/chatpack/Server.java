package chatpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Server implements ActionListener {
	JFrame f;
	JPanel p1;
	JTextField t1;
	JButton b1;
	static JTextArea ta;

	static ServerSocket skt;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;

	Server() {
//		FRAME
		f = new JFrame("Server");
		f.setBounds(280, 80, 400, 600);
//		f.getContentPane().setBackground(Color.GREEN);(only for whole frame)
		f.setLayout(null);
		f.setUndecorated(true);

//		PANEL-1
		p1 = new JPanel();
		p1.setBounds(0, 0, 400, 80);
		p1.setBackground(new Color(7, 94, 84));
		p1.setLayout(null);

//		ARROW IMAGE
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("3.png"));
		Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l1 = new JLabel(i3);
		l1.setBounds(5, 25, 30, 30);

		l1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				System.exit(0);
			}
		});

//		GAITONDE IMAGE
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("1.png"));
		Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		JLabel l2 = new JLabel(i6);
		l2.setBounds(38, 8, 65, 65);

//		GAITONDE LABEL
		JLabel l3 = new JLabel("Gaitonde");
		l3.setBounds(110, 20, 100, 18);
		l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
		l3.setForeground(Color.WHITE);

		
//		ACTIVE NOW LABEL
		JLabel l4 = new JLabel("Active Now(S)");
		l4.setBounds(110, 42, 100, 18);
		l4.setFont(new Font("SAN_SERIF", Font.BOLD, 12));
		l4.setForeground(Color.WHITE);

//		VIDEO IMAGE
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("video.png"));
		Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i9 = new ImageIcon(i8);
		JLabel l5 = new JLabel(i9);
		l5.setBounds(262, 25, 30, 30);

//		PHONE IMAGE
		ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("phone.png"));
		Image i11 = i10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i12 = new ImageIcon(i11);
		JLabel l6 = new JLabel(i12);
		l6.setBounds(312, 25, 30, 30);

//		3-DOT IMAGE
		ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("3icon.png"));
		Image i14 = i13.getImage().getScaledInstance(12, 30, Image.SCALE_DEFAULT);
		ImageIcon i15 = new ImageIcon(i14);
		JLabel l7 = new JLabel(i15);
		l7.setBounds(360, 25, 12, 30);

//		TEXTAREA
		ta = new JTextArea();
		ta.setBounds(5, 85, 390, 470);
//		ta.setBackground(Color.red);
		ta.setEditable(false);
		ta.setForeground(Color.red);
		ta.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);

//		TEXT FIELD
		t1 = new JTextField();
		t1.setBounds(5, 560, 300, 36);
		t1.setFont(new Font("SAN_SERIF", Font.ITALIC, 18));
		f.add(t1);

//		BUTTON
		b1 = new JButton("send");
		b1.setBounds(310, 560, 82, 35);
		b1.setFont(new Font("SAN_SERIF", Font.ITALIC, 18));
		b1.setForeground(Color.white);
		b1.setBackground(new Color(7, 94, 84));
		b1.addActionListener(this);

		p1.add(l1);
		p1.add(l2);
		p1.add(l3);
		p1.add(l4);
		p1.add(l5);
		p1.add(l6);
		p1.add(l7);
		f.add(p1);
		f.add(b1);
		f.add(ta);
		f.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			String out = t1.getText();
			ta.setText(ta.getText() + "\n\t\t" + out);
			dout.writeUTF(out);
			t1.setText("");
		} catch (Exception e1) {

		}
	}

	public static void main(String[] args) {
		Server o = new Server();

		String msgInput = "";
		try {
			System.out.println("waiting for client...........");
			skt = new ServerSocket(9719);

			s = skt.accept();

			System.out.println("connected");

			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());

			while (true) {
				msgInput = din.readUTF();
				ta.setText(ta.getText() + "\n" + msgInput);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
