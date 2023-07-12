import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class TextGenerator {
	
	private int order;
	private HashMap <String, Integer> occurances  = new HashMap <String, Integer>();
	private HashMap <String, ArrayList<Integer>> markovChain = new HashMap <String, ArrayList<Integer>>();
	private PrintWriter p;
	private String seedString;
	public TextGenerator(String fileName, int chainOrder) throws IOException {
		order = chainOrder;
		BufferedReader br = new BufferedReader (new FileReader (fileName));
		String current = new String (""); 
		while (br.ready()) {
			char c = (char) br.read();
			if (current.length()==order) {
				if (occurances.containsKey(current)) {
					Integer prev = occurances.get(current);
					occurances.replace(current, prev+1);
					markovChain.get(current).add((int)c); 
				} else {
					occurances.put(current,1);
					ArrayList <Integer> specific = new ArrayList <Integer>();
					specific.add((int)c);
					markovChain.put(current, specific); 
				}
			}
			current= current+c;
			if (current.length()>order) {
				current = current.substring(1);
			}
		}
		br.close(); 
		
	}

	
	public void generateText(String outputFileName, int numChars) throws FileNotFoundException {
		
		 p= new PrintWriter(outputFileName);
		 p.print (seedString); 
		 String previous = seedString;
		 for (int i = order; i<numChars; i++) {
			 char next = generateNextChar (previous);
			 if ((int)next ==0) {
				 p.close();
			 }
			p.print (next);
			previous = previous.substring(1)+next;
		
		 }
		 p.close();
	}
	
	public char generateNextChar (String base) {
		ArrayList<Integer> specific = markovChain.get(base);
		int random = (int)(Math.random()*specific.size());
		return (char)(int)(specific.get(random));
		
	}
}
