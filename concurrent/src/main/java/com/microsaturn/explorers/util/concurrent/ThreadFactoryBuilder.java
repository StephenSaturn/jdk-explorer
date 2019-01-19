package com.microsaturn.explorers.util.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread Factory Builder
 * @author Saturn
 */
public class ThreadFactoryBuilder {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private String nameFormat;

    public ThreadFactoryBuilder() {
        SecurityManager sc = System.getSecurityManager();
        group = (sc != null) ? sc.getThreadGroup() : Thread.currentThread().getThreadGroup();
        nameFormat = "pool-" + poolNumber.getAndIncrement() + "-thread-%d";
    }

    public ThreadFactoryBuilder setNameFormat(String nameFormat) {
        this.nameFormat = nameFormat;
        return this;
    }

    public ThreadFactory build() {
        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(group, r, String.format(nameFormat, threadNumber.getAndIncrement()), 0);
                if (t.isDaemon()) {
                    t.setDaemon(false);
                }
                if (t.getPriority() != Thread.NORM_PRIORITY) {
                    t.setPriority(Thread.NORM_PRIORITY);
                }
                return t;
            }
        };
    }
}
