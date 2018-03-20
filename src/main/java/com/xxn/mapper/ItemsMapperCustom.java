package com.xxn.mapper;



import java.util.List;

import com.xxn.po.ItemsCustom;
import com.xxn.po.ItemsQueryVo;
import org.apache.ibatis.annotations.Param;

public interface ItemsMapperCustom {
    //商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;
}