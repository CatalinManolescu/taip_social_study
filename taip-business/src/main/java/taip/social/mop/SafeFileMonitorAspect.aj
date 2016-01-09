package taip.social.mop;

import org.apache.commons.collections.map.AbstractReferenceMap;
import org.apache.commons.collections.map.ReferenceIdentityMap;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public aspect SafeFileMonitorAspect {
	static Map makeMap(Object key){
		if (key instanceof String) {
			return new HashMap();
		} else {
			return new ReferenceIdentityMap(AbstractReferenceMap.WEAK, AbstractReferenceMap.HARD, true);
		}
	}
	static List makeList(){
		return new ArrayList();
	}

	static Map SafeFile_f_Map = null;
	static List SafeFile_List = makeList();

	pointcut SafeFile_open1(File f) : (call(* open(..)) && target(f)) && !within(SafeFileMonitor_1) && !within(SafeFileMonitorAspect) && !adviceexecution();
	after (File f) : SafeFile_open1(f) {
		Object obj = null;

		SafeFileMonitor_1 monitor = null;
		boolean toCreate = false;

		Map m = SafeFile_f_Map;
		if(m == null) m = SafeFile_f_Map = makeMap(f);

		synchronized(SafeFile_f_Map) {
			obj = m.get(f);

			monitor = (SafeFileMonitor_1) obj;
			toCreate = (monitor == null);
			if (toCreate){
				monitor = new SafeFileMonitor_1();
				m.put(f, monitor);
			}

		}
		if(toCreate) {
			synchronized(SafeFile_List) {
				SafeFile_List.add(monitor);
			}//end of adding
		}

		{
			monitor.open(f);
			if(monitor.MOP_violation()) {
				monitor.reset();
			}

		}
	}

	pointcut SafeFile_write1(File f) : (call(* write(..)) && target(f)) && !within(SafeFileMonitor_1) && !within(SafeFileMonitorAspect) && !adviceexecution();
	after (File f) : SafeFile_write1(f) {
		Object obj = null;

		SafeFileMonitor_1 monitor = null;
		boolean toCreate = false;

		Map m = SafeFile_f_Map;
		if(m == null) m = SafeFile_f_Map = makeMap(f);

		synchronized(SafeFile_f_Map) {
			obj = m.get(f);

			monitor = (SafeFileMonitor_1) obj;
			toCreate = (monitor == null);
			if (toCreate){
				monitor = new SafeFileMonitor_1();
				m.put(f, monitor);
			}

		}
		if(toCreate) {
			synchronized(SafeFile_List) {
				SafeFile_List.add(monitor);
			}//end of adding
		}

		{
			monitor.write(f);
			if(monitor.MOP_violation()) {
				monitor.reset();
			}

		}
	}

	pointcut SafeFile_close1(File f) : (call(* close(..)) && target(f)) && !within(SafeFileMonitor_1) && !within(SafeFileMonitorAspect) && !adviceexecution();
	after (File f) : SafeFile_close1(f) {
		Object obj = null;

		SafeFileMonitor_1 monitor = null;
		boolean toCreate = false;

		Map m = SafeFile_f_Map;
		if(m == null) m = SafeFile_f_Map = makeMap(f);

		synchronized(SafeFile_f_Map) {
			obj = m.get(f);

			monitor = (SafeFileMonitor_1) obj;
			toCreate = (monitor == null);
			if (toCreate){
				monitor = new SafeFileMonitor_1();
				m.put(f, monitor);
			}

		}
		if(toCreate) {
			synchronized(SafeFile_List) {
				SafeFile_List.add(monitor);
			}//end of adding
		}

		{
			monitor.close(f);
			if(monitor.MOP_violation()) {
				monitor.reset();
			}

		}
	}

	pointcut SafeFile_sysbegin1() : (execution(* *(..))) && !within(SafeFileMonitor_1) && !within(SafeFileMonitorAspect) && !adviceexecution();
	before () : SafeFile_sysbegin1() {
		synchronized(SafeFile_List) {
			for(SafeFileMonitor_1 monitor : (List<SafeFileMonitor_1>)SafeFile_List) {
				monitor.sysbegin();
				if(monitor.MOP_violation()) {
					monitor.reset();
				}

			}
		}

	}

	pointcut SafeFile_sysend1() : (execution(* *(..))) && !within(SafeFileMonitor_1) && !within(SafeFileMonitorAspect) && !adviceexecution();
	after () : SafeFile_sysend1() {
		synchronized(SafeFile_List) {
			for(SafeFileMonitor_1 monitor : (List<SafeFileMonitor_1>)SafeFile_List) {
				monitor.sysend();
				if(monitor.MOP_violation()) {
					monitor.reset();
				}

			}
		}

	}

}