package monad4j.examples.maybe;

import monad4j.InjectWith;
import monad4j.Monad;

@SuppressWarnings("rawtypes")
@InjectWith(MaybeReturn.class)
public abstract class Maybe<T> implements Monad<Maybe, T> {

	public static <T> Maybe<T> nothing() {
		return new Nothing<T>();
	}

	public static <T> Maybe<T> just(T t) {
		return new Just<T>(t);
	}

	public abstract void doCase(MaybeCase<T> visitor);

}
