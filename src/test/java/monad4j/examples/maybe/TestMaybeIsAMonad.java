package monad4j.examples.maybe;

import static junit.framework.Assert.assertEquals;
import static monad4j.Monads.inject;
import static monad4j.examples.maybe.Maybe.just;
import monad4j.Function;
import monad4j.Monad;
import monad4j.Monads;

import org.junit.Test;

public class TestMaybeIsAMonad {
	
	/**
	 * (return a :: Maybe a) ≡ Just a
	 */
	@Test
	public void testReturn() {
		Maybe<Integer> fromReturn = Monads.inject(5, Maybe.class);
		assertEquals(Maybe.just(5),fromReturn);
	}
	
	/**
	 * "Left identity": return a >>= f ≡ f a
	 */
	@Test
	public void testLeftIdentityLaw() {
		final Integer a = 5;
		// definition to just make it clear which variables in the law the
		// functions take
		Function<Integer, Maybe<Integer>> f = increment(Maybe.class);
		assertEquals(inject(a,Maybe.class).chain(f), f.apply(a));
	}

	/**
	 * "Right identity": m >>= return ≡ m
	 */
	@Test
	public void testRightIdentityLaw() {
		Maybe<Integer> m = just(5);
		Maybe<Integer> chained = m
				.chain(new Function<Integer, Maybe<Integer>>() {
					@Override
					public Maybe<Integer> apply(Integer x) {
						return inject(x,Maybe.class);
					}
				});
		assertEquals(m, chained);
	}

	/**
	 * "Associativity": (m >>= f) >>= g ≡ m >>= (\x -> f x >>= g)
	 */
	@Test
	public void testAssociativityLaw() {
		Maybe<Integer> m = just(5);
		// definitions to just make it clear which variables in the law the
		// functions take
		final Function<Integer, Maybe<Integer>> f = increment(Maybe.class);
		final Function<Integer, Maybe<Integer>> g = decrement(Maybe.class);
		assertEquals(m.chain(f).chain(g),
				m.chain(new Function<Integer, Maybe<Integer>>() {
					@Override
					public Maybe<Integer> apply(Integer x) {
						return f.apply(x).chain(g);
					}
				}));
	}

	<T,M extends Monad<T,Integer>> Function<Integer, M> increment(final Class<T> cls) {
		return new Function<Integer, M>() {
			@Override
			public M apply(Integer a) {
				return inject(a + 1,cls);
			}
		};
	}

	<T,M extends Monad<T,Integer>> Function<Integer, M> decrement(final Class<T> cls) {
		return new Function<Integer, M>() {
			@Override
			public M apply(Integer a) {
				return inject(a - 1,cls);
			}
		};
	}
	
}
