package com.github.jesse.l2cache.spring.config;

import cn.hutool.core.util.StrUtil;
import com.github.jesse.l2cache.CacheConfig;
import com.github.jesse.l2cache.spring.L2CacheProperties;
import com.jd.platform.hotkey.client.ClientStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author wuweifeng wrote on 2020-01-14
 * @version 1.0
 */
@Configuration
@ConditionalOnProperty(name = "l2cache.config.hotkey.jdHotKey.serviceName")
public class JdHotKeyConfiguration {

    @Autowired
    L2CacheProperties l2CacheProperties;

    @PostConstruct
    public void init() {
        CacheConfig cacheConfig = l2CacheProperties.getConfig();
        CacheConfig.HotKey.JdHotKey jdHotKey = cacheConfig.getHotKey().getJdHotKey();
        if(StrUtil.isBlank(jdHotKey.getEtcdUrl())){
            throw new IllegalStateException("jdHotKey not found etcd url yaml config file:" + jdHotKey);
        }

        ClientStarter.Builder builder = new ClientStarter.Builder();
        ClientStarter starter = builder.setAppName(jdHotKey.getServiceName()).setEtcdServer(jdHotKey.getEtcdUrl()).build();
        starter.startPipeline();
    }
}
