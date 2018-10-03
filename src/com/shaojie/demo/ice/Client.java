package com.shaojie.demo.ice;

import com.shaojie.demo.slice.IbTestPrx;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectPrx;
import com.zeroc.Ice.Util;

public class Client {
	public static void main(String[] args) {
//		String[] initParams = new String[] {"--Ice.Default.Locator=IceGrid/Locator:tcp -h localhost -p 4061"};
		try (com.zeroc.Ice.Communicator ic = com.zeroc.Ice.Util.initialize(args)) {
			ObjectPrx base = ic.stringToProxy("OnlineBook:default -p 10000");
//			ObjectPrx base = ic.stringToProxy("OnlineBook@OnlineBookAdaper");
			IbTestPrx ob = IbTestPrx.checkedCast(base);
			if (ob == null) {
				throw new Error("Invalid proxy");
			}
			boolean f = ob.book("邵杰", 11);
			System.out.println(f);
		}
	}
}
