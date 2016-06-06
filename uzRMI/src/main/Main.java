package main;

import java.awt.EventQueue;

import logic.MyFrame;

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
