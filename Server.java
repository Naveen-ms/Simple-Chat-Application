package Sockets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server_pakka_final extends Thread implements ActionListener,KeyListener{
	private JFrame frame = new JFrame("Server-Box");
	private JButton button = new JButton("Send");
	private JTextField text = new JTextField();
	private JTextArea show = new JTextArea();
	private Socket socket;
	private ServerSocket ss;
	private PrintWriter writer;
	private InputStreamReader incoming;
	private BufferedReader reader;
	
	Server_pakka_final(){
		
		show.setBounds(0,0,300,320);
		show.setEditable(false);
		button.setBounds(220,330,65,30);
		text.setBounds(0,330, 220, 30);
		frame.setSize(300, 400);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(text);
		frame.add(button);
		frame.add(show);
		frame.setVisible(true);
		text.addKeyListener(this);
		button.addActionListener(this);
	}
	
	public void send(String msg) {
		writer.println(msg);
		writer.flush();
		
	}
	
	public void set_up_connection() {
		try {
			ss = new ServerSocket(7896);
			socket = ss.accept();	
			System.out.println("Connection Request");
			incoming = new InputStreamReader(socket.getInputStream());
			reader = new BufferedReader(incoming);
			writer = new PrintWriter(socket.getOutputStream());
		}catch(Exception e) {System.out.print("ININ");}
	}

	public void read_client() {
		try {
			String H = "";
			while(true) {
			if((H=reader.readLine())!=null) {
			show.append("Client: "+ H);
			show.append("\n");
			}}
		}
		catch(Exception e) {}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			send(text.getText());
			show.append("Server: "+text.getText());
			show.append("\n");
			text.setText(null);
		}
		
	}
	
	public static void main(String [] args) {
		Server_pakka_final Ser = new Server_pakka_final();
		Ser.set_up_connection();
		Ser.read_client();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			send(text.getText());
			show.append("Server: "+text.getText());
			show.append("\n");
			text.setText(null);
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
