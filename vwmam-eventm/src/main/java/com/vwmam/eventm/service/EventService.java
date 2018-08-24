package com.vwmam.eventm.service;

import java.util.List;

import com.vwmam.eventm.entity.Event;
import com.vwmam.eventm.result.PageResult;
import com.vwmam.eventm.result.ResultModel;
import com.vwmam.eventm.vo.EventMergeVO;

public interface EventService {
	public ResultModel<Event> save(Event event);
	
	public ResultModel<Event> merge(EventMergeVO event);
	
	public Event findById(String eventId);
	
	public ResultModel<String> delete(String eventId);
	
	public PageResult<Event> findByExample(int page,int size,Event event);
	
	public List<Event> findAll();
}
