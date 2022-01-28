package com.sean.apigateway.config

import com.netflix.client.config.IClientConfig
import com.netflix.loadbalancer.IPing
import com.netflix.loadbalancer.IRule
import com.netflix.loadbalancer.Server
import com.netflix.loadbalancer.ServerList
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Configuration
class MyLoadBalancerConfig {

    @Bean
    fun ribbonRule(config: IClientConfig?): IRule? {
        return AvailabilityBasedServerSelectionRule()
    }

    @Bean
    fun ribbonPing(): IPing? {
        return IPing { server: Server? ->
            try {
                // TODO: ping your server to decide if health check has passed or not
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            true
        }
    }

    @Bean
    fun ribbonServerList(config: IClientConfig?): ServerList<Server?>? {
        return object : ServerList<Server?> {
            override fun getInitialListOfServers(): MutableList<Server?> {
                val server1 = Server("server1")
                val server2 = Server("server2")
                return mutableListOf(server1, server2)
            }

//            override fun getUpdatedListOfServers(): MutableList<Server?> {
//                TODO("Not yet implemented")
//            }

            override fun getUpdatedListOfServers(): MutableList<Server?> {
                val server3 = Server("server3")
                val server4 = Server("server4")
                return mutableListOf(server3, server4)
            }
        }
    }
}