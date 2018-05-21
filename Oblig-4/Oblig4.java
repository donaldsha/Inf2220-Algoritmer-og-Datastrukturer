import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Oblig4{

	//private final static int CHAR_MAX = 256;//er den som ble brukt på foilene
	private static Scanner sc;
	// = new ArrayList<Integer>();


	public static String fileReader(String file){
		sc = null;
		try{
			sc = new Scanner(new File(file));
		}catch(IOException e){
			e.printStackTrace();
		}

		String contents = "";

		while (sc.hasNext()) {
			contents += sc.nextLine();

			if (sc.hasNext()) {
				contents += "\n"; //legge til linefeeds til needle
			}
		}
		return contents;
	}
	

	public static void main(String[] args) {

		if (args.length < 2) {
			System.out.println("Feil valg,\nFor kjøring bruk-->  Oblig4 needle.txt haystack.txt ");
		return;
		}

		String needleFile = args[0];
		String haystackFile = args[1];

		//lese input filene som brukeren vil 
		String needle = fileReader(needleFile);
		String haystack = fileReader(haystackFile);
		//sjekk om filene er tom 
		if (needle.length() < 1) {
			System.out.println("needle filen er tomt, vennligst proev en annen fil!");
			
			return;
		}else if (haystack.length() < 1) {
			System.out.println("haystack filen er tomt, vennligst proev en annen fil!");
			
			return;
		}


		//jeg gjør needle og haystack Strengene til Char Arrayer siden på den måten leser algoritmen min.
		char[] needleArr = needle.toCharArray();
		char[] haystackArr = haystack.toCharArray();
		BoyerMoore boyer = new BoyerMoore();

		ArrayList<Integer> matches;
		ArrayList<String> wordMatches= new ArrayList<>();

		System.out.println("Stemmer overens for needle: " + needle);
		matches = boyer.boyer_moore_horspool(needleArr, haystackArr);
		

		if (matches != null) {//sjekker om matches er ikke tom.

			for (int i = 0; i < matches.size() ;i++ ) {//lagrer matchingsordene i en annen ArrayList slik at det ser litt penere 
				wordMatches.add(haystack.substring(matches.get(i), matches.get(i)+needle.length()));
			}

			System.out.println("\nStemmer overens  " + matches.size() + " ganger");
			for (int i = 0;i<matches.size() ;i++) {//printer alle posisjonene hvor needle er funnet, punktum 5 paa oppgaveteksten
				System.out.println("\tPå indeks: " + matches.get(i) + " med streng: " + wordMatches.get(i));
				
			}
			/*
			for (int match : matches) {
				System.out.println("Match: " + matches);
				System.out.println("Stemmer overens på indeks: " + matches + " med streng: " + haystack.substring(match, match+needle.length()));
			break;
			}*/
		}else{//hvis det finnes ikke noen stemning
			System.out.println("Ingen stemning funnet!");
		}
	}
}