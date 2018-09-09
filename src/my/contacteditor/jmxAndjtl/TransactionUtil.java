package my.contacteditor.jmxAndjtl;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Util.BatUtil;
import entity.DatabaseUtil;
import entity.Transactions;
import my.contacteditor.SplitResponesByDpid;

public class TransactionUtil {
	
	//线程锁，当文件写完毕后，才将其置为true
	private volatile boolean writed =false;
	
	//path路径的前半部分，为了节省操作
	private static final String SUBPATH = "D:/apache-jmeter-4.0/bin/" ;
	
	
	//账号密码变量
	private String[] params;
	
	public TransactionUtil() {
		//super();
	}

	public TransactionUtil(String[] params) {
		this.params = params;
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
				File temJmx = new File(SUBPATH+"EtherJmx/sendTransactionTem.jmx");
				File newJmx = new File(SUBPATH+"EtherJmx/sendTransaction.jmx");
				if(newJmx.exists()) {
					newJmx.delete();
				}
				try {
				/*
    &quot;from&quot;:&quot;0x46eda370dd2615a63216f701f656e5562913326d&quot;,&#xd;
    &quot;to&quot;:&quot;0x7c96f80ce3740f2f66df997169335f5700c8dcf0&quot;,&#xd;
      &quot;gas&quot;: &quot;0x76c0&quot;, &#xd;
  &quot;gasPrice&quot;: &quot;0x9184e72a000&quot;, &#xd;
  &quot;value&quot;: &quot;0x9184e72a&quot;, &#xd;
    &quot;data&quot;:&quot;0x68656C6C6F20776F726C6468656C6C6F20776F726C6468656C6C6F20776F726C64&quot;&#xd;

				 * */	
					
					String str = srb.readFile(temJmx);
					str = str.replaceAll("&quot;from&quot;:&quot;0x46eda370dd2615a63216f701f656e5562913326d&quot;",
										 "&quot;from&quot;:&quot;"+params[0]+"&quot;");
					str = str.replaceAll("&quot;to&quot;:&quot;0x7c96f80ce3740f2f66df997169335f5700c8dcf0&quot;",
							             "&quot;to&quot;:&quot;"+params[1]+"&quot;");
					str = str.replaceAll("&quot;gas&quot;: &quot;0x76c0&quot;","&quot;gas&quot;: &quot;"+params[2]+"&quot;");
					str = str.replaceAll("&quot;gasPrice&quot;: &quot;0x9184e72a000&quot;",
										 "&quot;gasPrice&quot;: &quot;"+params[3]+"&quot;");
					str = str.replaceAll("&quot;value&quot;: &quot;0x9184e72a&quot;",
										 "&quot;value&quot;: &quot;"+params[4]+"&quot;");
					str = str.replaceAll("&quot;data&quot;:&quot;0x68656C6C6F20776F726C6468656C6C6F20776F726C6468656C6C6F20776F726C64&quot;",
										 "&quot;data&quot;:&quot;"+params[5]+"&quot;");
					str = str.replaceAll("&quot;nonce&quot;: &quot;0x9184e72a&quot;","&quot;nonce&quot;: &quot;"+params[6]+"&quot;");
					str = str.replaceAll("&quot;condition&quot;: &quot;0x9184e72a&quot;","&quot;condition&quot;: &quot;"+params[7]+"&quot;");
			
					
					srb.writeToFile(str, newJmx);
					//查看新建的脚本文件
					//System.out.println("jmx============"+srb.readFile(newJmx));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			//写结束
			File newJtl = new File(SUBPATH+"resultJtl/sendTransaction.jtl");
			if(newJtl.exists()){
				newJtl.delete();
			}
			//new BatUtil(s);
			
			File bat = new File(SUBPATH+"bat/SendTransaction.bat");
	    	/*String s = "cmd /c start /b "+bat.getPath();*/
			String s = "cmd /c start   "+bat.getPath();
	    	new BatUtil(s);
	    	
	    	
			if(newJtl.exists()){
				try {
					System.out.println("jtl1============="+srb.readFile(newJtl));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				writed = true;
			};
			
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
				File f = new File(SUBPATH+"resultJtl/sendTransaction.jtl");
				System.out.println(0);
				// TODO Auto-generated method stub
				if(f.exists()){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String tranHash="";
					//开始读文件
					try {
					  jtl = srb.readFile(f);
					  System.out.println(1);
				
					  System.out.println("jtl========"+jtl);
					  Matcher m = Pattern.compile("0x\\w*").matcher(jtl);
					  while(m.find()){
						 // System.out.println(m.group());
						  tranHash = m.group();
					  }
					  Transactions t = new Transactions();
					  t.setAccount(params[0]);
					  t.setTransHash(tranHash);
					  new DatabaseUtil().insert(t);
					  
					 // index = jtl.indexOf("0x");
					  //int indexEnd = jtl.indexOf("\"\"}\",Thr");
/*					  if(index!=-1){
						  System.out.println("成功");
						  //如果成功获取，获取文件内的交易hash值，加上 params【0】的值，生成
						
						//  System.out.println(jtl.substring(index, indexEnd));
						  
					  }*/
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
		
		
		
		

