package com.internousdev.hibiscus.action;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.hibiscus.dao.ProductInfoDAO;
import com.internousdev.hibiscus.dto.MCategoryDTO;
import com.internousdev.hibiscus.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;
/**
 *商品一覧
 * @author Sakamoto
 *
 */
public class ProductListAction extends ActionSupport implements SessionAware{

//	商品情報取得のためのvaluestack
	private String productName;
	private String productNameKana;
	private String imageFilePath;
	private String imageFileName;
	private int price;

//	カテゴリ検索から持ってくるため valuestack
	private String keywords;
	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();
	private List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();

	private Map<String,Object>session;

	public String execute(){
//		セッション切れを防ぐため
		if (!session.containsKey("mCategoryDtoList")) {
			return "session";
		}
		String result=ERROR;

		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		productInfoDtoList = productInfoDAO.getProductInfoList();
		session.put("productInfoDtoList", productInfoDtoList);

		result=SUCCESS;
		return result;
	}
	public List<MCategoryDTO> getmCategoryDtoList(){
		return mCategoryDtoList;
	}
	public void setmCategoryDtoList(List<MCategoryDTO>mCategoryDtoList){
		this.mCategoryDtoList=mCategoryDtoList;
	}
	public List<ProductInfoDTO> getProductInfoDtoList(){
		return productInfoDtoList;
	}
	public void setProductInfoDtoList(List<ProductInfoDTO>productInfoDtoList){
		this.productInfoDtoList=productInfoDtoList;
	}
	public String getProductName(){
		return productName;
	}
	public void setProductName(String productName){
		this.productName=productName;
	}
	public String getProductNameKana(){
		return productNameKana;
	}
	public void setProductNameKana(String productNameKana){
		this.productNameKana=productNameKana;
	}
	public String getImageFilePath(){
		return imageFilePath;
	}
	public void setImageFilePath(String imageFilePath){
		this.imageFilePath=imageFilePath;
	}
	public String getImageFileName(){
		return imageFileName;
	}
	public void setImageFileName(String imageFileName){
		this.imageFileName=imageFileName;
	}
	public int getPrice(){
		return price;
	}
	public void setPrice(int price){
		this.price=price;
	}
	public String getKeywords(){
		return keywords;
	}
	public void setKeywords(String keywords){
		this.keywords=keywords;
	}
	public Map<String,Object>getSession(){
		return session;
	}
	public void setSession(Map<String,Object>session){
		this.session=session;
	}
}