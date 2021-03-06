package web.controller.cor;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import web.controller.mypage.MypageController;
import web.dto.Member;
import web.dto.Recruit;
import web.dto.mypage.introduction.Introduction;
import web.dto.mypage.introduction.Sub_Introduction;
import web.dto.mypage.resume.Activities;
import web.dto.mypage.resume.Award;
import web.dto.mypage.resume.Career;
import web.dto.mypage.resume.Certificate;
import web.dto.mypage.resume.Education;
import web.dto.mypage.resume.Language;
import web.dto.mypage.resume.Overseas_Experience;
import web.dto.mypage.resume.Preferential;
import web.dto.mypage.resume.Resume;
import web.dto.mypage.resume.School;
import web.service.cor.face.CorService;
import web.service.mypage.face.IntroductionService;
import web.service.mypage.face.MypageService;
import web.service.mypage.face.ResumeService;
import web.util.resume.Paging;

@Controller
public class ResumeController {

	private static final Logger logger = LoggerFactory.getLogger(ResumeController.class);

	
	@Autowired CorService corService;
	@Autowired ResumeService resumeService;
	@Autowired IntroductionService introductionService;
	
	@RequestMapping(value="/resume/list", method= RequestMethod.GET)
	public void list(
			Model model,
			@RequestParam(defaultValue = "0") int curPage, 
			Paging search) {
	
		int totalcount = corService.getTotal(search);

		Paging paging = new Paging(totalcount, curPage);
		
		paging.setSearch(search.getSearch());
		
		model.addAttribute("paging", paging);
		
		//해쉬맵으로 받음
		List list =corService.getResumeList(paging);
	//	logger.info("list : " + list);
		
		model.addAttribute("list", list);
		
	}
	
	
	@RequestMapping(value="/resume/view", method=RequestMethod.GET)
	public void view(
			Model model,
			@RequestParam int resume_no
			) {
		
		Boolean checkSchool = resumeService.checkSchool(resume_no);
		Boolean checkCareer = resumeService.checkCareer(resume_no);
		Boolean checkActivities = resumeService.checkActivities(resume_no);
		Boolean checkEducation = resumeService.checkEducation(resume_no);
		Boolean checkCertificate = resumeService.checkCertificate(resume_no);
		Boolean checkAward = resumeService.checkAward(resume_no);
		Boolean checkOverseas_Experience = resumeService.checkOverseas_Experience(resume_no);
		Boolean checkLanguage = resumeService.checkLanguage(resume_no);
		Boolean checkPreferential = resumeService.checkPreferential(resume_no);
		
		Resume resume = resumeService.selectResume(resume_no);
		model.addAttribute("resume", resume);
		
		model.addAttribute("checkSchool", checkSchool);
		model.addAttribute("checkCareer", checkCareer);
		model.addAttribute("checkActivities", checkActivities);
		model.addAttribute("checkEducation", checkEducation);
		model.addAttribute("checkCertificate", checkCertificate);
		model.addAttribute("checkAward", checkAward);
		model.addAttribute("checkOverseas_Experience", checkOverseas_Experience);
		model.addAttribute("checkLanguage", checkLanguage);
		model.addAttribute("checkPreferential", checkPreferential);
		
		
		if(checkSchool) {
			List<School> school = resumeService.selectSchool(resume_no);
			model.addAttribute("school", school);
		}
		if(checkCareer) {
			List<Career> career = resumeService.selectCareer(resume_no);
			model.addAttribute("career", career);
		}
		if(checkActivities) {
			List<Activities> activities = resumeService.selectActivities(resume_no);
			model.addAttribute("activities", activities);

		}
		if(checkEducation) {
			List<Education> education = resumeService.selectEducation(resume_no);
			model.addAttribute("education", education);
		}
		if(checkCertificate) {
			List<Certificate> certificate = resumeService.selectCertificate(resume_no);
			model.addAttribute("certificate", certificate);
		}
		if(checkAward) {
			List<Award> award = resumeService.selectAward(resume_no);
			model.addAttribute("award", award);
		}
		if(checkOverseas_Experience) {
			List<Overseas_Experience> overseas_Experience = resumeService.selectOverseas_Experience(resume_no);
			model.addAttribute("overseas_Experience", overseas_Experience);
		}
		if(checkLanguage) {
			List<Language> language = resumeService.selectLanguage(resume_no);
			model.addAttribute("language", language);
		}
		if(checkPreferential) {
			Preferential preferential = resumeService.selectPreferential(resume_no);
			model.addAttribute("preferential", preferential);
		}
		
		
		int introduction_no = corService.getMainIntroductionNoByMemberNo(resume.getMember_no());
	
		Boolean checkSub = introductionService.checkSub(introduction_no);
		Introduction introduction = introductionService.selelctIntroduction(introduction_no);

		model.addAttribute("introduction", introduction);
		model.addAttribute("checkSub", checkSub);		
		
		if(checkSub) {
			List<Sub_Introduction> sub = introductionService.selectSub(introduction_no);
			model.addAttribute("sub", sub);
		}	
		
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/resume/scrabCheck", method=RequestMethod.GET)
	public boolean myScrabCheck(Authentication auth, int resume_no) {
		//view페이지에서 스크랩버튼을 눌렀을 때 넘어오는 값 

		Member member = (Member) auth.getDetails();

		int member_no = member.getMember_no();

		HashMap map = new HashMap(); 
		map.put("member_no", member_no);
		map.put("resume_no", resume_no);

		//스크랩 성공 여부 및 메소드 실행 
		boolean res = corService.scrabCheck(map);
		//logger.info("scrab Check : "+ res );

		return res;

	}


	@ResponseBody
	@RequestMapping(value="/resume/scrabInsert", method=RequestMethod.POST)
	public boolean myScrab(Authentication auth, int resume_no) {
		//view페이지에서 스크랩버튼을 눌렀을 때 넘어오는 값 

		Member member = (Member) auth.getDetails();

		int member_no = member.getMember_no();

		HashMap map = new HashMap(); 
		map.put("member_no", member_no);
		map.put("resume_no", resume_no);

		//스크랩 성공 여부 및 메소드 실행 
		boolean res = corService.myScrab(map);

		return res;

	}

	@ResponseBody
	@RequestMapping(value="/resume/scrabCancel", method=RequestMethod.POST) 
	public boolean scrabCancel(Authentication auth, int resume_no) {

		Member member = (Member) auth.getDetails();

		int member_no = member.getMember_no();

		HashMap map = new HashMap();
		map.put("member_no", member_no);
		map.put("resume_no", resume_no);

		boolean res = false;

		res = corService.myScrabCancel(map);
		//취소 성공하면 true, 실패하면 false

		return res;

	}


	@RequestMapping(value="/cor/resumeScrab", method=RequestMethod.GET)
	public void scrab(Authentication auth, Model model) {

		Member member = (Member) auth.getDetails();
		int member_no = member.getMember_no();

		List<Resume> list = corService.getScrabList(member_no);

		model.addAttribute("list", list);

	}
	
	
}
