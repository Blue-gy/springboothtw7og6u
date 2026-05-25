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


import com.dao.ShebeiweixiujiluDao;
import com.entity.ShebeiweixiujiluEntity;
import com.service.ShebeiweixiujiluService;
import com.entity.vo.ShebeiweixiujiluVO;
import com.entity.view.ShebeiweixiujiluView;

@Service("shebeiweixiujiluService")
public class ShebeiweixiujiluServiceImpl extends ServiceImpl<ShebeiweixiujiluDao, ShebeiweixiujiluEntity> implements ShebeiweixiujiluService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShebeiweixiujiluEntity> page = this.selectPage(
                new Query<ShebeiweixiujiluEntity>(params).getPage(),
                new EntityWrapper<ShebeiweixiujiluEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ShebeiweixiujiluEntity> wrapper) {
		  Page<ShebeiweixiujiluView> page =new Query<ShebeiweixiujiluView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ShebeiweixiujiluVO> selectListVO(Wrapper<ShebeiweixiujiluEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ShebeiweixiujiluVO selectVO(Wrapper<ShebeiweixiujiluEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ShebeiweixiujiluView> selectListView(Wrapper<ShebeiweixiujiluEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ShebeiweixiujiluView selectView(Wrapper<ShebeiweixiujiluEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
