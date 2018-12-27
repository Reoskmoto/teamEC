package com.internousdev.hibiscus.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.hibiscus.dto.ProductInfoDTO;
import com.internousdev.hibiscus.util.DBConnector;
/**
 *
 * @author Sakamoto
 *
 */
public class ProductInfoDAO {

	private DBConnector db=new DBConnector();

	//	商品一覧用
	public List<ProductInfoDTO> getProductInfoList(){
		Connection con=db.getConnection();
		List<ProductInfoDTO>productInfoList =new ArrayList<ProductInfoDTO>();
		String sql="select * from product_info";

		try{
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();

			while(rs.next()){
				ProductInfoDTO productListDTO= new ProductInfoDTO();
				productListDTO.setId(rs.getInt("id"));
				productListDTO.setProductId(rs.getInt("product_id"));
				productListDTO.setProductName(rs.getString("product_name"));
				productListDTO.setProductNameKana(rs.getString("product_name_kana"));
				productListDTO.setProductDescription(rs.getString("product_description"));
				productListDTO.setCategoryId(rs.getInt("category_id"));
				productListDTO.setPrice(rs.getInt("price"));
				productListDTO.setImageFilePath(rs.getString("image_file_path"));
				productListDTO.setImageFileName(rs.getString("image_file_name"));
				productListDTO.setReleaseDate(rs.getDate("release_date"));
				productListDTO.setReleaseCompany(rs.getString("release_company"));
				productListDTO.setStatus(rs.getInt("status"));
				productListDTO.setRegistDate(rs.getDate("regist_date"));
				productListDTO.setUpdateDate(rs.getDate("update_date"));
				productInfoList.add(productListDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoList;
	}

	//	商品詳細用
	public ProductInfoDTO getProductInfo(int productId){
		Connection con=db.getConnection();
		ProductInfoDTO productInfoDTO=new ProductInfoDTO();
		//		クリックされた商品IDから情報をselectしてくる
		String sql="select * from product_info where product_id=?";

		try{
			PreparedStatement preparedStatement=con.prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			ResultSet rs=preparedStatement.executeQuery();

			while(rs.next()){
				productInfoDTO.setId(rs.getInt("id"));
				productInfoDTO.setProductId(rs.getInt("product_id"));
				productInfoDTO.setProductName(rs.getString("product_name"));
				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
				productInfoDTO.setProductDescription(rs.getString("product_description"));
				productInfoDTO.setCategoryId(rs.getInt("category_id"));
				productInfoDTO.setPrice(rs.getInt("price"));
				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
				productInfoDTO.setStatus(rs.getInt("status"));
				productInfoDTO.setRegistDate(rs.getDate("regist_date"));
				productInfoDTO.setUpdateDate(rs.getDate("update_date"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDTO;
	}

	//	商品検索全てのカテゴリー
	public List<ProductInfoDTO> getProductInfoListAll(String[] keywordsList){
		Connection con=db.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();

		String sql="select * from product_info where";
		//		中身が空なら次へ
		boolean initializeFlag = true;

		for(String keyword : keywordsList){
			if(initializeFlag){
				//				商品名、かなのキーワードを'%" + keyword +"%'←が部分一致でselectしている
				sql += " (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
				initializeFlag = false;
			}else{
				//				or以降で第二検索ワードを上と同じようにselect
				sql += " or (product_name like '%" + keyword + "%'  or product_name_kana like '%" + keyword + "%')";
			}
		}
		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				ProductInfoDTO productInfoDTO = new ProductInfoDTO();

				productInfoDTO.setId(rs.getInt("id"));
				productInfoDTO.setProductId(rs.getInt("product_id"));
				productInfoDTO.setProductName(rs.getString("product_name"));
				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
				productInfoDTO.setProductDescription(rs.getString("product_description"));
				productInfoDTO.setCategoryId(rs.getInt("category_id"));
				productInfoDTO.setPrice(rs.getInt("price"));
				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
				productInfoDTO.setStatus(rs.getInt("status"));
				productInfoDTO.setRegistDate(rs.getDate("regist_date"));
				productInfoDTO.setUpdateDate(rs.getDate("update_date"));
				productInfoDTOList.add(productInfoDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDTOList;
	}

	//	商品カテゴリーから検索用
	public List<ProductInfoDTO> getProductInfoListByKeywords(String[] keywordsList,String categoryId){
		Connection con=db.getConnection();
		List<ProductInfoDTO>productInfoDTOList = new ArrayList<ProductInfoDTO>();
		String sql="select * from product_info where";
		boolean initializeFlag = true;

		for(String keyword : keywordsList){
			if(initializeFlag){
				sql += " category_id =" + categoryId + " and ((product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
				initializeFlag = false;
			}else{
				sql += " or (product_name like '%" + keyword + "%' or product_name_kana like '%" + keyword + "%')";
			}
		}
		sql += ")";

		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				ProductInfoDTO productInfoDTO = new ProductInfoDTO();

				productInfoDTO.setId(rs.getInt("id"));
				productInfoDTO.setProductId(rs.getInt("product_id"));
				productInfoDTO.setProductName(rs.getString("product_name"));
				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
				productInfoDTO.setProductDescription(rs.getString("product_description"));
				productInfoDTO.setCategoryId(rs.getInt("category_id"));
				productInfoDTO.setPrice(rs.getInt("price"));
				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
				productInfoDTO.setStatus(rs.getInt("status"));
				productInfoDTO.setRegistDate(rs.getDate("regist_date"));
				productInfoDTO.setUpdateDate(rs.getDate("update_date"));
				productInfoDTOList.add(productInfoDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDTOList;
	}

	//	商品詳細画面に関連商品を表示する用
	public List<ProductInfoDTO> getProductInfoListByCategoryId(int categoryId,int productId,int limitOffset,int limitRowCount){
		Connection con=db.getConnection();
		List<ProductInfoDTO>productInfoDTOList = new ArrayList<ProductInfoDTO>();

		//		not in(?)で現在ページは含まない rand()はランダムで取り出し limitで取り出し件数を指定
		String sql = "select * from product_info where category_id=? and product_id not in(?) order by rand() limit ?,?";

		try{
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, categoryId);
			ps.setInt(2, productId);
			ps.setInt(3, limitOffset);
			ps.setInt(4, limitRowCount);
			ResultSet rs=ps.executeQuery();

			while(rs.next()){
				ProductInfoDTO productInfoDTO = new ProductInfoDTO();

				productInfoDTO.setId(rs.getInt("id"));
				productInfoDTO.setProductId(rs.getInt("product_id"));
				productInfoDTO.setProductName(rs.getString("product_name"));
				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
				productInfoDTO.setProductDescription(rs.getString("product_description"));
				productInfoDTO.setCategoryId(rs.getInt("category_id"));
				productInfoDTO.setPrice(rs.getInt("price"));
				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
				productInfoDTO.setStatus(rs.getInt("status"));
				productInfoDTO.setRegistDate(rs.getDate("regist_date"));
				productInfoDTO.setUpdateDate(rs.getDate("update_date"));
				productInfoDTOList.add(productInfoDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return productInfoDTOList;
	}

}
