package Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class EthUtil {
	public static void main(String[] args) throws Throwable {
		 args = new String[]{"0x61188af0cda75099520d499224b5545e12ace86e"};
        Map<String, String> headers = new HashMap<String, String>(1);
        headers.put("Content-Type", "application/json");		
        JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://127.0.0.1:8101"), headers);
        Object result = client.invoke("eth_getBalance", new String[]{"0x46eda370dd2615a63216f701f656e5562913326d"}, Object.class);
        System.out.println(result);
    }

	public static String getAccount(int index){
		  String[] args = new String[1];
		  Map<String, String> headers = new HashMap<String, String>(1);
		  //= new ArrayList<String>();
		  
	        headers.put("Content-Type", "application/json");

	        JsonRpcHttpClient client;
			try {
				client = new JsonRpcHttpClient(new URL("http://127.0.0.1:8101"), headers);
				 //  Object result;
				try {
					List<String> result  =(ArrayList<String>) client.invoke("eth_accounts", args, Object.class);
					 return result.get(index);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      
			} catch (MalformedURLException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			}
			return "error";	     
	}
	
	public static List<String> getAccounts(){
		  String[] args = new String[1];
		  Map<String, String> headers = new HashMap<String, String>(1);
		  //= new ArrayList<String>();
		  
	        headers.put("Content-Type", "application/json");

	        JsonRpcHttpClient client;
			try {
				client = new JsonRpcHttpClient(new URL("http://127.0.0.1:8101"), headers);
				 //  Object result;
				try {
					List<String> result  =(ArrayList<String>) client.invoke("eth_accounts", args, Object.class);
					 return result;
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      
			} catch (MalformedURLException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			return null;	     
	}
	
	
	public static String getBalance(String account){
		  String[] args = new String[]{"0x46eda370dd2615a63216f701f656e5562913326d"};
		  Map<String, String> headers = new HashMap<String, String>(1);
		  //= new ArrayList<String>();
		  
	        headers.put("Content-Type", "application/json");

	        JsonRpcHttpClient client;
			try {
				client = new JsonRpcHttpClient(new URL("http://127.0.0.1:8101"), headers);
				  Object result;
				try {
				/*	List<String>*/ result  = client.invoke("eth_getBalance", args, Object.class);
					
				System.out.println(result.getClass());
				return result.toString();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      
			} catch (MalformedURLException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			}
			return "error";
	}
	
	@Test
	public void fun(){
		EthUtil.getAccount(1);
	}
	
}
