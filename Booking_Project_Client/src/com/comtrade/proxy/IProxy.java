package com.comtrade.proxy;

import java.io.IOException;

import com.comtrade.domain.User;

public interface IProxy {

	void login(User user) throws ClassNotFoundException, IOException;

}
