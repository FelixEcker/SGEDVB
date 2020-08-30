package de.felixeckert.sgedvb;

import java.util.HashMap;
import java.util.Scanner;

import de.felixeckert.sgedvb.experimente.Bleeps;

public class Main {
	public static void main(String[] args) throws Exception {
		System.out.println("Kekkis SGEDVB");

		HashMap<String, GrafikExperiment> experimente = new HashMap<>();
		GrafikExperiment experiment;
		
		experimente.put("Bleeps", new Bleeps(1920, 1080));
		
		Scanner s = new Scanner(System.in);
		String in;
		
		while (true) {
			System.out.print("Welches \"Experiment\"? ");
			in = s.nextLine();
			
			if (in.matches("exitProgram")) {
				System.exit(0);
			}
			
			if (experimente.containsKey(in)) {
				experiment = experimente.get(in);
				break;
			} else {
				System.out.println("Es gibt kein Experiment mit den Namen \""+in+"\"");
			}
		}
		
		experiment.start();
	}
}
