package tp1.control;

import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;


import tp1.view.Messages;

public class InitialConfiguration {
	
	private List<String> descriptions;
	
	public static final InitialConfiguration NONE = new InitialConfiguration();
	
	private InitialConfiguration() {}
	
	private InitialConfiguration(List<String> descriptions) {
		this.descriptions = descriptions;
	}
	
	public List<String> getShipDescription(){
		return Collections.unmodifiableList(descriptions);
	}
	
	public static InitialConfiguration readFromFile(String filename) 
			throws FileNotFoundException, IOException {
		
		List<String> input = new ArrayList<String>(); 
		
		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			
			String l = in.readLine();
			
			while(l != null) { 
				input.add(l);
				l = in.readLine();
			}
		} 
		
		return new InitialConfiguration(input);
	}
	
}