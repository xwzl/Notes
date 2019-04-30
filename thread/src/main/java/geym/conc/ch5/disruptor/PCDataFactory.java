package geym.conc.ch5.disruptor;

import com.java.thread.disruptor.EventFactory;

public class PCDataFactory implements EventFactory<PCData>
{
    public PCData newInstance()
    {
        return new PCData();
    }
}