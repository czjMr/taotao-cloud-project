package com.taotao.cloud.redis.delay.handler;


import com.taotao.cloud.common.utils.log.LogUtils;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * DefaultIsolationStrategy
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2022-02-18 10:25:25
 */
public class DefaultIsolationStrategy implements IsolationStrategy {

    @Override
    public String getRedisQueueName(String queue) {
        String prefix;
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String hostAddress = localHost.getHostAddress();
            String hostName = localHost.getHostName();
            prefix = hostName + "@" + hostAddress;
        } catch (UnknownHostException e) {
	        LogUtils.warn("can not detect host info,instead with localhost@127.0.0.1");
            prefix = "localhost@127.0.0.1";
        }
        return prefix + "-" + queue;
    }

}
