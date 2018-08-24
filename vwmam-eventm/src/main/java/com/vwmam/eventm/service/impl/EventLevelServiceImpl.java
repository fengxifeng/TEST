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

import com.vwmam.eventm.dao.EventLevelDao;
import com.vwmam.eventm.dao.GenericDao;
import com.vwmam.eventm.entity.EventLevel;
import com.vwmam.eventm.result.JsonResult;
import com.vwmam.eventm.result.PageResult;
import com.vwmam.eventm.result.ResultModel;
import com.vwmam.eventm.service.EventLevelService;
import com.vwmam.eventm.vo.EventLevelMergeVO;

@Service
//@CacheConfig(cacheNames = "userService")
public class EventLevelServiceImpl implements EventLevelService {
	
	private final static Logger log = LoggerFactory.getLogger(EventLevelServiceImpl.class);

	
	@Autowired
	private GenericDao genericDao;
	
	@Autowired
	private EventLevelDao eventLevelDao;
	
	public ResultModel<EventLevel> save(EventLevel event) {
		String id=UUID.randomUUID().toString();
		event.setLevelId(id.replaceAll("-", ""));
		event.setAddTime(new Date());
		try {
			genericDao.saveObject(event);
		} catch (Exception e) {
			log.error("添加事件失败", e);
			return JsonResult.error("添加事件失败");	
		}
		return JsonResult.success(event);		
	}
	
	
	public ResultModel<EventLevel> merge(EventLevelMergeVO eventVo) {
		try {
			EventLevel event=findById(eventVo.getLevelId());
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
	
	public ResultModel<String> delete(String levelId) {
		try {
			genericDao.delete(levelId, EventLevel.class);
			//eventLevelDao.delete(eventId);
			/*EventLevel entity=new EventLevel();
			entity.setLevelId(levelId);
			eventLevelDao.delete(entity);*/
			return JsonResult.success();	
		} catch (Exception e) {
			log.error("删除事件失败", e);
			return JsonResult.error("删除事件失败");	
		}
				
	}
	
	public EventLevel findById(String eventId){
		return eventLevelDao.findOne(eventId);
	}
	
	public PageResult<EventLevel> findByExample(int page,int size,EventLevel event){
		
		Pageable pageable = new PageRequest(page, size,Direction.ASC,"addTime");
    	Example<EventLevel> example=Example.of(event);
    	Page<EventLevel> pageList=eventLevelDao.findAll(example, pageable);
    	List<EventLevel> data=pageList.getContent();
    	long count=pageList.getTotalElements();
		return new PageResult<EventLevel>(page,size,count,data);
		
	}
	
	
	public List<EventLevel> findAll() {		
		return eventLevelDao.findAll();		
	}

}
