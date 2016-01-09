package taip.social.mop;

import org.apache.commons.collections.map.AbstractReferenceMap;
import org.apache.commons.collections.map.ReferenceIdentityMap;
import taip.social.domain.entity.Person;

import java.util.*;

public aspect HasNextMonitorAspect {
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

	static Map HasNext_i_Map = null;

	pointcut HasNext_hasnext1(Iterator i) : (call(* Iterator.hasNext()) && target(i)) && !within(HasNextMonitor_1) && !within(HasNextMonitorAspect) && !adviceexecution();
	after (Iterator i) : HasNext_hasnext1(i) {
		Object obj = null;

		HasNextMonitor_1 monitor = null;
		boolean toCreate = false;

		Map m = HasNext_i_Map;
		if(m == null) m = HasNext_i_Map = makeMap(i);

		synchronized(HasNext_i_Map) {
			obj = m.get(i);

			monitor = (HasNextMonitor_1) obj;
			toCreate = (monitor == null);
			if (toCreate){
				monitor = new HasNextMonitor_1(new Person());
				m.put(i, monitor);
			}

		}

		{
			monitor.hasnext(i);
			if(monitor.MOP_violation()) {
				monitor.reset();
			}

		}
	}

	pointcut testPointcut() : execution(* HasNextMonitor_1.hasnext(..));
	after() returning(): testPointcut() {
		System.out.println("testPrint");
	}
	
	pointcut HasNext_next1(Iterator i) : (call(* Iterator.next()) && target(i)) && !within(HasNextMonitor_1) && !within(HasNextMonitorAspect) && !adviceexecution();
	before (Iterator i) : HasNext_next1(i) {
//		System.out.println("doing something before HasNext_next1(i)");
		Object obj = null;

		HasNextMonitor_1 monitor = null;
		boolean toCreate = false;

		Map m = HasNext_i_Map;
		if(m == null) m = HasNext_i_Map = makeMap(i);

		synchronized(HasNext_i_Map) {
			obj = m.get(i);

			monitor = (HasNextMonitor_1) obj;
			toCreate = (monitor == null);
			if (toCreate){
				monitor = new HasNextMonitor_1(new Person());
				m.put(i, monitor);
			}
		}
		{
			monitor.next(i);
			if(monitor.MOP_violation()) {
				monitor.reset();
			}

		}
	}

}
