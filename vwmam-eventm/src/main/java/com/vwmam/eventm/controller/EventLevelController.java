package com.vwmam.eventm.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vwmam.eventm.entity.EventLevel;
import com.vwmam.eventm.result.JsonResult;
import com.vwmam.eventm.result.PageResult;
import com.vwmam.eventm.result.ResultModel;
import com.vwmam.eventm.service.EventLevelService;
import com.vwmam.eventm.util.BeanUtil;
import com.vwmam.eventm.vo.EventLevelMergeVO;
import com.vwmam.eventm.vo.EventLevelVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/eventLevel",produces = { "application/json;charset=UTF-8" })
public class EventLevelController {
	
	private final static Logger log = LoggerFactory.getLogger(EventLevelController.class);
	@Autowired
	private EventLevelService eventLevelService;
	
    @ApiOperation(value = "添加事件级别", notes = "添加事件级别")
    @PostMapping(value = "/v1/add")
	public ResultModel<EventLevel> saveEventLevel(@Valid @ApiParam("添加事件级别请求参数") @RequestBody EventLevelVO eventVO){
    	log.info("saveEventLevel request={}",eventVO);
    	EventLevel event=BeanUtil.copyBean(eventVO, EventLevel.class);	
    	ResultModel<EventLevel> result= eventLevelService.save(event);
    	log.info("saveEventLevel response={}",result);
    	return result;
	}
    
    @ApiOperation(value = "修改事件级别", notes = "修改事件级别")
    @PutMapping(value = "/v1/merge")
	public ResultModel<EventLevel> mergeEventLevel(@Valid @ApiParam("修改事件级别请求参数") @RequestBody EventLevelMergeVO eventVO){
    	log.info("mergeEventLevel request={}",eventVO);   
    	ResultModel<EventLevel> result= eventLevelService.merge(eventVO);
    	log.info("mergeEventLevel response={}",result);
    	return result;
	}
    
    @ApiOperation(value = "删除事件级别", notes = "删除事件级别")
    @DeleteMapping(value = "/v1/delete/{id}")
	public ResultModel<String> deleteEventLevel(@Valid @ApiParam("事件级别id") @PathVariable("id")String id){
    	log.info("deleteEventLevel request={}",id);   
    	ResultModel<String> result= eventLevelService.delete(id);
    	log.info("deleteEventLevel response={}",result);
    	return result;
	}
    
    @GetMapping("/v1/find/{id}")
    @ApiOperation(value = "根据id获取事件级别信息", notes = "根据id获取事件级别信息")
    public ResultModel<EventLevel> getLevelById(@ApiParam("事件级别id") @PathVariable("id")String id){
    	log.info("getLevelById request id={};"+id);
    	EventLevel data=eventLevelService.findById(id);
    	log.info("getLevelById response={}",data);
        return JsonResult.success(data);
    }
    
    @GetMapping("/v1/get/{page}/{size}")
    @ApiOperation(value = "根据条件分页查询事件级别信息", notes = "根据条件分页查询事件级别信息")
    public ResultModel< PageResult<EventLevel>> getAllPage(@PathVariable("page")Integer page,@PathVariable("size")Integer size,EventLevelVO eventVO){
    	log.info("getAllPage request page={},size={},params={};",page,size,eventVO);
    	EventLevel event=BeanUtil.copyBean(eventVO, EventLevel.class);	
    	PageResult<EventLevel> result=eventLevelService.findByExample(page, size, event);
    	log.info("getAllPage response={}",result);
        return JsonResult.success(result);
    }
}
