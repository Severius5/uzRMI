package panels;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public MyFrame(){
		super("Okienko");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(500, 300);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel menuPanel = new MenuPanel();
		add(menuPanel);
		
		pack();
		
	}

}
