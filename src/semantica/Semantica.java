package semantica;

import Matrix.Node;
import Matrix.SimpleNode;

public class Semantica {

	public static void analise(Node node) throws Exception{
		
	 	SimpleNode n = (SimpleNode) node; 
		
		for(int i = 0; i < n.jjtGetNumChildren(); i++) {
			
			switch(n.jjtGetChild(i).toString()){
			case "input":
				analisaTamanhoLinha(n.jjtGetChild(i));
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
	
	public static void analisaTamanhoLinha(Node node) throws Exception{
		
		int tamanhoLinha=-1;
		
		for(int i = 0; i < node.jjtGetNumChildren(); i++) {
			
			int tamanho=node.jjtGetChild(i).jjtGetNumChildren();
			
			if(tamanhoLinha==-1){
				tamanhoLinha=tamanho;
			}
			else if(tamanhoLinha!=tamanho){
				throw new Exception("Tamanho das linhas da matriz não é igual"); 
			}
			
		}
	}
	
	
}
