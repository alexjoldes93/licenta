package decisionTreeClassifier;

import java.util.List;

public class Attribute {

	List<String> values;
	String name;
	Object label;
	
	public Attribute(String name,List<String> values){
		this.name=name;
		this.values=values;
	
	}
	
	public Attribute(Object label){
		this.label=label;
		name="";
		values=null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getValues() {
		if(values!=null){
			return values;
		}
		else
			return null;
	}
	
	public int indexValue(String value){
		if(values!=null)
			return this.getIndex(value);
		else 
			return -1;
	}
	
	public int getIndex(String value){
		int val=-1;
		for(int i=0;i<values.size();i++){
			if(values.get(i).equals(value))
			{
				val= i;
				break;
			}
		}
		return val;
	}
	
	public boolean isValidValue(String value){
		return this.indexValue(value)>=0;
	}
	
	@Override
	public String toString(){
		if(this.name!="")
		{
			return name;
		}
		else{
			return label.toString();
		}
	}
}
