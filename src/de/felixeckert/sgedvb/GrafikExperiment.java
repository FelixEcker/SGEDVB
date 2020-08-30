package de.felixeckert.sgedvb;

import de.felixeckert.sgedvb.grafik.HexagonGraphicsEngine;

public class GrafikExperiment implements Runnable {
	protected Render render;
	private Thread thread;
	private HexagonGraphicsEngine hge;
	protected int width, height;
	
	public GrafikExperiment(Render render, int width, int height) {
		this.render = render;
		this.width = width;
		this.height = height;
	}
	
	public void start() throws Exception {	
		this.hge = new HexagonGraphicsEngine(new int[] {width, height}, "Grafik Experiment", render);
		this.thread = new Thread(this, "GrafikExperiment");
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			hge.getRenderer().render();
		}
	}
}
