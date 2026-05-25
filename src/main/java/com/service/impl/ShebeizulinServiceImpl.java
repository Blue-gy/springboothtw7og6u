package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.ShebeizulinDao;
import com.entity.ShebeizulinEntity;
import com.service.ShebeizulinService;
import com.entity.vo.ShebeizulinVO;
import com.entity.view.ShebeizulinView;

@Service("shebeizulinService")
public class ShebeizulinServiceImpl extends ServiceImpl<ShebeizulinDao, ShebeizulinEntity> implements ShebeizulinService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShebeizulinEntity> page = this.selectPage(
                new Query<ShebeizulinEntity>(params).getPage(),
                new EntityWrapper<ShebeizulinEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ShebeizulinEntity> wrapper) {
		  Page<ShebeizulinView> page =new Query<ShebeizulinView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ShebeizulinVO> selectListVO(Wrapper<ShebeizulinEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ShebeizulinVO selectVO(Wrapper<ShebeizulinEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ShebeizulinView> selectListView(Wrapper<ShebeizulinEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ShebeizulinView selectView(Wrapper<ShebeizulinEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
