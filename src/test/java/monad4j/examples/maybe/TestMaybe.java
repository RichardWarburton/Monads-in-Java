package monad4j.examples.maybe;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static monad4j.examples.maybe.Maybe.just;

import org.junit.Test;

public class TestMaybe {

	@Test
	public void simple() {
		// let t = 5 in
		final Integer t = 5;
		// let justT = Just 5
		Maybe<Integer> justT = just(t);
		assertFalse(justT.equals(Maybe.<Integer>nothing()));
	}
	
	@Test
	public void maybeCaseJust() {
		just(5).doCase(new MaybeCase<Integer>() {
			
			@Override
			public void doNothing() {
				fail();
			}
			
			@Override
			public void doJust(Integer t) {
				assertTrue(true);
			}
		});
	}
	
	@Test
	public void maybeCaseNothing() {
		Maybe.nothing().doCase(new MaybeCase<Object>() {
			
			@Override
			public void doNothing() {
				assertTrue(true);
			}
			
			@Override
			public void doJust(Object t) {
				fail();
			}
		});
	}

}
