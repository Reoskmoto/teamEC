package com.internousdev.hibiscus.action;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.hibiscus.dao.ProductInfoDAO;
import com.internousdev.hibiscus.dto.MCategoryDTO;
import com.internousdev.hibiscus.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 商品詳細
 * @author Sakamoto
 *
 */
public class ProductDetailsAction extends ActionSupport implements SessionAware{

	private	int productId;
	private String imageFilePath;
	private Map<String,Object>session;

	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();
	private List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();

	public String execute(){
//		セッション対策に
		if (!session.containsKey("mCategoryDtoList")) {
			return "session";
		}
		String result=ERROR;

		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		ProductInfoDTO productInfoDTO = new ProductInfoDTO();
//		商品クリック時に選択された商品情報をDTOクラスに送っている
		productInfoDTO = productInfoDAO.getProductInfo(productId);

		session.put("id", productInfoDTO.getId());
		session.put("productId", productInfoDTO.getProductId());
		session.put("productName", productInfoDTO.getProductName());
		session.put("productNameKana", productInfoDTO.getProductNameKana());
		session.put("imageFilePath", productInfoDTO.getImageFilePath());
		session.put("imageFileName", productInfoDTO.getImageFileName());
		session.put("price", productInfoDTO.getPrice());
		session.put("releaseCompany", productInfoDTO.getReleaseCompany());
		session.put("releaseDate", productInfoDTO.getReleaseDate());
		session.put("productDescription", productInfoDTO.getProductDescription());

//		商品画像が存在しない場合リストへ
		if(productInfoDTO.getImageFilePath()==null) {
			productInfoDtoList = productInfoDAO.getProductInfoList();
			session.put("productInfoDtoList", productInfoDtoList);
			return ERROR;
		}
//		整数のリストに直して１～５の数字をいれている 後からasList内の数字を変えれば在庫数の変更が容易
		List<Integer> productCountList = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
		session.put("productCountList", productCountList);

//		関連商品を同カテゴリ内から取得
		productInfoDtoList = productInfoDAO.getProductInfoListByCategoryId(productInfoDTO.getCategoryId(), productInfoDTO.getProductId(), 0, 3);
		Iterator<ProductInfoDTO> iterator = productInfoDtoList.iterator();
//		次があるかを確認している
		if(!(iterator.hasNext())){
			productCountList = null;
		}
		if(!productInfoDtoList.isEmpty() || productCountList==null){
			session.put("productInfoDtoList", productInfoDtoList);
			result=SUCCESS;
		}
		return result;
	}
	public List<MCategoryDTO>getmCategoryDtoList(){
		return mCategoryDtoList;
	}
	public void setmCategoryDtoList(List<MCategoryDTO> mCategoryDtoList){
		this.mCategoryDtoList=mCategoryDtoList;
	}
	public int getProductId(){
		return productId;
	}
	public void setProductId(int productId){
		this.productId=productId;
	}
	public String getImageFilePath(){
		return imageFilePath;
	}
	public void setImageFilePath(String imageFilePath){
		this.imageFilePath=imageFilePath;
	}
	public List<ProductInfoDTO>getProductInfoDtoList(){
		return productInfoDtoList;
	}
	public void setProductInfoDtoList(List<ProductInfoDTO> productInfoDtoList){
		this.productInfoDtoList=productInfoDtoList;
	}
	public Map<String,Object>getSession(){
		return session;
	}
	public void setSession(Map<String,Object>session){
		this.session=session;
	}
}
