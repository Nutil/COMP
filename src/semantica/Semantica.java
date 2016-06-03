package semantica;


import java.util.HashMap;


import Matrix.Node;
import Matrix.SimpleNode;

public class Semantica {

	private static HashMap<String, double[][]> inputTable= new HashMap<>();
	private String outputName;

	public String getOutputName() {
		return outputName;
	}

	public Semantica() {}

	public  void analise(Node node) throws Exception{
		
	 	SimpleNode n = (SimpleNode) node; 
		
		for(int i = 0; i < n.jjtGetNumChildren(); i++) {
			
			switch(n.jjtGetChild(i).toString()){
			case "input":
				if(inputTable.get((String) ((SimpleNode) n.jjtGetChild(i)).jjtGetValue())!=null)
					throw new Exception("vari�vel de input j� declarada"); 
				double [][] matrix = analisaTamanhoLinha(n.jjtGetChild(i));
				inputTable.put((String) ((SimpleNode) n.jjtGetChild(i)).jjtGetValue(), matrix);

				analise(n.jjtGetChild(i));
				break;
				
			case "output":
				outputName=(String) ((SimpleNode) n.jjtGetChild(i)).jjtGetValue();
				analise(n.jjtGetChild(i));
				break;
				
			case "InnerArray":
				analise(n.jjtGetChild(i));
				break;
				
			case "Term":
				SimpleNode filho= (SimpleNode)  n.jjtGetChild(i);
				Object objeto = filho.jjtGetValue();
				
				if (!(objeto instanceof Double)){
					throw new Exception("N�o � double"); 
				}
				break;
				
			case "Matrix":
				if(inputTable.get((String) ((SimpleNode) n.jjtGetChild(i)).jjtGetValue())==null)
					throw new Exception("vari�vel de input - " +(String) ((SimpleNode) n.jjtGetChild(i)).jjtGetValue()+" nao foi declarada"); 
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
				
				double[][] array=inputTable.get((String) ((SimpleNode) node.jjtGetChild(i)).jjtGetValue());
				
				
				
				if (array==null){
					throw new Exception("vari�vel de input - " +(String) ((SimpleNode) node.jjtGetChild(i)).jjtGetValue()+" nao foi declarada"); 
				}
				
				if(largura==-1 && altura==-1){
					largura=array[0].length;
					altura=array.length;
				}else if(largura!=array[0].length || altura!=array.length){
					throw new Exception("vari�veis a somar/subtrair n�o t�m tamanhos identicos"); 
					
				}
			}
			else{
				System.out.println("A variavel a somar/subtrair depende de outra opera��o, ainda nao sei resolver este caso");
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
				throw new Exception("Tamanho das linhas da matriz n�o � igual"); 
			}
		}
		
		matrix=new double[node.jjtGetNumChildren()][tamanhoLinha];
		for(int i = 0; i < node.jjtGetNumChildren(); i++)
			for(int j=0; j < tamanhoLinha; j++)
				matrix[i][j] = (double) ((SimpleNode) node.jjtGetChild(i).jjtGetChild(j)).jjtGetValue();
		
		return matrix;
	}
	
	public HashMap<String, double[][]> getInputTable() {
		return inputTable;
	}
}