package com.microsaturn.explorers.algorithm;

/**
 * Created by A14532 on 2019/1/22.
 */
public class SnowflakeIdWorker {

    /**
     * 开始时间戳（2015-01-01）
     */
    private final long twepoch = 1420041600000L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 5L;

    /**
     * 数据标识id所占的位数
     */
    private final long dataCenterIdBits = 5L;

    /**
     * 支持的最大机器id，结果是31
     * 这个位移算法可以很快的计算出几位二进制数所能表示的最大十进制数
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据标识id，结果是31
     */
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;


}
