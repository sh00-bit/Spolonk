package kr.co.softsoldesk.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.softsoldesk.beans.imageBean;

@Component
public class PictureValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return imageBean.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		imageBean ImageBean = (imageBean) target;
		
		 // 이미지나 동영상 중 하나는 필수로 입력되어야 함
        if ((ImageBean.getImage() == null || ImageBean.getImage().isEmpty()) &&
            (ImageBean.getVideo() == null || ImageBean.getVideo().isEmpty())) {
        	errors.rejectValue("image", "error.imageOrVideoRequired", "イメージや動画をアップロードしてください。");        }
			
		}
		
	}


