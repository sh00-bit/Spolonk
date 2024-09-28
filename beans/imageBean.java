package kr.co.softsoldesk.beans;


import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

public class imageBean {
	
private int picture_idx;//게시글 idx
    
    private String title;//제목
    
    private String writing_time; //작성일
    
   
    private String text; //내용
   
   
    private MultipartFile image;  // 이미지 파일
    private String post_file; //DB에 이미지 파일 이름을 담을 변수
    
    private MultipartFile video; //동영상 파일
    private String post_video; //DB에 동영상 파일 이름을 담을 변수
    
    @NotBlank(message = "カテゴリーを選択してください。")
    private String category; //카테고리
    
    private int user_idx;
    private String user_name;
    

    
    public int getPicture_idx() {
        return picture_idx;
    }
    public void setPicture_idx(int picture_idx) {
        this.picture_idx = picture_idx;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getWriting_time() {
        return writing_time;
    }
    public void setWriting_time(String writing_time) {
        this.writing_time = writing_time;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public String getPost_file() {
		return post_file;
	}
	public void setPost_file(String post_file) {
		this.post_file = post_file;
	}
    public MultipartFile getVideo() {
		return video;
	}
	public void setVideo(MultipartFile video) {
		this.video = video;
	}
	public String getPost_video() {
		return post_video;
	}
	public void setPost_video(String post_video) {
		this.post_video = post_video;
	}
	public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
    
}
