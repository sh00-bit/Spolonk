package kr.co.softsoldesk.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.mapper.UserMapper;
import kr.co.softsoldesk.service.UserService;

@RestController //응답 결과가 데이터로 
public class RestApiController {

	@Autowired
	private UserService userService;
	
	 @Value("${path.upload}")
	 private String uploadFolder; // 프로퍼티에 저장된 업로드 폴더 경로

	 @Autowired
	 private UserMapper userMapper;
	 
	//ID 중복확인
	@GetMapping("/user/checkUserIdExist/{user_id}")// HTTP GET 요청을 처리합니다. URL 경로에 {user_id}를 변수로 사용 
	public String checkUserIdExist(@PathVariable String user_id) {
		
		boolean chk = userService.checkUserIdExist(user_id);
		
		return  chk+"";
		//ID가 DB에 있으면 false, 없으면 true
	}
	
    private String saveUploadFile(MultipartFile uploadFile) {
        String fileName = System.currentTimeMillis() + "_" + uploadFile.getOriginalFilename();
        File uploadDir = new File(uploadFolder);
        
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        try {
            File fileToSave = new File(uploadDir, fileName);
            uploadFile.transferTo(fileToSave);
            System.out.println("File saved to: " + fileToSave.getAbsolutePath()); // 로그 추가
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return fileName;
    }
  
	@Resource(name = "loginUserBean")  // 명확하게 어떤 빈을 사용할지 지정
	private UserBean loginUserBean;
    
    
	 @PostMapping("/myPage/uploadProfileImage")
	    public ResponseEntity<String> uploadProfileImage(@RequestParam("profileImage") MultipartFile file) {
		
	        if (file.isEmpty()) {
	            return new ResponseEntity<>("파일이 비었습니다", HttpStatus.BAD_REQUEST);
	        }

	        String fileName = saveUploadFile(file);
	        int user_idx = loginUserBean.getUser_idx();
	        
	       
	        
	        if (fileName != null) {
	           
	        	// 데이터베이스 업데이트	        	
	            userMapper.updateProfileImage(fileName, user_idx);
	            
	            // 세션 업데이트
	            loginUserBean.setProfileImagePath(fileName);
	           	                     
	            return new ResponseEntity<>("업로드 완료: " + fileName, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("파일 저장 실패", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        
	        
	        }
	    
	    @GetMapping("/getProfileImage/{profileImage}")
	    public ResponseEntity<byte[]> getProfileImage(@PathVariable String fileName) {
	        try {

	            Path filePath = Paths.get(uploadFolder, fileName);

	            if (!Files.exists(filePath)) {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }

	            byte[] imageBytes = Files.readAllBytes(filePath);

	            return ResponseEntity.ok()
	                    .header("Content-Type", Files.probeContentType(filePath))
	                    .body(imageBytes);

	        } catch (IOException e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	}


