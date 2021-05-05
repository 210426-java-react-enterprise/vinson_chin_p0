package com.revature.vinson_chin_p0.util;

public interface Queue<T> extends Collection<T> {
    T poll();
    T peek();
}
