public class matrix{

	public void calcula(){

		double[][]A= new double[][]{{1.0,2.0,3.0},{4.0,5.0,7.0}};
		double[][]B= new double[][]{{1.0,2.0},{4.0,5.0},{7.0,8.0}};

		double[][] AmulB = new double[2][2];

		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < 3; k++) {
					AmulB[i][j] += A[i][k] * B[k][j];
				}
			}
		}

	}
}