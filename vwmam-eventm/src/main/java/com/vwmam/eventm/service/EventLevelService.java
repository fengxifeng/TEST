package com.vwmam.eventm.service;

import java.util.List;

import com.vwmam.eventm.entity.EventLevel;
import com.vwmam.eventm.result.PageResult;
import com.vwmam.eventm.result.ResultModel;
import com.vwmam.eventm.vo.EventLevelMergeVO;

public interface EventLevelService {
	public ResultModel<EventLevel> save(EventLevel event);
	
	public ResultModel<EventLevel> merge(EventLevelMergeVO event);
	
	public EventLevel findById(String levelId);
	
	public ResultModel<String> delete(String levelId);
	
	public PageResult<EventLevel> findByExample(int page,int size,EventLevel event);
	
	public List<EventLevel> findAll();
}
