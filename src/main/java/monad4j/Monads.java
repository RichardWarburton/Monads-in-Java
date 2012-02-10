package monad4j;

/**
 * Static utility methods
 * 
 * @author richard
 * 
 */
public class Monads {

	/**
	 * In Haskell this is part of the type class, but here its a static method
	 * 
	 */
	public static <T, W, M extends Monad<W, T>> M inject(T t, Class<W> cls) {
		InjectWith injectWith = cls.getAnnotation(InjectWith.class);
		if(injectWith == null) {
			throw new IllegalArgumentException("Don't know what class to inject "+cls.getName()+" with");
		}
		
		try {
			@SuppressWarnings("unchecked")
			Return<T,M> injector = injectWith.value().newInstance();
			
			return injector.inject(t);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalStateException("Unable to create injector",e);
		}
	}
	
	/**
	 * With JSR 335's default this could be in the monad interface (where it
	 * should be).
	 * 
	 * In Haskell:
	 * 
	 * m >> f = m >>= \_ -> f
	 */
	public static <A, RA, R extends Monad<M, RA>, M> R chainIgnore(
			Monad<M, A> m, final Function<Void, R> f) {
		return m.<RA, R> chain(new Function<A, R>() {
			@Override
			public R apply(A a) {
				return f.apply(null);
			}
		});
	}

	/**
	 * Promote a function to a monad. 
	 * 
	 * In Haskell:
	 * 
	 * liftM f m = m >>= \x -> return (f x)
	 */
	public static <M, R, A, RM extends Monad<M, R>, AM extends Monad<M, A>> Function<AM, RM> liftM(
			final Function<A, R> f) {
		return new Function<AM, RM>() {
			@Override
			public RM apply(final AM m) {
				return m.chain(new Function<A, RM>() {
					@Override
					public RM apply(A a) {
						// TODO: implement return
						return null;
					}
				});
			}
		};
	}

}
