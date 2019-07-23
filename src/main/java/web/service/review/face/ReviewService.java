package web.service.review.face;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.Review;
import web.util.Paging;

public interface ReviewService {

	public int getCorno(String selectCor);

	public void write(Review review);

	public String scanTable(String result);

	public List<HashMap<String, Object>> getReviewList(Paging paging);

	public Paging getCurPage(HttpServletRequest request);

	public List<HashMap<String, Object>> tagSearch(Paging paging);

	public String getTag(String review_tag);

	public List<HashMap<String, Object>> reviewSearch(Paging paging, String keyword);

}
