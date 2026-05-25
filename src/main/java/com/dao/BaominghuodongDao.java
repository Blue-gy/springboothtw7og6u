package com.dao;

import com.entity.BaominghuodongEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.BaominghuodongVO;
import com.entity.view.BaominghuodongView;


/**
 * 报名活动
 * 
 * @author 
 * @email 
 * @date 2030-02-26 18:34:50
 */
public interface BaominghuodongDao extends BaseMapper<BaominghuodongEntity> {
	
	List<BaominghuodongVO> selectListVO(@Param("ew") Wrapper<BaominghuodongEntity> wrapper);
	
	BaominghuodongVO selectVO(@Param("ew") Wrapper<BaominghuodongEntity> wrapper);
	
	List<BaominghuodongView> selectListView(@Param("ew") Wrapper<BaominghuodongEntity> wrapper);

	List<BaominghuodongView> selectListView(Pagination page,@Param("ew") Wrapper<BaominghuodongEntity> wrapper);

	
	BaominghuodongView selectView(@Param("ew") Wrapper<BaominghuodongEntity> wrapper);
	

}
