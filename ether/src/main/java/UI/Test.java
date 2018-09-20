package UI;

import java.io.IOException;

import Demo.Utils;

public class Test {
	public static void main(String[] args) {
		String address = "0x46eda370dd2615a63216f701f656e5562913326d";
    	String password = "123456789";
    	boolean isRight = true;
    	System.out.println(address);
    	System.out.println(password);
    	try {
			isRight = Utils.unlockAccount(address, password);
			System.out.println(isRight);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
