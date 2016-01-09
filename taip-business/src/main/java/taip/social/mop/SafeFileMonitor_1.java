package taip.social.mop;

import taip.social.domain.entity.Person;

import java.io.File;

public class SafeFileMonitor_1 implements Cloneable {
	public Object clone() {
		try {
			SafeFileMonitor_1 ret = (SafeFileMonitor_1) super.clone();
			return ret;
		}
		catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}
	static int counter = 0;
	int writes = 0;
	boolean[] alpha = new boolean[1];
	boolean[] beta = new boolean[2];
	boolean condition;
	MOPStack<Person[]> stack = new MOPStack<Person[]> ();
	boolean MOP_violation = false;

	public SafeFileMonitor_1 () {
		boolean sysbegin = false;
		boolean sysend = false;
		boolean open = false;
		boolean write = false;
		boolean close = false;
		beta[0] = false ;
		beta[1] = false ;
		alpha[0] = false ;
	}
	synchronized public final void open(File f) {
		boolean sysbegin = false;
		boolean sysend = false;
		boolean open = false;
		boolean write = false;
		boolean close = false;
		open = true;

		beta[0] = open || ! close && beta[0] ;
		beta[1] = sysbegin || ! open && beta[1] ;
		alpha[0] = alpha[0] || sysend && ! beta[1] && beta[0] ;
		condition = ! alpha[0];
		MOP_violation = ! condition;
		{
			this.writes = 0;
		}
	}
	synchronized public final void write(File f) {
		boolean sysbegin = false;
		boolean sysend = false;
		boolean open = false;
		boolean write = false;
		boolean close = false;
		write = true;

		beta[0] = open || ! close && beta[0] ;
		beta[1] = sysbegin || ! open && beta[1] ;
		alpha[0] = alpha[0] || sysend && ! beta[1] && beta[0] ;
		condition = ! alpha[0];
		MOP_violation = ! condition;
		{
			this.writes++;
		}
	}
	synchronized public final void close(File f) {
		boolean sysbegin = false;
		boolean sysend = false;
		boolean open = false;
		boolean write = false;
		boolean close = false;
		close = true;

		beta[0] = open || ! close && beta[0] ;
		beta[1] = sysbegin || ! open && beta[1] ;
		alpha[0] = alpha[0] || sysend && ! beta[1] && beta[0] ;
		condition = ! alpha[0];
		MOP_violation = ! condition;
	}
	synchronized public final void sysbegin() {
		boolean sysbegin = false;
		boolean sysend = false;
		boolean open = false;
		boolean write = false;
		boolean close = false;
		sysbegin = true;

		Person[] persons = new Person[2];
		stack.push(persons) ;
		boolean [] temp_beta = new boolean[2];
		for(int i = 0; i < 2; i++)
		{
			temp_beta[i] = beta[i];
		}
		beta = temp_beta;
		beta[0] = open || ! close && beta[0] ;
		beta[1] = sysbegin || ! open && beta[1] ;
		alpha[0] = alpha[0] || sysend && ! beta[1] && beta[0] ;
		condition = ! alpha[0];
		MOP_violation = ! condition;
	}
	synchronized public final void sysend() {
		boolean sysbegin = false;
		boolean sysend = false;
		boolean open = false;
		boolean write = false;
		boolean close = false;
		sysend = true;

		beta[0] = open || ! close && beta[0] ;
		beta[1] = sysbegin || ! open && beta[1] ;
		alpha[0] = alpha[0] || sysend && ! beta[1] && beta[0] ;
		condition = ! alpha[0];
		MOP_violation = ! condition;
		Person[] persons = null;
		persons = (Person[])stack.peek();
		stack.pop(1);
	}
	synchronized public final boolean MOP_violation() {
		return MOP_violation;
	}
	synchronized public final void reset() {
		boolean sysbegin = false;
		boolean sysend = false;
		boolean open = false;
		boolean write = false;
		boolean close = false;
		beta[0] = false ;
		beta[1] = false ;
		alpha[0] = false ;
		MOP_violation = false;
	}
	 public static void main(String[] args){
		 SafeFileMonitor_1 monitor = new SafeFileMonitor_1();
		 File testFile = new File("test");
		 monitor.open(testFile);
		 monitor.write(testFile);
		 monitor.close(testFile);
	 }
}
