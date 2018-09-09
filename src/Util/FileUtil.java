package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;




public class FileUtil {
	
	
	public static List<String> changeArrayToList(String[] str){
		List<String> al = new ArrayList<>();
		for(String s:str) {
			al.add(s);
		}
		return al;
	}
	
	public static String[] changeListToArray(List<String> al){
		String[] strArr = new String[al.size()];
		for(int i=0;i<al.size();i++) {
			strArr[i] = al.get(i);
		}
		return strArr;
	}
	
	
	public static String[] changeListToArrayLimitLength(List<String> al){
		String[] strArr = new String[4];
		for(int i=0;i<strArr.length;i++) {
			strArr[i] = al.get(i);
		}
		return strArr;
	}
	
	
	public  String fileReader(String fileStr) throws IOException {
		//fileStr = "C:\\apache-jmeter-4.0\\bin\\getTopo.jtl";
		
		File f = new File(fileStr);
	//	String res = null;
		StringBuffer sb = new StringBuffer();
		if(!(f.exists())) {
			System.out.println(fileStr);
			System.out.println(f.getName()+" is null");
			return "";
		}
		else {
			FileInputStream in = null;
			
			 in = new FileInputStream(f);
			byte[] media = new byte[5];
			int length=-1;
			while(-1 !=(length= in.read(media))) {
				String ingo = new String(media, 0, length);
				sb.append(ingo);
				//System.out.print(ingo);
			}
			in.close();
			String res = new String(sb);
			return res;	
		}
	}
	
	public static List<String> fileReadDpid(String fileStr){
		File f = new File(fileStr);
		BufferedReader br = null ;
		List<String> dpid = new ArrayList<>();
		if(f.exists()) {
			try {
				 br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String s="";
			try {
				while((s=br.readLine())=="") {
					System.out.println(s);
					dpid.add(s);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("FIleUtil.java:file is not exist!");
		}
		return dpid;
	}
	
	//��ȡ�洢������id���ļ�
	public static List<String> fileReadLineDpid(String fileStr){
		File f = new File(fileStr);
		List<String> dpid = new ArrayList<>();
		//StringBuffer sb = 
		String dpidStr = "";
		BufferedReader br =null;
		try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileStr))));
			try {
				
				while((dpidStr = br.readLine()) != null){
				//	System.out.println(dpidStr);
					dpid.add(dpidStr);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dpid;
	}
	
	public static List<String> fileReadLineIP(String fileStr){
		File f = new File(fileStr);
		List<String> dpid = new ArrayList<>();
		//StringBuffer sb = 
		String dpidStr = "";
		BufferedReader br =null;
		try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileStr))));
			try {
				
				while((dpidStr = br.readLine()) != null){
					//System.out.println(dpidStr);
					dpid.add(dpidStr.split(",")[0]);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		java.util.Collections.sort(dpid);
		return dpid;
	}
	
	public static int fileReadLineIPType(String fileStr,List<String> teacher,List<String> underGraduate, List<String> postGraduate){
		List<String> dpid = new ArrayList<>();
		
		//StringBuffer sb = 
		String IpStr = "";
		BufferedReader br =null;
		try {
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileStr))));
			try {
				
				while((IpStr = br.readLine()) != null){
					//System.out.println(dpidStr);
					if(IpStr.contains("������")) {
						underGraduate.add(IpStr.split(",")[0]);
					}
					else if(IpStr.contains("�о���")){
						postGraduate.add(IpStr.split(",")[0]);
					}
					else {
						teacher.add(IpStr.split(",")[0]);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		java.util.Collections.sort(teacher);
		java.util.Collections.sort(underGraduate);
		java.util.Collections.sort(postGraduate);
		int maxLength;
		maxLength = underGraduate.size()>=postGraduate.size()?underGraduate.size():postGraduate.size();
		maxLength = maxLength>=postGraduate.size()?maxLength:teacher.size();
		return maxLength;
	}
	
	public  static List<String> fileReaderDpid(String fileStr)  {
		
		File f = new File(fileStr);
	//	String res = null;
		List<String> dpid = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		if(!f.exists()) {
			System.out.println(f.getName()+" is null");
			return dpid;
		}
		else {
			FileInputStream in = null;
			
			 try {
				in = new FileInputStream(f);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] media = new byte[16];
			int length=-1;
			try {
				while(-1 !=(length= in.read(media))) {
					String ingo = new String(media, 0, length);
					//sb.append(ingo);
					dpid.add(ingo);
					//System.out.print(ingo);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//String res = new String(sb);
			return dpid;	
		}
	}
		
	public  static List<String> fileReaderToListLine(String fileStr) throws IOException  {
			
		List<String> dpid = new ArrayList<>();
	    File filename = new File(fileStr); // Ҫ��ȡ����·����input��txt�ļ�  
	    InputStreamReader reader;
		try {
			reader = new InputStreamReader(  
			        new FileInputStream(filename));
		    BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������  
		    String line = "";  
		    //line = br.readLine();  
		    while (line != null) {  
		        line = br.readLine(); // һ�ζ���һ������  
		        dpid.add(line);
		    }  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // ����һ������������reader  

	    return dpid;
		
	}
	
	
	//���б��еĸ���Ԫ�����λ������뵽�ļ���
	public  void writeListToFile(File f,List<String> l ) {
		java.util.Collections.sort(l);
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
			for (String string : l) {
				try {
					bw.write(string);
					bw.write("\r\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				bw.flush();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public File NewJmxFile(String newStr) {
		File newFile = null ;
		
		return newFile;
	}
	
	public boolean replaceFileContent(String newStr, String oldStr) {
		boolean flag = false;
		return flag;
	}
	
	public static void main(String[] args) {
		try {
		System.out.println(	new FileUtil().fileReader("C:\\apache-jmeter-4.0\\bin\\getTopo.jtl"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
