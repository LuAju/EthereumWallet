package my.contacteditor.jmxAndjtl;

import java.io.File;
import java.io.IOException;

import Util.BatUtil;
import my.contacteditor.SplitResponesByDpid;

public class LoginUtil {
	
	//线程锁，当文件写完毕后，才将其置为true
	private volatile boolean writed =false;
	
	//path路径的前半部分，为了节省操作
	private static final String SUBPATH = "D:/apache-jmeter-4.0/bin/" ;
	
	
	//账号密码变量
	private String account;
	private String pwd;
	
	public LoginUtil() {
		//super();
	}
	
	public LoginUtil(String account, String pwd) {
		this.account = account;
		this.pwd = pwd;
	}

	//创建读线程与写线程，写在前读在后。
	public void action(){
		Thread w = new Thread(new WriteThread());
		Thread r = new Thread(new ReadThread());
		w.start();
		r.start();
	}
	
	//写线程
	class WriteThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			//
			 SplitResponesByDpid srb = new SplitResponesByDpid();
				File temJmx = new File(SUBPATH+"EtherJmx/unlockAccountTem.jmx");
				File newJmx = new File(SUBPATH+"EtherJmx/unlockAccount.jmx");
				if(newJmx.exists()) {
					newJmx.delete();
				}
				try {
					String str = srb.readFile(temJmx);
					str = str.replaceAll("0x46eda370dd2615a63216f701f656e5562913326d",account);
					str = str.replaceAll(";,&quot;22222&quot;",";,&quot;"+pwd+"&quot;");  //可能会报错  
					srb.writeToFile(str, newJmx);
					//查看新建的脚本文件
					System.out.println("jmx============"+srb.readFile(newJmx));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			//写结束
			File newJtl = new File(SUBPATH+"resultJtl/unlockaccount.jtl");
			if(newJtl.exists()){
				newJtl.delete();
			}
			//new BatUtil(s);
			
			File bat = new File(SUBPATH+"bat/unlockAccount.bat");
	    	/*String s = "cmd /c start /b "+bat.getPath();*/
			String s = "cmd /c start  /b "+bat.getPath();
	    	new BatUtil(s);
	    	
	    	
/*			if(newJtl.exists()){
				try {
					System.out.println("jtl1============="+srb.readFile(newJtl));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				writed = true;
			};*/
			
		}
		
	}
	
	//读线程
	class ReadThread implements  Runnable {

		@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				SplitResponesByDpid srb = new SplitResponesByDpid();
				String jtl = null;
				File f = new File(SUBPATH+"resultJtl/unlockaccount.jtl");
				System.out.println(0);
				// TODO Auto-generated method stub
				if(f.exists()){
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					//开始读文件
					try {
					  jtl = srb.readFile(f);
				/*	  System.out.println(1);
				 * 查看运行结果文件*/
					  System.out.println("jtl========"+jtl);
					  if(!jtl.isEmpty()){
						  System.out.println("成共");
					  }
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					/*System.out.println(2);*/
					
					f.delete();
					break;
				}
				
			}
		}
		
	}
	
	
	
}
		/*
		 * package Util;
	
	public void writeJmx(String switchName,String deploy) {
		 SplitResponesByDpid srb = new SplitResponesByDpid();
		File temJmx = new File("E:\\apache-jmeter-4.0\\bin\\FlowtableDeleteTem.jmx");
		File newJmx = new File("E:\\apache-jmeter-4.0\\bin\\FlowtableDeleteTem"+switchName+".jmx");
		if(newJmx.exists()) {
			newJmx.delete();
		}
		try {
			String str = srb.readFile(temJmx);
			str = str.replaceAll("\\{&quot;dpid&quot;:0000000000000003,&quot;cookie&quot;:0,&quot;cookie_mask&quot;:0,&quot;table_id&quot;:0,&quot;idle_timeout&quot;:0,&quot;hard_timeout&quot;:0,&quot;priority&quot;:4444,&quot;buffer_id&quot;:0,&quot;flag&quot;:0,&quot;match&quot;:\\{\\},&quot;action&quot;:\\[\\]\\}",deploy);
			srb.writeToFile(str, newJmx);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void writeBatAndRun(String switchName) {
		// TODO Auto-generated method stub
		 SplitResponesByDpid srb = new SplitResponesByDpid();
		File temBat = new File("E:\\apache-jmeter-4.0\\bin\\bat\\FlowtableDeleteTem.bat");
		File newBat = new File("E:\\apache-jmeter-4.0\\bin\\bat\\FlowtableDeleteTem"+switchName+".bat");
/*		File newJtl = new File("C:\\apache-jmeter-4.0\\bin\\FlowtableDeleteTem"+switchName+".jtl");
		if(newJtl.exists()) {
			newJtl.delete();
		}*/
/*		if(newBat.exists()) {
			newBat.delete();
		}
		String str;
		try {
			str = srb.readFile(temBat);
			str = str.replaceAll("FlowtableDeleteTem.jmx",
					"FlowtableDeleteTem"+switchName+".jmx");
/*			str = str.replaceAll("FlowtableDeleteTem.jtl", 
					"FlowtableDeleteTem"+switchName+".jtl");*/
	/*		srb.writeToFile(str, newBat);
			String s = "cmd /c start /b "+newBat.getPath();
			//System.out.println(s);
			new BatUtil(s);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}

		 * 
		 * */
		
		
		
		

