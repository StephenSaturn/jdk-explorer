ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, 
                   BlockingQueue<Runnable> workQueue)<br>
                    
ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, 
                   BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler)<br> 
                   
ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, 
                   BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory)<br> 
                   
ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, 
                   BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler)<br> 

# corePoolSize
核心线程数, 默认情况下核心线程会一直存活, 即使处于闲置状态也不会受keepAliveTime限制.除非将allowCoreThreadTimeOut设置为true.

# maximumPoolSize
线程池所能容纳的最大线程数.超过这个数的线程将被阻塞.当任务队列为没有设置大小的LinkedBlockingDeque时, 这个值无效.

# keepAliveTime
非核心线程的闲置超时时间, 超过这个时间就会被回收.
``
# unit(TimeUnit)
指定keepAliveTime的单位，如TimeUnit.SECONDS.当将allowCoreThreadTimeOut设置为true时对corePoolSize生效.

# workQueue(BlockingQueue<Runnable>)
线程池中的任务队列.常用的有三种队列, SynchronousQueue,LinkedBlockingDeque,ArrayBlockingQueue
1. ArrayBlockingQueue:是一个有边界的阻塞队列，它的内部实现是一个数组。有边界的意思是它的容量是有限的，
我们必须在其初始化的时候指定它的容量大小，容量大小一旦指定就不可改变

2. DelayQueue:阻塞的是其内部元素，DelayQueue中的元素必须实现java.util.concurrent.Delayed接口，该接口只有一个方法，
long getDelay(TimeUnit unit)，返回值就是队列元素被释放前的保持时间，如果返回0或者负值，就意味着该元素已经到期需要被释放，
此时DelayQueue会通过其take()方法释放此对象，DelayQueue可用于定时关闭连接‘缓存对象、超时处理等各种场景

3. LinkedBlockingQueue:阻塞队列大小的配置是可选的，如果我们初始化时指定一个大小，它就是有边界的，如果不指定，他就是无边界的。
说是无边界，其实是采用了默认大小为Integer.MAX_VALUE的容量。它的内部实现是一个链表。

4. PriorityBlockingQueue:一个没有边界的队列，它的排序规则和java.util.PriorityQueue一样。需要注意的是，PriorityBlockingQueue中
允许插入null对象。所有插入PriorityBlockingQueue的对象必须实现java.lang.Comparable接口，队列的优先级的排序规则就是按照我们对这个接口
的实现来定义的。

5.SynchronousQueue:这个队列内部仅允许容纳一个元素。当一个线程插入一个元素后会被阻塞，除非这个元素被另一个线程消费

# handler(RejectedExecutionHandler)
RejectedExecutionHandler也是一个接口, 只有一个方法
public interface RejectedExecutionHandler {
    void rejectedExecution(Runnable r, ThreadPoolExecutor executor);
}
当线程池中的资源已经全部使用, 添加新线程被拒绝时, 会调用RejectedExecutionHandler的rejectedExecution方法

ThreadPoolExecutor.AbortPolicy:
ThreadPoolExecutor.CallerRunsPolicy
ThreadPoolExecutor.DiscardOldestPolicy
ThreadPoolExecutor.DiscardPolicy

# threadFactory(ThreadFactory)
线程工厂, 提供创建新线程的功能, ThreadFactory是一个接口, 只有一个方法
public interface ThreadFactory {
    Thread newThread(Runnable r);
}
通过线程工厂可以对线程的一些属性进行定制, 默认的线程工厂为DefaultThreadFactory