/**
 * Copyright (c) 2025 the Eclipse FA³ST Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eclipse.digitaltwin.fa3st.common.util;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


/**
 * Helper class for handling exceptions in lambda expressions.
 */
public class LambdaExceptionHelper {

    /**
     * Wrapper for {@link java.util.function.Consumer} with expected exception.
     *
     * @param <T> type of input argument
     * @param <E> type of expected exception
     */
    @FunctionalInterface
    public interface ConsumerWithExceptions<T, E extends Exception> {

        /**
         * Wrapper for {@link java.util.function.Consumer#accept(java.lang.Object)}.
         *
         * @param t the input argument
         * @throws E if operation fails
         */
        public void accept(T t) throws E;
    }

    /**
     * Wrapper for {@link java.util.function.BiConsumer} with expected exception.
     *
     * @param <T> type of first input argument
     * @param <U> type of second input argument
     * @param <E> type of expected exception
     */
    @FunctionalInterface
    public interface BiConsumerWithExceptions<T, U, E extends Exception> {

        /**
         * Wrapper for {@link java.util.function.BiConsumer#accept(java.lang.Object, java.lang.Object)}.
         *
         * @param t the first input argument
         * @param u the second input argument
         * @throws E if operation fails
         */
        public void accept(T t, U u) throws E;
    }

    /**
     * Wrapper for {@link java.util.function.Function} with expected exception.
     *
     * @param <T> the type of the input to the function
     * @param <R> the type of the result of the function
     * @param <E> the type of expected exception
     */
    @FunctionalInterface
    public interface FunctionWithException<T, R, E extends Exception> {

        /**
         * Wrapper for {@link java.util.function.Function#apply(java.lang.Object)}.
         *
         * @param t the function argument
         * @return the function result
         * @throws E if operation fails
         */
        public R apply(T t) throws E;
    }

    /**
     * Wrapper for {@link java.util.function.Function} with expected exception.
     *
     * @param <T1> the type of the first input to the function
     * @param <T2> the type of the second input to the function
     * @param <R> the type of the result of the function
     * @param <E> the type of expected exception
     */
    @FunctionalInterface
    public interface BiFunctionWithExceptions<T1, T2, R, E extends Exception> {
        /**
         * Wrapper for {@link java.util.function.BiFunction#apply(java.lang.Object, java.lang.Object)}.
         *
         * @param t1 the first function argument
         * @param t2 the second function argument
         * @return the function result
         * @throws E if operation fails
         */
        public R apply(T1 t1, T2 t2) throws E;
    }

    /**
     * Wrapper for {@link java.util.function.Supplier} with expected exception.
     *
     * @param <T> the type of results supplied by this supplier
     * @param <E> the type of expected exception
     */
    @FunctionalInterface
    public interface SupplierWithExceptions<T, E extends Exception> {

        /**
         * Wrapper for {@link java.util.function.Supplier#get()}.
         *
         * @return the result
         * @throws E if operation fails
         */
        public T get() throws E;
    }

    /**
     * Wrapper for {@link java.lang.Runnable} with expected exception.
     *
     * @param <E> the type of expected exception
     */
    @FunctionalInterface
    public interface RunnableWithExceptions<E extends Exception> {

        /**
         * Wrapper for {@link java.lang.Runnable#run()}.
         *
         * @throws E if operation fails
         */
        public void run() throws E;
    }

    /**
     * Wraps a {@link Consumer} throwing an Exception to be conveniently used in functional expressions.
     *
     * @param <T> type of the consumer
     * @param <E> type of the potentially thrown exception
     * @param consumer the actual consumer
     * @return a wrapping consumer
     * @throws E if execution of underlying consumer throws given exception
     */
    public static <T, E extends Exception> Consumer<T> rethrowConsumer(ConsumerWithExceptions<T, E> consumer) throws E {
        return t -> {
            try {
                consumer.accept(t);
            }
            catch (Exception e) {
                throwAsUnchecked(e);
            }
        };
    }


    /**
     * Wraps a {@link BiConsumer} throwing an Exception to be conveniently used in functional expressions.
     *
     * @param <T> first type of the biconsumer
     * @param <U> second type of the biconsumer
     * @param <E> type of the potentially thrown exception
     * @param biConsumer the actual biconsumer
     * @return a wrapping biconsumer
     * @throws E if execution of underlying biconsumer throws given exception
     */
    public static <T, U, E extends Exception> BiConsumer<T, U> rethrowBiConsumer(BiConsumerWithExceptions<T, U, E> biConsumer) throws E {
        return (t, u) -> {
            try {
                biConsumer.accept(t, u);
            }
            catch (Exception e) {
                throwAsUnchecked(e);
            }
        };
    }


