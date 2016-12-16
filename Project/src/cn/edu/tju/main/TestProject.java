package cn.edu.tju.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;

public class TestProject {

	/**
	 * @param args
	 */

	public TestProject() {

		
		//若之前未生成过欧拉网格，则进行网格生成以及初始化操作；否则，直接读取网格即可。本程序中，默认网格未生成。
		if(true){
			//欧拉网格，△x=△y=0.02
			EulaPoint ep = new EulaPoint();
			System.out.println("*****网格生成中*****");
			ep.eulaGeneration();
			System.out.println("*****网格已生成*****");
			
			//设置初始条件	
			SetCondition sc = new SetCondition();
			System.out.println("*******初始化******");
			sc.initCondition();
			//sc.boundCondition();
			
			sc.saveInitPUV();
			System.out.println("*******初始化操作完成******");
		}
		TimeOperation to = new TimeOperation();
		
		int itime=1;
		int filecount=1;
		double cov=1.0;
		//初始施加边界
		to.applyBoundary(ParDefinition.p, ParDefinition.u, ParDefinition.v,ParDefinition.im,ParDefinition.jm);
		to.applyBoundary(ParDefinition.pp, ParDefinition.us, ParDefinition.vs,ParDefinition.im,ParDefinition.jm);
		//对每一时间步进行处理
		while(true)
		{		
			//ApplyBoundaryConditions(P,U,V);	
			    //step1
			   to.SolveUdWithoutP(ParDefinition.ud, ParDefinition.u, ParDefinition.v, ParDefinition.dx, ParDefinition.dy, 1,1 , ParDefinition.im-2, ParDefinition.jm-1, ParDefinition.dt);
			   to.SolveVdWithoutP(ParDefinition.vd, ParDefinition.v, ParDefinition.u, ParDefinition.dx, ParDefinition.dy, 1,1 , ParDefinition.im-1, ParDefinition.jm-2, ParDefinition.dt);
			   
			   //step2
			   to.IterativePp(ParDefinition.p, ParDefinition.ud, ParDefinition.vd, ParDefinition.dx, ParDefinition.dy, 1, 1, ParDefinition.im-1, ParDefinition.jm-1, ParDefinition.dt);
			   
			   //step3:
			   
			   to.solveUS(ParDefinition.us, ParDefinition.u,ParDefinition.vd,ParDefinition.p,ParDefinition.dx,ParDefinition.dy,1,1,ParDefinition.im-2,ParDefinition.jm-1,ParDefinition.dt);
			   to.solveVS(ParDefinition.vs, ParDefinition.ud,ParDefinition.v,ParDefinition.p,ParDefinition.dx,ParDefinition.dy,1,1,ParDefinition.im-1,ParDefinition.jm-2,ParDefinition.dt);			   
			   
			   //step4:
			   
			   to.IterativePp(ParDefinition.pp, ParDefinition.us, ParDefinition.vs, ParDefinition.dx,ParDefinition. dy, 1, 1, ParDefinition.im-1, ParDefinition.jm-1, ParDefinition.dt);
			   //更新边界   为了计算/计算U' V' 
			   to.applyBoundary(ParDefinition.pp, ParDefinition.us, ParDefinition.vs,ParDefinition.im,ParDefinition.jm); 
			   cov=to.SolveUV(ParDefinition.u, ParDefinition.v,  ParDefinition.us, ParDefinition.vs,ParDefinition.pp, ParDefinition.dx,ParDefinition.dy,1, 1, ParDefinition.im-1,ParDefinition.jm-1, ParDefinition.dt);				
				
			   //to.CalculateP(ParDefinition.p, ParDefinition.ps, ParDefinition.pp, 0, 0, ParDefinition.im, ParDefinition.jm, 0.6);
			   //to.applyBoundary(ParDefinition.p, ParDefinition.u, ParDefinition.v,ParDefinition.im,ParDefinition.jm);
			   
				
				for (int i = 0; i < ParDefinition.im; i++)
					for (int  j = 0; j < ParDefinition.jm - 1; j++)
						ParDefinition.vs[i][j] = ParDefinition.vd[i][j] = ParDefinition.v[i][j];
				for (int i = 0; i < ParDefinition.im - 1; i++)
					for (int j = 0; j < ParDefinition.jm; j++)
						ParDefinition.us[i][j] = ParDefinition.ud[i][j] = ParDefinition.u[i][j];
				
			   itime++;
				if((itime % 100) == 0){
					to.saveXYP(filecount);
					filecount++;
					System.out.println("------" + itime + "------");
				}				
				System.out.println(itime+"    live   "+cov);
				if(cov<1.5E-6)
				   break;					
		}//for结束，时间步处理结束
		
	}   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//读取时间步长、初始时间步、结束时间步。
		ReadFile readInit = new ReadFile();
		String initString = readInit.readInitFile("src/cn/edu/tju/main/in.dat");
		String []init = initString.split(" ");

		ParDefinition.dt = Double.parseDouble(init[0]);
		ParDefinition.tfrom = Integer.parseInt(init[1]);
		ParDefinition.tend = Integer.parseInt(init[2]);	
		System.out.println("时间间隔：" + ParDefinition.dt + "  初始时间步：" + ParDefinition.tfrom + "  结束时间步：" + ParDefinition.tend);
		
		//创建对象，调用默认的构造函数
		TestProject tp = new TestProject();		
	}

}
