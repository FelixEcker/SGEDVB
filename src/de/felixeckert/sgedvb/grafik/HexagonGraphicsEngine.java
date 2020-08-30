package de.felixeckert.sgedvb.grafik;

import de.felixeckert.sgedvb.Render;

public class HexagonGraphicsEngine {
	private int[] resolution;
	private GameWindow gameWindow;
	private MainRenderer renderer;
	
	public HexagonGraphicsEngine(int[] resolution, String title, Render render) {
		this.resolution = resolution;
		if (this.resolution == null) {
			System.out.println("[WRN] HGE: WARNING: THE CLIENT DID NOT SPECIFY THE RESOLUTION; SETTING RESOLUTION TO 1024x512!");
			this.resolution = new int[] {1024, 512};
		}
		
		this.gameWindow = new GameWindow(resolution, title);
		this.renderer = new MainRenderer(render, gameWindow);
	}
	
	public GameWindow getGameWindow() { return this.gameWindow; }
	public MainRenderer getRenderer() { return this.renderer; }
}
