package my.contacteditor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SplitResponesByDpid {

	private boolean flag = false;
	
    public  String modify(String s) {
    	String mos[] = null;
    	mos = s.split("\"", 2);
    	return mos[0];
    }
    /*
     * ��ȡ�����з��ظ��Ľ���������
     * */
    public  int numOfDpid(String[][] res) {
    	ArrayList<String> str = new ArrayList<String>();
    	for(int i =0;i<res.length;i++) {
    		for(int j=0;j<res[i].length;j++) {
    			if(-1==(str.lastIndexOf(res[i][j]))) {
    				str.add(res[i][j]);
    			}
    		}
    	}
    	return str.size();
    }
    
    public ArrayList<String> switchNameByArray(String[][] res) {
    	ArrayList<String> str = new ArrayList<String>();
    	for(int i2 =0;i2<res.length;i2++) {
    		for(int j2=0;j2<res[i2].length;j2++) {
    			if(-1==(str.lastIndexOf(res[i2][j2]))) {
    				str.add(res[i2][j2]);
    			}
    		}
    	}
    	String[] SwitchName = new String[str.size()] ;
    	System.out.println(str.size());
    	for(int i2=0;i2<str.size();i2++) {
    		SwitchName[i2] = str.get(i2);
    	}
    	return str;
    }
    
    public List<String> rangeList(List<String> l){
    	
    	return l;
    }
    
    public String[] switchName(String[][] res) {
    	ArrayList<String> str = new ArrayList<String>();
    	for(int i2 =0;i2<res.length;i2++) {
    		for(int j2=0;j2<res[i2].length;j2++) {
    			if(-1==(str.lastIndexOf(res[i2][j2]))) {
    				str.add(res[i2][j2]);
    			}
    		}
    	}
    	String[] SwitchName = new String[str.size()] ;
    	System.out.println(str.size());
    	for(int i2=0;i2<str.size();i2++) {
    		SwitchName[i2] = str.get(i2);
    	}
    	return SwitchName;
    }
    
    public String[][] getErWeiArray(String s){
    	String[] splitByDpid = s.split("\"dpid\": \"");
    	int n = splitByDpid.length;
    	String[][] res = new String[n/2][2];
    	//2.��n���ַ�������������ʹ��ÿ���ַ���ֻ����id��
    	for(int i=1;i<n;i++) {
    		splitByDpid[i] = modify (splitByDpid[i]);
    	}
    	//3.ÿ������һ��
    	for(int i=1;i<n/2+1;i++) {
    		for(int j=0;j<2;j++) {
    			res[i-1][j] = splitByDpid[2*i+j-1];
    		}
    	} 
    	return res;
    }
    
    public int numberOfSwitches(String s ) {
    	int i =0;
    	String[][] res = getErWeiArray(s);
    	i = numOfDpid(res);
    	return i;
    }
    
    public String readFile(File f) throws IOException {
    	String s = "";
    	
    	 if(f.exists()) {	
        	StringBuilder sb = new StringBuilder();
        	FileReader reader = new FileReader(f);


        	BufferedReader breader = new BufferedReader(reader);
        	//String s ="";
        	while((s =breader.readLine()) != null) {
        		sb.append(s+"\n");
        	}
        	breader.close();
        	s = new String(sb);
    	}
    	 else {
    		
    		
    		 
    		 System.out.println(f.getPath()+"   file is not exist");
    	 }
    	return s;
    }
    
    public StringBuilder readFileToSB(File f) throws IOException {
    	StringBuilder sb = new StringBuilder();
    	FileReader reader = new FileReader(f);
    	BufferedReader breader = new BufferedReader(reader);
    	String s ="";
    	while((s =breader.readLine()) != null) {
    		sb.append(s+"\n");
    	}
    	breader.close();
    	return sb;
    }
    
    
    
    public void writeToFile(String s,File f) {
    	 try {  
             FileWriter fw = new FileWriter(f, true);  
             BufferedWriter bw = new BufferedWriter(fw);  
             bw.write(s);  
             bw.close();  
             fw.close();  
         } catch (Exception e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
         }  
    	
    }
    
    public void makeJmxFile(List<String> l) {
    	for(int i=0;i<l.size();i++) {
    		File f = new File("C:\\apache-jmeter-4.0\\bin\\"+"getSwitchStats"+l.get(i)+".jmx");
    		if(!(f.exists())) {
    			
    		}
    	}
    }
    
    public static void main(String[] args) {
		SplitResponesByDpid srb = new SplitResponesByDpid();
/*		String s ="[{\"src\": {\"hw_addr\": \"aa:a6:81:be:a0:c6\", \"name\": \"s2-eth3\", \"port_no\": \"00000003\", \"dpid\": \"0000000000000002\"}, \"dst\": {\"hw_addr\": \"06:5b:dd:7a:eb:ee\", \"name\": \"s1-eth1\", \"port_no\": \"00000001\", \"dpid\": \"0000000000000001\"}}, {\"src\": {\"hw_addr\": \"36:c3:c6:61:e2:35\", \"name\": \"s5-eth1\", \"port_no\": \"00000001\", \"dpid\": \"0000000000000005\"}, \"dst\": {\"hw_addr\": \"46:23:57:e5:75:bc\", \"name\": \"s6-eth3\", \"port_no\": \"00000003\", \"dpid\": \"0000000000000006\"}}, {\"src\": {\"hw_addr\": \"ce:31:4d:9d:57:40\", \"name\": \"s4-eth3\", \"port_no\": \"00000003\", \"dpid\": \"0000000000000004\"}, \"dst\": {\"hw_addr\": \"be:4f:32:f3:5a:36\", \"name\": \"s2-eth2\", \"port_no\": \"00000002\", \"dpid\": \"0000000000000002\"}}, {\"src\": {\"hw_addr\": \"2e:b4:88:11:f5:5b\", \"name\": \"s1-eth2\", \"port_no\": \"00000002\", \"dpid\": \"0000000000000001\"}, \"dst\": {\"hw_addr\": \"aa:68:65:8c:0a:07\", \"name\": \"s5-eth3\", \"port_no\": \"00000003\", \"dpid\": \"0000000000000005\"}}, {\"src\": {\"hw_addr\": \"16:d2:3c:ea:ce:d9\", \"name\": \"s2-eth1\", \"port_no\": \"00000001\", \"dpid\": \"0000000000000002\"}, \"dst\": {\"hw_addr\": \"2e:2a:dc:19:60:01\", \"name\": \"s3-eth3\", \"port_no\": \"00000003\", \"dpid\": \"0000000000000003\"}}, {\"src\": {\"hw_addr\": \"06:5b:dd:7a:eb:ee\", \"name\": \"s1-eth1\", \"port_no\": \"00000001\", \"dpid\": \"0000000000000001\"}, \"dst\": {\"hw_addr\": \"aa:a6:81:be:a0:c6\", \"name\": \"s2-eth3\", \"port_no\": \"00000003\", \"dpid\": \"0000000000000002\"}}, {\"src\": {\"hw_addr\": \"2e:2a:dc:19:60:01\", \"name\": \"s3-eth3\", \"port_no\": \"00000003\", \"dpid\": \"0000000000000003\"}, \"dst\": {\"hw_addr\": \"16:d2:3c:ea:ce:d9\", \"name\": \"s2-eth1\", \"port_no\": \"00000001\", \"dpid\": \"0000000000000002\"}}, {\"src\": {\"hw_addr\": \"b6:e8:d7:8e:b8:35\", \"name\": \"s5-eth2\", \"port_no\": \"00000002\", \"dpid\": \"0000000000000005\"}, \"dst\": {\"hw_addr\": \"76:9b:c2:07:a7:d7\", \"name\": \"s7-eth3\", \"port_no\": \"00000003\", \"dpid\": \"0000000000000007\"}}, {\"src\": {\"hw_addr\": \"76:9b:c2:07:a7:d7\", \"name\": \"s7-eth3\", \"port_no\": \"00000003\", \"dpid\": \"0000000000000007\"}, \"dst\": {\"hw_addr\": \"b6:e8:d7:8e:b8:35\", \"name\": \"s5-eth2\", \"port_no\": \"00000002\", \"dpid\": \"0000000000000005\"}}, {\"src\": {\"hw_addr\": \"46:23:57:e5:75:bc\", \"name\": \"s6-eth3\", \"port_no\": \"00000003\", \"dpid\": \"0000000000000006\"}, \"dst\": {\"hw_addr\": \"36:c3:c6:61:e2:35\", \"name\": \"s5-eth1\", \"port_no\": \"00000001\", \"dpid\": \"0000000000000005\"}}, {\"src\": {\"hw_addr\": \"aa:68:65:8c:0a:07\", \"name\": \"s5-eth3\", \"port_no\": \"00000003\", \"dpid\": \"0000000000000005\"}, \"dst\": {\"hw_addr\": \"2e:b4:88:11:f5:5b\", \"name\": \"s1-eth2\", \"port_no\": \"00000002\", \"dpid\": \"0000000000000001\"}}, {\"src\": {\"hw_addr\": \"be:4f:32:f3:5a:36\", \"name\": \"s2-eth2\", \"port_no\": \"00000002\", \"dpid\": \"0000000000000002\"}, \"dst\": {\"hw_addr\": \"ce:31:4d:9d:57:40\", \"name\": \"s4-eth3\", \"port_no\": \"00000003\", \"dpid\": \"0000000000000004\"}}]\r\n" + 
				"";
		String s = "[{\"\"src\"\": {\"\"hw_addr\"\": \"\"02:75:52:9e:72:53\"\", \"\"name\"\": \"\"s1-eth2\"\", \"\"port_no\"\": \"\"00000002\"\", \"\"dpid\"\": \"\"0000000000000001\"\"}, \"\"dst\"\": {\"\"hw_addr\"\": \"\"56:40:ba:20:d5:84\"\", \"\"name\"\": \"\"s3-eth3\"\", \"\"port_no\"\": \"\"00000003\"\", \"\"dpid\"\": \"\"0000000000000003\"\"}}, {\"\"src\"\": {\"\"hw_addr\"\": \"\"ba:51:fd:1b:f5:89\"\", \"\"name\"\": \"\"s1-eth1\"\", \"\"port_no\"\": \"\"00000001\"\", \"\"dpid\"\": \"\"0000000000000001\"\"}, \"\"dst\"\": {\"\"hw_addr\"\": \"\"fa:31:0a:d3:1c:3e\"\", \"\"name\"\": \"\"s2-eth3\"\", \"\"port_no\"\": \"\"00000003\"\", \"\"dpid\"\": \"\"0000000000000002\"\"}}, {\"\"src\"\": {\"\"hw_addr\"\": \"\"fa:31:0a:d3:1c:3e\"\", \"\"name\"\": \"\"s2-eth3\"\", \"\"port_no\"\": \"\"00000003\"\", \"\"dpid\"\": \"\"0000000000000002\"\"}, \"\"dst\"\": {\"\"hw_addr\"\": \"\"ba:51:fd:1b:f5:89\"\", \"\"name\"\": \"\"s1-eth1\"\", \"\"port_no\"\": \"\"00000001\"\", \"\"dpid\"\": \"\"0000000000000001\"\"}}, {\"\"src\"\": {\"\"hw_addr\"\": \"\"56:40:ba:20:d5:84\"\", \"\"name\"\": \"\"s3-eth3\"\", \"\"port_no\"\": \"\"00000003\"\", \"\"dpid\"\": \"\"0000000000000003\"\"}, \"\"dst\"\": {\"\"hw_addr\"\": \"\"02:75:52:9e:72:53\"\", \"\"name\"\": \"\"s1-eth2\"\", \"\"port_no\"\": \"\"00000002\"\", \"\"dpid\"\": \"\"0000000000000001\"\"}}]\"";
		s = s.replace("\"\"", "\"");
		int i = srb.numOfDpid(srb.getErWeiArray(s));
		int j = srb.numberOfSwitches(s);
		System.out.println(j);
		System.out.println(i);
		String[][] res = srb.getErWeiArray(s);
   	
		String[] s2 = srb.switchName(srb.getErWeiArray(s));
		for(String tem:s2) {
			//System.out.println(tem);
		}
		
		List<String> ll = new ArrayList<String>();
		ll = srb.switchNameByArray(srb.getErWeiArray(s));
		for(String tem:ll) {
			//System.out.println(tem);
		}
		
	    File f = new File("C:\\apache-jmeter-4.0\\bin\\"+"getSwitchStats"+".jmx");
		try {
			s = srb.readFile(f);
		//	System.out.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//���� �滻
	//	s = s.replaceAll("<stringProp name=\"HTTPSampler.path\">/v1.0/topology/switches/0000000000000002</stringProp>", "<stringProp name=\"HTTPSampler.path\">/v1.0/topology/switches/0000000000000004</stringProp>");
		//System.out.println(s);
	//	srb.writeToFile(s, new File("C:\\apache-jmeter-4.0\\bin\\"+"getSwitchStats555555"+".jmx"));
		
		
		
		File switchf = new File("C:\\apache-jmeter-4.0\\bin\\"+"SwitchStats"+"0000000000000002"+".jtl"); //�����þ����ֵ�滻��SwitchName
		if(!(switchf.exists())) {
			//2. �滻�ű��ļ� �е� ��ɶ �����½��ű��ļ���ִ��
			
			
		}
		else {
			try {
				String switchStr = srb.readFile(switchf);
				switchStr =switchStr.substring(switchStr.indexOf("[{"));
				System.out.println(switchStr);
				switchStr = switchStr.substring(0, switchStr.indexOf("\",T"));
				
				System.out.println(switchStr);
				
				switchStr = switchStr.replaceAll("\"\"", "\"");
				System.out.println(switchStr);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
    }
    
}
