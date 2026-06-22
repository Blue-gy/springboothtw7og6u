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

import com.entity.ZulinguihaiEntity;
import com.entity.ZulinjiluEntity;
import com.entity.view.ZulinguihaiView;

import com.service.ZulinguihaiService;
import com.service.ZulinjiluService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 租赁归还
 * 后端接口
 * @author 
 * @email 
 * @date 2030-02-26 18:34:50
 */
@RestController
@RequestMapping("/zulinguihai")
public class ZulinguihaiController {
    @Autowired
    private ZulinguihaiService zulinguihaiService;

    @Autowired
    private ZulinjiluService zulinjiluService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZulinguihaiEntity zulinguihai,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("huiyuan")) {
			zulinguihai.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ZulinguihaiEntity> ew = new EntityWrapper<ZulinguihaiEntity>();

		PageUtils page = zulinguihaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zulinguihai), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZulinguihaiEntity zulinguihai, 
		HttpServletRequest request){
        EntityWrapper<ZulinguihaiEntity> ew = new EntityWrapper<ZulinguihaiEntity>();

		PageUtils page = zulinguihaiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zulinguihai), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZulinguihaiEntity zulinguihai){
       	EntityWrapper<ZulinguihaiEntity> ew = new EntityWrapper<ZulinguihaiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zulinguihai, "zulinguihai")); 
        return R.ok().put("data", zulinguihaiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZulinguihaiEntity zulinguihai){
        EntityWrapper< ZulinguihaiEntity> ew = new EntityWrapper< ZulinguihaiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zulinguihai, "zulinguihai")); 
		ZulinguihaiView zulinguihaiView =  zulinguihaiService.selectView(ew);
		return R.ok("查询租赁归还成功").put("data", zulinguihaiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZulinguihaiEntity zulinguihai = zulinguihaiService.selectById(id);
        return R.ok().put("data", zulinguihai);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZulinguihaiEntity zulinguihai = zulinguihaiService.selectById(id);
        return R.ok().put("data", zulinguihai);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @Transactional
    public R save(@RequestBody ZulinguihaiEntity zulinguihai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zulinguihai);
        zulinguihai.setSfsh("通过");
        zulinguihaiService.insert(zulinguihai);
        // 更新租赁记录的归还状态
        updateZulinjiluGuihaiStatus(zulinguihai);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    @Transactional
    public R add(@RequestBody ZulinguihaiEntity zulinguihai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zulinguihai);
        zulinguihai.setSfsh("通过");
        zulinguihaiService.insert(zulinguihai);
        // 更新租赁记录的归还状态
        updateZulinjiluGuihaiStatus(zulinguihai);
        return R.ok();
    }

    /**
     * 更新租赁记录的归还状态为"已归还"
     */
    private void updateZulinjiluGuihaiStatus(ZulinguihaiEntity zulinguihai) {
        Long crossrefid = zulinguihai.getCrossrefid();
        if (crossrefid != null) {
            ZulinjiluEntity zulinjilu = zulinjiluService.selectById(crossrefid);
            if (zulinjilu != null) {
                zulinjilu.setGuihaizhuangtai("已归还");
                zulinjiluService.updateById(zulinjilu);
            }
        }
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZulinguihaiEntity zulinguihai, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zulinguihai);
        zulinguihaiService.updateById(zulinguihai);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ZulinguihaiEntity> list = new ArrayList<ZulinguihaiEntity>();
        for(Long id : ids) {
            ZulinguihaiEntity zulinguihai = zulinguihaiService.selectById(id);
            zulinguihai.setSfsh(sfsh);
            zulinguihai.setShhf(shhf);
            list.add(zulinguihai);
        }
        zulinguihaiService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zulinguihaiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
