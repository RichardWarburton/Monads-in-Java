Monads in Java
==============

Simple encoding of monads in the java language.

In order to make class M a monad, implement Monad<M, T>.
Then annotate M with @InjectWith, and an appropriate Return<T, M> implementation.

The 'return' and '>>' methods are called 'inject' and 'chainIgnore' and in the 
Monads utility class.

See main/monad4j.examples.maybe package for a Maybe type as a monad
See test/monad4j.examples.maybe.TestMaybeIsAMonad for tests for the 3 monadic laws

