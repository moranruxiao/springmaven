package com.xxn.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.xxn.po.ItemsCustom;
import com.xxn.po.ItemsQueryVo;
import com.xxn.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



/**
	*
	* <p>
	* Title: ItemsController
	* </p>
	* <p>
	* Description:商品管理
	* </p>
	* <p>
	* Company: www.itcast.com
	* </p>
	*
	* @author 传智.燕青
	* @date 2015-3-20下午3:04:57
	* @version 1.0
	*/
@Controller
//定义url的根路径，访问时根路径+方法的url
@RequestMapping("/items")
public class ItemsController {

	//注入service
	@Autowired
	private ItemsService itemsService;

	@RequestMapping("/queryItems")
	public ModelAndView queryItems(HttpServletRequest request) throws Exception {

		//System.out.println(request.getParameter("id"));

		//调用service查询商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		// 指定逻辑视图名
		modelAndView.setViewName("itemsList");

		return modelAndView;
	}

	//商品修改页面显示
	//使用method=RequestMethod.GET限制使用get方法
//	@RequestMapping(value="/editItems",method={RequestMethod.GET})
//	public ModelAndView editItems()throws Exception{
//
//		ModelAndView modelAndView = new ModelAndView();
//
//		//调用 service查询商品信息
//		ItemsCustom itemsCustom = itemsService.findItemsById(1);
//		//将模型数据传到jsp
//		modelAndView.addObject("item", itemsCustom);
//		//指定逻辑视图名
//		modelAndView.setViewName("editItem");
//
//		return modelAndView;
//
//	}

	//方法返回 字符串，字符串就是逻辑视图名，Model作用是将数据填充到request域，在页面展示
	@RequestMapping(value = "/editItems", method = {RequestMethod.GET})
	public String editItems(Model model, Integer id) throws Exception {

		model.addAttribute("id",id);
		//调用 service查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(id);

		model.addAttribute("item", itemsCustom);


		//return "editItem_2";
		return "editItem";

	}

	//方法返回void
//	@RequestMapping(value="/editItems",method={RequestMethod.GET})
//	public void editItems(
//			HttpServletRequest request,
//			HttpServletResponse response,
//			//@RequestParam(value = "item_id", required = false, defaultValue = "1") Integer id
//			Integer id
//			)
//			throws Exception {
//
//		//调用 service查询商品信息
//		ItemsCustom itemsCustom = itemsService.findItemsById(id);
//		request.setAttribute("item", itemsCustom);
//		//注意如果使用request转向页面，这里指定页面的完整路径
//		request.getRequestDispatcher("/WEB-INF/jsp/editItem.jsp").forward(request, response);
//
//	}
	@ModelAttribute("itemsType")
	public Map<String,String> getItemsType(){
		HashMap<String,String> itemsType =new HashMap<String, String>();
			itemsType.put("001","数码");
			itemsType.put("002","电器");
			return itemsType;

	}

	@RequestMapping("/deleteItems")
	public  String deleteItems(Integer[] delete_id) throws  Exception{
		System.out.println("666");
		System.out.println(delete_id);

		return "editItem";
	}

	//商品修改提交

	//itemsQueryVo是包装类型的pojo
	// 	public String editItemSubmit(Integer id,ItemsCustom itemsCustom,
//			ItemsQueryVo itemsQueryVo)throws Exception{
	@RequestMapping("/editItemSubmit")
	public String editItemSubmit(Model model, Integer id, @ModelAttribute(value = "item") ItemsCustom itemsCustom) throws Exception {
		System.out.println(id);
		model.addAttribute("id", id);
		//调用service接口更新商品信息
	//model.addAttribute("item",itemsCustom);

		itemsService.updateItems(id, itemsCustom);
		//请求重定向
		//return "redirect:queryItems.action";
		return "editItem";

	}
	@RequestMapping("/editItemsList")
	public ModelAndView editItemsList(HttpServletRequest request) throws Exception {

		System.out.println(request.getParameter("id"));

		//调用service查询商品列表
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		// 指定逻辑视图名
		modelAndView.setViewName("editItemsList");

		return modelAndView;
	}

	@RequestMapping("/editItemsListSubmit")
	public String editItemsListSubmit(ItemsQueryVo itemsQueryVo) throws  Exception{
		System.out.println("666");

		return "success";
	}
}
