package pl.training.blog.common.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LinkedHashMapCache<Key, Value> {

    private final Map<Key, Value> store;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public LinkedHashMapCache(int maxCapacity) {
        store = new LinkedHashMap<>(maxCapacity, 1, true) {

            @Override
            protected boolean removeEldestEntry(Map.Entry<Key, Value> eldest) {
                return size() > maxCapacity;
            }

        };
    }

    public Optional<Value> get(Key key) {
        lock.readLock().lock();
        var result = store.get(key);
        lock.readLock().unlock();
        return Optional.ofNullable(result);
    }

    public void put(Key key, Value value) {
        lock.writeLock().lock();
        store.put(key, value);
        lock.writeLock().unlock();
    }

}
