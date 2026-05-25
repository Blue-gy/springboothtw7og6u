package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.DiscussbisaiEntity;
import com.entity.view.DiscussbisaiView;

import com.service.DiscussbisaiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 比赛评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2030-02-26 18:34:52
 */
@RestController
@RequestMapping("/discussbisai")
public class DiscussbisaiController {
    @Autowired
    private DiscussbisaiService discussbisaiService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussbisaiEntity discussbisai,
		HttpServletRequest request){
        EntityWrapper<DiscussbisaiEntity> ew = new EntityWrapper<DiscussbisaiEntity>();

		PageUtils page = discussbisaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussbisai), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussbisaiEntity discussbisai, 
		HttpServletRequest request){
        EntityWrapper<DiscussbisaiEntity> ew = new EntityWrapper<DiscussbisaiEntity>();

		PageUtils page = discussbisaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussbisai), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussbisaiEntity discussbisai){
       	EntityWrapper<DiscussbisaiEntity> ew = new EntityWrapper<DiscussbisaiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussbisai, "discussbisai")); 
        return R.ok().put("data", discussbisaiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussbisaiEntity discussbisai){
        EntityWrapper< DiscussbisaiEntity> ew = new EntityWrapper< DiscussbisaiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussbisai, "discussbisai")); 
		DiscussbisaiView discussbisaiView =  discussbisaiService.selectView(ew);
		return R.ok("查询比赛评论表成功").put("data", discussbisaiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussbisaiEntity discussbisai = discussbisaiService.selectById(id);
        return R.ok().put("data", discussbisai);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussbisaiEntity discussbisai = discussbisaiService.selectById(id);
        return R.ok().put("data", discussbisai);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussbisaiEntity discussbisai, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussbisai);
        discussbisaiService.insert(discussbisai);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussbisaiEntity discussbisai, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussbisai);
        discussbisaiService.insert(discussbisai);
        return R.ok();
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussbisaiEntity discussbisai = discussbisaiService.selectOne(new EntityWrapper<DiscussbisaiEntity>().eq("", username));
        return R.ok().put("data", discussbisai);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussbisaiEntity discussbisai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussbisai);
        discussbisaiService.updateById(discussbisai);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussbisaiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussbisaiEntity discussbisai, HttpServletRequest request,String pre){
        EntityWrapper<DiscussbisaiEntity> ew = new EntityWrapper<DiscussbisaiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = discussbisaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussbisai), params), params));
        return R.ok().put("data", page);
    }










}
