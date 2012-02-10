package monad4j;

public interface Monad<M, T> {

	public <RA, R extends Monad<M, RA>> R chain(Function<T, R> f);
	
}