    /**
     * Wraps a {@link Function} throwing an Exception to be conveniently used in functional expressions.
     *
     * @param <T> input type of the function
     * @param <R> result type of the function
     * @param <E> type of the potentially thrown exception
     * @param function the actual function
     * @return a wrapping function
     * @throws E if execution of underlying function throws given exception
     */
    public static <T, R, E extends Exception> Function<T, R> rethrowFunction(FunctionWithException<T, R, E> function) throws E {
        return t -> {
            try {
                return function.apply(t);
            }
            catch (Exception e) {
                throwAsUnchecked(e);
                return null;
            }
        };
    }


    /**
     * Wraps a {@link Function} throwing an Exception to be conveniently used in functional expressions.
     *
     * @param <T> input type of the function
     * @param <R> result type of the function
     * @param <E1> type of the first potentially thrown exception
     * @param <E2> type of the second potentially thrown exception
     * @param function the actual function
     * @param e1 type of the first potentially thrown exception
     * @param e2 type of the second potentially thrown exception
     * @return a wrapping function
     * @throws E1 if execution of underlying function throws given exception
     * @throws E2 if execution of underlying function throws given exception
     */
    public static <T, R, E1 extends Exception, E2 extends Exception> Function<T, R> rethrowFunction(FunctionWithException<T, R, ? extends Exception> function, Class<E1> e1,
                                                                                                    Class<E2> e2)
            throws E1, E2 {
        return t -> {
            try {
                return function.apply(t);
            }
            catch (Exception e) {
                throwAsUnchecked(e);
                return null;
            }
        };
    }


    /**
     * Wraps a {@link BiFunction} throwing an Exception to be conveniently used in functional expressions.
     *
     * @param <T1> input type of the first argument of the function
     * @param <T2> input type of the second argument of the function
     * @param <R> result type of the function
     * @param <E> type of the potentially thrown exception
     * @param function the actual function
     * @return a wrapping function
     * @throws E if execution of underlying function throws given exception
     */
    public static <T1, T2, R, E extends Exception> BiFunction<T1, T2, R> rethrowBiFunction(BiFunctionWithExceptions<T1, T2, R, E> function) throws E {
        return (t1, t2) -> {
            try {
                return function.apply(t1, t2);
            }
            catch (Exception e) {
                throwAsUnchecked(e);
                return null;
            }
        };
    }


    /**
     * Wraps a {@link Supplier} throwing an Exception to be conveniently used in functional expressions.
     *
     * @param <T> type of the supplier
     * @param <E> type of the potentially thrown exception
     * @param supplier the actual supplier
     * @return a wrapping supplier
     * @throws E if execution of underlying supplierthrows given exception
     */
    public static <T, E extends Exception> Supplier<T> rethrowSupplier(SupplierWithExceptions<T, E> supplier) throws E {
        return () -> {
            try {
                return supplier.get();
            }
            catch (Exception e) {
                throwAsUnchecked(e);
                return null;
            }
        };
    }


    /**
     * Wraps a Supplier interface and rethrows all exceptions as RuntimeException.
     *
     * @param <T> result type of the supplier
     * @param supplier the supplier to wrap
     * @return wrapped supplier
     * @throws RuntimeException if calling the supplier fails
     */
    public static <T> Supplier<T> wrap(SupplierWithExceptions<T, Exception> supplier) {
        return () -> {
            try {
                return supplier.get();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }


    /**
     * Wraps a Consumer interface and rethrows all exceptions as RuntimeException.
     *
     * @param <T> input type of the consumer
     * @param consumer the consumer to wrap
     * @return wrapped consumer
     * @throws RuntimeException if calling the consumer fails
     */
    public static <T> Consumer<T> wrap(ConsumerWithExceptions<T, Exception> consumer) {
        return arg -> {
            try {
                consumer.accept(arg);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }


    /**
     * Wraps a Function interface and rethrows all exceptions as RuntimeException.
     *
     * @param <T> input type of the function
     * @param <R> result type of the function
     * @param function the actual function
     * @return wrapped function
     * @throws RuntimeException if calling the consumer fails
     */
    public static <T, R> Function<T, R> wrapFunction(FunctionWithException<T, R, Exception> function) {
        return t -> {
            try {
                return function.apply(t);
            }
            catch (Exception e) {
                throwAsUnchecked(e);
                return null;
            }
        };
    }


    /**
     * Wraps a Runnable interface and rethrows all exceptions as RuntimeException.
     *
     * @param runnable the runnable to wrap
     * @return wrapped runnable
     * @throws RuntimeException if calling the runnable fails
     */
    public static Runnable wrap(Runnable runnable) {
        return () -> {
            try {
                runnable.run();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }


    @SuppressWarnings("unchecked")
    private static <E extends Throwable> void throwAsUnchecked(Exception e) throws E {
        throw (E) e;
    }
}
