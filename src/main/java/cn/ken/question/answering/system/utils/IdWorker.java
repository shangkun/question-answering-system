package cn.ken.question.answering.system.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author: shangkun <br/>
 * date: 2018/7/13 <br/>
 * what: id生成，Twitter开源的分布式ID生成算法，结果是一个long型的ID。<br/>
 * 其核心思想是：使用41bit作为毫秒数，10bit作为机器的ID（5个bit是数据中心，<br/>
 * 5个bit的机器ID），12bit作为毫秒内的流水号（意味着每个节点在每毫秒可以产生 4096 <br/>
 * 个 ID），最后还有一个符号位，永远是0 <br/>
 * from:https://github.com/twitter/snowflake<br/>
 */
public class IdWorker {
    // 基准时间
    private final static Logger logger = LoggerFactory.getLogger(IdWorker.class);
    private static IdWorker flowIdWorker;
    private long workerId;
    // 时间起始标记点，作为基准，一般取系统的最近时间
    private final long epoch = 1403854494756L;
    // 机器标识位数
    private final long workerIdBits = 10L;
    // 机器ID最大值: 1023
    private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;
    //毫秒内自增位
    private final long sequenceBits = 12L;
    // 12
    private final long workerIdShift = this.sequenceBits;
    // 22
    private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;
    // 4095,111111111111,12位
    private final long sequenceMask = -1L ^ -1L << this.sequenceBits;
    // 0，并发控制
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    private IdWorker(){}

    public IdWorker(long workerId) {
        if (workerId > this.maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
        }
        this.workerId = workerId;
        flowIdWorker = new IdWorker();
    }

    public static IdWorker getInstance() {
        return flowIdWorker;
    }

    /**
     * 获得系统当前毫秒数
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }

    public synchronized String nextId() {
        try {
            long timestamp = this.timeGen();
            if (this.lastTimestamp == timestamp) { // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环); 对新的timestamp，sequence从0开始
                this.sequence = this.sequence + 1 & this.sequenceMask;
                if (this.sequence == 0) {
                    timestamp = this.tilNextMillis(this.lastTimestamp);// 重新生成timestamp
                }
            } else {
                this.sequence = 0;
            }

            if (timestamp < this.lastTimestamp) {
                logger.error(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
                throw new Exception(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
            }
            this.lastTimestamp = timestamp;
            return String.valueOf(timestamp - this.epoch << this.timestampLeftShift | this.workerId << this.workerIdShift | this.sequence);
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
        }
        return "";
    }

    public synchronized String nextIdRandom() {
        try {
            long timestamp = this.timeGen();
            if (this.lastTimestamp == timestamp) { // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环); 对新的timestamp，sequence从0开始
                this.sequence = this.sequence + 1 & this.sequenceMask;
                if (this.sequence == 0) {
                    timestamp = this.tilNextMillis(this.lastTimestamp);// 重新生成timestamp
                }
            } else {
                this.sequence = 0;
            }

            if (timestamp < this.lastTimestamp) {
                logger.error(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
                throw new Exception(String.format("clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
            }
            this.lastTimestamp = timestamp;
            return String.valueOf(timestamp - this.epoch << this.timestampLeftShift | this.workerId << this.workerIdShift | this.sequence)+random();
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
        }
        return "";
    }

    /**
     * 获取随机数
     * @return
     */
    public static String random(){
        String random = Math.random()+"";
        random = random.substring(3,random.length());
        return random;
    }

    /**
     * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }
}
