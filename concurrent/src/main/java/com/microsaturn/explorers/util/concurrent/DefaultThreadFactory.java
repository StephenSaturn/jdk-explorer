package com.microsaturn.explorers.util.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JDK 提供的默认的thread factory
 * @author Saturn
 */
public class DefaultThreadFactory implements ThreadFactory {

    private static final AtomicInteger poolNum = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNum = new AtomicInteger(1);
    private final String namePrefix;

    public DefaultThreadFactory() {
        SecurityManager sm = System.getSecurityManager();
        group = (sm != null) ? sm.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = "pool-" + poolNum.getAndIncrement() + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNum.getAndIncrement(), 0);
        if(t.isDaemon()) {
            t.setDaemon(false);
        }
        if(t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
