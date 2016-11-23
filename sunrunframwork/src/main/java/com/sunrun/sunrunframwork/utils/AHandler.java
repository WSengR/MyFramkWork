package com.sunrun.sunrunframwork.utils;

import java.util.Timer;
import java.util.TimerTask;

import com.sunrun.sunrunframwork.app.BaseApplication;

import android.os.Handler;
import android.util.SparseArray;

/**
 * Handler优化工具
 * 
 * @author cnsunrun
 *
 */
public class AHandler {
	static SparseArray<Task> tasks = new SparseArray<Task>();// 任务集合
	static Timer timer = new Timer();// 计时器
	static Handler handler = new Handler(BaseApplication.getInstance().getMainLooper()) {
		public void handleMessage(android.os.Message msg) {
			Task task = tasks.get(msg.what);
			if (task != null) {
				if (task.isRun()) {
					task.update();
				}
				if (!task.isPeriod) {// 非周期任务,更新完界面之后就从任务列表将任务移除
					tasks.remove(msg.what);
					timer.purge();
				}

			}
		};
	};

	private AHandler() {
//		handler.post(r)
	}
	public  boolean post(Runnable r)
    {
		return handler.post(r);
    }
	/**
	 * 执行任务
	 * 
	 * @param task
	 */
	public static void runTask(Task task) {
		timer.schedule(task, 0);
		tasks.put(task.hashCode(), task);
	}

	public static void runTask(Task task, long delay) {
		timer.schedule(task, delay);
		tasks.put(task.hashCode(), task);
	}
	public static void cancel(Task task) {
		if (task != null)
			task.cancel();
	}

	/**
	 * 执行一个任务
	 * 
	 * @param task
	 *            任务对象
	 * @param delay
	 *            延时
	 * @param period
	 *            周期
	 */
	public static void runTask(final Task task, long delay, long period) {
		timer.schedule(task, delay, period);
		tasks.put(task.hashCode(), task.setPeriod(period > 0));
	}

	/**
	 * @author cnsunrun 任务对象
	 */
	public static abstract class Task extends TimerTask {
		/**
		 * 是否为周期任务
		 */
		boolean isPeriod = false;
		boolean isRun = true;

		final public Task setPeriod(boolean isPeriod) {
			this.isPeriod = isPeriod;
			return this;
		}

		/**
		 * 异步任务
		 */
		public void task() {}

		/**
		 * 视图更新,该方法在主线程中运行
		 */
		public void update() {}

		public boolean isRun() {
			return isRun;
		}

		@Override
		public boolean cancel() {
			isPeriod = false;
			isRun = false;
			handler.sendEmptyMessage(hashCode());
			return super.cancel();
		}

		@Override
		final public void run() {
			task();
			handler.sendEmptyMessage(hashCode());

			// TODO Auto-generated method stub
		}

		@Override
		final public long scheduledExecutionTime() {
			// TODO Auto-generated method stub
			return super.scheduledExecutionTime();
		}

		@Override
		final public int hashCode() {
			// TODO Auto-generated method stub
			return super.hashCode();
		}

	}

}
