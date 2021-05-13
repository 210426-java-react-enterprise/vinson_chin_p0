package com.revature.vinson_chin_p0.util;

/**
 * Simple interface Queue.
 *
 * @param <T>
 */
public interface Queue<T> extends Collection<T> {
    T poll();
    T peek();
}
