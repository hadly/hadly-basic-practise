package com.hadly.rpc;

/**
 * Created by mi-123 on 2017/8/6.
 */
public class EchoServiceImpl implements IEchoService {
    public String echo(String ping) {
        String result = ping + " --> I'm OK";
        return result;
    }
}
