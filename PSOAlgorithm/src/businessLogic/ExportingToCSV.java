package businessLogic;

import java.io.FileWriter;
import java.io.IOException;

import model.Position;

public class ExportingToCSV {
	private Position p;
	
	public ExportingToCSV(Position p)
	{
		this.p=p;
	}
	
	public void doCsvExport(String filename){
		String csvHeader = "LeadSectionUnderstandable,ClearOfStruture,ReferencesToReliableSources,LanguageProblems,"+
		  "RevisionHistory,AuthorInformation,ProportionParagraphsWithCitations,ArticleSize,ProportionUnregisteredEditorsWithSingleEdit,ProportionRegisteredEditorsWithEditFrequency,RevisionCount";
	    FileWriter writer;
		try {
			writer = new FileWriter(filename);
			writer.append(csvHeader);
			writer.append('\n');
			
			for(int i=0;i<p.getElements().size();i++){
				//System.out.println("aici"+p.getElements().get(i));
				writer.append((""+p.getElements().get(i)));
				writer.append(',');
			}
			writer.append('\n');
		    writer.flush();
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doExport(String filename,int iteration){
	    FileWriter writer;
		try {
			writer = new FileWriter(filename);
			writer.append("Iteratia "+iteration+ " ");
			
			for(int i=0;i<p.getElements().size();i++){
				//System.out.println("aici"+p.getElements().get(i));
				writer.append((""+p.getElements().get(i)));
				writer.append(',');
			}
			writer.append('\n');
		    writer.flush();
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
