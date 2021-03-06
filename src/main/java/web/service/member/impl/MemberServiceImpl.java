package web.service.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.dao.member.face.MemberDao;
import web.dto.Member;
import web.dto.Oauth;
import web.dto.Review;
import web.service.member.face.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired MemberDao memberDao;
	
	@Override
	public void join(Member member) {
	 
		memberDao.insert(member);
	}

	@Override
	public void corJoin(Member member) {
	 
		memberDao.corInsert(member);
	}
	
	@Override
	public void socialJoin(Member member) {
		
		int res = memberDao.selectById(member.getMember_id());
		
		if( res == 0){
			memberDao.socialInsert(member);
			
			Member newMem = memberDao.getUserDetails(member.getMember_id());
			
			System.out.println("newMem : "+ newMem);
			
			Oauth oauth = new Oauth();
			
			oauth.setMember_no(newMem.getMember_no());
			oauth.setSocial_email(newMem.getMember_email());
			oauth.setSocial_id(newMem.getMember_id());
			oauth.setSocial_name(newMem.getMember_name());
			
			memberDao.insertOauth(oauth);
			
		}
	
	}
	
	@Override
	public boolean memChk(String member_id) {
		
		boolean res = false;
		
		if( memberDao.selectById(member_id) == 0) {
			res = false;
		}else if(memberDao.selectById(member_id) == 1) {
			res = true;
		}
	
		return res;
		
	}

	@Override
	public void update(Member member) {
		
		memberDao.update(member);
		
	}

	@Override
	public Member selectById(String member_id) {
		
		return 	memberDao.getUserDetails(member_id);

	}

	@Override
	public void withdraw(int member_no) {
		
		memberDao.getWithdrawByNo(member_no);
		
	}

	@Override
	public List<Review> getList(int member_no) {
		
		return memberDao.getMyReviewList(member_no);
		
	}

	@Override
	public void updateMemberInfoExceptPw(Member member) {
		memberDao.updateMemberInfoExceptPw(member);
		
	}

	

}
