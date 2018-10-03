package com.shaojie.demo.ice;

import com.shaojie.demo.slice.IbTest;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Current;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import com.zeroc.IceBox.Service;

public class IbBookServer implements Service,IbTest{
	private ObjectAdapter adapter;
	@Override
	public void start(String name, Communicator communicator, String[] args) {
		adapter = communicator.createObjectAdapter(name);
		adapter.add(this , Util.stringToIdentity(name));
		adapter.activate();
		System.out.println("start success  service name:"+name);
	}

	@Override
	public void stop() {
		adapter.deactivate();
		System.out.println("stop success");
	}

	@Override
	public boolean book(String name, int num, Current current) {
		System.out.println("book success name:"+name+",num:"+num);
		return true;
	}

}
