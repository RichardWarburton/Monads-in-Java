package monad4j.examples.maybe;

import monad4j.Function;
import monad4j.Monad;

class Nothing<T> extends Maybe<T> {
	
	Nothing() {
		
	}

	@Override
	public void doCase(MaybeCase<T> visitor) {
		visitor.doNothing();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Nothing;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * Haskell equivalent:
	 * 
	 * Nothing  >>= _      = Nothing
	 */

	@Override
	public String toString() {
		return "Nothing";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <RA, R extends Monad<Maybe, RA>> R chain(Function<T, R> f) {
		return (R) Maybe.nothing();
	}
	
}