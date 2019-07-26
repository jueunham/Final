package web.dao.review.face;

import java.util.HashMap;
import java.util.List;

import web.dto.Recommend;
import web.dto.Review;
import web.util.Paging;

public interface ReviewDao {

	public int selectCorno(String selectCor);
	
	public void insertReview(Review review);

	public String selectCor(String result);

	public List<HashMap<String, Object>> selectReview(Paging paging);

	public int selectCntAll();

	public List<HashMap<String, Object>> selectReviewByTag(Paging paging);

	public List<HashMap<String, Object>> searchReview(Paging paging);

	public List<Review> selectViewList(int reviewno);

	public void addHit(int reviewno);

	public int addLike(int reviewno);

	public int selectLike(int reviewno);

	public int minLike(int reviewno);

	public void insertRecommend(Recommend recommend);

	public void deleteRecommend(Recommend recommend);

	public List<Recommend> scanRecommend(int memno);

	public void addRecommend(Recommend recommend);


}
