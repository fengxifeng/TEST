package com.vwmam.eventm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vwmam.eventm.dao.EventDao;
import com.vwmam.eventm.dao.GenericDao;
import com.vwmam.eventm.entity.Event;
import com.vwmam.eventm.result.JsonResult;
import com.vwmam.eventm.result.PageResult;
import com.vwmam.eventm.result.ResultModel;
import com.vwmam.eventm.service.EventService;
import com.vwmam.eventm.vo.EventMergeVO;

@Service
//@CacheConfig(cacheNames = "userService")
public class EventServiceImpl implements EventService {
	
	private final static Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

	
	@Autowired
	private GenericDao genericDao;
	
	@Autowired
	private EventDao eventDao;
	
	public ResultModel<Event> save(Event event) {
		String id=UUID.randomUUID().toString();
		event.setEventId(id.replaceAll("-", ""));
		event.setAddTime(new Date());
		try {
			genericDao.saveObject(event);
		} catch (Exception e) {
			log.error("添加事件失败", e);
			return JsonResult.error("添加事件失败");	
		}
		return JsonResult.success(event);		
	}
	
	
	public ResultModel<Event> merge(EventMergeVO eventVo) {
		try {
			Event event=findById(eventVo.getEventId());
			if(event==null) {
				return JsonResult.error("当前事件不存在");	
			}
			if(eventVo.getCode()!=null) {
				event.setCode(eventVo.getCode());
			}
			if(eventVo.getName()!=null) {
				event.setName(eventVo.getName());
			}
			if(eventVo.getRemark()!=null) {
				event.setRemark(eventVo.getRemark());
			}
			if(eventVo.getClassId()!=null) {
				event.setClassId(eventVo.getClassId());
			}
			genericDao.update(event);
			return JsonResult.success(event);
		} catch (Exception e) {
			log.error("修改事件失败", e);
			return JsonResult.error("修改事件失败");	
		}
				
	}
	
	public ResultModel<String> delete(String eventId) {
		try {
			eventDao.delete(eventId);
			return JsonResult.success();	
		} catch (Exception e) {
			log.error("删除事件失败", e);
			return JsonResult.error("删除事件失败");	
		}
				
	}
	
	public Event findById(String eventId){
		return eventDao.findOne(eventId);
	}
	
	public PageResult<Event> findByExample(int page,int size,Event event){
		
		Pageable pageable = new PageRequest(page, size,Direction.ASC,"addTime");
    	Example<Event> example=Example.of(event);
    	Page<Event> pageList=eventDao.findAll(example, pageable);
    	List<Event> data=pageList.getContent();
    	long count=pageList.getTotalElements();
		return new PageResult<Event>(page,size,count,data);
		
	}
	
	
	public List<Event> findAll() {		
		return eventDao.findAll();		
	}

}
