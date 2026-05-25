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

import com.entity.ZulinjiluEntity;
import com.entity.view.ZulinjiluView;

import com.service.ZulinjiluService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 租赁记录
 * 后端接口
 * @author 
 * @email 
 * @date 2030-02-26 18:34:50
 */
@RestController
@RequestMapping("/zulinjilu")
public class ZulinjiluController {
    @Autowired
    private ZulinjiluService zulinjiluService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZulinjiluEntity zulinjilu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("huiyuan")) {
			zulinjilu.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ZulinjiluEntity> ew = new EntityWrapper<ZulinjiluEntity>();

		PageUtils page = zulinjiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zulinjilu), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZulinjiluEntity zulinjilu, 
		HttpServletRequest request){
        EntityWrapper<ZulinjiluEntity> ew = new EntityWrapper<ZulinjiluEntity>();

		PageUtils page = zulinjiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zulinjilu), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZulinjiluEntity zulinjilu){
       	EntityWrapper<ZulinjiluEntity> ew = new EntityWrapper<ZulinjiluEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zulinjilu, "zulinjilu")); 
        return R.ok().put("data", zulinjiluService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZulinjiluEntity zulinjilu){
        EntityWrapper< ZulinjiluEntity> ew = new EntityWrapper< ZulinjiluEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zulinjilu, "zulinjilu")); 
		ZulinjiluView zulinjiluView =  zulinjiluService.selectView(ew);
		return R.ok("查询租赁记录成功").put("data", zulinjiluView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZulinjiluEntity zulinjilu = zulinjiluService.selectById(id);
        return R.ok().put("data", zulinjilu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZulinjiluEntity zulinjilu = zulinjiluService.selectById(id);
        return R.ok().put("data", zulinjilu);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZulinjiluEntity zulinjilu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zulinjilu);
        zulinjiluService.insert(zulinjilu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZulinjiluEntity zulinjilu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zulinjilu);
        zulinjiluService.insert(zulinjilu);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZulinjiluEntity zulinjilu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zulinjilu);
        zulinjiluService.updateById(zulinjilu);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ZulinjiluEntity> list = new ArrayList<ZulinjiluEntity>();
        for(Long id : ids) {
            ZulinjiluEntity zulinjilu = zulinjiluService.selectById(id);
            zulinjilu.setSfsh(sfsh);
            zulinjilu.setShhf(shhf);
            list.add(zulinjilu);
        }
        zulinjiluService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zulinjiluService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
