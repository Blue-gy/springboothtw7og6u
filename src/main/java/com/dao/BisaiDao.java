package com.dao;

import com.entity.BisaiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.BisaiVO;
import com.entity.view.BisaiView;


/**
 * 比赛
 * 
 * @author 
 * @email 
 * @date 2030-02-26 18:34:49
 */
public interface BisaiDao extends BaseMapper<BisaiEntity> {
	
	List<BisaiVO> selectListVO(@Param("ew") Wrapper<BisaiEntity> wrapper);
	
	BisaiVO selectVO(@Param("ew") Wrapper<BisaiEntity> wrapper);
	
	List<BisaiView> selectListView(@Param("ew") Wrapper<BisaiEntity> wrapper);

	List<BisaiView> selectListView(Pagination page,@Param("ew") Wrapper<BisaiEntity> wrapper);

	
	BisaiView selectView(@Param("ew") Wrapper<BisaiEntity> wrapper);
	

}
