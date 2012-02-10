package monad4j.examples.maybe;

public interface MaybeCase<T> {
	
	public void doJust(T t);
	
	public void doNothing();

}
