package kr.co.softsoldesk.controller;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.softsoldesk.Validator.PictureValidator;
import kr.co.softsoldesk.beans.PageBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.beans.imageBean;
import kr.co.softsoldesk.service.BoardService;
import kr.co.softsoldesk.service.ImageService;

@Controller
@RequestMapping("/image")
public class ImageController {
	

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private BoardService boardService;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	@Autowired
	private PictureValidator pictureValidator;
	
	
	@GetMapping("/picture")
	private String picture(Model model, @RequestParam(value = "page", defaultValue = "1")int page) {
		
		List<imageBean> pictureList = imageService.selectImage(page);
		
		model.addAttribute("pictureList",pictureList);
		
		PageBean pageBean = imageService.getImageCnt(page);
		model.addAttribute("pageBean", pageBean);
				
		return "image/picture";
	} 
	
	@GetMapping("/picture_upload")
	private String picture_upload(@ModelAttribute("imageBean")imageBean bean, Model model) {	
		
		int user_idx = loginUserBean.getUser_idx();
		String user_name = boardService.findName(loginUserBean.getUser_idx());
		
		model.addAttribute("user_name",user_name);
		model.addAttribute("user_idx",user_idx);
		
		return "image/picture_upload";
	}
	
	  @PostMapping("/picture_upload_pro")
	    public String picture_upload_pro(@Validated @ModelAttribute("imageBean") imageBean bean, 
	                                     BindingResult result, 
	                                     RedirectAttributes redirectAttributes) {
	        // 유효성 검사 수행
	        pictureValidator.validate(bean, result);

	        // 유효성 검사 오류가 있는 경우
	        if (result.hasErrors()) {
	        	
	            return "image/picture_upload";
	        }
	        
	        // imageBean 객체를 데이터베이스에 저장
	        imageService.addImage(bean);

	        redirectAttributes.addFlashAttribute("message", "이미지가 성공적으로 업로드되었습니다.");

	        return "redirect:/image/picture";
	    }
	@GetMapping("/picture_read")
	private String picture_read(@RequestParam("picture_idx") int picture_idx, @RequestParam("user_idx") int user_idx, Model model) {
				
		imageBean readBean = imageService.getImageInfo(picture_idx, user_idx);
		int loggedInUserId = loginUserBean.getUser_idx();
		
		model.addAttribute("readBean",readBean);
		model.addAttribute("picture_idx",picture_idx);
		model.addAttribute("user_idx", user_idx);
		model.addAttribute("loggedInUserId", loggedInUserId);
		
		return "image/picture_read";
	}
	
	@GetMapping("/pictureDelete")
	private String deleteImage(@RequestParam("picture_idx") int picture_idx) {
		
		imageService.deleteImage(picture_idx);
		
		return "image/pictureDelete";
	}
	
    @GetMapping("/pictureModify")
    public String modifyImage(@RequestParam("picture_idx") int picture_idx,@RequestParam("user_idx") int user_idx, Model model) {
        
    	imageBean modifyBean = imageService.getImageInfo(picture_idx,user_idx);
        
    	model.addAttribute("modifyBean", modifyBean);
    	model.addAttribute("picture_idx",picture_idx);
    	
    	return "image/pictureModify";
    }

    @PostMapping("/pictureModify_pro")
    public String pictureModify_pro(@Validated @ModelAttribute("modifyBean") imageBean modifyBean,
    								BindingResult result, @RequestParam("picture_idx") int picture_idx, 
    		 	 	 	  	 	 	Model model) {
        
    	 // 유효성 검사 수행
        pictureValidator.validate(modifyBean, result);
    	
        // 유효성 검사 오류가 있는 경우
        if (result.hasErrors()) {
            return "image/pictureModify";
        }
    	
	
    	imageService.modifyImage(modifyBean,picture_idx);
       
    	model.addAttribute("modifyBean", modifyBean);
    	model.addAttribute("picture_idx",picture_idx);
    	model.addAttribute("user_idx",modifyBean.getUser_idx());
    	
    	return "image/pictureModify_success";
    }
	
}
