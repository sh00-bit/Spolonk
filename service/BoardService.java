package kr.co.softsoldesk.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.beans.CommentBean;
import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.beans.PageBean;
import kr.co.softsoldesk.dao.BoardDAO;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Value("${page.listcnt}")
	private int page_list;
	
	@Value("${page.paginationcnt}")
	private int paginationcnt;
	
	//게시판 이름 가져오기
	public String getBoardInfoName(int board_info_idx) {
				
		return boardDAO.getBoardInfoName(board_info_idx);
	}
	//게시글 저장
	public void addContent(ContentBean writeContentBean) {
		
		boardDAO.addContent(writeContentBean);
	}
	
	//게시글 작성 이름 찾기
	public String findName(int user_idx) {
		
		return boardDAO.findName(user_idx);
	}
	
	//게시글 목록 * 페이지네이션
	public List<ContentBean> getContentList(int board_info_idx, int page){
			
		int start = (page - 1) * page_list;
		RowBounds rowBounds = new RowBounds(start, page_list);
		/*
		 1페이지 링크, 0
		 2페이지 링크, 10
		 3페이지 링크, 20
		 */
			
		return boardDAO.getContentList(board_info_idx, rowBounds);
	}
	
	//게시글 읽기
	public ContentBean getContentInfo(int post_id) {
		
		return boardDAO.getCotentInfo(post_id);
	}
	
	//게시글 삭제
	public void deleteContentInfo(int post_id) {
		
		boardDAO.deleteContentInfo(post_id);
	}
	
	//게시글 수정
	public void modifyContent(ContentBean modifyContentBean) {
		
		boardDAO.modifyContent(modifyContentBean);
	}
	
	//게시글 답글 달기
	public void addComment(CommentBean commentBean) {
		
		boardDAO.addComment(commentBean);
	}
	
	// 댓글 목록 조회
	public List<CommentBean> getCommentList(int post_id) {
	    
		
		return boardDAO.getCommentList(post_id);
	}
	
	//댓글 삭제
	public void deleteComment(int post_id, int comments_id) {
       
		boardDAO.deleteComment(post_id, comments_id);
    }
	
	//대댓글 달기
	public void addReplyComment(CommentBean commentBean) {
		
		boardDAO.addReplyComment(commentBean);
	}
	
	//대댓글 목록
	public List<CommentBean> getCommentReplies(int post_id) {
			
		return boardDAO.getCommentReplies(post_id);
	}
	
	//대댓글 삭제
	public void deleteReplyCommet(int comments_id) {
		
		boardDAO.deleteReplyComment(comments_id);
	}
	//page
	public PageBean getContentCnt(int board_info_idx, int currentPage) {
			
		int content_cnt = boardDAO.getContentCnt(board_info_idx); //게시판의 전체글 개수
			
		PageBean pageBean = new PageBean(content_cnt, currentPage, page_list, paginationcnt);
			
		return pageBean;
	}
		
	//좋아요, 싫어요
	public void incrementLike(int post_id) {
		boardDAO.incrementLike(post_id);
	}

	public void incrementDislike(int post_id) {
		boardDAO.incrementDislike(post_id);
	}

	public int getLikeCount(int post_id) {
	    return boardDAO.getLikeCount(post_id);
	}

	public int getDislikeCount(int post_id) {
	    return boardDAO.getDislikeCount(post_id);
	}
	
	//조회수
	public void incrementViewCount(int post_id) {
			
		boardDAO.incrementViewCount(post_id);
	}
	//조회수 Top 10
	public List<ContentBean> getTopPosts(int board_info_idx) {
			
		return boardDAO.getTopPosts(board_info_idx);
	}
		
	// 한 페이지에 보여줄 게시물 수
    private static final int PAGE_SIZE = 10;

    public List<ContentBean> getUserContentList(int user_idx) {
        return boardDAO.getUserContentList(user_idx);
    }
}
