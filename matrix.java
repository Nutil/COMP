public class matrix{

	public void calcula(){

		double[][]A= new double[][]{{-3.0,2.0,1.0},{5.0,6.0,7.0}};
		double[][]B= new double[][]{{5.0,3.0},{7.0,6.0},{3.0,4.0}};
		double[][]C= new double[][]{{3.0,3.0,3.0},{3.0,2.0,3.0},{4.0,3.0,2.0}};

		double[][] BmulA = new double[3][3];

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				for(int k = 0; k < 2; k++) {
					BmulA[i][j] += B[i][k] * A[k][j];
				}
			}
		}

	}
}