package LLD.ObjectPool;

import java.util.Enumeration;
import java.util.Hashtable;

public abstract class ObjectPool<T> {
    private final long deadTime;
    private Hashtable<T, Long> lock, unlock;

    ObjectPool() {
        deadTime = 10000;
        lock = new Hashtable<>();
        unlock = new Hashtable<>();
    }

    abstract T create();

    abstract boolean validate(T t);

    abstract void dead(T t);

    synchronized T takeOut() {
        long currentTimestamp = System.currentTimeMillis();
        T t;
        if (unlock.size() > 0) {
            Enumeration<T> e = unlock.keys();
            while (e.hasMoreElements()) {
                t = e.nextElement();
                if (currentTimestamp - unlock.get(t) > deadTime || !validate(t)) {
                    unlock.remove(t);
                    dead(t);
                } else {
                    unlock.remove(t);
                    lock.put(t, currentTimestamp);
                    return t;
                }
            }
        }
        t = create();
        lock.put(t, currentTimestamp);
        return t;
    }

    synchronized void takeIn(T t) {
        lock.remove(t);
        unlock.put(t, System.currentTimeMillis());
    }
}
