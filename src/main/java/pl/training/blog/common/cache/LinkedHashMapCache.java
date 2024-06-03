package pl.training.blog.common.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LinkedHashMapCache<K, V> implements Cache<K, V> {

    private final Map<K, V> cache;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public LinkedHashMapCache(int capacity) {
        cache = new LinkedHashMap<>(capacity, 1, true) {

            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }

        };
    }

    @Override
    public void put(K key, V value) {
        lock.writeLock().lock();
        cache.put(key, value);
        lock.writeLock().unlock();
    }

    @Override
    public Optional<V> get(K key) {
        lock.readLock().lock();
        var result = cache.get(key);
        lock.readLock().unlock();
        return Optional.ofNullable(result);
    }

}
