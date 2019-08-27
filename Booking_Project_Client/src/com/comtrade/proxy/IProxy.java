package com.comtrade.proxy;

import java.io.IOException;
import java.net.URISyntaxException;

import com.comtrade.domain.user.User;

public interface IProxy {

	void login(User user) throws ClassNotFoundException, IOException, URISyntaxException;

}
