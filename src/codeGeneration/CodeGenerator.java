package codeGeneration;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import Matrix.Node;
import Matrix.SimpleNode;

public class CodeGenerator {
	
	private PrintWriter outputFile;
	private File generatedFile;
	private Node node;
	private String outputName;
	private HashMap<String, double[][]> inputTable;
	private int contadorMatrix=1;
	
	public CodeGenerator(HashMap<String, double[][]> symbolTable,Node node,String outputName) throws IOException {
		this.inputTable = symbolTable;
		this.generatedFile = new File("Matrix.java");
		this.outputFile =  new PrintWriter(generatedFile);
		this.outputName=outputName;
		this.node=node;
	}
	
	public String writeAddTofile(String Matrix1,String Matrix2) throws Exception{
		
		int numeroLinhas= inputTable.get(Matrix1).length;
		int numeroColunas= inputTable.get(Matrix1)[0].length;
		
		
		int numeroLinhas2= inputTable.get(Matrix2).length;
		int numeroColunas2= inputTable.get(Matrix2)[0].length;
		
		if(numeroLinhas!=numeroLinhas2 || numeroColunas!=numeroColunas2)
			throw new Exception("Impossivel somar estas matrizes");
		
		String newMatrixName= "Matrix-"+contadorMatrix;
		contadorMatrix++;
		
		this.outputFile.write("\n\t\tdouble[][] "+ newMatrixName+" = new double["+numeroLinhas+"]["+numeroColunas+"];\n\n");
		
		this.outputFile.write("	\tfor(int c=0;c<"+numeroLinhas+";c++)"
				+ "{\n"
				+ "\t\t\tfor(int h=0;h<"+numeroColunas+";h++)"
				+ "{\n"
				+ "\t\t\t\t"+newMatrixName+"[c][h]="+Matrix1+"[c][h]+"+Matrix2+"[c][h];\n"
				+ "\t\t\t}"
				+ "\n\t\t}");
		
		
		inputTable.put(newMatrixName,new double[numeroLinhas][numeroColunas] );
		
		return newMatrixName;
		
	}
	
	
	public String writeSubTofile(String Matrix1,String Matrix2) throws Exception{
		
		int numeroLinhas= inputTable.get(Matrix1).length;
		int numeroColunas= inputTable.get(Matrix1)[0].length;
		
		int numeroLinhas2= inputTable.get(Matrix2).length;
		int numeroColunas2= inputTable.get(Matrix2)[0].length;
		
		if(numeroLinhas!=numeroLinhas2 || numeroColunas!=numeroColunas2)
			throw new Exception("Impossivel subtrair estas matrizes");
			
		
		String newMatrixName= "Matrix-"+contadorMatrix;
		contadorMatrix++;
		
		this.outputFile.write("\n\t\tdouble[][] "+ newMatrixName+" = new double["+numeroLinhas+"]["+numeroColunas+"];\n\n");
		
		this.outputFile.write("	\tfor(int c=0;c<"+numeroLinhas+";c++)"
				+ "{\n"
				+ "\t\t\tfor(int h=0;h<"+numeroColunas+";h++)"
				+ "{\n"
				+ "\t\t\t\t"+newMatrixName+"[c][h]="+Matrix1+"[c][h]-"+Matrix2+"[c][h];\n"
				+ "\t\t\t}"
				+ "\n\t\t}");
		
	
		
		inputTable.put(newMatrixName,new double[numeroLinhas][numeroColunas] );
		
		return newMatrixName;
		
	}
	
public String writeTransTofile(String Matrix1) throws Exception{
		
		int numeroLinhas1= inputTable.get(Matrix1).length;
		int numeroColunas1= inputTable.get(Matrix1)[0].length;


		String newMatrixName= "Matrix-"+contadorMatrix;
		contadorMatrix++;
		
		this.outputFile.write("\n\t\tdouble[][] "+ newMatrixName+" = new double["+numeroColunas1+"]["+numeroLinhas1+"];\n\n");
		
		this.outputFile.write("\t\tfor(int i = 0; i < "+numeroLinhas1+"; i++) {\n"
				+ "\t\t\tfor(int j = 0; j < "+numeroColunas1+"; j++) {\n"
				+ "\t\t\t\t\t"+newMatrixName+"[j][i] = "+Matrix1+"[i][j];\n"
				+ "\t\t\t}\n"
				+ "\t\t}\n");
		
		
		inputTable.put(newMatrixName,new double[numeroColunas1][numeroLinhas1] );
		
		
		return newMatrixName;
		
	}
	
public String writeMulTofile(String Matrix1,String Matrix2) throws Exception{
		
		int numeroLinhas1= inputTable.get(Matrix1).length;
		int numeroColunas1= inputTable.get(Matrix1)[0].length;
		int numeroLinhas2= inputTable.get(Matrix2).length;
		
		int numeroColunas2= inputTable.get(Matrix2)[0].length;
		
		System.out.println("nC: " + numeroColunas1);
		System.out.println("nL: " + numeroLinhas2);
		
		if(numeroColunas1 != numeroLinhas2) throw new Exception("Impossivel multiplicar estas matrizes");

		String newMatrixName= "Matrix-"+contadorMatrix;
		contadorMatrix++;
		
		this.outputFile.write("\n\t\tdouble[][] "+ newMatrixName+" = new double["+numeroLinhas1+"]["+numeroColunas2+"];\n\n");
		
		this.outputFile.write("\t\tfor(int i = 0; i < "+numeroLinhas1+"; i++) {\n"
				+ "\t\t\tfor(int j = 0; j < "+numeroColunas2+"; j++) {\n"
				+ "\t\t\t\tfor(int k = 0; k < "+numeroColunas1+"; k++) {\n"
				+ "\t\t\t\t\t"+newMatrixName+"[i][j] += "+Matrix1+"[i][k] * "+Matrix2+"[k][j];\n"
				+ "\t\t\t\t}\n"
				+ "\t\t\t}\n"
				+ "\t\t}\n");
		
		
		
		
		inputTable.put(newMatrixName,new double[numeroLinhas1][numeroColunas2] );
		
		
		return newMatrixName;
		
		 
		
	}
public  String  analise(Node node) throws Exception{
	
 	SimpleNode n = (SimpleNode) node; 
 	
	
	for(int i = 0; i < n.jjtGetNumChildren(); i++) {
		String matrixGerada1=null;
		String matrixGerada2=null;
		switch(n.jjtGetChild(i).toString()){
		
		case "input":	
			break;
			
		case "output":
			return analise(n.jjtGetChild(i));
			
		case "InnerArray":
			break;
			
		case "Term":
			break;
			
		case "Matrix":
			break;

		case "Mul":

			if(n.jjtGetChild(i).jjtGetChild(0).toString()!="Matrix"){
		
				matrixGerada1=analise(n.jjtGetChild(i));
		
			}
			if(n.jjtGetChild(i).jjtGetChild(1).toString()!="Matrix")
				matrixGerada2=analise(n.jjtGetChild(i));


			if(matrixGerada1==null && matrixGerada2==null)
				return writeMulTofile((String) ((SimpleNode) (node.jjtGetChild(i)).jjtGetChild(0)).jjtGetValue(),
						(String) ((SimpleNode) (node.jjtGetChild(i)).jjtGetChild(1)).jjtGetValue());
			
			else if(matrixGerada1!=null && matrixGerada2==null)
				return writeMulTofile(matrixGerada1,
						(String) ((SimpleNode) (node.jjtGetChild(i)).jjtGetChild(1)).jjtGetValue());
			else if(matrixGerada1==null && matrixGerada2!=null)
				return writeMulTofile((String) ((SimpleNode) (node.jjtGetChild(i)).jjtGetChild(0)).jjtGetValue(),
						matrixGerada2);
			else if(matrixGerada1!=null && matrixGerada2!=null)
				return writeMulTofile(matrixGerada1,
						matrixGerada2);
			
			break;
			
		case "Add":
			
			if(n.jjtGetChild(i).jjtGetChild(0).toString()!="Matrix"){
		
				matrixGerada1=analise(n.jjtGetChild(i));
		
			}
			if(n.jjtGetChild(i).jjtGetChild(1).toString()!="Matrix")
				matrixGerada2=analise(n.jjtGetChild(i));


			if(matrixGerada1==null && matrixGerada2==null)
				return writeAddTofile((String) ((SimpleNode) (node.jjtGetChild(i)).jjtGetChild(0)).jjtGetValue(),
						(String) ((SimpleNode) (node.jjtGetChild(i)).jjtGetChild(1)).jjtGetValue());
			
			else if(matrixGerada1!=null && matrixGerada2==null)
				return writeAddTofile(matrixGerada1,
						(String) ((SimpleNode) (node.jjtGetChild(i)).jjtGetChild(1)).jjtGetValue());
			else if(matrixGerada1==null && matrixGerada2!=null)
				return writeAddTofile((String) ((SimpleNode) (node.jjtGetChild(i)).jjtGetChild(0)).jjtGetValue(),
						matrixGerada2);
			else if(matrixGerada1!=null && matrixGerada2!=null)
				return writeAddTofile(matrixGerada1,
						matrixGerada2);
			
			break;
		case "Sub":
			
			if(n.jjtGetChild(i).jjtGetChild(0).toString()!="Matrix"){
				
				matrixGerada1=analise(n.jjtGetChild(i));
		
			}
			if(n.jjtGetChild(i).jjtGetChild(1).toString()!="Matrix")
				matrixGerada2=analise(n.jjtGetChild(i));


			if(matrixGerada1==null && matrixGerada2==null)
				return writeSubTofile((String) ((SimpleNode) (node.jjtGetChild(i)).jjtGetChild(0)).jjtGetValue(),
						(String) ((SimpleNode) (node.jjtGetChild(i)).jjtGetChild(1)).jjtGetValue());
			
			else if(matrixGerada1!=null && matrixGerada2==null)
				return writeSubTofile(matrixGerada1,
						(String) ((SimpleNode) (node.jjtGetChild(i)).jjtGetChild(1)).jjtGetValue());
			else if(matrixGerada1==null && matrixGerada2!=null)
				return writeSubTofile((String) ((SimpleNode) (node.jjtGetChild(i)).jjtGetChild(0)).jjtGetValue(),
						matrixGerada2);
			else if(matrixGerada1!=null && matrixGerada2!=null)
				return writeSubTofile(matrixGerada1,
						matrixGerada2);
			break;
			
		case "Tra":
			
			if(n.jjtGetChild(i).jjtGetChild(0).toString()!="Matrix"){
				
				matrixGerada1=analise(n.jjtGetChild(i));
		
			}
		
			if(matrixGerada1==null )
				return writeTransTofile((String) ((SimpleNode) (node.jjtGetChild(i)).jjtGetChild(0)).jjtGetValue());
			
			else if(matrixGerada1!=null)
				return writeTransTofile(matrixGerada1);
		
		}
		
		
	}
	return null;
	
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
		 this.outputFile.write("public class Matrix{\n\n");
		
		 this.outputFile.write("\tpublic double[][] calcula(){\n\n");
	
		 initiateVariables();
		 
		 String output=analise(this.node);
		 this.outputFile.write("\n\n\tdouble[][] "+this.outputName+"= "+output+";\n");

		 this.outputFile.write("\n\treturn "+this.outputName+";\n\t}");

		 
		 this.outputFile.write("\n}");
	}
	
	public void closeOutput(){
		outputFile.close();
		return;
	}
	
	public File getGeneratedFile() {
		return generatedFile;
	}
}