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

import com.entity.ChangdiyuyueEntity;
import com.entity.view.ChangdiyuyueView;

import com.service.ChangdiyuyueService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 场地预约
 * 后端接口
 * @author 
 * @email 
 * @date 2030-02-26 18:34:49
 */
@RestController
@RequestMapping("/changdiyuyue")
public class ChangdiyuyueController {
    @Autowired
    private ChangdiyuyueService changdiyuyueService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChangdiyuyueEntity changdiyuyue,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("huiyuan")) {
			changdiyuyue.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ChangdiyuyueEntity> ew = new EntityWrapper<ChangdiyuyueEntity>();

		PageUtils page = changdiyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, changdiyuyue), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChangdiyuyueEntity changdiyuyue, 
		HttpServletRequest request){
        EntityWrapper<ChangdiyuyueEntity> ew = new EntityWrapper<ChangdiyuyueEntity>();

		PageUtils page = changdiyuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, changdiyuyue), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChangdiyuyueEntity changdiyuyue){
       	EntityWrapper<ChangdiyuyueEntity> ew = new EntityWrapper<ChangdiyuyueEntity>();
      	ew.allEq(MPUtil.allEQMapPre( changdiyuyue, "changdiyuyue")); 
        return R.ok().put("data", changdiyuyueService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChangdiyuyueEntity changdiyuyue){
        EntityWrapper< ChangdiyuyueEntity> ew = new EntityWrapper< ChangdiyuyueEntity>();
 		ew.allEq(MPUtil.allEQMapPre( changdiyuyue, "changdiyuyue")); 
		ChangdiyuyueView changdiyuyueView =  changdiyuyueService.selectView(ew);
		return R.ok("查询场地预约成功").put("data", changdiyuyueView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChangdiyuyueEntity changdiyuyue = changdiyuyueService.selectById(id);
        return R.ok().put("data", changdiyuyue);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChangdiyuyueEntity changdiyuyue = changdiyuyueService.selectById(id);
        return R.ok().put("data", changdiyuyue);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChangdiyuyueEntity changdiyuyue, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(changdiyuyue);
        changdiyuyueService.insert(changdiyuyue);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChangdiyuyueEntity changdiyuyue, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(changdiyuyue);
        changdiyuyueService.insert(changdiyuyue);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChangdiyuyueEntity changdiyuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(changdiyuyue);
        changdiyuyueService.updateById(changdiyuyue);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ChangdiyuyueEntity> list = new ArrayList<ChangdiyuyueEntity>();
        for(Long id : ids) {
            ChangdiyuyueEntity changdiyuyue = changdiyuyueService.selectById(id);
            changdiyuyue.setSfsh(sfsh);
            changdiyuyue.setShhf(shhf);
            list.add(changdiyuyue);
        }
        changdiyuyueService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        changdiyuyueService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
