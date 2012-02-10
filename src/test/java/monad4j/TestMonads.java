package monad4j;

import static junit.framework.Assert.assertEquals;
import static monad4j.Monads.chainIgnore;
import static monad4j.examples.maybe.Maybe.just;
import static monad4j.examples.maybe.Maybe.nothing;
import monad4j.Function;
import monad4j.examples.maybe.Maybe;

import org.junit.Test;

public class TestMonads {

	Function<Void, Maybe<Integer>> f = new Function<Void, Maybe<Integer>>() {
		@Override
		public Maybe<Integer> apply(Void a) {
			return just(5);
		}
	};
	
	@Test
	public void chainIgnoreNothing() {
		assertEquals(nothing(),chainIgnore(nothing(),f));
	}
	
	@Test
	public void chainIgnoreJust() {
		assertEquals(just(5),chainIgnore(just(1),f));
	}

}
