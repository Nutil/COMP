package semantica;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Matrix.Node;
import Matrix.SimpleNode;

public class Semantica {

	private static HashMap<String, double[][]> symbolTable= new HashMap<>();
	public PrintWriter outputFile;
	public Path tempFilePath;
	
	
	

	public Semantica() throws IOException {
		this.tempFilePath = Files.createTempFile("Matrix", null);
		this.outputFile =  new PrintWriter(new FileOutputStream(tempFilePath.toFile()), true);
	}

	public  void analise(Node node) throws Exception{

		
		
		
	 	SimpleNode n = (SimpleNode) node; 
		
		for(int i = 0; i < n.jjtGetNumChildren(); i++) {
			
			switch(n.jjtGetChild(i).toString()){
			case "input":
				if(symbolTable.get((String) ((SimpleNode) n.jjtGetChild(i)).jjtGetValue())!=null)
					throw new Exception("variável de input já declarada"); 
				double [][] matrix = analisaTamanhoLinha(n.jjtGetChild(i));
				symbolTable.put((String) ((SimpleNode) n.jjtGetChild(i)).jjtGetValue(), matrix);

				analise(n.jjtGetChild(i));
				break;
				
			case "output":
				analise(n.jjtGetChild(i));
				break;
				
			case "InnerArray":
				analise(n.jjtGetChild(i));
				break;
				
			case "Term":
				SimpleNode filho= (SimpleNode)  n.jjtGetChild(i);
				Object objeto = filho.jjtGetValue();
				
				if (!(objeto instanceof Double)){
					throw new Exception("Não é double"); 
				}
				break;
				
			case "Matrix":
				if(symbolTable.get((String) ((SimpleNode) n.jjtGetChild(i)).jjtGetValue())==null)
					throw new Exception("variável de input - " +(String) ((SimpleNode) n.jjtGetChild(i)).jjtGetValue()+" nao foi declarada"); 
				break;

			case "Mul":
				analise(n.jjtGetChild(i));
				break;
				
			case "Add":
				
				verificaSePodeSomarSubtrair(n.jjtGetChild(i));
				analise(n.jjtGetChild(i));
				break;
				
			case "Sub":
				
				verificaSePodeSomarSubtrair(n.jjtGetChild(i));
				analise(n.jjtGetChild(i));
				break;
				
			case "Tra":
				analise(n.jjtGetChild(i));
				break;
			}
			
			
		}
		
	}
	
	// SO FUNCIONA COM SOMA TERMO A TERMO, E NAO SOMA DIRETA DE MATRIZES
	private static void verificaSePodeSomarSubtrair(Node node) throws Exception{
		
		int largura=-1,altura=-1;
		
		for(int i = 0; i < node.jjtGetNumChildren(); i++) {
		
			String nomeNo=node.jjtGetChild(i).toString();
			if(nomeNo=="Matrix"){
				
				double[][] array=symbolTable.get((String) ((SimpleNode) node.jjtGetChild(i)).jjtGetValue());
				
				
				
				if (array==null){
					throw new Exception("variável de input - " +(String) ((SimpleNode) node.jjtGetChild(i)).jjtGetValue()+" nao foi declarada"); 
				}
				
				if(largura==-1 && altura==-1){
					largura=array[0].length;
					altura=array.length;
				}else if(largura!=array[0].length || altura!=array.length){
					throw new Exception("variáveis a somar/subtrair não têm tamanhos identicos"); 
					
				}
			}
			else{
				System.out.println("A variavel a somar/subtrair depende de outra operação, ainda nao sei resolver este caso");
			}	
		}
	}
	

	
	private static double[][] analisaTamanhoLinha(Node node) throws Exception{
		
		int tamanhoLinha=-1;
		double [][] matrix;
		
		for(int i = 0; i < node.jjtGetNumChildren(); i++) {
			
			int tamanho=node.jjtGetChild(i).jjtGetNumChildren();
			
			if(tamanhoLinha==-1){
				tamanhoLinha=tamanho;
			}
			else if(tamanhoLinha!=tamanho){
				throw new Exception("Tamanho das linhas da matriz não é igual"); 
			}
		}
		
		matrix=new double[node.jjtGetNumChildren()][tamanhoLinha];
		for(int i = 0; i < node.jjtGetNumChildren(); i++)
			for(int j=0; j < tamanhoLinha; j++)
				matrix[i][j] = (double) ((SimpleNode) node.jjtGetChild(i).jjtGetChild(j)).jjtGetValue();
		
		return matrix;
	}
	
	public  void printMap() {
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
}