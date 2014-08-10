package core.be.common;



import com.androidquery.AQuery;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import core.be.util.PKFHttp;

public class Manager extends Service{

	private final IBinder binder = new MyBinder();
	private AQuery aq = new AQuery(Manager.this);
	
	public Manager(){
		aq = new AQuery(this);
		PKFHttp.getInstance().init(aq);
	}
	
	public class MyBinder extends Binder{
		public void show() {
		}

		public Manager getService() {
			return Manager.this;
		}
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return binder;
	}
	
	public void excute(IExcute task){
		new ExcuteThread(task).start();
		
	}
	
	
	class ExcuteThread extends Thread {
		private IExcute task;
		public ExcuteThread(IExcute task){
			this.task = task;
		}

		@Override
		public void run() {
			try {
				task.excute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
