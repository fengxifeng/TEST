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

import com.vwmam.eventm.entity.Event;
import com.vwmam.eventm.result.JsonResult;
import com.vwmam.eventm.result.PageResult;
import com.vwmam.eventm.result.ResultModel;
import com.vwmam.eventm.service.EventService;
import com.vwmam.eventm.util.BeanUtil;
import com.vwmam.eventm.vo.EventMergeVO;
import com.vwmam.eventm.vo.EventVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author chuxunfeng
 *
 */
@RestController
@RequestMapping(value = "/event",produces = { "application/json;charset=UTF-8" })
public class EventController {

	private final static Logger log = LoggerFactory.getLogger(EventController.class);
	
	@Autowired
	private EventService eventService;
	
    @ApiOperation(value = "添加事件", notes = "添加事件")
    @PostMapping(value = "/v1/add")
	public ResultModel<Event> saveEvent(@Valid @ApiParam("添加事件请求参数") @RequestBody EventVO eventVO){
    	log.info("saveEvent request={}",eventVO);
    	Event event=BeanUtil.copyBean(eventVO, Event.class);	
    	ResultModel<Event> result= eventService.save(event);
    	log.info("saveEvent response={}",result);
    	return result;
	}
    
    @ApiOperation(value = "修改事件", notes = "修改事件")
    @PutMapping(value = "/v1/merge")
	public ResultModel<Event> mergeEvent(@Valid @ApiParam("修改事件请求参数") @RequestBody EventMergeVO eventVO){
    	log.info("mergeEvent request={}",eventVO);   
    	ResultModel<Event> result= eventService.merge(eventVO);
    	log.info("mergeEvent response={}",result);
    	return result;
	}
    
    @ApiOperation(value = "删除事件", notes = "删除事件")
    @DeleteMapping(value = "/v1/delete/{id}")
	public ResultModel<String> deleteEvent( @Valid @ApiParam("事件id") @PathVariable("id")String id){
    	log.info("deleteEvent request={}",id);   
    	ResultModel<String> result= eventService.delete(id);
    	log.info("deleteEvent response={}",result);
    	return result;
	}
    
    @GetMapping("/v1/find/{id}")
    @ApiOperation(value = "根据id获取事件信息", notes = "根据id获取事件信息")
    public ResultModel<Event> getById(@ApiParam("事件id") @PathVariable("id")String id){
    	log.info("geById request id={};"+id);
    	Event data=eventService.findById(id);
    	log.info("geById response={}",data);
        return JsonResult.success(data);
    }
    
    @GetMapping("/v1/get/{page}/{size}")
    @ApiOperation(value = "根据条件分页查询事件信息", notes = "根据条件分页查询事件信息")
    public ResultModel< PageResult<Event>> getAllPage(@PathVariable("page")Integer page,@PathVariable("size")Integer size,EventVO eventVO){
    	log.info("getAllPage request page={},size={},params={};",page,size,eventVO);
    	Event event=BeanUtil.copyBean(eventVO, Event.class);	
    	PageResult<Event> result=eventService.findByExample(page, size, event);
    	log.info("getAllPage response={}",result);
        return JsonResult.success(result);
    }
}
