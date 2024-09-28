package kr.co.softsoldesk.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import kr.co.softsoldesk.beans.CommentBean;
import kr.co.softsoldesk.beans.ContentBean;


public interface BoardMapper {
	
	//게시판 이름 
		@Select("select board_info_name from board_info where board_info_idx = #{board_info_idx}")
		String getBoardInfoName(int board_info_idx);
		
		//글쓰기
		@Insert("insert into Post(post_id, title, content, writing_time, user_idx, board_info_idx) values(Post_seq.NEXTVAL, #{title}, #{content}, SYSTIMESTAMP, #{user_idx}, #{board_info_idx})")
		void addContent(ContentBean writeContentBean);

		//글 목록
		@Select("SELECT p.post_id, p.title, p.content, to_char(p.writing_time, 'YYYY-MM-DD') AS writing_time, p.user_idx, p.board_info_idx, b.board_info_name\r\n"
				+ "FROM Post p, board_info b\r\n"
				+ "where p.board_info_idx = b.board_info_idx\r\n"
				+ "and b.board_info_idx = #{board_info_idx} order by p.writing_time DESC, p.post_id DESC")
		List<ContentBean>getContentList(int board_info_idx, RowBounds rowBounds);
		

		//글 읽기
		@Select("select p.title, p.content, to_char(p.writing_time, 'YYYY-MM-DD') AS writing_time, " +
		        "p.view_count AS view_count, p.like_count AS like_count, " +  
		        "p.dislike_count AS dislike_count, " +
		        "u.user_name AS user_name, u.user_idx AS user_idx " +
		        "from Post p JOIN users u ON p.user_idx = u.user_idx where p.post_id = #{post_id}")
		ContentBean getContentInfo(int post_id);
		
		//글 삭제
		@Delete("delete from Post where post_id=#{post_id}")
		void deleteContentInfo(int post_id);
		
		//글 수정
		@Update("update Post set title = #{title}, content = #{content} where post_id = #{post_id}")
		void modifyContent(ContentBean modifyContentBean);
		
		//게시판 댓글 달기
		@Insert("INSERT INTO comments (content, writing_time, user_idx, post_id, board_info_idx) " +
		        "VALUES (#{content},SYSTIMESTAMP, #{user_idx},#{post_id}, #{board_info_idx})")
		void addComment(CommentBean commentBean);
		
		// 댓글 목록 조회
		@Select("SELECT c.user_idx, c.comments_id, c.content, to_char(c.writing_time, 'YYYY-MM-DD') AS writing_time, u.user_name AS user_name, u.profileImage AS profileImage, u.profileImagePath AS profileImagePath\r\n"
				+ "FROM comments c JOIN users u ON c.user_idx = u.user_idx\r\n"
				+ "WHERE c.post_id = #{post_id} and parent_comment_id is null\r\n"
				+ "ORDER BY c.writing_time DESC")
		List<CommentBean> getCommentList(int post_id);
		
		//댓글 삭제
		@Delete("delete from comments where comments_id = #{comments_id} and post_id = #{post_id}")
		void deleteComment(@Param("post_id") int post_id,@Param("comments_id") int comments_id);
		
		//대댓글 달기
		@Insert("insert into comments (content, writing_time, user_idx, post_id, board_info_idx, parent_comment_id) " +
		        "values (#{content}, SYSTIMESTAMP, #{user_idx}, #{post_id}, #{board_info_idx}, #{parent_comment_id})")
		void addReplyComment(CommentBean commentBean);
		
		//대댓글 목록 
		@Select("select u.user_idx, c.comments_id, c.content, to_char(c.writing_time, 'YYYY-MM-DD') AS writing_time,u.profileImage AS profileImage, u.profileImagePath AS profileImagePath, u.user_name AS user_name, c.parent_comment_id " +
				"from comments c join users u on c.user_idx = u.user_idx " +
				"where c.post_id = #{post_id} " +
				"order by coalesce(c.parent_comment_id,c.comments_id),c.writing_time DESC")
		List<CommentBean> getCommentReplies(int post_id);
		
		//대댓글 삭제
		@Delete("delete from comments where comments_id = #{comments_id}")
		void deleteReplyComment( @Param("comments_id") int comments_id);
		
		//page 
		@Select("select count(*) from Post where board_info_idx = #{board_info_idx}")
		int getContentCnt(int board_info_idx);
		
		//좋아요
		@Update("UPDATE Post SET like_count = like_count + 1 WHERE post_id = #{post_id}")
		
		void incrementLike(int post_id);

		@Update("UPDATE post SET dislike_count = dislike_count + 1 WHERE post_id = #{post_id}")
		
		void incrementDislike(int post_id);

		@Select("SELECT like_count FROM Post WHERE post_id = #{post_id}")
		
		int getLikeCount(int post_id);

		@Select("SELECT dislike_count FROM Post WHERE post_id = #{post_id}")
		
		int getDislikeCount(int post_id);
		
		//조회수 
		@Update("UPDATE Post SET view_count = view_count + 1 WHERE post_id = #{post_id}")
		void incrementViewCount(int post_id);
		
		//조회수 Top 10
		@Select("select * from (select * from Post where board_info_idx = #{board_info_idx} order by view_count DESC) where rownum <= 10")
		List<ContentBean> getTopPosts(int board_info_idx);
		
		   //내가 쓴 글 목록
		@Select("SELECT p.post_id, p.title, p.content, to_char(p.writing_time, 'YYYY-MM-DD') AS writing_time, " +
		           "       p.user_idx, p.board_info_idx,b.board_info_idx, b.board_info_name " +
		           "FROM Post p " +
		           "JOIN board_info b ON p.board_info_idx = b.board_info_idx " +
		           "WHERE p.user_idx = #{user_idx}" +
		           "ORDER BY p.writing_time DESC, p.post_id DESC")
		List<ContentBean> getUserContentList(int user_idx);
}