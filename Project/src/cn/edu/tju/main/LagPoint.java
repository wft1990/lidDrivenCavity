package cn.edu.tju.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class LagPoint {
	String tempString = null;
	static double PI = 3.14159265;
	
    String fileName = "src/cn/edu/tju/main/lp.dat";
	//生成拉格朗日点，将圆柱以360个点进行表示，存放于sx[i]、sy[i]中
	public void writeLagFile() {
		try {
			File outFile = new File(fileName);
			// if file doesnt exists, then create it
			if (!outFile.exists()) {
				outFile.createNewFile();
			}
			
			FileWriter fw = new FileWriter(outFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			//产生方柱上的拉格朗日点
			for(int i = 1;i < (ParDefinition.im - 1);i ++)
			{
				for(int j = 1;j < (ParDefinition.jm - 1);j ++){	
					
					double xvalue=ParDefinition.sl+i*ParDefinition.dx;
					double yvalue=ParDefinition.sd+j*ParDefinition.dy;
					if(xvalue>=ParDefinition.sl&&xvalue<=ParDefinition.sr&&yvalue>=ParDefinition.sd&&yvalue<=ParDefinition.sr)
					{
						ParDefinition.just[i][j]=1;
						//bw.write(ParDefinition.sx[i] + "\t" + ParDefinition.sy[i]+"\t"+ParDefinition.just[i][j]);
			            bw.newLine();//换行
					}

				}
			}               
			bw.close();
			fw.close();

    	  } catch (IOException e) {
    	   e.printStackTrace();
    	  }   	 
		
	}
	
	//读取拉格朗日点信息
	public String readLagFile() {
		File inFile = new File(fileName);
		BufferedReader reader = null;
		String tempString  = null;
		int i = 0;
		try {
			reader = new BufferedReader(new FileReader(inFile));
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				String[] lp = tempString.split("\t");
				//ParDefinition.sx[i] = Double.parseDouble(lp[0]);
				//ParDefinition.sy[i] = Double.parseDouble(lp[1]);
				i++;
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
		return tempString;
	    }
	
}
