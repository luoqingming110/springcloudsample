package com.neo.controller;

import com.neo.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URL;
import java.util.List;

@RestController
public class ConsumerController {

    @Autowired
    HelloRemote HelloRemote;


    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping("/dc")
    public String dc() {

       List< ServiceInstance> instance = discoveryClient.getInstances("spring-cloud-producer");

       URI url =  instance.get(0).getUri();

        System.err.println();

      //  System.out.println("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());

        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return HelloRemote.hello(name);
    }

}