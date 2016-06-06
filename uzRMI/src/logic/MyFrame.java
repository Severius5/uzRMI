package logic;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public MyFrame(){
		super("Okienko");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(200, 120);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(new JButton("Button 1"));
		add(new JButton("Button 2"));
		add(new JButton("Button 3"));
	}

}
