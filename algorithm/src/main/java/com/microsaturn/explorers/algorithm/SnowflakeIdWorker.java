package com.microsaturn.explorers.algorithm;

import java.text.SimpleDateFormat;

/**
 * @author saturn
 * @version 1.0
 * @className SnowflakeIdWorker
 * @description
 *     snowflake生产的ID二进制结构表示如下（每部分用-分开）：
 *     0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 *     第一位未使用，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，因为id一般是正数，所以最高位是0
 *     41位时间戳（毫秒级），注意，41位时间戳不是存储当前时间的时间戳，而是存储时间戳的差值（当前时间戳 - 开始时间戳）得到的值
 *     这里的开始时间戳，一般是我们的id生成器开始使用的时间，由我们程序来指定。
 *
 *     41位的时间戳，可以使用69年，T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69
 *
 *     10位的数据机器位，可以部署在1024个节点，包括5位dataCenterId 和workerId
 *
 *     12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒（同一机器，同一时间戳）产生4096个ID序号
 *
 *     整个加起来刚好64位，为一个long型
 *
 *     SnowFlake的优点在于，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞，（由数据中心ID和机器ID作区分），并且效率较高
 *
 * @date 2019-01-24 16:15
 **/
public class SnowflakeIdWorker {

    public static void main(String[] args) throws Exception {
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 1000; i++) {
            long id = snowflakeIdWorker.next();
            System.out.println(Long.toBinaryString(id) + ":" + id);
        }

//        System.out.println((1L << 41) / (1000L * 60 * 60 * 24 * 365));
//        System.out.println(-1L ^ (-1l << 5L));
//        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
//        System.out.println(sdf.parse("2015-01-01").getTime());
    }

    /**
     * 开始时间戳
     */
    private final long twepoch = 1419696000000L;
    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 5L;
    /**
     * 数据标识id所占的位数
     */
    private final long dataCenterIdBits = 5L;

    /**
     * 支持的最大机器id，结果是31，（这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数）
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);// aka 31
    /**
     * 支持的最大数据标识id
     */
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);// aka 31

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;
    /**
     * 机器id向左移12位
     */
    private final long workerIdShift = sequenceBits; // aka 12
    /**
     * 数据标识id向左移17位（12 + 5）
     */
    private final long dataCenterIdShift = sequenceBits + workerIdBits; // aka 17
    /**
     * 时间戳向左移22位（5 + 5 + 12）
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits; // aka 22
    /**
     * 生成序列的掩码，这里为4096（0b111111111111=0xfff=4096）
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits); // aka 4096

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;
    /**
     * 数据中心ID（0～31）
     */
    private long dataCenterId;
    /**
     * 毫秒内序列（0～4096）
     */
    private long sequence = 0L;
    /**
     * 上次生成ID的时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     * @param workerId 工作ID （0～31）
     * @param dataCenterId 数据中心ID （0～31）
     */
    public SnowflakeIdWorker(long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("workd Id can't be greater than %d or less than 0.", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || maxWorkerId < 0) {
            throw new IllegalArgumentException(
                    String.format("dataCenter Id can't be greater than %d or less than 0.", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    public synchronized long next() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampLeftShift)
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return 0;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}
