package kr.co.softsoldesk.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.CommentBean;
import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.mapper.BoardMapper;
import kr.co.softsoldesk.mapper.UserMapper;

@Repository
public class BoardDAO {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	//게시판 이름 가져오기
	public String getBoardInfoName(int board_info_idx) {
		
		return boardMapper.getBoardInfoName(board_info_idx);
	}
	
	//게시글 저장
	public void addContent(ContentBean writeContentBean) {
		
		boardMapper.addContent(writeContentBean);
	}
	
	//게시글 작성 이름 찾기
	public String findName(int user_idx) {
		
		return userMapper.findName(user_idx);
	}
	
	//게시글 목록
	public List<ContentBean> getContentList(int board_info_idx,RowBounds rowBounds){
		
		return boardMapper.getContentList(board_info_idx, rowBounds);
	}
	
	//게시글 읽기
	public ContentBean getCotentInfo(int post_id) {
		
		return boardMapper.getContentInfo(post_id);
	}
	
	//게시글 삭제
	public void deleteContentInfo(int post_id) {
		
		boardMapper.deleteContentInfo(post_id);
	}
	
	//게시글 수정
	public void modifyContent(ContentBean modifyContentBean) {
		
		boardMapper.modifyContent(modifyContentBean);
	}
	
	//게시글 답글 달기
	public void addComment(CommentBean commentBean) {
		
		boardMapper.addComment(commentBean);
	}	
	
	// 댓글 목록 조회
	public List<CommentBean> getCommentList(int post_id) {
	  
		return boardMapper.getCommentList(post_id);
	}
	
	// 댓글 삭제
	public void deleteComment(int post_id,int comments_id) {
	
		boardMapper.deleteComment(post_id, comments_id);
	}
	
	//대댓글 달기
	public void addReplyComment(CommentBean commentBean) {
		
		boardMapper.addReplyComment(commentBean);
	}
	
	//대댓글 목록
	public List<CommentBean> getCommentReplies(int post_id) {
		
		return boardMapper.getCommentReplies(post_id);
	}
	
	//대댓글 삭제
	public void deleteReplyComment( int comments_id) {
		
		boardMapper.deleteReplyComment(comments_id);
	}
	//page
	public int getContentCnt(int board_info_idx) {
			
		return boardMapper.getContentCnt(board_info_idx);
	}
		
	//좋아요,싫어요
	 public void incrementLike(int post_id) {
			 
	 boardMapper.incrementLike(post_id);
	}

	 public void incrementDislike(int post_id) {
		    
		 boardMapper.incrementDislike(post_id);
	}

	public int getLikeCount(int post_id) {
	    
		return boardMapper.getLikeCount(post_id);
	}

	public int getDislikeCount(int post_id) {
	     
		return boardMapper.getDislikeCount(post_id);
	}
	//조회수
		public void incrementViewCount(int post_id) {
			
			boardMapper.incrementViewCount(post_id);
		}
		
		//조회수  Top 10
		public List<ContentBean> getTopPosts(int board_info_idx) {
			
			return boardMapper.getTopPosts(board_info_idx);
		}
		
		//이 위로 ㄱㅊ
		 public List<ContentBean> getUserContentList(int user_idx) {
		       return boardMapper.getUserContentList(user_idx);
		   }
}
