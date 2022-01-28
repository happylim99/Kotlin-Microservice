package com.sean.apigateway.config

import com.netflix.loadbalancer.AvailabilityFilteringRule
import com.netflix.loadbalancer.Server

class AvailabilityBasedServerSelectionRule: AvailabilityFilteringRule() {

    override fun choose(key: Any?): Server {
        var chosenServer: Server = super.choose(key)

        var count: Int = 1
        val reachableServers: List<Server> = this.loadBalancer.reachableServers
        val allServers: List<Server> = this.loadBalancer.allServers
        if(reachableServers.size > 0) {
            while(!reachableServers.contains(chosenServer) && count++ < allServers.size) {
                chosenServer = super.choose(key);
            }
        }
        println("chosenServer.isAlive() = " + chosenServer.isAlive());
        return chosenServer;
    }
}