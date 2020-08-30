package de.felixeckert.sgedvb.grafik;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import de.felixeckert.sgedvb.Render;

public class MainRenderer {
	private BufferStrategy bufferStrat;
	private Graphics g;
	private Render render;
	private GameWindow win;
	
	public MainRenderer(Render render, GameWindow win) {
		this.render = render;
		this.win = win;
	}
	
	public void render() {
		bufferStrat = win.getBufferStrategy();
		
		if (bufferStrat == null) {
			win.createBufferStrategy(3);
			return;
		}
		
		g = bufferStrat.getDrawGraphics();
		
		g.clearRect(0, 0, win.getWidth(), win.getHeight());
		
		render.render(g);
		
		g.dispose();
		bufferStrat.show();
	}
}
