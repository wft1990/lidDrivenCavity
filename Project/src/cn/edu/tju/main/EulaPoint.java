package cn.edu.tju.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EulaPoint {
	
	String fileName = "src/cn/edu/tju/main/gridxyz.dat";
	
	//����ŷ������
	public void eulaGeneration(){
		try {
			File outFile = new File(fileName);
			// if file doesnt exists, then create it
			if (!outFile.exists()) {
				outFile.createNewFile();
			}
			
			FileWriter fw = new FileWriter(outFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0;i < ParDefinition.im ;i ++){
				for(int j = 0;j < ParDefinition.jm ;j ++){
					//x[i][j]��y[i][j]�зֱ��������ֵ������У���i�У�j�е������ֵ��i��j��0~2000��
					
					ParDefinition.x[i][j] = ParDefinition.rl  + i * ParDefinition.dx;
					ParDefinition.y[i][j] = ParDefinition.rd  + j * ParDefinition.dy;
					if(ParDefinition.x[i][j]>=ParDefinition.sl && ParDefinition.x[i][j]<=ParDefinition.sr &&
							ParDefinition.y[i][j]>=ParDefinition.sd && ParDefinition.y[i][j]<=ParDefinition.st)
					{
						ParDefinition.just[i][j]=0;
						bw.write(ParDefinition.x[i][j] + "\t" + ParDefinition.y[i][j]+"\t"+ParDefinition.just[i][j]);
			            bw.newLine();//����
					}
					else 
					{
						ParDefinition.just[i][j]=0;
						bw.write(ParDefinition.x[i][j] + "\t" + ParDefinition.y[i][j]+"\t"+ParDefinition.just[i][j]);
			            bw.newLine();//����
					}

				}		
			}
	               
			bw.close();
			fw.close();

    	  } catch (IOException e) {
    	   e.printStackTrace();
    	  }   	 
	}
	
	//��ȡŷ��������Ϣ����������δ�õ���
	public String eulaRead() {
		File inFile = new File(fileName);
		BufferedReader reader = null;
		String tempString  = null;
		int count = 0,i,j;
		try {
			reader = new BufferedReader(new FileReader(inFile));
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			while ((tempString = reader.readLine()) != null) {
				i = count / ParDefinition.im;
				j = count % ParDefinition.jm;
				String[] temp = tempString.split("\t");
				ParDefinition.x[i][j] = Double.parseDouble(temp[0]);
				ParDefinition.y[i][j] = Double.parseDouble(temp[1]);
				ParDefinition.just[i][j] = Integer.parseInt(temp[2]);
				count ++;
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
//		System.out.println(tempString);
		return tempString;
	    }
	
}
