public class Matrix{

	public double[][] calcula(){

<<<<<<< HEAD
		double[][]A= new double[][]{{-3.0,2.0,1.0},{5.0,6.0,7.0}};
		double[][]B= new double[][]{{5.0,3.0},{7.0,6.0},{3.0,4.0}};
		double[][]C= new double[][]{{3.0,3.0,3.0},{3.0,2.0,3.0},{4.0,3.0,2.0}};

		double[][] BmulA = new double[3][3];

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				for(int k = 0; k < 2; k++) {
					BmulA[i][j] += B[i][k] * A[k][j];
				}
=======
		double[][]A= new double[][]{{1.0,2.0,3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}};
		double[][]B= new double[][]{{5.0,6.0,7.0},{7.0,8.0,9.0},{9.0,10.0,11.0}};

		double[][] AaddB = new double[3][3];

		for(int c=0;c<3;c++){
			for(int h=0;h<3;h++){
				AaddB[c][h]=A[c][h]+B[c][h];
>>>>>>> 7485d6b04c5dbc977766771de5e9505d296525b7
			}
		}
		double[][] AaddB = new double[3][3];

		for(int c=0;c<3;c++){
			for(int h=0;h<3;h++){
				AaddB[c][h]=A[c][h]+B[c][h];
			}
		}
		double[][] AaddBaddAaddB = new double[3][3];

		for(int c=0;c<3;c++){
			for(int h=0;h<3;h++){
				AaddBaddAaddB[c][h]=AaddB[c][h]+AaddB[c][h];
			}
		}

	double[][] C= AaddBaddAaddB;

	return C;
	}
}