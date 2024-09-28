package kr.co.softsoldesk.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.imageBean;
import kr.co.softsoldesk.mapper.ImageMapper;

@Repository
public class ImageDAO {
	
	@Autowired
	private ImageMapper imageMapper;
	
	//게시글(이미지 저장)
		 public void addImage(imageBean bean) {
		        
			 imageMapper.addImage(bean);
		    }
		
		 //게시글(이미지 목록 찾기)
		 public List<imageBean> selectImage(RowBounds rowBounds){ 
			 
			 return imageMapper.selectImage(rowBounds);
		 }
		 
		 //게시글(특정 게시글 찾기)
		 public imageBean getImageInfo(int picture_idx, int user_idx) {
			 
			 return imageMapper.getImageInfo(picture_idx, user_idx);
		 }
		 
		 //게시글 삭제
		 public void deleteImage(int picture_idx) {
			 
			 imageMapper.deleteImage(picture_idx);
		 }
		 
		 //게시글 수정
		 public void modifyImage(imageBean modifyBean, int picture_idx) {
			 
			 imageMapper.modifyImage(modifyBean,picture_idx);
		 }
		 	
		 //page
		 public int  getImageCnt() {
			 
			 return imageMapper.getImageCnt();
		 }
	 	
	 
}
