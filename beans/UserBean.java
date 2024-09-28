package kr.co.softsoldesk.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserBean {
   
   private int user_idx;
   
   @Pattern(regexp = "[一-龯々ぁ-んァ-ン]{2,12}") // 漢字、々、平仮名、片仮名
   private String user_name;
   
   @Pattern(regexp = "[a-zA-Z0-9]{5,20}") //영어 대소문자, 숫자만 입력
   private String user_id;
   
   @Email
   private String user_email;
   
   @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?])[a-zA-Z0-9!@#$%^&*?]{8,20}$")
   private String user_pass1;
   
   @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?])[a-zA-Z0-9!@#$%^&*?]{8,20}$")
   private String user_pass2;
   
   @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$")
   private String user_Phone;
   
   private boolean userIdExist; //아이디 중복확인 여부
   private boolean userLogin; //로그인 상태
   
   @Size(max = 100)
   private String introduction;
   
   private MultipartFile profileImage;
   private String profileImagePath;

   private String gender = "";
   private Integer event_id = 0; //eventId 속성 정의 
   
   private int authority; // 1이 관리자 0은 기본 회원   
   private String inChargeContest;// authority가 0이면 null 1이면 대회 이름 넣기
   
   private String teamName; //myPage/myteam = 내팀 정보 불러오기 위한 겟셋 객체 등록 
   
   private int team_id; //승인 거절
   
   private String EventName;
   
    public UserBean() {
       this.userIdExist = false;   //userIdExist의 조건 값은 false
       this.userLogin = false;   //userLogin의 초기값은 false
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

   public String getUser_id() {
      return user_id;
   }

   public void setUser_id(String user_id) {
      this.user_id = user_id;
   }

   public String getUser_email() {
      return user_email;
   }

   public void setUser_email(String user_email) {
      this.user_email = user_email;
   }

   public String getUser_pass1() {
      return user_pass1;
   }

   public void setUser_pass1(String user_pass1) {
      this.user_pass1 = user_pass1;
   }

   public String getUser_pass2() {
      return user_pass2;
   }

   public void setUser_pass2(String user_pass2) {
      this.user_pass2 = user_pass2;
   }

   public String getUser_Phone() {
      return user_Phone;
   }

   public void setUser_Phone(String user_Phone) {
      this.user_Phone = user_Phone;
   }

   public boolean isUserIdExist() {
      return userIdExist;
   }

   public void setUserIdExist(boolean userIdExist) {
      this.userIdExist = userIdExist;
   }

   public boolean isUserLogin() {
      return userLogin;
   }

   public void setUserLogin(boolean userLogin) {
      this.userLogin = userLogin;
   }

   public String getIntroduction() {
      return introduction;
   }

   public void setIntroduction(String introduction) {
      this.introduction = introduction;
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

   public String getGender() {
      return gender;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public Integer getEvent_id() {
      return event_id;
   }

   public void setEvent_id(Integer event_id) {
      this.event_id = event_id;
   }

   public int getAuthority() {
      return authority;
   }

   public void setAuthority(int authority) {
      this.authority = authority;
   }

   public String getInChargeContest() {
      return inChargeContest;
   }

   public void setInChargeContest(String inChargeContest) {
      this.inChargeContest = inChargeContest;
   }

   public String getTeamName() {
      return teamName;
   }

   public void setTeamName(String teamName) {
      this.teamName = teamName;
   }

   public int getTeam_id() {
      return team_id;
   }

   public void setTeam_id(int team_id) {
      this.team_id = team_id;
   }

   public String getEventName() {
      return EventName;
   }

   public void setEventName(String eventName) {
      EventName = eventName;
   }
   

}
