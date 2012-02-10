package monad4j;

/**
 * Corresponds to the 'unit function' of the Monad.  In haskell this is called 'return', thus the naming.
 * 
 * We have to represent this using an interface, since Java doesn't have function types
 * 
 * @author richard
 *
 * @see Monads#inject(Object, Class)
 *
 */
public interface Return<T, M extends Monad<?, T>> {

	public M inject(T t);
	
}
