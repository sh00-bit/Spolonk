package kr.co.softsoldesk.beans;

import javax.validation.constraints.NotEmpty;

public class ContentBean {

private int post_id;
	
	@NotEmpty(message = "제목을 입력해 주세요.")
	private String title;
	
	@NotEmpty(message = "내용을 입력해 주세요.")
	private String content;
	private int user_idx;
	private String writing_time;
	
	private String user_name;
	
	//BOARD_INFO_IDX값 조인
	private int board_info_idx;
	
	private String board_info_name;
	
	//조회수
	
	public String getBoard_info_name() {
		return board_info_name;
	}
	public void setBoard_info_name(String board_info_name) {
		this.board_info_name = board_info_name;
	}
	//좋아요, 싫어요
	private int like_count;
	
    private int dislike_count;
	
	//조회수
    private int view_count;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getBoard_info_idx() {
		return board_info_idx;
	}
	public void setBoard_info_idx(int board_info_idx) {
		this.board_info_idx = board_info_idx;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getWriting_time() {
		return writing_time;
	}
	public void setWriting_time(String writing_time) {
		this.writing_time = writing_time;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public int getDislike_count() {
		return dislike_count;
	}
	public void setDislike_count(int dislike_count) {
		this.dislike_count = dislike_count;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	
	
	
	
	
}
