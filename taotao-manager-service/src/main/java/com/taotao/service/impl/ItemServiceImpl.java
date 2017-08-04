package com.taotao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
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
	
	@Override
	public TbItem getItemById(Long itemId) {
		
		TbItem item =	itemMapper.selectByPrimaryKey(itemId);
		
		return item;
	}

}
