package kr.co.softsoldesk.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.softsoldesk.beans.PageBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.beans.imageBean;
import kr.co.softsoldesk.dao.ImageDAO;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class ImageService {

	@Value("${path.upload}")
    private String path_upload;

    @Autowired
    private ImageDAO imageDAO;
    
    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;
    
    @Value("${page.imgcnt}")
    private int imgcnt;

    @Value("${page.paginationcting}")
    private int paginationcting ;
    
  //게시글(이미지 저장)
    private String saveUploadFile(MultipartFile image) {
        
    	String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
       
        try {
        	image.transferTo(new File(path_upload + "/" + fileName));
        	
        } catch (IOException e) {
           
        	e.printStackTrace();
        }

        return fileName; 
    }

    
    public void addImage(imageBean bean) {
        
    	MultipartFile image = bean.getImage();
    	
    	if (image != null && image.getSize() > 0) {
    		
    	    String fileName = saveUploadFile(image);
    	    bean.setPost_file(fileName);
    	}
    	
    	MultipartFile video = bean.getVideo();
    	if(video != null && video.getSize() > 0) {
    		
    		String videoFileName = saveUploadFile(video);
    		bean.setPost_video(videoFileName);
    	}
    		imageDAO.addImage(bean);
    	
    }
    
    //게시글(이미지 목록 찾기)
    public List<imageBean> selectImage(int page){ 
		 
    	int start = (page - 1) * imgcnt;
    	RowBounds rowBounds = new RowBounds(start, imgcnt);
    	
		 return imageDAO.selectImage(rowBounds);
	 }
    
    //게시글(특정 게시글 찾기)
    public imageBean getImageInfo(int picture_idx, int user_idx) {
    	
    	return imageDAO.getImageInfo(picture_idx, user_idx);
    }
    
    //게시글 삭제
    public void deleteImage(int picture_idx) {
    	
    	imageDAO.deleteImage(picture_idx);
    }
    
    //게시글 수정
    public void modifyImage(imageBean modifyBean, int picture_idx) {
        MultipartFile image = modifyBean.getImage();
        
        if (image != null && image.getSize() > 0) {
            String fileName = saveUploadFile(image);
            modifyBean.setPost_file(fileName);
        }
        
        MultipartFile video = modifyBean.getVideo();
    	if(video != null && video.getSize() > 0) {
    		String videoFileName = saveUploadFile(video);
    		modifyBean.setPost_video(videoFileName);
    	}
    	
        imageDAO.modifyImage(modifyBean, picture_idx);
    }
    
    //page
    public PageBean getImageCnt(int currentPage) {
    	
    	int content_cnt = imageDAO.getImageCnt();
    	
    	PageBean pageBean = new PageBean(content_cnt, currentPage, imgcnt, paginationcting);
    	
    	return pageBean;
    	
    }
    
    
}



