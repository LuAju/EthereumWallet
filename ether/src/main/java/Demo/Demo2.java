package Demo;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;

/**
 * @author zhuzhisheng
 * @Description
 * @date on 2016/12/31.
 */
public class Demo2 {
	/*
	 * https://blog.csdn.net/su_bo_2010/article/details/80095211
	 * */

    private static final String URL = "http://127.0.0.1:8101/";
	private static HttpService getService(){
        return new HttpService(URL);
    }

	public static Web3j initWeb3j() {
        //return new JsonRpc2_0Web3j(getService());
		return Web3j.build(getService());
    }
 
 
    /**
     * 初始化personal级别的操作对象
     * @return Geth
     */
    public static Geth initGeth(){
        return Geth.build(getService());
    }
 
    /**
     * 初始化admin级别操作的对象
     * @return Admin
     */
    public static Admin initAdmin(){
        return Admin.build(getService());
    }





	public static EthBlock getBlockEthBlock(Integer blockNumber) throws IOException {
	    Web3j web3j = initWeb3j();
	 
	    DefaultBlockParameter defaultBlockParameter = new DefaultBlockParameterNumber(blockNumber);
	    Request<?, EthBlock> request = web3j.ethGetBlockByNumber(defaultBlockParameter, true);
	    EthBlock ethBlock = request.send();
	    EthBlock.Block block = ethBlock.getBlock();
		System.out.println(block.getParentHash());
		System.out.println(block.getTimestampRaw());
		return ethBlock;
	}
	
	//获取交易信息
	public static Transaction getTransactionByHash(String hash) throws IOException {
	    Web3j web3j = initWeb3j();
	    Request<?, EthTransaction> request = web3j.ethGetTransactionByHash(hash);
	    Transaction tx = request.send().getTransaction().get();
	    return tx;
	}
	
	public static Map<String,Object> getTransInDetail(String hash){
		Map<String,Object> map = new HashMap<>();
		Transaction tx ;
		try {
			tx =  getTransactionByHash(hash);
			map.put("TransHash", hash);
			map.put("BlockNumber", tx.getBlockNumber());
			map.put("BlockHash", tx.getBlockHash());
			map.put("TransIndex", tx.getTransactionIndex());
			map.put("From", tx.getFrom());
			map.put("To", tx.getTo());
			map.put("Gas", tx.getGas());
			map.put("GasPrice", tx.getGasPrice());
			map.put("Value", tx.getValue());
			map.put("Data", tx.getInput());
			map.put("Nonce", tx.getNonce());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;	
	}
	
	
	@Test
	public void fun2(){
		Map<String,Object> map = new HashMap<>();
		map = getTransInDetail("0x370b167c2dc7f225ac3943a3a2e4ce579a790cb922266d1b758630c03a48e77e");
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			Object obj = it.next();
			System.out.println(obj);
			System.out.println(map.get(obj));
		}
	}
	
	
	//发送交易
	
	public static String sendTransaction(org.web3j.protocol.core.methods.request.Transaction transaction, String password) throws IOException {
	    Admin admin = initAdmin();
	    Request<?, EthSendTransaction> request = admin.personalSendTransaction(transaction, password);
	    EthSendTransaction ethSendTransaction = request.send();
	    return ethSendTransaction.getTransactionHash();		 
	}
	
	public static String sendTransaction(List<Object> augs, String password) throws IOException {
		//augs 的参数顺序
		/*String from, BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit,
        String to, BigInteger value, String data*/
		org.web3j.protocol.core.methods.request.Transaction tx = new org.web3j.protocol.core.methods.request.Transaction((String)augs.get(0), (BigInteger)augs.get(1), (BigInteger)augs.get(2), (BigInteger)augs.get(3), (String)augs.get(4), (BigInteger)augs.get(5), (String)augs.get(6));
	    Admin admin = initAdmin();
	    Request<?, EthSendTransaction> request = admin.personalSendTransaction(tx, password);
	    EthSendTransaction ethSendTransaction = request.send();
	    return ethSendTransaction.getTransactionHash();		 
	}
	
	
	public static String sendTransaction(List<Object> augs, String password,boolean simple) throws IOException {
		//augs 的参数顺序  为不需要的赋默认值
		/*String from, BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit,
        String to, BigInteger value, String data*/
		org.web3j.protocol.core.methods.request.Transaction tx = 
				new org.web3j.protocol.core.methods.request.Transaction((String)augs.get(0), 
						(BigInteger)augs.get(1), (BigInteger)augs.get(2), (BigInteger)augs.get(3), 
						(String)augs.get(4), (BigInteger)augs.get(5), (String)augs.get(6));
	    Admin admin = initAdmin();
	    Request<?, EthSendTransaction> request = admin.personalSendTransaction(tx, password);
	    EthSendTransaction ethSendTransaction = request.send();
	    return ethSendTransaction.getTransactionHash();		 
	}
	
	
	//注册账户

	public static String newAccount(String password) throws IOException {
	    Admin admin = initAdmin();
	    Request<?, NewAccountIdentifier> request = admin.personalNewAccount(password);
	    NewAccountIdentifier result = request.send();
	    return result.getAccountId();
	}
	
	
	
	//账户解锁
	public static Boolean unlockAccount(String address, String password, BigInteger duration) throws IOException {
	    Admin admin = initAdmin();
	    Request<?, PersonalUnlockAccount> request = admin.personalUnlockAccount(address, password, duration);
	    PersonalUnlockAccount account = request.send();
	    return account.accountUnlocked();
	}
	


 
	public static Boolean unlockAccount(String address, String password) throws IOException {
		//默认持续时间为20s
		BigInteger duration = new BigInteger("20");
	    Admin admin = initAdmin();
	    Request<?, PersonalUnlockAccount> request = admin.personalUnlockAccount(address, password, duration);
	    PersonalUnlockAccount account = request.send();
	    return account.accountUnlocked();
	} 
	
	@Test
	public void testUnlockAccount(){//成功
		try {
			System.out.println(unlockAccount("0x46eda370dd2615a63216f701f656e5562913326d", "123456789"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//
	
	@Test
	public void fun(){
		try {
			Transaction tx = Demo2.getTransactionByHash("0x370b167c2dc7f225ac3943a3a2e4ce579a790cb922266d1b758630c03a48e77e");
			System.out.println(tx.getBlockHash());
			System.out.println(tx.getFrom());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
}