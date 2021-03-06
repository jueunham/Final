package web.service.cor.face;

import java.util.HashMap;
import java.util.List;

import web.dto.Cor;
import web.dto.mypage.resume.Resume;
import web.util.resume.Paging;

public interface CorService {

	public Cor select(int cor_no);
	
	public void update(Cor cor);


	//---------인재 게시판---------------
	public int getTotal(Paging paging);
	
	public List getResumeList(Paging search);
	
	public int getMainIntroductionNoByMemberNo(int member_no);
	
	public boolean myScrab(HashMap map);
	
	public boolean scrabCheck(HashMap map);
	
	public boolean myScrabCancel(HashMap map);
	
	public List<Resume> getScrabList(int member_no);

	public int getCountScrab(int member_no);
}
