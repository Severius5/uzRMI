package main;

import java.awt.EventQueue;

import panels.MyFrame;

// TESTOWE
public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable(){

			@Override
			public void run() {
				new MyFrame();
			}
			
		});
			
	}

}
