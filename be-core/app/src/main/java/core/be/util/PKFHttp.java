package core.be.util;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.auth.AccountHandle;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

/**
 * DESCRIPTION <short description of component this file declares/defines>
 * 
 * PRIVATE CLASSES <list of private classes defined - with one-line
 * descriptions>
 * 
 * 
 * NOTES <other useful comments, qualifications, etc.> AUTHOR (MM/DD/YY) Daniel
 * 2013-9-20 - Creation
 * 
 * MODIFIED (MM/DD/YY) Daniel 2013-9-21
 */
public class PKFHttp {

	private Result questResult = new Result();
	private static PKFHttp instance;
	private AQuery aq;
	private String url;
	private Map params;

	private PKFHttp() {
	}

	public static PKFHttp getInstance() {
		if (instance == null) {
			instance = new PKFHttp();
			return instance;
		} else {
			return instance;
		}
	}

	public void init(AQuery aq) {
		this.aq = aq;
	}

	public enum RESPONSE_TYPE {
		GET_JSON, POST_JSON
	}

	public enum SERVICE_RESPONSE {
		SUCCESS, FAILED, NULL,
	}

	public static class Result {
		private SERVICE_RESPONSE response;
		private String msg;

		public Result() {
		}

		public void setResponse(SERVICE_RESPONSE response) {
			this.response = response;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public String getMsg() {
			return this.msg;
		}

		public SERVICE_RESPONSE getResponse() {
			return this.response;
		}
		
		public void clear() {
			this.response = SERVICE_RESPONSE.NULL;
			this.msg = null;
		}
	}

	public static String Post() {
		String result = "";
		return result;
	}

	public Result AsynchronousRequest(String url, RESPONSE_TYPE type, Map params) {
		this.url = url;
		this.params = params;
		Log.d("request", "url:" + url);
		Log.d("request", "params:" + params);
		questResult.clear();
		switch (type) {
		case GET_JSON:
			asyncGetJson();
			break;
		case POST_JSON:
			asynPostJson();

		default:
			break;
		}

		return questResult;
	}

	/**
	 * 
	 * @Title: AsynchronousRequest
	 * @Description: TODO
	 * @param url
	 * @param type
	 * @return Object return result
	 * @throws null
	 */
	public Result AsynchronousRequest(String url, RESPONSE_TYPE type) {
		return this.AsynchronousRequest(url, type, null);
	}

	private void asyncGetJson() {

		AjaxCallback<JSONObject> cb = new AjaxCallback<JSONObject>();
		if (params != null) {
			setParams(params);
		}
		cb.url(this.url).type(JSONObject.class);
		aq.sync(cb);

		JSONObject json = cb.getResult();
		AjaxStatus status = cb.getStatus();
		cb.getUrl();
		if (json != null) {
			questResult.setResponse(SERVICE_RESPONSE.SUCCESS);
			questResult.setMsg(json.toString());
			status.invalidate();
			Log.d("Success", json.toString());
		} else {
			questResult.setResponse(SERVICE_RESPONSE.FAILED);
			questResult.setMsg("Error:" + status.getCode());
			status.invalidate();
			Log.d("Error", status.getCode() + " " + url);
		}
	}

	private void setParams(Map<String, String> params) {
		int index = 0;
		for (Map.Entry<String, String> entry : params.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			this.url += ((index == 0) ? "" : "&") + key + "=" + value;
			index++;
		}
	}

	private void asynPostJson() {
		AjaxCallback<JSONObject> cb = new AjaxCallback<JSONObject>();
		if( params != null){
			cb.params(params);
		}
		cb.url(this.url).type(JSONObject.class);
		aq.sync(cb);
		
		JSONObject json = cb.getResult();
		AjaxStatus status = cb.getStatus();
		cb.getUrl();
		if (json != null) {
			questResult.setResponse(SERVICE_RESPONSE.SUCCESS);
			questResult.setMsg(json.toString());
			status.invalidate();
		} else {
			questResult.setResponse(SERVICE_RESPONSE.FAILED);
			questResult.setMsg("Error:" + status.getCode());
			Log.d("Error", status.getCode() + " " + url);
			status.invalidate();
		}
	}
}
