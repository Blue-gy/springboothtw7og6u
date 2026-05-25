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


import com.dao.ChangdiweixiuDao;
import com.entity.ChangdiweixiuEntity;
import com.service.ChangdiweixiuService;
import com.entity.vo.ChangdiweixiuVO;
import com.entity.view.ChangdiweixiuView;

@Service("changdiweixiuService")
public class ChangdiweixiuServiceImpl extends ServiceImpl<ChangdiweixiuDao, ChangdiweixiuEntity> implements ChangdiweixiuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChangdiweixiuEntity> page = this.selectPage(
                new Query<ChangdiweixiuEntity>(params).getPage(),
                new EntityWrapper<ChangdiweixiuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ChangdiweixiuEntity> wrapper) {
		  Page<ChangdiweixiuView> page =new Query<ChangdiweixiuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ChangdiweixiuVO> selectListVO(Wrapper<ChangdiweixiuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ChangdiweixiuVO selectVO(Wrapper<ChangdiweixiuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ChangdiweixiuView> selectListView(Wrapper<ChangdiweixiuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChangdiweixiuView selectView(Wrapper<ChangdiweixiuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
