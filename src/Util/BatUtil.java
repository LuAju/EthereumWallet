package Util;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

public class BatUtil {
	
	public BatUtil() {
		// TODO Auto-generated constructor stub
	
	}

	public BatUtil(String s) {
		// TODO Auto-generated constructor stub
		try {
			callbat(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new JFrame("no such file");
		}
	}
	
	public static void callbat(String locationCmd) throws IOException {
		Process child = Runtime.getRuntime().exec(locationCmd);
		try {
			child.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = child.exitValue();
		if(i==0) {
			System.out.println("succeed");
			new JFrame("�ɹ�");
		}
		else {
			System.out.println("fail");
			new JFrame("ʧ��");
		}
		child.destroy();
		child = null ;
		
		//killProcess();
	}
	
	 public static void killProcess(){
		  Runtime rt = Runtime.getRuntime();
		  Process p = null;  
		  try {
		   rt.exec("cmd.exe /C start wmic process where name='cmd.exe' call terminate");
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
	}
	 
	 public static void main(String[] args) {
			File newScriptFile = new File("C:\\apache-jmeter-4.0\\bin\\bat\\TopoSwitchesTem.bat");
			String s = "cmd /c start "+newScriptFile.getPath();
			new BatUtil(s);
	}
}
