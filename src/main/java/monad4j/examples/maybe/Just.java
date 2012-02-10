package monad4j.examples.maybe;

import monad4j.Function;
import monad4j.Monad;

public class Just<T> extends Maybe<T> {

	private final T t;

	Just(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}

	@Override
	public void doCase(MaybeCase<T> visitor) {
		visitor.doJust(t);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((t == null) ? 0 : t.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Just other = (Just) obj;
		if (t == null) {
			if (other.t != null)
				return false;
		} else if (!t.equals(other.t))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Just "+t;
	}

	/**
	 * Haskell Equivalent:
	 * 
	 * (Just x) >>= k = k x
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public <RA, R extends Monad<Maybe, RA>> R chain(Function<T, R> f) {
		return f.apply(t);
	}

}
