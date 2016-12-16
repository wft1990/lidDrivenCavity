package cn.edu.tju.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TimeOperation {

	// primitive variables formulation
	// 求u(n)->u*(n+1),v(n)->v*(n+1)
	public void solveUS(double us[][], double u[][], double v[][],
			double ps[][], double dx, double dy, int sx, int sy, int nx,
			int ny, double dt) {
		double Re = 1000;
		double As;
		double Vd;
		double Vdd;
		// int i,j;
		for (int j = sy; j < ny; j++)
			for (int i = sx; i < nx; i++) {

				Vd = 0.5 * (v[i][j] + v[i + 1][j]);
				Vdd = 0.5 * (v[i][j - 1] + v[i + 1][j - 1]);
				As = -((u[i][j + 1] * Vd - u[i][j - 1] * Vdd) / (2.0f * dy) + (Math
						.pow(u[i + 1][j], 2) - Math.pow(u[i - 1][j], 2))
						/ (2.0f * dx));
				As = As
						+ (1.0f / Re)
						* ((u[i + 1][j] - 2.0f * u[i][j] + u[i - 1][j])
								/ Math.pow(dx, 2) + (u[i][j + 1] - 2.0f
								* u[i][j] + u[i][j - 1])
								/ Math.pow(dy, 2));

				us[i][j] = us[i][j] + dt
						* (As - (1.0f / dx) * (ps[i + 1][j] - ps[i][j]));
			}

	}

	public void solveVS(double vs[][], double u[][], double v[][],
			double ps[][], double dx, double dy, int sx, int sy, int nx,
			int ny, double dt) {
		double Re = 1000;
		double As;
		double Vd;
		double Vdd;
		// int i,j;
		double Bs; // B*
		double Ud; // u^-
		double Udd; // u^{--}

		for (int j = sy; j < ny; j++)
			for (int i = sx; i < nx; i++) {
				// !!
				Udd = 0.5 * (u[i - 1][j] + u[i - 1][j + 1]);
				Ud = 0.5 * (u[i][j] + u[i][j + 1]);

				Bs = -((v[i + 1][j] * Ud - v[i - 1][j] * Udd) / (2.0f * dx) + (Math
						.pow(v[i][j + 1], 2) - Math.pow(v[i][j - 1], 2))
						/ (2.0f * dy));
				Bs = Bs
						+ (1.0f / Re)
						* ((v[i + 1][j] - 2.0f * v[i][j] + v[i - 1][j])
								/ Math.pow(dx, 2) + (v[i][j + 1] - 2.0f
								* v[i][j] + v[i][j - 1])
								/ Math.pow(dy, 2));

				vs[i][j] = vs[i][j] + dt
						* (Bs - (1.0f / dy) * (ps[i][j + 1] - ps[i][j]));
			}

	}

	public void SolveUdWithoutP(double us[][], double u[][], double v[][],
			double dx, double dy, int sx, int sy, int nx, int ny, double dt) {
		int i, j;

		// divide by rho at the end!

		double As; // A*
		double Vd; // v^-
		double Vdd; // v^{--}
		double Re = 1000;
		for (j = sy; j < ny; j++)
			for (i = sx; i < nx; i++) {

				Vd = 0.5 * (v[i][j] + v[i + 1][j]);
				Vdd = 0.5 * (v[i][j - 1] + v[i + 1][j - 1]);
				As = -((u[i][j + 1] * Vd - u[i][j - 1] * Vdd) / (2.0f * dy) + (Math
						.pow(u[i + 1][j], 2) - Math.pow(u[i - 1][j], 2))
						/ (2.0f * dx));
				As = As
						+ (1.0f / Re)
						* ((u[i + 1][j] - 2.0f * u[i][j] + u[i - 1][j])
								/ Math.pow(dx, 2) + (u[i][j + 1] - 2.0f
								* u[i][j] + u[i][j - 1])
								/ Math.pow(dy, 2));

				us[i][j] = u[i][j] + dt * As;// - (1.0f/dx)*(ps[i+1][j] -
												// ps[i][j]));
			}
	}

	public void SolveVdWithoutP(double vs[][], double v[][], double u[][],
			double dx, double dy, int sx, int sy, int nx, int ny, double dt) {
		int i, j;
		double Bs; // B*
		double Ud; // u^-
		double Udd; // u^{--}
		double Re = 1000;
		for (j = sy; j < ny; j++)
			for (i = sx; i < nx; i++) {
				// !!
				Udd = 0.5 * (u[i - 1][j] + u[i - 1][j + 1]);
				Ud = 0.5 * (u[i][j] + u[i][j + 1]);

				Bs = -((v[i + 1][j] * Ud - v[i - 1][j] * Udd) / (2.0f * dx) + (Math.pow(v[i][j + 1], 2) - Math.pow(v[i][j - 1], 2))
						/ (2.0f * dy));
				Bs = Bs
						+ (1.0f / Re)
						* ((v[i + 1][j] - 2.0f * v[i][j] + v[i - 1][j])
								/ Math.pow(dx, 2) + (v[i][j + 1] - 2.0f
								* v[i][j] + v[i][j - 1])
								/ Math.pow(dy, 2));

				vs[i][j] = v[i][j] + dt * Bs;// - (1.0f/dy) * (ps[i][j+1] -
												// ps[i][j]));
			}
	}

	public void IterativePp(double pp[][], double us[][], double vs[][],
			double dx, double dy, int sx, int sy, int nx, int ny, double dt) {
		double a, b, c, d;
		int i, j, k;
		double covmax; // maximum change on a grid
		double last_pp;

		a = 2 * (dt / Math.pow(dx, 2) + dt / Math.pow(dy, 2));
		b = -dt / Math.pow(dx, 2);
		c = -dt / Math.pow(dy, 2);

		// @todo: maybe test the covergence...

		// for(k=0;k<KMAX;k++) // iteration steps
		do {

			covmax = 0;
			for (j = ny - 1; j >= sy; j--)
				for (i = sx; i < nx; i++) {
					d = (1.0f / dx) * (us[i][j] - us[i - 1][j]) + (1.0f / dy)
							* (vs[i][j] - vs[i][j - 1]);
					last_pp = pp[i][j];
					pp[i][j] = -(1.0f / a)
							* (b * pp[i + 1][j] + b * pp[i - 1][j] + c
									* pp[i][j + 1] + c * pp[i][j - 1] + d);
					covmax = Math.max(Math.abs(pp[i][j] - last_pp), covmax);
				}

		} while (covmax > 1E-7);

	}

	
	// 更新U V
	public double SolveUV(double u[][], double v[][], double us[][],
			double vs[][], double pp[][], double dx, double dy, int sx, int sy,
			int nx, int ny, double dt) {
		int i, j;
		double cov;

		cov = 0;
		double uvold;

		for (i = sx; i < nx; i++)
			for (j = sy; j < ny - 1; j++) {
				uvold = v[i][j];
				v[i][j] = vs[i][j] - (dt / dy) * (pp[i][j + 1] - pp[i][j]);
				vs[i][j] = v[i][j];
				// cov=max(cov,fabs(v[i][j])/fabs(uvold));
				cov = Math.max(cov, Math.abs(v[i][j] - uvold));
			}

		for (i = sx; i < nx - 1; i++)
			for (j = sy; j < ny; j++) {
				uvold = u[i][j];
				u[i][j] = us[i][j] - (dt / dx) * (pp[i + 1][j] - pp[i][j]);
				us[i][j] = u[i][j];
				// cov=max(cov,fabs(u[i][j])/fabs(uvold));
				cov = Math.max(cov, Math.abs(u[i][j] - uvold));
			}

		return cov;
	}

	public void CalculateP(double p[][], double ps[][], double pp[][], int sx,
			int sy, int nx, int ny, double alpha) {
		int i, j;

		for (j = sy; j < ny; j++)
			for (i = sx; i < nx; i++)
				p[i][j] = ps[i][j] + alpha * pp[i][j];

		for (j = sy; j < ny; j++)
			for (i = sx; i < nx; i++)
				ps[i][j] = p[i][j]; // alpha*pp[i][j];

		for (j = sy; j < ny; j++)
			for (i = sx; i < nx; i++)
				pp[i][j] = 0;

	}

	// 设置边界条件的函数
	public void applyBoundary(double p[][], double u[][], double v[][], int NX,
			int NY) {
		int i, j;
		double a, b;

		// p

		// -----------------------------------------

		for (i = 0; i <= NX - 1; i++) {
			p[i][0] = p[i][1];
			p[i][NY - 1] = p[i][NY - 2];
		}

		for (j = 0; j <= NY - 1; j++) {
			p[0][j] = p[1][j];
			p[NX - 1][j] = p[NX - 2][j];
		}
		p[0][0] = p[1][1];
		p[0][NY - 1] = p[1][NY - 2];
		p[NX - 1][NY - 1] = p[NX - 2][NY - 2];
		p[NX - 1][0] = p[NX - 2][1];

		// -----------------------------------------

		// v

		double LINEARCOR = -(2.0f / 3.0f);

		for (i = 0; i <= NX - 2; i++) {
			v[i][0] = LINEARCOR * v[i][1];
			v[i][NY - 2] = LINEARCOR * v[i][NY - 3];
		}

		for (j = 0; j <= NY - 2; j++) { // 1 left
			v[0][j] = 0;
			v[NX - 1][j] = 0;
		}

		// -----------------------------------------

		// u
		for (j = 0; j <= NY - 2; j++) {
			u[0][j] = LINEARCOR * u[1][j];
			u[NX - 2][j] = LINEARCOR * u[NX - 3][j];
		}

		for (i = 0; i <= NX - 2; i++) {
			u[i][NY - 1] = 1;
			u[i][0] = 0;

		}
		// -----------------------------------------
	}

	// 该函数用于存放每个网格点的坐标值以及相应的p值
	public void saveXYP(int fileCount) {
		String fileName = "src/cn/edu/tju/main/values/puv_" + fileCount
				+ ".dat";
		try {
			File outFile = new File(fileName);
			// if file doesnt exists, then create it
			if (!outFile.exists()) {
				outFile.createNewFile();
			}

			FileWriter fw = new FileWriter(outFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < (ParDefinition.im - 1); i++) {
				for (int j = 0; j < (ParDefinition.jm - 1); j++) {
					bw.write(ParDefinition.x[i][j] + "\t"
							+ ParDefinition.y[i][j] + "\t"
							+ ParDefinition.p[i][j] + "\t"
							+ ParDefinition.u[i][j] + "\t"
							+ ParDefinition.v[i][j]);
					bw.newLine();// 换行
				}
			}

			bw.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
