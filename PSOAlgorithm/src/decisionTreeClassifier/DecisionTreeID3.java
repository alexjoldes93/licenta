package decisionTreeClassifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DecisionTreeID3 {

	private List<HashMap<String,String>> dataset;
	private int total=0;
	private String targetAttribute = "result";
	private double entropySet=0.0;
	public Attribute result=null;
	
	public DecisionTreeID3(List<HashMap<String,String>> data)
	{
		this.dataset=data;
	}
	
	private double calculateEntropy(List<Integer> values){
		double result=0.0;
		for(int i=0;i<values.size();i++){
			double ratioAttribute=(double)values.get(i)/values.size();
			if (ratioAttribute!=0)
			{
				ratioAttribute=-(ratioAttribute)*(Math.log(ratioAttribute)/Math.log(2));
				result+=ratioAttribute;
			}
			
		}
		return result;
	}
	
	private int getSum(List<Integer> values){
		int sum=0;
		for(Integer i:values){
			sum+=i;
		}
		return sum;
	}
	
	//pentru fiecare rand din dataset verific valoarea de la
	//rezultatul atributului cu acelasi nume (mai exact numar 
	//pentru fiecare domeniu de cate ori apare un cuvant)
	private HashMap<String,Integer> getValuesToAttribute(Attribute attribute,String value){
		HashMap<String,Integer> counters=new HashMap<String, Integer>();
		List<String>domains = new ArrayList<String>();
		for(HashMap<String,String> hm:dataset){
			String attributeValue = ((String)hm.get(attribute.getName()));
		    if(attributeValue.equals(value)){
		    	String domain = ((String)hm.get(this.targetAttribute));
		    	domains.add(domain);
		    }
			
		}
		for(String s:domains){
			int contor=0;
			for(String ss:domains){			
				if(ss.equals(s)){
					contor++;
				}
				
			}
			counters.put(s, contor);
			
		}
		return counters;
	}
	
	private double gain(Attribute attribute){
		double sum= 0.0;
		List<String> values= attribute.getValues();
		
		for(int i=0;i<values.size();i++){
			HashMap<String,Integer> numberOfOccurance = this.getValuesToAttribute(attribute, values.get(i));
			List<Integer> listOfOccurances = this.putValuesOfHashMapInList(numberOfOccurance);
			double entropy = this.calculateEntropy(listOfOccurances);
			sum+=-(double)(this.getSum(listOfOccurances))/entropy*this.total;
		}
		return sum+this.entropySet;
		
	}
	
	private List<Integer> putValuesOfHashMapInList(HashMap<String,Integer> numberOfOccurance){
		List<Integer> listOfOccurances = new ArrayList<Integer>();
		Set<Entry<String, Integer>> set = numberOfOccurance.entrySet();
	      // Get an iterator
	    Iterator<Entry<String, Integer>> iterator = set.iterator();
	      // Display elements
	      while(iterator.hasNext()) {
	         Map.Entry me = (Map.Entry)iterator.next();
	         listOfOccurances.add((Integer) me.getValue());
	      }
	      return listOfOccurances;
	}
	
	private Attribute getBestAttribute(List<Attribute> attributes){
		double maxGain=0.0;
		Attribute result=null;
		
		for(Attribute a:attributes){
			double aux=this.gain(a);
			if(aux>maxGain){
				maxGain=aux;
				result=a;
			}
		}
		return result;
	}
	
	private List<String> getDistinctValues(String targetAttribute){
		List<String> distinctValues = new ArrayList<String>(dataset.size());
		for(HashMap<String,String> hm:dataset){
			if(distinctValues.indexOf(hm.get(targetAttribute)) == -1){
				distinctValues.add(hm.get(targetAttribute));
			}
		}
		return distinctValues;
	}
	
	private Object getMostCommonValue(String targetAttribute){
		List<String> distinctValues= this.getDistinctValues(targetAttribute);
		int[] count = new int[distinctValues.size()];
		
		for(HashMap<String,String> hm:dataset){
			int index=distinctValues.indexOf(hm.get(targetAttribute));
			count[index]++;
		}
		
		int MaxIndex=0;
		int MaxCount=0;
		
		for(int i=0;i<count.length;i++){
			if(count[i]>MaxCount){
				MaxCount=count[i];
				MaxIndex=i;
			}
		}
		return distinctValues.get(MaxIndex);
	}
	
	private HashMap<String,Integer> getNrOfOccuranceOfEachDomain()
	{
		HashMap<String,Integer> counters=new HashMap<String, Integer>();
		List<String>domains = new ArrayList<String>();
		for(HashMap<String,String> hm:dataset){
		    	String domain = ((String)hm.get(this.targetAttribute));
		    	domains.add(domain);
			
		}
		for(String s:domains){
			int contor=0;
			for(String ss:domains){			
				if(ss.equals(s)){
					contor++;
				}
				
			}
			counters.put(s, contor);
			
		}
		return counters;
	}
	
	//construiest un arbore de decizie intern pe baza exemplelor
	private TreeNode getInternalTree(String targetAttribute,List<Attribute> attributes){
		HashMap<String,Integer> nrOfOccurance=this.getNrOfOccuranceOfEachDomain();
		if(nrOfOccurance.size()==1){
			//return new TreeNode(new Attribute(true));

			return new TreeNode(new Attribute(nrOfOccurance.entrySet().iterator().next().getKey(),null));
		}
		if(attributes.size()==0){
			return new TreeNode(new Attribute(this.getMostCommonValue(targetAttribute)));
			
		}
		
		this.total=this.dataset.size();
		//System.out.println("Total"+total);
		this.targetAttribute=targetAttribute;
		//System.out.println("Target Attribute "+this.targetAttribute);
		
		
		List<Integer> values=this.putValuesOfHashMapInList(nrOfOccurance);
		this.entropySet=this.calculateEntropy(values);
		//System.out.println("EntropySet "+this.entropySet);
		
		Attribute bestAttribute=this.getBestAttribute(attributes);
		//System.out.println("Best Attribute"+bestAttribute.name);
		
		TreeNode root = new TreeNode(bestAttribute);
		
		ArrayList<HashMap<String,String>> aDataset = (ArrayList<HashMap<String, String>>) ((ArrayList<HashMap<String, String>>) dataset).clone();
	
		for(String value:bestAttribute.getValues()){
			//gasim toate hasurile cu key si val ca si a bestattribute
			aDataset.clear();
			aDataset= (ArrayList<HashMap<String, String>>) this.selectAllElementsWithValue(bestAttribute, value);
		
			List<Attribute> attribs = new ArrayList<Attribute>(attributes.size()-1);
			for(int i=0; i<attributes.size();i++){
				if(attributes.get(i).getName()!=bestAttribute.getName()){
					attribs.add(attributes.get(i));
				}
			}
			
			if(aDataset.size()==0){
				return new TreeNode(new Attribute(this.getMostCommonValue(targetAttribute)));
			}
			else{
				DecisionTreeID3 dc3 = new DecisionTreeID3(aDataset);
				TreeNode childNode = dc3.buildTree(targetAttribute, attribs);
				root.addTreeNode(childNode, value);
			}
		}
		return root;
	}
	
	private List<HashMap<String,String>> selectAllElementsWithValue(Attribute attribute,String value){
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		for(HashMap<String,String> hm:dataset){
			Set<Entry<String, String>> set = hm.entrySet();
		      // Get an iterator
		    Iterator<Entry<String, String>> iterator = set.iterator();
		      // Display elements
		      while(iterator.hasNext()) {
		         Map.Entry me = (Map.Entry)iterator.next();
		         if((boolean)me.getKey().equals(attribute.getName()))
		        	 if(me.getValue().equals(value)){
		        		 result.add(hm);
		        		 break;
		        	 }
		         
		      }
		}
		return result;
	}
	
	public TreeNode buildTree(String targetAttribute,List<Attribute> attributes){
		return this.getInternalTree(targetAttribute, attributes);
	}
	
	public void getResult(TreeNode root){
		
		
		if(root.getAttribute().getValues()!=null){
			for(int i=0;i<root.getAttribute().getValues().size();i++){
				TreeNode childNode = root.getChildByBranchName(root.getAttribute().getValues().get(i));
				this.getResult(childNode);
			}
		}
		if(this.result==null)
				this.result=root.getAttribute();
		
	}
}
