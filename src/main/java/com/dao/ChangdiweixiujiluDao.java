package com.dao;

import com.entity.ChangdiweixiujiluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ChangdiweixiujiluVO;
import com.entity.view.ChangdiweixiujiluView;


/**
 * 场地维修记录
 * 
 * @author 
 * @email 
 * @date 2030-02-26 18:34:51
 */
public interface ChangdiweixiujiluDao extends BaseMapper<ChangdiweixiujiluEntity> {
	
	List<ChangdiweixiujiluVO> selectListVO(@Param("ew") Wrapper<ChangdiweixiujiluEntity> wrapper);
	
	ChangdiweixiujiluVO selectVO(@Param("ew") Wrapper<ChangdiweixiujiluEntity> wrapper);
	
	List<ChangdiweixiujiluView> selectListView(@Param("ew") Wrapper<ChangdiweixiujiluEntity> wrapper);

	List<ChangdiweixiujiluView> selectListView(Pagination page,@Param("ew") Wrapper<ChangdiweixiujiluEntity> wrapper);

	
	ChangdiweixiujiluView selectView(@Param("ew") Wrapper<ChangdiweixiujiluEntity> wrapper);
	

}
