package taip.social.mop;

import java.io.File;

public class MOPStack<Elt> {
	static int CAPACITY = 100;
	int curr_index = 0;
	Elt[] elements;
	int capacity;
	public MOPStack(){
		elements = (Elt[])new Object[CAPACITY];
		capacity = CAPACITY;
	}
	public MOPStack(int initial_capacity){
		elements = (Elt[])new Object[initial_capacity];
		capacity = initial_capacity;
	}
	public Elt peek(){
		return elements[curr_index - 1];
	}
	public void pop(int num){
		curr_index -= num;
	}
	public void push(Elt elt){
		if(curr_index < elements.length){
			elements[curr_index++] = elt;
		} else {
			int len = elements.length;
			Elt[] old = elements;
			elements = (Elt[])new Object[len << 1];
			for(int i = 0; i < len; i++){
				elements[i] = old[i];
			}
			elements[curr_index++] = elt;
			}
		}
		public void clear(){
			for (int i = 0; i < curr_index; i ++)
			elements[i] = null;
			curr_index = 0;
		}
		public MOPStack<Elt> fclone(){
			MOPStack<Elt> ret = new MOPStack<Elt>(capacity);
			ret.curr_index = curr_index;
			for (int i = 0; i < curr_index; i ++)
			ret.elements[i] = elements[i];
			return ret;
		}
}
class SafeFileMonitor_1 implements Cloneable {
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