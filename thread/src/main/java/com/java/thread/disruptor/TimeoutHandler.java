package com.java.thread.disruptor;

public interface TimeoutHandler
{
    void onTimeout(long sequence) throws Exception;
}
