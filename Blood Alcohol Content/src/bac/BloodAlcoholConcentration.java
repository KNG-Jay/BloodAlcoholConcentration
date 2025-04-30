package bac;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.InputMismatchException;

public class BloodAlcoholConcentration {
	
	private String name;
	private int weightByLbs;
	private boolean isFemale;
	private int minutesSinceLastDrink;
	private double BAC;
	private HashMap<String, Integer> typeDescription = new HashMap<>();
	private static BloodAlcoholConcentration bac = new BloodAlcoholConcentration();
	
	
	BloodAlcoholConcentration() {
		this.name = "unknown";
		this.weightByLbs = 0;
		this.isFemale = false;
		this.typeDescription = new HashMap<>();
		this.minutesSinceLastDrink = 0;
		this.BAC = 0;
	}
	
	
	public void setName(String name) {
		
		this.name = name.trim();
		
	}
	
	
	public void setLbs(int weightByLbs) {
		
		this.weightByLbs = weightByLbs;
		
	}
	
	
	public void setGender(String gender) {
		
		if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("m")) {
			this.isFemale = false;
		} else if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("f")) {
			this.isFemale = true;
		} else {
			System.out.println("Invalid entry: \nPlease enter male/m for male or female/f for female");
			System.exit(1);
		}
	}
	
	
	public <K, V extends Comparable<V>> void setMap(HashMap<String, Integer> map) {
		
		this.typeDescription.putAll(map);
		
	}
	
	
	public void setDrinks() {
		
		@SuppressWarnings("resource")
		Scanner scnr = new Scanner(System.in);
		char character;
		
		System.out.println("\nEnter type of alcohol, drinks taken:");
		System.out.println("Type:");
		String type = scnr.next();
		System.out.println("Count:");
		int count = scnr.nextInt();
		this.typeDescription.put(type, count);
		
		System.out.println("\nEnter Any Key to Proceed!");
		System.out.println("Enter 'q' to Quit...\n");
		character = scnr.next().toUpperCase().charAt(0);
		System.out.println();
		
		while (character != 'Q') {
			
			System.out.println("Type:");
			type = scnr.next();
			System.out.println("Count:");
			count = scnr.nextInt();
			System.out.println();
			this.typeDescription.put(type, count);
			
			System.out.println("\nEnter Any Key to Proceed!");
			System.out.println("Enter 'q' to Quit...\n");
			character = scnr.next().toUpperCase().charAt(0);
			System.out.println();
			
		}
		
		bac.setMap(typeDescription);
	}
	
	
	public void setMinutesPerLastDrink(int minutes) {
		
		this.minutesSinceLastDrink = minutes;
		
	}
	
	
	public void setInfo() throws InputMismatchException {
		
		@SuppressWarnings("resource")
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Enter a name:");
		bac.setName(scnr.nextLine());
		
		System.out.println("\nEnter weight in lbs.:");
		bac.setLbs(scnr.nextInt());
		
		System.out.println("\nEnter gender:");
		bac.setGender(scnr.next());
		
		System.out.println("\nEnter minutes since last drink:");
		bac.setMinutesPerLastDrink(scnr.nextInt()); 
		
		bac.setDrinks();
		
	}
	
	
	public String getName() {
		
		return name;
		
	}
	
	
	public int getLbs() {
		
		return weightByLbs;
		
	}
	
	
	public boolean getGender() {
		
		return isFemale;
	}
	
	
	public HashMap<String, Integer> getType() {
		
		return typeDescription;
		
	}
	
	
	public int getMinutesPerLastDrink() {
		
		return minutesSinceLastDrink;
		
	}
	
	
	public void getInfo() {
		
		System.out.println("Name: " + this.name);
		System.out.println("Weight: " + this.weightByLbs + " lbs");
		System.out.println("Female: " + this.isFemale);
		System.out.println("Minutes Since Last Drink: " + this.minutesSinceLastDrink);
		System.out.println("\nListed Drinks: ");
		for (Map.Entry<String, Integer> drinks: typeDescription.entrySet()) {
			System.out.println(drinks.getKey() + " -- " + drinks.getValue());
		}
		System.out.println();
	}
	
	
	public double getBAC() {
		
		int drinkCount = 0;
		double R;
		int gramsOfAlcohol;
		final double HOURS = this.minutesSinceLastDrink / 60.0;
		final double ELIMINATIONRATE = HOURS * 0.015;
		final int WEIGHTINGRAMS = this.weightByLbs * 454;
		
		for (Map.Entry<String, Integer> drinks: typeDescription.entrySet()) {
			drinkCount += drinks.getValue();
		}
		
		if (isFemale) {
			R = 0.55;
		} else R = 0.68;
		
		gramsOfAlcohol = drinkCount * 14;
		
		BAC = (gramsOfAlcohol / (WEIGHTINGRAMS * R)) * 100;
		
		BAC = BAC - ELIMINATIONRATE;
		
		return BAC;
		
	}
	
	public void getEffects() {
		
		BAC = bac.getBAC();
		
		System.out.println("Effects At This Concentration:");
		
		if (BAC < 0.02) {
			System.out.println("Sober...");
		} else if (BAC >= 0.02 && BAC < 0.05) {
			System.out.println("Altered mood, relaxation.");
		} else if (BAC >= 0.05 && BAC < 0.08) {
			System.out.println("Uninhibited, lower alertnass and impaired judgement.");
		} else if (BAC >= 0.08 && BAC < 0.10) {
			System.out.println("Reduced muscle coordination, impaired judgment and reasoning.");
		} else if (BAC >= 0.10 && BAC < 0.15) {
			System.out.println("Reduced reaction time, slurred speech, slowed thinking.");
		} else if (BAC >= 0.15 && BAC < 0.30) {
			System.out.println("Altered mood, nausea and vomiting, loss of balance.");
		} else if (BAC >= 0.30) {
			System.out.println("WARNING: DANGEROUS LEVELS OF ALCOHOL!!!\nALCOHOL POISONING IS LIKELY!");
		} else System.out.println("ERROR: Error Loading Blood Alcohol Concentration");
		
	}
	
	
	public void process() {
		
		bac.setInfo();
		
		bac.getInfo();
		
		System.out.println("Blood Alcohol Concentration for " + bac.name + " is: ");
		System.out.printf("%.3f", bac.getBAC());
		System.out.print("%\n\n");
		
		bac.getEffects();
		
	}
	
	
	public static void main(String[] args) {
		
		bac.process();
		
	}
	
	
}
