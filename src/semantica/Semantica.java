package semantica;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import Matrix.Node;
import Matrix.SimpleNode;

public class Semantica {

	private static HashMap<String, double[][]> symbolTable= new HashMap<>();;
	
	public static void analise(Node node) throws Exception{
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
				
				break;

			case "Mul":
				break;
				
			case "Add":
				
				break;
				
			case "Sub":
				break;
				
			case "Tra":
				break;
			}
			
			
		}
		
	}
	
	public static double[][] analisaTamanhoLinha(Node node) throws Exception{
		
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
	
	public static void printMap() {
		System.out.println("PRINT DA SYMBOL TABLE");
		for (Map.Entry<String, double[][]> entry : symbolTable.entrySet()) {
		    String key = entry.getKey();
		    double[][] value = entry.getValue();
		    System.out.println(key + "-");
		    
		    int i=value.length;
		    int h=value[0].length;
		    
		    for(int z=0;z<i;z++){
		        for(int d=0;d<h;d++){
		            System.out.print("\t"+value[z][d]);
			    }

		    	System.out.println();
		    }
		    
		}
	}
}