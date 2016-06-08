package fitness;

import java.util.List;
import model.Position;

public class Errors {
	//private Position actualValue;
	//private List<Double> forecastValues;
	public Errors(){	
	}
	
	public Double getErrorValue(Position actualValue,List<Double> forecastValue){
		double errorValue=0.0;
		errorValue=calculateMAPEerror(actualValue,forecastValue)+calculateMSEerror(actualValue, forecastValue)+calculateRMSEerror(actualValue, forecastValue);
		
		return errorValue;
	}
	
	private Double calculateMAPEerror(Position actualValue,List<Double> forecastValue){
		double errorValue=0.0;
		for(int i=0;i<actualValue.getElements().size();i++){
			errorValue+=Math.abs((actualValue.getElements().get(i)-forecastValue.get(i))/actualValue.getElements().get(i));
		}
		
		return errorValue/actualValue.getElements().size();
	}
	
	private Double calculateMSEerror(Position actualValue,List<Double> forecastValue){
		double errorValue=0.0;
	
		for(int i=0;i<actualValue.getElements().size();i++){
			errorValue+=Math.pow(actualValue.getElements().get(i)-forecastValue.get(i), 2);
		}
	
		return errorValue/actualValue.getElements().size();
	}
	
	private Double calculateRMSEerror(Position actualValue,List<Double> forecastValue){
		double errorMSE=calculateMSEerror(actualValue, forecastValue);
	
		return Math.sqrt(errorMSE);
	}
	
	//calculate mean absolute deviation

}
