package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
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

	
}
