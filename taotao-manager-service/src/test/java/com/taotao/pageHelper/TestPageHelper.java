package com.taotao.pageHelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.container.page.PageHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;

public class TestPageHelper {
	
	@Test
	public void testPageHelper(){
		// 1,在mybatis的配置文件中配置分页插件
		// 2、在执行查询之前配置分页条件，使用PageHelper的静态方法
		PageHelper.startPage(1, 10);
		// 3、执行查询
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		
		// 创建Exaple对象
		TbItemExample example = new TbItemExample();
		// 创建查询条件
		//Criteria criteria = example.createCriteria();
		List<TbItem> list=itemMapper.selectByExample(example);
		// 4、区分页信息。使用PageInfo对象。
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		System.out.println("总记录数："+pageInfo.getTotal()); 
		System.out.println("总记页数："+pageInfo.getPages());
		System.out.println("总行数："+list.size());
	}

}
