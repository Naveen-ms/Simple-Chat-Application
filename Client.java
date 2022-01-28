package Sockets;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client_Pakka_Final implements ActionListener,KeyListener{
	
	private JFrame frame = new JFrame("Chat-Box");
	private JButton button = new JButton("Send");
	private JTextField text = new JTextField();
	private JTextArea show = new JTextArea();
	private Socket socket;
	private PrintWriter writer;
	private InputStreamReader incoming;
	private BufferedReader reader;
	private JScrollPane scrollpane = new JScrollPane(show);
	
	Client_Pakka_Final(){
		show.setBounds(0,0,300,320);
		show.setEditable(false);
		button.setBounds(220,330,65,30);
//		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollpane.setBounds(250,0,30,310);
//		scrollpane.getViewport().add(show);
//		frame.add(scrollpane);
		text.setBounds(0,330, 220, 30);
		frame.setSize(300, 400);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(text);
		frame.add(button);
		frame.add(show);
		text.addKeyListener(this);
		frame.setVisible(true);
		button.addActionListener(this);
	}
	
	public void send(String msg) {
		writer.println(msg);
		writer.flush();
	}
	
	public void set_up_connection() {
		try {
			socket = new Socket("localhost",7896);	
			incoming = new InputStreamReader(socket.getInputStream());
			reader = new BufferedReader(incoming);
			writer = new PrintWriter(socket.getOutputStream());
		}catch(Exception e) {}
	}

	public void read_server() {
		try {
			String H = "";
			while(true) {
			if((H=reader.readLine())!=null) {
			show.append("Server: "+ H);
			show.append("\n");
			}}
		}
		catch(Exception e) {}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			send(text.getText());
			show.append("Client: "+text.getText());
			show.append("\n");
			text.setText(null);
			
		}
		
	}
	
	public static void main(String [] args) {
		Client_Pakka_Final C = new Client_Pakka_Final();
		C.set_up_connection();
		C.read_server();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			System.out.print("IN");
			send(text.getText());
			show.append("Client: "+text.getText());
			show.append("\n");
			text.setText(null);
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
