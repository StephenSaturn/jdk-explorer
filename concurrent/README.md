ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue)
ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler)
ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory)
ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler)

# corePoolSize
核心线程数, 默认情况下核心线程会一直存活, 即使处于闲置状态也不会受keepAliveTime限制.除非将allowCoreThreadTimeOut设置为true.

# maximumPoolSize
线程池所能容纳的最大线程数.超过这个数的线程将被阻塞.当任务队列为没有设置大小的LinkedBlockingDeque时, 这个值无效.

# keepAliveTime
非核心线程的闲置超时时间, 超过这个时间就会被回收.

# unit(TimeUnit)
指定keepAliveTime的单位，如TimeUnit.SECONDS.当将allowCoreThreadTimeOut设置为true时对corePoolSize生效.

# workQueue(BlockingQueue<Runnable>)
线程池中的任务队列.常用的有三种队列, SynchronousQueue,LinkedBlockingDeque,ArrayBlockingQueue

# handler(RejectedExecutionHandler)
RejectedExecutionHandler也是一个接口, 只有一个方法
public interface RejectedExecutionHandler {
    void rejectedExecution(Runnable r, ThreadPoolExecutor executor);
}
当线程池中的资源已经全部使用, 添加新线程被拒绝时, 会调用RejectedExecutionHandler的rejectedExecution方法

# threadFactory(ThreadFactory)
线程工厂, 提供创建新线程的功能, ThreadFactory是一个接口, 只有一个方法
public interface ThreadFactory {
    Thread newThread(Runnable r);
}
通过线程工厂可以对线程的一些属性进行定制, 默认的线程工厂为DefaultThreadFactory