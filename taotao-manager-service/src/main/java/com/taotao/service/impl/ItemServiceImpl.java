package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;

/**
 * 商品信息查询
 * 
 * @author lquan
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	/**
	 * 根据商品信息查询商品信息
	 * @param itemId
	 * @return
	 */
	@Override
	public TbItem getItemById(Long itemId) {
		
		TbItem item =	itemMapper.selectByPrimaryKey(itemId);
		
		return item;
	}

	/**
	 * 对商品信息进行分页
	 * @param page 页数
	 * @param rows 行数
	 * @return 返回分页信息
	 */
	@Override
	public EasyUIDataGridResult getTtemList(int page, int rows) {
		// 设置分页信息
		PageHelper.startPage(page, rows);
		// 执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		// 去查询结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	/**
	 * 添加商品信息
	 * @param item 商品信息
	 * @param desc 商品描述
	 * @return
	 */
	@Override
	public TaotaoResult addItem(TbItem item, String desc) {
		// 生成商品id
		Long id = IDUtils.genItemId();
		//补全item的属性
		item.setId(id);
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//向商品表插入数据
		itemMapper.insert(item);
		//创建一个商品描述表对应的pojo
		TbItemDesc tbItem = new TbItemDesc();
		//补全pojo的属性
		tbItem.setItemId(id);
		tbItem.setItemDesc(desc);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		//向商品描述表插入数据
		tbItemDescMapper.insert(tbItem);
		// 返回结果
		return TaotaoResult.ok();
	}

	
}
