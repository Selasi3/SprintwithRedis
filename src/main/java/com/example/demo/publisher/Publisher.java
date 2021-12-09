package com.example.demo.publisher;

import com.example.demo.dto.RawMarketData;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Component
@RestController
public class Publisher {

    @Autowired
    private RedisTemplate template;

    @Autowired
    private ChannelTopic topic;

    @Autowired
    private Gson gson;


    @ResponseBody
    @PostMapping("/subscribe")
    public String marketDataFromExchange(@RequestBody List<RawMarketData> marketData ){
        template.convertAndSend(topic.getTopic(), gson.toJson(marketData));
        return "MarketData Published!";
    }

    @ResponseBody
    @PostMapping("/subscribe2")
    public String marketDataFromExchange2(@RequestBody List<RawMarketData>  marketData){
        template.convertAndSend(topic.getTopic(), marketData);
        return "MarketData Published!";
    }

//    @RequestMapping("/fullData")
//    public List<MarketData> combinedMarketData(){
//
//    }



}
