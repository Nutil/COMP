public class Matrix{

	public double[][] calcula(){

<<<<<<< HEAD
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
=======
		double[][]A= new double[][]{{1.0,2.0,3.0}};
		double[][]B= new double[][]{{3.0,4.0,5.0}};
>>>>>>> ed2552e9b5595224f63bb1fa6e466ce4ffa78656

		double[][] Matrix-1 = new double[1][3];

		for(int c=0;c<1;c++){
			for(int h=0;h<3;h++){
<<<<<<< HEAD
				AaddB[c][h]=A[c][h]+B[c][h];
>>>>>>> 7485d6b04c5dbc977766771de5e9505d296525b7
=======
				Matrix-1[c][h]=A[c][h]+B[c][h];
>>>>>>> ed2552e9b5595224f63bb1fa6e466ce4ffa78656
			}
		}
		double[][] Matrix-2 = new double[1][3];

		for(int c=0;c<1;c++){
			for(int h=0;h<3;h++){
				Matrix-2[c][h]=A[c][h]+B[c][h];
			}
		}
		double[][] Matrix-3 = new double[1][3];

		for(int c=0;c<1;c++){
			for(int h=0;h<3;h++){
				Matrix-3[c][h]=Matrix-1[c][h]+Matrix-2[c][h];
			}
		}

	double[][] D= Matrix-3;

	return D;
	}
}