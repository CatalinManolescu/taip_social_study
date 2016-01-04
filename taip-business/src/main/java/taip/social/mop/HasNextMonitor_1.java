package taip.social.mop;


import java.io.*;
import java.util.*;
import java.lang.ref.WeakReference;

import com.sun.xml.internal.fastinfoset.util.StringArray;
public class HasNextMonitor_1 {

	
	public Object clone() {
		try {
			HasNextMonitor_1 ret = (HasNextMonitor_1) super.clone();
			return ret;
		}
		catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}
	int k = 0;
	Person p;
	boolean MOP_violation = false;

	public HasNextMonitor_1 (Person p) {
		boolean hasnext = false;
		boolean next = false;
		this.p = p;
	}
	synchronized public final void hasnext(Iterator i) {
		boolean hasnext = false;
		boolean next = false;
//		hasnext = true;
		hasnext = i.hasNext();
		boolean MOPPTLTL_truth = false;

		MOPPTLTL_truth = (! next |   p.friends.get(0)!= null ) ;
		MOP_violation = MOPPTLTL_truth == false;
	}
	synchronized public final void next(Iterator i) {
		boolean hasnext = false;
		boolean next = false;
		next = true;

		boolean MOPPTLTL_truth = false;

		MOPPTLTL_truth = (! next |    p.friends.get(0)!= null ) ;
		MOP_violation = MOPPTLTL_truth == false;
	}
	synchronized public final boolean MOP_violation() {
		return MOP_violation;
	}
	synchronized public final void reset() {
		boolean hasnext = false;
		boolean next = false;
		MOP_violation = false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person p = new Person();//original Person
		p.friends = new ArrayList<>();
		for(int i=0; i< 3;i++){
			p.friends.add(new Person());
		}
		HasNextMonitor_1 testHasNext = new HasNextMonitor_1(p);
////		String testString = "newString";
//		ArrayList al = new ArrayList();
//		al.add("A");
//		al.add("B");
//		al.add("C");
		Iterator i = p.friends.iterator();
		testHasNext.hasnext(i);
		
	}
}
