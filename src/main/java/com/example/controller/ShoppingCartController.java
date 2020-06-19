package com.example.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;

@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ServletContext application;
	
	@RequestMapping("")
	public String index(Model model) {
//		applicationスコープに商品一覧を格納
		List<Item> itemList = (List<Item>)application.getAttribute("itemList");
		if (itemList == null) {
			itemList = new LinkedList<Item>();
			itemList.add(new Item("手帳ノート", 1000));
			itemList.add(new Item("文房具セット", 1500));
			itemList.add(new Item("ファイル", 2000));
		}
		itemList.forEach(e -> System.out.println(e));
		application.setAttribute("itemList", itemList);
//		sessionスコープ内の合計金額をrequestスコープに格納
		List<Item> cartList = (List<Item>)session.getAttribute("cartList");
		int sum = 0;
		if (cartList == null) {
			session.setAttribute("cartList", new LinkedList<>());
		} else {
			for (Item item : cartList) {
				sum += item.getPrice();
			}
		}
		model.addAttribute("sum", sum);
		
		return "item-and-cart";
	}
	
	@RequestMapping("/inCart")
	public String inCart(String index, Model model) {
//		applicationスコープから商品一覧を取得
		List<Item> itemList = (List<Item>)application.getAttribute("itemList");
//		sessionスコープからカート内の一覧を取得
		List<Item> cartList = (List<Item>)session.getAttribute("cartList");
//		新たにカートに追加してsessionスコープへ格納
		cartList.add(itemList.get(Integer.parseInt(index)));
		session.setAttribute("cartList", cartList);
		return index(model);
	}
	
	@RequestMapping("/deleteCart")
	public String deleteCart(String index, Model model) {
		List<Item> cartList = (List<Item>)session.getAttribute("cartList");
		cartList.remove(Integer.parseInt(index));
		session.setAttribute("cartList", cartList);
		return index(model);
	}
}
