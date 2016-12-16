package cn.edu.tju.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SetCondition {
	/**
	 * 设置初始条件和边界条件
	 */
	static double E = 2.71828183;
	public void initCondition(){
		for(int i = 0;i < ParDefinition.im;i ++){
			for(int j = 0;j < ParDefinition.jm; j ++){
				ParDefinition.p[i][j] = 0;
				ParDefinition.ps[i][j] = 0;
				ParDefinition.pp[i][j] = 0;//pp代表p'  p(n+1)=p(n)+p'			
			}
		}
		for(int i = 0;i < ParDefinition.im-1;i ++){
			for(int j = 0;j < ParDefinition.jm; j ++){
				ParDefinition.u[i][j] = 0;
				ParDefinition.us[i][j] = 0;				
			}
		}	
		for(int i = 0;i < ParDefinition.im;i ++){
			for(int j = 0;j < ParDefinition.jm-1; j ++){
				ParDefinition.v[i][j] = 0;
				ParDefinition.vs[i][j] = 0;				
			}
		}
		System.out.println(ParDefinition.x[0][0]+ "---" + ParDefinition.y[0][0]);
	}
	/*
	public void boundCondition(){
		//设置入流边界条件  初始入流速度为1
		for(int j = 0;j < ParDefinition.jm;j ++)
			ParDefinition.u[0][j] = 1;
		
		//设置上下侧边界条件
		for(int i = 0;i < ParDefinition.im;i ++){
			ParDefinition.u[i][0] = 0;
			ParDefinition.u[i][ParDefinition.jm - 1] = 0;
		}
		//设置出流边界条件
		for(int j = 0;j < ParDefinition.jm;j ++)
			ParDefinition.u[ParDefinition.im-1][j] = 0;
	*/	
		/*
		
		 // 中间小方柱的边界条件设置
		 
		
		for(int j = 0;j < ParDefinition.sjm;j ++)//左边界
		{
			ParDefinition.u[0][j] = 0;
			ParDefinition.v[0][j] = 0;
		}
		for(int j = 0;j < ParDefinition.sjm;j ++)//右边界
		{
			ParDefinition.u[ParDefinition.sim-1][j] = 0;
			ParDefinition.v[ParDefinition.sim-1][j] = 0;
		}
		for(int j = 0;j < ParDefinition.sim;j ++)//下边界
		{
			ParDefinition.u[j][0] = 0;
			ParDefinition.u[j][0] = 0;
		}
		for(int j = 0;j < ParDefinition.sim;j ++)//上边界
		{
			ParDefinition.u[ParDefinition.sjm-1][j] = 0;
			ParDefinition.v[ParDefinition.sjm-1][j] = 0;
		}
		
		
	}*/
	//保存初始化的数据
	public void saveInitPUV()
	{
		String fileName = "src/cn/edu/tju/main/values/puv_0.dat";
		try {
			File outFile = new File(fileName);
			// if file doesnt exists, then create it
			if (!outFile.exists()) {
				outFile.createNewFile();
			}
			
			FileWriter fw = new FileWriter(outFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			for(int i = 0;i < (ParDefinition.im-1);i ++){
				for(int j = 0;j < (ParDefinition.jm-1);j ++){
					bw.write(ParDefinition.x[i][j] + "\t" +ParDefinition.y[i][j] + "\t" + ParDefinition.p[i][j]+"\t" + ParDefinition.u[i][j]+"\t" + ParDefinition.v[i][j]);
		            bw.newLine();//换行
				}		
			}
	               
			bw.close();
			fw.close();

    	  } catch (IOException e) {
    	   e.printStackTrace();
    	  }   
	}
}
