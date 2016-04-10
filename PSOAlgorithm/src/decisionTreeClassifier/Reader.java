package decisionTreeClassifier;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Reader {
	
	List<HashMap<String,String>> dataset;
	List<String> attributes;// = new ArrayList<>(Arrays.asList("outlook","temperature","humidity","wind","result"));
	
	public Reader(){
		dataset=new ArrayList<HashMap<String, String>>();
	}
	
	public void createAttributesName(int n){
		this.attributes=new ArrayList<String>(n);
		for(int i=0;i<n;i++){
			String name = "word"+i;
			this.attributes.add(name);
		}
		this.attributes.add("result");
	}
	
	public List<String> getAttributesNames(){
		return this.attributes;
	}
	
	public List<HashMap<String,String>> readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            //System.out.println(line);
	            String[] words = line.split(",");
	            HashMap<String,String> values = new HashMap<String,String>();
	            for(int s=0;s<words.length;s++){
	            	values.put(attributes.get(s), words[s]);
	            }
	            dataset.add(values);
	            line = br.readLine();
	        }
	        return dataset;
	    } finally {
	        br.close();
	    }
	}

}
