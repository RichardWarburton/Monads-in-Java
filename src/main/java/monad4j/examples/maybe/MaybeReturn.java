package monad4j.examples.maybe;

import static monad4j.examples.maybe.Maybe.just;
import monad4j.Return;

/**
 * Haskell equivalent:
 * 
 * return = Just
 */
public class MaybeReturn<T> implements Return<T,Maybe<T>> {
	
	@Override
	public Maybe<T> inject(T t) {
		return just(t);
	}

}
