package practice;

import java.util.concurrent.atomic.AtomicInteger;

public class MetricsKeeperImpl implements MetricsKeeper {

    private final AtomicInteger metricCounter = new AtomicInteger();

    @Override
    public int incrementAndGet() {
        return metricCounter.incrementAndGet();
    }
}
