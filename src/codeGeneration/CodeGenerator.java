package codeGeneration;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {
	
	private PrintWriter outputFile;
	private File generatedFile;
	
	private HashMap<String, double[][]> inputTable;
	
	public CodeGenerator(HashMap<String, double[][]> symbolTable) throws IOException {
		this.inputTable = symbolTable;
		this.generatedFile = new File("Matrix.java");
		this.outputFile =  new PrintWriter(generatedFile);
	}
	
	public void writeAddTofile(String Matrix1,String Matrix2){
		
		int numeroLinhas= inputTable.get(Matrix1).length;
		int numeroColunas= inputTable.get(Matrix1)[0].length;
		
		String newMatrixName= Matrix1+"add"+Matrix2;
		
		this.outputFile.write("\n\t\tdouble[][] "+ newMatrixName+" = new double["+numeroLinhas+"]["+numeroColunas+"];\n\n");
		
		this.outputFile.write("	\tfor(int c=0;c<"+numeroLinhas+";c++)"
				+ "{\n"
				+ "\t\t\tfor(int h=0;h<"+numeroColunas+";h++)"
				+ "{\n"
				+ "\t\t\t\t"+newMatrixName+"[c][h]="+Matrix1+"[c][h]+"+Matrix2+"[c][h];\n"
				+ "\t\t\t}"
				+ "\n\t\t}");
		
		
		
	}
	
	
	public void writeSubTofile(String Matrix1,String Matrix2){
		
		int numeroLinhas= inputTable.get(Matrix1).length;
		int numeroColunas= inputTable.get(Matrix1)[0].length;
		
		String newMatrixName= Matrix1+"sub"+Matrix2;
		
		this.outputFile.write("\n\t\tdouble[][] "+ newMatrixName+" = new double["+numeroLinhas+"]["+numeroColunas+"];\n\n");
		
		this.outputFile.write("	\tfor(int c=0;c<"+numeroLinhas+";c++)"
				+ "{\n"
				+ "\t\t\tfor(int h=0;h<"+numeroColunas+";h++)"
				+ "{\n"
				+ "\t\t\t\t"+newMatrixName+"[c][h]="+Matrix1+"[c][h]-"+Matrix2+"[c][h];\n"
				+ "\t\t\t}"
				+ "\n\t\t}");
		
		
		
	}
	
public void writeMulTofile(String Matrix1,String Matrix2) throws Exception{
		
		int numeroLinhas1= inputTable.get(Matrix1).length;
		int numeroColunas1= inputTable.get(Matrix1)[0].length;
		int numeroLinhas2= inputTable.get(Matrix2).length;
		
		int numeroColunas2= inputTable.get(Matrix2)[0].length;
		
		System.out.println("nC: " + numeroColunas1);
		System.out.println("nL: " + numeroLinhas2);
		
		if(numeroColunas1 != numeroLinhas2) throw new Exception("Impossivel multiplicar estas matrizes");

		String newMatrixName= Matrix1+"mul"+Matrix2;
		
		this.outputFile.write("\n\t\tdouble[][] "+ newMatrixName+" = new double["+numeroLinhas1+"]["+numeroColunas2+"];\n\n");
		
		this.outputFile.write("\t\tfor(int i = 0; i < "+numeroLinhas1+"; i++) {\n"
				+ "\t\t\tfor(int j = 0; j < "+numeroColunas2+"; j++) {\n"
				+ "\t\t\t\tfor(int k = 0; k < "+numeroColunas1+"; k++) {\n"
				+ "\t\t\t\t\t"+newMatrixName+"[i][j] += "+Matrix1+"[i][k] * "+Matrix2+"[k][j];\n"
				+ "\t\t\t\t}\n"
				+ "\t\t\t}\n"
				+ "\t\t}\n");
		
		 
		
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
	
	public void generate() throws Exception {
		 this.outputFile.write("public class matrix{\n\n");
		
		 this.outputFile.write("\tpublic void calcula(){\n\n");
	
		 initiateVariables();
		 
		 //HARCODED
		 writeMulTofile("B","A");
		 
		 this.outputFile.write("\n\t}\n}");
	}
	
	public void closeOutput(){
		outputFile.close();
		return;
	}
	
	public File getGeneratedFile() {
		return generatedFile;
	}
}