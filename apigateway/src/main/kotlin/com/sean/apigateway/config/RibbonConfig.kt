package com.sean.apigateway.config

import com.netflix.client.config.IClientConfig
import com.netflix.loadbalancer.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean


class RibbonConfig {

//    @Autowired
//    lateinit var config: IClientConfig

    @Bean
    fun ribbonPing(config: IClientConfig?): IPing? {
        return PingUrl()
    }

    @Bean
    fun ribbonRule(config: IClientConfig?): IRule? {
        return AvailabilityFilteringRule()
    }

    @Bean
    fun ribbonServerList(): ServerList<Server?>? {
        return ConfigurationBasedServerList()
        // or new StaticServerList<>(new Server("server1", 80));
    }
}