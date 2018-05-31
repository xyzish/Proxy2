package com.bjsxt.proxy.test;

import com.bjsxt.proxy.InvocationHandler;
import com.bjsxt.proxy.Proxy;

public class Client {

	public static void main(String[] args) throws Exception {
		UserMgr mgr = new UserMgrImpl();
		InvocationHandler h = new TransactionHandler(mgr);
		UserMgr u = (UserMgr)Proxy.newProxyInstance(UserMgr.class, h);
		u.addUser();

	}

}
