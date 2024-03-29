/*
 * Copyright 2014 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.gogym.getty.resolver;

import java.util.List;

import org.gogym.getty.util.concurrent.EventExecutor;
import org.gogym.getty.util.concurrent.Future;
import org.gogym.getty.util.concurrent.Promise;
import org.gogym.getty.util.internal.UnstableApi;

import static org.gogym.getty.util.internal.ObjectUtil.*;

/**
 * A skeletal {@link NameResolver} implementation.
 */
@UnstableApi
public abstract class SimpleNameResolver<T> implements NameResolver<T> {

    private final EventExecutor executor;

    /**
     * @param executor the {@link EventExecutor} which is used to notify the listeners of the {@link Future} returned
     *                 by {@link #resolve(String)}
     */
    protected SimpleNameResolver(EventExecutor executor) {
        this.executor = checkNotNull(executor, "executor");
    }

    /**
     * Returns the {@link EventExecutor} which is used to notify the listeners of the {@link Future} returned
     * by {@link #resolve(String)}.
     */
    protected EventExecutor executor() {
        return executor;
    }

    @Override
    public final Future<T> resolve(String inetHost) {
        final Promise<T> promise = executor().newPromise();
        return resolve(inetHost, promise);
    }

    @Override
    public Future<T> resolve(String inetHost, Promise<T> promise) {
        checkNotNull(promise, "promise");

        try {
            doResolve(inetHost, promise);
            return promise;
        } catch (Exception e) {
            return promise.setFailure(e);
        }
    }

    @Override
    public final Future<List<T>> resolveAll(String inetHost) {
        final Promise<List<T>> promise = executor().newPromise();
        return resolveAll(inetHost, promise);
    }

    @Override
    public Future<List<T>> resolveAll(String inetHost, Promise<List<T>> promise) {
        checkNotNull(promise, "promise");

        try {
            doResolveAll(inetHost, promise);
            return promise;
        } catch (Exception e) {
            return promise.setFailure(e);
        }
    }

    /**
     * Invoked by {@link #resolve(String)} to perform the actual name resolution.
     */
    protected abstract void doResolve(String inetHost, Promise<T> promise) throws Exception;

    /**
     * Invoked by {@link #resolveAll(String)} to perform the actual name resolution.
     */
    protected abstract void doResolveAll(String inetHost, Promise<List<T>> promise) throws Exception;

    @Override
    public void close() { }
}
