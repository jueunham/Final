package web.service.jobfair.face;

import java.util.List;

import web.dto.JobFair;

public interface JobFairService {
	
	public List<JobFair> calList();
	
	public void registerFair(JobFair jobfair);
	
	public JobFair adminView(int jobfair_no);
	
	public void updateFair(JobFair jobfair);
	
	public List<String> jobFairSearch(String title); 

}