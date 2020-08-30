package de.felixeckert.sgedvb.experimente;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import de.felixeckert.sgedvb.GrafikExperiment;
import de.felixeckert.sgedvb.Render;
import de.felixeckert.sgedvb.grafik.HexagonGraphicsEngine;

public class Bleeps extends GrafikExperiment {
	private Thread t;
	private HexagonGraphicsEngine graphics;
	
	public Bleeps(int width, int height) {
		super(null, width, height);
	}
	
	@Override
	public void start() throws Exception {
		Scanner s = new Scanner(System.in);
		String in;
		
		Properties config = new Properties();
		
		while (true) {
			System.out.print("Konfigurations Datei (Leer lassen für Standard Konfig): ");
			in = s.nextLine();
			
			if (in.matches("")) {
				InputStream input = getClass().getResourceAsStream("/configs/bleepsStandard.properties");
				config.load(input);
				s.close();
				break;
			} else {
				InputStream input = new FileInputStream(in);
				config.load(input);
				s.close();
				break;
			}
		}
		
		int[] params = getParams(config);
		
		this.graphics = new HexagonGraphicsEngine(new int[] {params[3], params[4]}, "Grafik Experiment", new BleepsRender(
				Integer.parseInt(String.valueOf(params[3]).substring(0,String.valueOf(params[3]).length()-1)),
				Integer.parseInt(String.valueOf(params[4]).substring(0,String.valueOf(params[4]).length()-1)),
				params,
				Boolean.valueOf(config.getProperty("raised")),
				Boolean.valueOf(config.getProperty("raisedRandom"))
				));
		
		t = new Thread(this, "Bleeps-Experiment");
		t.start();
	}
	
	@Override
	public void run() {
		while (true) {
			graphics.getRenderer().render();
		}
	}
	
	private int[] getParams(Properties cfg) throws Exception {		
		return new int[] {
				Integer.parseInt(cfg.getProperty("rBound")),
				Integer.parseInt(cfg.getProperty("gBound")),
				Integer.parseInt(cfg.getProperty("bBound")),
				Integer.parseInt(cfg.getProperty("width")),
				Integer.parseInt(cfg.getProperty("height"))
		};
	}
	
	public static class BleepsRender extends Render {
		private int width, height;
		private int[] bounds;
		private boolean raised, raisedRandom;
		
		public BleepsRender(int width, int height, int[] bounds, boolean raised, boolean raisedRandom) {
			this.width = width;
			this.height = height;
			this.bounds = bounds;
			this.raised = raised;
			this.raisedRandom = raisedRandom;
			System.out.println("Konfig:");
			System.out.println("	WIDTH:        "+width*10);
			System.out.println("	HEIGHT:       "+height*10);
			System.out.println("	rBOUND:       "+bounds[0]);
			System.out.println("	gBOUND:       "+bounds[1]);
			System.out.println("	bBOUBD:       "+bounds[2]);
			System.out.println("	RAISED:       "+raised);
			System.out.println("	RAISEDRANDOM: "+raisedRandom);
		}
		
		@Override
		public void render(Graphics g) {
			for (int x = 0; x < this.width; x++) {
				for (int y = 0; y < this.height; y++) {
					Color color = new Color(ThreadLocalRandom.current().nextInt(bounds[0]), ThreadLocalRandom.current().nextInt(bounds[1]), ThreadLocalRandom.current().nextInt(bounds[2]));
					g.setColor(color);
					if (this.raisedRandom)
						this.raised = ThreadLocalRandom.current().nextBoolean();
					
					g.fill3DRect(x*10, y*10, 10, 10, this.raised);
				}
			}
		}
	}
}
