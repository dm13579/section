package com.dm.controller;

import com.alibaba.fastjson.JSON;
import com.dm.entity.CapitalConfigAllEntity;
import com.dm.entity.HistoricalTradesEntity;
import com.dm.entity.HrPriceUpdateEntity;
import com.dm.entity.SystemStatusEntity;
import com.dm.service.CapitalConfigAllService;
import com.dm.service.HistoricalTradesService;
import com.dm.service.HrPriceUpdateService;
import com.dm.service.SystemStatusApiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
  *                  ,;,,;
  *                ,;;'(    
  *      __      ,;;' ' \   
  *   /'  '\'~~'~' \ /'\.)  
  * ,;(      )    /  |.     
  *,;' \    /-.,,(   ) \    
  *     ) /       ) / )|    
  *     ||        ||  \)     
  *    (_\       (_\
  *@className BinanceController
  *@description TODO
  *@author dm
  *@date 2021/6/17 13:38
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@RestController
@RequestMapping("/ticker")
public class BinanceController {

    @Resource
    private HrPriceUpdateService hrPriceUpdateService;

    @Resource
    private SystemStatusApiService systemStatusApiService;

    @Resource
    private HistoricalTradesService historicalTradesService;

    @Resource
    private CapitalConfigAllService capitalConfigAllService;
    /**
     * 查询系统状态
     */
    @RequestMapping(value = "/systemStatus", method = RequestMethod.GET)
    public String get24hrTickerPriceChange(){
        return JSON.toJSONString(systemStatusApiService.getData(new SystemStatusEntity()));
    }

    /**
     * 24hr价格变动情况
     */
    @RequestMapping(value = "/get24hrTickerPriceChange", method = RequestMethod.GET)
    public String get24hrTickerPriceChange(@RequestParam("symbol") String symbol){
        HrPriceUpdateEntity hrPriceUpdateEntity = new HrPriceUpdateEntity();
        hrPriceUpdateEntity.setSymbol(symbol);
        return JSON.toJSONString(hrPriceUpdateService.getData(hrPriceUpdateEntity));
    }

    /**
     * 查询历史成交
     */
    @RequestMapping(value = "/historicalTrades", method = RequestMethod.GET)
    public String historicalTrades(@RequestParam("symbol") String symbol,
                                 @RequestParam(value = "limit", required = false) Integer limit){
        HistoricalTradesEntity historicalTradesEntity = new HistoricalTradesEntity();
        historicalTradesEntity.setSymbol(symbol);
        historicalTradesEntity.setLimit(limit);
        return JSON.toJSONString(historicalTradesService.getData(historicalTradesEntity));
    }

    /**
     * 获取所有币信息
     */
    @RequestMapping(value = "/capitalConfigAll", method = RequestMethod.GET)
    public String capitalConfigAll(){
        CapitalConfigAllEntity capitalConfigAllEntity = new CapitalConfigAllEntity();
        return JSON.toJSONString(capitalConfigAllService.getData(capitalConfigAllEntity));
    }
}
