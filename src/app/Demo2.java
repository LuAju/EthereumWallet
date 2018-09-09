package app;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import my.contacteditor.SplitResponesByDpid;

public class Demo2 {
	
	@Test
	public void readJtl(){
		
		String fileStr = "D:/apache-jmeter-4.0/bin/"+"resultJtl/getTransaction.jtl";
		SplitResponesByDpid srb = new SplitResponesByDpid();
		String jtlStr = null;
		try {
			jtlStr = srb.readFile(new File(fileStr));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * timeStamp,elapsed,label,responseCode,responseMessage,threadName,dataType,success,failureMessage,bytes,sentBytes,
		 * grpThreads,allThreads,Latency,IdleTime,Connect1534647360639,71,aa,200,
		 * "{""jsonrpc"":""2.0"",""id"":1,""result"":
		 * {""blockHash"":""0xd2fb4c32d1b3369f013b10850fde0c3aac32ac26fc78cd530ac3dadd0f117d85"",
		 * ""blockNumber"":""0x94"",""from"":""0x46eda370dd2615a63216f701f656e5562913326d"",
		 * ""gas"":""0x76000"",""gasPrice"":""0x9184e72a000"","
		 * "hash"":""0x3b4604b0241c0fd9d132e953826876488c4c352b7d14a19f9f13407b18bb956b"",
		 * ""input"":""0x68656c6c6f20776f726c6468656c6c6f20776f726c6468656c6c6f20776f726c64"",
		 * ""nonce"":""0xe"",""to"":""0x7c96f80ce3740f2f66df997169335f5700c8dcf0"",
		 * ""transactionIndex"":""0xa"",""value"":""0x9184e72a"",""v"":""0x38"",""r"":""0x800f4863c4b181b942d095789a7a66e763fbbcbf4bd9ddac3598813e416f8e0b"",""s"":""0x35b2bf29e8fb0bcabd0183c401e0d8edb1dcb4fe18f615b74e3464b8bd11b3a1""}}
",Thread Group 1-1,text,true,,764,311,1,1,71,0,54
		 * 
		 * */
		jtlStr = jtlStr.replace("\"\"", "\"");
		Matcher m = Pattern.compile("0x\\w+").matcher(jtlStr);
		while(m.find()){
			System.out.println(m.group());
		}
		System.out.println(jtlStr);
		
	}
	
	
	public static void main(String[] args) {
		
	}
}
