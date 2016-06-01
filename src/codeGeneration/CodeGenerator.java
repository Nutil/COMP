package codeGeneration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {
	
	private PrintWriter outputFile;
	private Path tempFilePath;
	
	private HashMap<String, double[][]> inputTable;
	
	public CodeGenerator(HashMap<String, double[][]> symbolTable) throws IOException {
		this.inputTable = symbolTable;
		this.tempFilePath = Files.createTempFile("Matrix", null);
		this.outputFile =  new PrintWriter(new FileOutputStream(tempFilePath.toFile()), true);
	}
	
	
	private void initiateVariables(){
		for (Map.Entry<String, double[][]> entry : inputTable.entrySet()) {
		    String key = entry.getKey();
		    double[][] value = entry.getValue();
		    this.outputFile.write("\t\tdouble[][]"+key+"= new double[][]{");
		    
		    int i=value.length;
		    int h=value[0].length;
		    
		    for(int z=0;z<i;z++){
		        this.outputFile.write("{");
				this.outputFile.flush();
		        for(int d=0;d<h;d++){
		            this.outputFile.write(value[z][d]+"");
		    		this.outputFile.flush();
		            if(d!=(h-1)){
		            	this.outputFile.write(",");
		        		this.outputFile.flush();
		            }
			    }
		        this.outputFile.write("}");
		        
		        if(z!=(i-1)){
	            	this.outputFile.write(",");
	        		this.outputFile.flush();
	            }

				this.outputFile.flush();
		    }
		     this.outputFile.write("};\n");
			this.outputFile.flush();
		
		}
	}
	
	public void generate() {
		
		 this.outputFile.write("public class matrix{\n\n");
		
		 this.outputFile.write("\tpublic void calcula(){\n\n");
	
		 initiateVariables();
		 
		 this.outputFile.write("\n\t}\n}");
	}
	
	public void closeOutput(){
		outputFile.close();
		return;
	}
	
	public Path getTempFilePath() {
		return tempFilePath;
	}
}
