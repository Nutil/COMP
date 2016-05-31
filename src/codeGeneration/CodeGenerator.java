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
	
	private HashMap<String, double[][]> symbolTable;
	
	public CodeGenerator(HashMap<String, double[][]> symbolTable) throws IOException {
		this.symbolTable = symbolTable;
		this.tempFilePath = Files.createTempFile("Matrix", null);
		this.outputFile =  new PrintWriter(new FileOutputStream(tempFilePath.toFile()), true);
	}
	
	public void printMap() {
		System.out.println("\nPRINT DA SYMBOL TABLE\n");
		for (Map.Entry<String, double[][]> entry : symbolTable.entrySet()) {
		    String key = entry.getKey();
		    double[][] value = entry.getValue();
		    System.out.println(key + "-");
		    this.outputFile.write("double[][]"+key+"= [");
		    
		    int i=value.length;
		    int h=value[0].length;
		    
		    for(int z=0;z<i;z++){
		        System.out.print("  | ");
		        this.outputFile.write("[");
				this.outputFile.flush();
		        for(int d=0;d<h;d++){
		            System.out.print(value[z][d]+" ");
		            this.outputFile.write(value[z][d]+"");
		    		this.outputFile.flush();
		            if(d!=(h-1)){
		            	this.outputFile.write(",");
		        		this.outputFile.flush();
		            }
			    }
		        this.outputFile.write("]");
		        
		        if(z!=(i-1)){
	            	this.outputFile.write(",");
	        		this.outputFile.flush();
	            }

				this.outputFile.flush();
		        System.out.print("|");
		    	System.out.println();
		    }
		     this.outputFile.write("];\n");
						this.outputFile.flush();
		
	    	System.out.println();
		}
	}
	
	public void closeOutput(){
		outputFile.close();
		return;
	}
	
	public Path getTempFilePath() {
		return tempFilePath;
	}
}
