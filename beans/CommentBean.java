package kr.co.softsoldesk.beans;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class CommentBean {
    
    private int comments_id; // 댓글 ID
    
    @NotEmpty(message = "댓글 내용을 입력해 주세요.")
    private String content; // 댓글 내용
   
    private String writing_time; // 댓글 작성 시간
    private int user_idx; // 작성자 ID
    private int post_id; // 게시물 ID
    private int board_info_idx; // 게시판 정보 ID
    private String user_name; // 작성자 이름
    
    private MultipartFile profileImage; //작성자 사진
    private String profileImagePath;
    
    private int parent_comment_id; // 대댓글의 부모 댓글 ID
    
    //관리자페이지용 제목
    private String posttitle;
    private String board_info_name;
    
    public String getPosttitle() {
		return posttitle;
	}

	public void setPosttitle(String posttitle) {
		this.posttitle = posttitle;
	}

	public String getBoard_info_name() {
		return board_info_name;
	}

	public void setBoard_info_name(String board_info_name) {
		this.board_info_name = board_info_name;
	}

	// 기본 생성자
    public CommentBean() {}
    
 // Getters and Setters
    public int getComments_id() {
        return comments_id;
    }
    public void setComments_id(int comments_id) {
        this.comments_id = comments_id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getWriting_time() {
        return writing_time;
    }
    public void setWriting_time(String writing_time) {
        this.writing_time = writing_time;
    } 
    public int getUser_idx() {
        return user_idx;
    }
    public void setUser_idx(int user_idx) {
        this.user_idx = user_idx;
    }
    public int getPost_id() {
        return post_id;
    }
    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }
    public int getBoard_info_idx() {
        return board_info_idx;
    }
    public void setBoard_info_idx(int board_info_idx) {
        this.board_info_idx = board_info_idx;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public int getParent_comment_id() {
        return parent_comment_id;
    }
    public void setParent_comment_id(int parent_comment_id) {
        this.parent_comment_id = parent_comment_id;
    }

	public MultipartFile getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(MultipartFile profileImage) {
		this.profileImage = profileImage;
	}

	public String getProfileImagePath() {
		return profileImagePath;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}
    
}
