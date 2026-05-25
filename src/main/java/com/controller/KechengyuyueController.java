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

import com.entity.KechengyuyueEntity;
import com.entity.view.KechengyuyueView;

import com.service.KechengyuyueService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 课程预约
 * 后端接口
 * @author 
 * @email 
 * @date 2030-02-26 18:34:50
 */
@RestController
@RequestMapping("/kechengyuyue")
public class KechengyuyueController {
    @Autowired
    private KechengyuyueService kechengyuyueService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KechengyuyueEntity kechengyuyue,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("huiyuan")) {
			kechengyuyue.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<KechengyuyueEntity> ew = new EntityWrapper<KechengyuyueEntity>();

		PageUtils page = kechengyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengyuyue), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KechengyuyueEntity kechengyuyue, 
		HttpServletRequest request){
        EntityWrapper<KechengyuyueEntity> ew = new EntityWrapper<KechengyuyueEntity>();

		PageUtils page = kechengyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengyuyue), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KechengyuyueEntity kechengyuyue){
       	EntityWrapper<KechengyuyueEntity> ew = new EntityWrapper<KechengyuyueEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kechengyuyue, "kechengyuyue")); 
        return R.ok().put("data", kechengyuyueService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KechengyuyueEntity kechengyuyue){
        EntityWrapper< KechengyuyueEntity> ew = new EntityWrapper< KechengyuyueEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kechengyuyue, "kechengyuyue")); 
		KechengyuyueView kechengyuyueView =  kechengyuyueService.selectView(ew);
		return R.ok("查询课程预约成功").put("data", kechengyuyueView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KechengyuyueEntity kechengyuyue = kechengyuyueService.selectById(id);
        return R.ok().put("data", kechengyuyue);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KechengyuyueEntity kechengyuyue = kechengyuyueService.selectById(id);
        return R.ok().put("data", kechengyuyue);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KechengyuyueEntity kechengyuyue, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kechengyuyue);
        kechengyuyueService.insert(kechengyuyue);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KechengyuyueEntity kechengyuyue, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(kechengyuyue);
        kechengyuyueService.insert(kechengyuyue);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KechengyuyueEntity kechengyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kechengyuyue);
        kechengyuyueService.updateById(kechengyuyue);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<KechengyuyueEntity> list = new ArrayList<KechengyuyueEntity>();
        for(Long id : ids) {
            KechengyuyueEntity kechengyuyue = kechengyuyueService.selectById(id);
            kechengyuyue.setSfsh(sfsh);
            kechengyuyue.setShhf(shhf);
            list.add(kechengyuyue);
        }
        kechengyuyueService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kechengyuyueService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
