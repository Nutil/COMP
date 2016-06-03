public class Matrix{

	public double[][] calcula(){

		double[][]A= new double[][]{{1.0,2.0,3.0}};
		double[][]B= new double[][]{{3.0,4.0,5.0}};

		double[][] Matrix-1 = new double[1][3];

		for(int c=0;c<1;c++){
			for(int h=0;h<3;h++){
				Matrix-1[c][h]=A[c][h]+B[c][h];
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