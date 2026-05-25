package com.dao;

import com.entity.ShebeiweixiujiluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ShebeiweixiujiluVO;
import com.entity.view.ShebeiweixiujiluView;


/**
 * 设备维修记录
 * 
 * @author 
 * @email 
 * @date 2030-02-26 18:34:51
 */
public interface ShebeiweixiujiluDao extends BaseMapper<ShebeiweixiujiluEntity> {
	
	List<ShebeiweixiujiluVO> selectListVO(@Param("ew") Wrapper<ShebeiweixiujiluEntity> wrapper);
	
	ShebeiweixiujiluVO selectVO(@Param("ew") Wrapper<ShebeiweixiujiluEntity> wrapper);
	
	List<ShebeiweixiujiluView> selectListView(@Param("ew") Wrapper<ShebeiweixiujiluEntity> wrapper);

	List<ShebeiweixiujiluView> selectListView(Pagination page,@Param("ew") Wrapper<ShebeiweixiujiluEntity> wrapper);

	
	ShebeiweixiujiluView selectView(@Param("ew") Wrapper<ShebeiweixiujiluEntity> wrapper);
	

}
