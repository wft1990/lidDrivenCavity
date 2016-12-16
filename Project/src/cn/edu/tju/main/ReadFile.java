package cn.edu.tju.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	/**
	 * ��ȡʱ�䲽������ʼʱ�䡢����ʱ�����Ϣ
	 */
	String tempString = null;
	public String readInitFile(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		String tempString  = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			tempString = reader.readLine();
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
