package com.dao;

import com.entity.ShebeizulinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ShebeizulinVO;
import com.entity.view.ShebeizulinView;


/**
 * 设备租赁
 * 
 * @author 
 * @email 
 * @date 2030-02-26 18:34:49
 */
public interface ShebeizulinDao extends BaseMapper<ShebeizulinEntity> {
	
	List<ShebeizulinVO> selectListVO(@Param("ew") Wrapper<ShebeizulinEntity> wrapper);
	
	ShebeizulinVO selectVO(@Param("ew") Wrapper<ShebeizulinEntity> wrapper);
	
	List<ShebeizulinView> selectListView(@Param("ew") Wrapper<ShebeizulinEntity> wrapper);

	List<ShebeizulinView> selectListView(Pagination page,@Param("ew") Wrapper<ShebeizulinEntity> wrapper);

	
	ShebeizulinView selectView(@Param("ew") Wrapper<ShebeizulinEntity> wrapper);
	

}
