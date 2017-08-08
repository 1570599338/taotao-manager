package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemCatService;

/**
 * 商品分类管理Service
 * @author lquan
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	// 注入dao
	@Autowired
	private TbItemCatMapper ItemCatService;

	/**
	 * 根据父节点查找商品子节点列表
	 * @param parentId 父节点ID
	 * @return
	 */
	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		// 根据父节id 查询子节点
		TbItemCatExample example = new TbItemCatExample();
		// 查询条件
		Criteria crite = example.createCriteria();
		// 设置parentid
		crite.andParentIdEqualTo(parentId);
		
		//执行查询
		List<TbItemCat> list = this.ItemCatService.selectByExample(example);
		
		// TbItemCat的list 转换成easyuitreNode类型的list
		List<EasyUITreeNode> nodeList = new ArrayList<EasyUITreeNode>();
		for (TbItemCat cat :list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(cat.getId());// 设置ID
			node.setText(cat.getName()); // 设置文本内容
			node.setState(cat.getIsParent()?"closed":"open");
			nodeList.add(node);
		}
		
		return nodeList;
	}

}
