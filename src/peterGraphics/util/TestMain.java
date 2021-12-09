package peterGraphics.util;

import java.awt.Dimension;

import javax.swing.JFrame;

public class TestMain {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Graphics Test");
		Drawing draw = new Drawing();
		Graphic texture = new Graphic();
		Graphic texture2 = new Graphic();
		
		texture.rect(10,10,100,100,20,20,20);
		texture.rect(10,110,100,100,20,20,20);
		texture.rect(110,10,100,100,20,20,20);
		texture.rect(110,110,100,100,20,20,20);
		texture.rect(210,110,100,100,20,20,20);
		texture.rect(110,210,100,100,20,20,20);
		texture.rect(210,10,100,100,20,20,20);
		texture.rect(10,210,100,100,20,20,20);
		texture.rect(210,210,100,100,20,20,20);
		
		texture.circle(20,20,80,80,0,0,255);
		texture.line(120,120,200,200,255,0,0);
		texture.line(120,200,200,120,255,0,0);
		texture.circle(220,220,80,80,0,0,255);
		texture.circle(220,20,80,80,0,0,255);
		texture.line(20,220,100,300,255,0,0);
		texture.line(20,300,100,220,255,0,0);
		
		texture.rectF(0, 0, 10, 320,200,200,200);
		texture.rectF(0, 0, 320, 10,200,200,200);
		texture.rectF(311, 0, 10, 320,200,200,200);
		texture.rectF(0, 311, 320, 10,200,200,200);
		
		texture.polygon(0, 400, 50, 400, 25, 450, 255, 0, 0);
		texture.polygon(0, 450, 50, 450, 25, 400, 0, 255, 0);
		
		texture.polygonF(0,450, 50,450, 100,500, 50,500, 0,0,255);
		
		texture2.rectF(320, 320, 320, 320,0,0,0);
		texture2.circleF(320,320,320,320,255,0,0);
		
		texture2.text(400, 20, 'X', 0, 0, 0);
		draw.addGraphic(texture);
		draw.addGraphic(texture2);
		
		draw.text(400, 20, 'X', 0, 0, 0);
		//draw.addGraphic(texture2,100,100);
		frame.setPreferredSize(new Dimension(1000,1000));
		frame.getContentPane().add(draw);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
}
