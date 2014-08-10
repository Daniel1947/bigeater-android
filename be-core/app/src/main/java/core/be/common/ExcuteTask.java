package core.be.common;

import java.util.Map;

import android.os.Handler;

public abstract class ExcuteTask {
	protected String result;
	protected Map params;
	protected Handler handle; 
	public static final int EXCUTE_SUCCESS = 0x00000000;
	public static final int EXCUTE_FAILED  = 0x00000001;

	
	public ExcuteTask(){}
	public ExcuteTask(Map params){
		this.params = params;
	}
	public ExcuteTask(Map params, Handler handle){
		this.params = params;
		this.handle = handle;
	}
}
