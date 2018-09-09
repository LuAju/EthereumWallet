package Demo;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class Demo1 {
	public static void main(String[] args) throws Throwable {

        Map<String, String> headers = new HashMap<String, String>(1);
        headers.put("Content-Type", "application/json");

        JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://127.0.0.1:8101"), headers);
        Object result = client.invoke("eth_accounts", args, Object.class);
        System.out.println(result);
    }

}
