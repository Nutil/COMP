public class Matrix{

	public double[][] calcula(){

		double[][]A= new double[][]{{1.0,2.0,3.0},{4.0,5.0,6.0},{7.0,8.0,9.0}};
		double[][]B= new double[][]{{5.0,6.0,7.0},{7.0,8.0,9.0},{9.0,10.0,11.0}};

		double[][] AaddB = new double[3][3];

		for(int c=0;c<3;c++){
			for(int h=0;h<3;h++){
				AaddB[c][h]=A[c][h]+B[c][h];
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