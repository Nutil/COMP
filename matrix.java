public class matrix{

	public double[][] calcula(){

		double[][]A= new double[][]{{1.0,2.0,3.0},{4.0,5.0,6.0}};
		double[][]B= new double[][]{{1.0,2.0},{3.0,4.0},{5.0,6.0}};
		double[][]C= new double[][]{{1.0},{2.0}};
		double[][]D= new double[][]{{3.0},{4.0}};

		double[][] AmulB = new double[2][2];

		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < 3; k++) {
					AmulB[i][j] += A[i][k] * B[k][j];
				}
			}
		}

		double[][] AmulBmulC = new double[2][1];

		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 1; j++) {
				for(int k = 0; k < 2; k++) {
					AmulBmulC[i][j] += AmulB[i][k] * C[k][j];
				}
			}
		}

		double[][] AmulBmulCsubD = new double[2][1];

		for(int c=0;c<2;c++){
			for(int h=0;h<1;h++){
				AmulBmulCsubD[c][h]=AmulBmulC[c][h]-D[c][h];
			}
		}

	double[][] H= AmulBmulCsubD;

	return H;
	}
}