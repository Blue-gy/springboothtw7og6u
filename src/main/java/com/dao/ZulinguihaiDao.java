package com.dao;

import com.entity.ZulinguihaiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ZulinguihaiVO;
import com.entity.view.ZulinguihaiView;


/**
 * 租赁归还
 * 
 * @author 
 * @email 
 * @date 2030-02-26 18:34:50
 */
public interface ZulinguihaiDao extends BaseMapper<ZulinguihaiEntity> {
	
	List<ZulinguihaiVO> selectListVO(@Param("ew") Wrapper<ZulinguihaiEntity> wrapper);
	
	ZulinguihaiVO selectVO(@Param("ew") Wrapper<ZulinguihaiEntity> wrapper);
	
	List<ZulinguihaiView> selectListView(@Param("ew") Wrapper<ZulinguihaiEntity> wrapper);

	List<ZulinguihaiView> selectListView(Pagination page,@Param("ew") Wrapper<ZulinguihaiEntity> wrapper);

	
	ZulinguihaiView selectView(@Param("ew") Wrapper<ZulinguihaiEntity> wrapper);
	

}
