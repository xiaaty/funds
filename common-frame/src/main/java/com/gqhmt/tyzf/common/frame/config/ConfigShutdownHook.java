package com.gqhmt.tyzf.common.frame.config;


import com.gqhmt.tyzf.common.frame.common.AbstractMultiThread;
import com.gqhmt.tyzf.common.frame.common.IConfigurable;

import java.util.Vector;

/**
 * JVM关闭时执行线程，用于关闭当前资源
 */
public class ConfigShutdownHook extends Thread {
	Vector<IConfigurable> components = new Vector<IConfigurable>();

	Vector<AbstractMultiThread> daemons = new Vector<AbstractMultiThread>();

	/**
	 * 注册组件
	 * @param component
	 */
	public void registerComponent(IConfigurable component) {
		components.add(component);
	}

	/**
	 * 注册守护线程
	 * @param daemon
	 */
	public void registerDaemon(AbstractMultiThread daemon) {
		daemons.add(daemon);
	}

	public void run() {
		try {
			ConfigManager.getInstance().execute(new ConfigCommand(ConfigManager.COMMAND_SHUTDOWN));
		} catch (Exception e) {
			for (int i = daemons.size() - 1; i >= 0; i--) {
				AbstractMultiThread daemon = (AbstractMultiThread) daemons
						.get(i);
				if (daemon != null&& AbstractMultiThread.RUN == daemon.getStatus()) {
					daemon.askStop();
				}
			}
			for (int i = components.size() - 1; i >= 0; i--) {
				IConfigurable component = (IConfigurable) components.get(i);
				if (component != null) {
					component.release();
				}
			}
		}
	}

}
