package com.shopnow.main;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class ConnectManager {
	private static OkHttpClient okHttpClient;
	private static ConnectManager connectManager;

	private ConnectManager() {
		okHttpClient = new OkHttpClient();
		CookieManager cookieManager = new CookieManager();
		CookieHandler.setDefault(cookieManager);
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		okHttpClient.setCookieHandler(cookieManager);
	}

	public static ConnectManager getInstance() {
		if (okHttpClient == null)
			connectManager = new ConnectManager();
		return connectManager;
	}

	public static boolean isNetworkAvailable(final Context context) {
		final ConnectivityManager connectivityManager = ((ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE));
		return connectivityManager.getActiveNetworkInfo() != null
				&& connectivityManager.getActiveNetworkInfo().isConnected();
	}

	public JSONObject request(String url, RequestBody formBody) {
		Request request = new Request.Builder().url(url).post(formBody).build();
		Response response;
		try {
			response = okHttpClient.newCall(request).execute();
			String str = response.body().string();
			Log.i("response before", str);
			str = str.replaceAll(">.+<", "><").replaceAll("<.+>", "");
			Log.i("response", str);
			if (response.isSuccessful())
				try {
					return new JSONObject(str);
				} catch (JSONException e) {
					return null;
				}
			return null;
		} catch (IOException e) {
			return null;
		}

	}

}