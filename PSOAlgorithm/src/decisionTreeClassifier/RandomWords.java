package decisionTreeClassifier;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RandomWords {
	private List<HashMap<String,String>> words;
	
	public List<HashMap<String, String>> getWords() {
		return words;
	}

	public void setWords(List<HashMap<String, String>> words) {
		this.words = words;
	}

	public RandomWords(){
		this.words=new ArrayList<HashMap<String,String>>();
	}
	
	public void readFromFileWords(String fileName,List<String> attributesName) throws IOException{
		 BufferedReader br = new BufferedReader(new FileReader(fileName));
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();
		        

		        while (line != null) {
		            sb.append(line);
		            sb.append("\n");
		            //System.out.println(line);
		            String[] wordsFile = line.split(",");
		            HashMap<String,String> values = new HashMap<String,String>();
		            for(int s=0;s<wordsFile.length;s++){
		            	values.put(attributesName.get(s), wordsFile[s]);
		            }
		            words.add(values);
		            line = br.readLine();
		        }
		    } finally {
		        br.close();
		    }
	}
	
	public String generateRadomWord(int size,String key){
		Random rand = new Random();
		HashMap<String,String> randomHashMap = words.get(rand.nextInt(size));
		return randomHashMap.get(key);
	}

}
