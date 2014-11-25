/* 
 **

 ** Copyright 2014, Jules White
 **
 ** 
 */
package com.wachira.symptomapp.services;

import com.wachira.symptomapp.SecuredRestBuilder;
import com.wachira.symptomapp.EasyHttpClient;

import retrofit.RestAdapter.LogLevel;
import retrofit.client.ApacheClient;
import android.content.Context;
import android.content.Intent;

public class CheckinSvc {

	public static final String CLIENT_ID = "mobile";

	private static CheckinSvcApi videoSvc_;

	/*public static synchronized CheckinSvcApi getOrShowLogin(Context ctx) {
		if (videoSvc_ != null) {
			return videoSvc_;
		} else {
			Intent i = new Intent(ctx, LoginScreenActivity.class);
			ctx.startActivity(i);
			return null;
		}
	}*/

	public static synchronized CheckinSvcApi init(String server, String user,
			String pass) {

		videoSvc_ = new SecuredRestBuilder()
				.setLoginEndpoint(server + CheckinSvcApi.TOKEN_PATH)
				.setUsername(user)
				.setPassword(pass)
				.setClientId(CLIENT_ID)
				.setClient(
						new ApacheClient(new EasyHttpClient()))
				.setEndpoint(server).setLogLevel(LogLevel.FULL).build()
				.create(CheckinSvcApi.class);

		return videoSvc_;
	}
}
