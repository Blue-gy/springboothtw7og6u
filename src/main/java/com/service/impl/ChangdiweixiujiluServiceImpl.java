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


import com.dao.ChangdiweixiujiluDao;
import com.entity.ChangdiweixiujiluEntity;
import com.service.ChangdiweixiujiluService;
import com.entity.vo.ChangdiweixiujiluVO;
import com.entity.view.ChangdiweixiujiluView;

@Service("changdiweixiujiluService")
public class ChangdiweixiujiluServiceImpl extends ServiceImpl<ChangdiweixiujiluDao, ChangdiweixiujiluEntity> implements ChangdiweixiujiluService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChangdiweixiujiluEntity> page = this.selectPage(
                new Query<ChangdiweixiujiluEntity>(params).getPage(),
                new EntityWrapper<ChangdiweixiujiluEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ChangdiweixiujiluEntity> wrapper) {
		  Page<ChangdiweixiujiluView> page =new Query<ChangdiweixiujiluView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ChangdiweixiujiluVO> selectListVO(Wrapper<ChangdiweixiujiluEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ChangdiweixiujiluVO selectVO(Wrapper<ChangdiweixiujiluEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ChangdiweixiujiluView> selectListView(Wrapper<ChangdiweixiujiluEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChangdiweixiujiluView selectView(Wrapper<ChangdiweixiujiluEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
