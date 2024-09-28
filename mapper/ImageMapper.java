package kr.co.softsoldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import kr.co.softsoldesk.beans.imageBean;


public interface ImageMapper {
	 
	//게시글(이미지 저장)
		@Insert("INSERT INTO image (picture_idx, title, writing_time, text,post_file,post_video, category, user_idx) " +
	            "VALUES (picture_seq.nextval, #{title, jdbcType=VARCHAR}, SYSTIMESTAMP, #{text, jdbcType=VARCHAR}, " +
	            "#{post_file, jdbcType=VARCHAR},#{post_video, jdbcType=VARCHAR}, #{category, jdbcType=VARCHAR}, #{user_idx, jdbcType=NUMERIC})")
	    void addImage(imageBean bean);
		
		//게시글(이미지 목록 찾기)
		@Select("select picture_idx,title,to_char(writing_time, 'YYYY-MM-DD') AS writing_time, post_file,post_video, category, user_idx"
				+ " from image order by writing_time DESC, picture_idx DESC")
		List<imageBean> selectImage(RowBounds rowBounds);
		
		//게시글(특정 게시글 찾기)
		@Select("select i.picture_idx, i.title, to_char(i.writing_time, 'YYYY-MM-DD') AS writing_time, i.text, i.post_file, i.post_video, i.category, u.user_name AS user_name, i.user_idx"
				+" from image i , users u where i.picture_idx = #{picture_idx} and u.user_idx = i.user_idx")
		imageBean getImageInfo(@Param("picture_idx") int picture_idx,@Param("user_idx") int user_idx);
		
		//게시글 삭제
		@Delete("delete from image where picture_idx=#{picture_idx}")
		void deleteImage(int picture_idx);
		
		//게시글 수정
		@Update("UPDATE image SET title = #{modifyBean.title, jdbcType=VARCHAR},text = #{modifyBean.text, jdbcType=VARCHAR}, post_file = #{modifyBean.post_file, jdbcType=VARCHAR}, " +
		        "post_video = #{modifyBean.post_video, jdbcType=VARCHAR},category = #{modifyBean.category, jdbcType=VARCHAR} " +
		        "WHERE picture_idx = #{picture_idx}")
		void modifyImage(@Param("modifyBean") imageBean modifyBean, @Param("picture_idx") int picture_idx);

		//page
		@Select("SELECT COUNT(*) AS total_count FROM image")
		int getImageCnt();
	
}
