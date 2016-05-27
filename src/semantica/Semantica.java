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
			SimpleNode iChild = (SimpleNode) n.jjtGetChild(i);
			switch(iChild.toString()){
			case "input":
				if(symbolTable.get((String) iChild.jjtGetValue())!=null)
					throw new Exception("variável de input já declarada"); 
				double [][] matrix = analisaTamanhoLinha(iChild);
				symbolTable.put((String) iChild.jjtGetValue(), matrix);
				analise(iChild);
				break;
				
			case "output":
				analise(iChild);
				break;
				
			case "InnerArray":
				analise(iChild);
				break;
				
			case "Term":
				Object objeto = iChild.jjtGetValue();
				
				if (!(objeto instanceof Double)){
					throw new Exception("Não é double"); 
				}
				break;
				
			case "Matrix":
				if(symbolTable.get((String) iChild.jjtGetValue())==null)
					throw new Exception("variável de input - " +(String) iChild.jjtGetValue()+" nao foi declarada"); 
				break;

			case "Mul":
				analise(iChild);
				break;
				
			case "Add":
				
				verificaSePodeSomarSubtrair(iChild);
				analise(iChild);
				break;
				
			case "Sub":
				
				verificaSePodeSomarSubtrair(iChild);
				analise(iChild);
				break;
				
			case "Tra":
				analise(iChild);
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
	
	public static void printMap() {
		System.out.println("\nPRINT DA SYMBOL TABLE\n");
		for (Map.Entry<String, double[][]> entry : symbolTable.entrySet()) {
		    String key = entry.getKey();
		    double[][] value = entry.getValue();
		    System.out.println(key + "-");
		    
		    int i=value.length;
		    int h=value[0].length;
		    
		    for(int z=0;z<i;z++){
		        System.out.print("  | ");
		        for(int d=0;d<h;d++){
		            System.out.print(value[z][d]+" ");
			    }
		        System.out.print("|");
		    	System.out.println();
		    }
		    ;
	    	System.out.println();
		}
	}
}