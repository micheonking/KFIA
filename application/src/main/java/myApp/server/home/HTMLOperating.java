package myApp.server.home;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.ibatis.session.SqlSession;

import myApp.server.utils.db.DatabaseFactory;

public class HTMLOperating implements javax.servlet.Servlet {

	private String actionCode = "retrieve"; 
	
	private String getApprStateName(String apprStateCode) {
		
		if(apprStateCode == null ) {
			return "<b>(대기)</b>"; 
		}
		else if("10".equals(apprStateCode)) {
			return "<b>(작성중)</b>"; 
		}
		else if("20".equals(apprStateCode)) {
			return "<b>(결재요청)</b>";
		}
		else if("30".equals(apprStateCode)) {
			return "<b>(승인완료)</b>";
		}
		else if("90".equals(apprStateCode)) {
			return "<b>(반려)</b>";
		}
		else {
			return "<b>(미확인오류)</b>";
		}
	}

	private String nullString(String data) {
		if("null".equals(data) || data == null) {
			return ""; 
		}
		else {
			String str = data.replaceAll("\r", "<br>"); 
			str = str.replaceAll("\n", "<br>");
			return  str; 
		}
	}

	
	private String mboBottomUp(Long evalTargetId, String empId) {
		
		SqlSession sqlSession = DatabaseFactory.openSession();
		List<String> tableString = new ArrayList<String>(); 
		
		/*
		 * 평가대상자 정보 찾기
		 */
//		Pmx05_EvalTargetModel myEvalTargetModel = sqlSession.selectOne("pmx05_eval_target.selectById", evalTargetId);
//
//		if(empId.equals(myEvalTargetModel.getTargetEmpId().toString())) {
//			this.actionCode = "edit"; 
//			System.out.println(this.actionCode + ":" + empId); 
//		}
//		
//		/* 
//		 * 나의 평가직무정보를 찾는다.  
//		 */
//		Map<String, Object> myJobManagerParam = new HashMap<String, Object>();
//		myJobManagerParam.put("startId", myEvalTargetModel.getStartId()); 
//		myJobManagerParam.put("jobCodeId", myEvalTargetModel.getJobCodeId());
//		myJobManagerParam.put("baseDate", myEvalTargetModel.getBaseDate());
//		Pmx03_JobManagerModel myJobManagerModel = sqlSession.selectOne("pmx03_job_mgr.selectByJobCodeId", myJobManagerParam);
//
//		/*
//		 * 나를 평가자로 하는 하위 직무자를 찾는다. 
//		 */
//		Map<String, Object> childEvalTargetParam = new HashMap<String, Object>(); 
//		childEvalTargetParam.put("startId", myEvalTargetModel.getStartId()); 
//		childEvalTargetParam.put("apprEmpId", myEvalTargetModel.getTargetEmpId());
//		List<Pmx05_EvalTargetModel> childEvalTargetList = sqlSession.selectList("pmx05_eval_target.selectByApprEmpId", childEvalTargetParam);
//		
//		// 직무명을 한줄로 보여준다.   
//		String jobHeader 
//			= tdGrey("직무명", 50, "center") 
//			+ tdColSpanGrey(3, myEvalTargetModel.getJobInfoModel().getJobName(), 640, "center") ;  
//
//		/*
//		 * 상위자 평가 정보찾기 
//		 */
//		Map<String, Object> parentTargetParam = new HashMap<String, Object>();
//		parentTargetParam.put("startId", myEvalTargetModel.getStartId());
//		
//		// 현재 설정된 평가자 정보를 기준으로 한다. 평가자가 변경되면 다시 수정할 수 있다.
//		if(myEvalTargetModel.getStep10_EmpId() == null) {
//			parentTargetParam.put("empId", myEvalTargetModel.getApprEmpId());
//		}
//		else {
//			parentTargetParam.put("empId", myEvalTargetModel.getStep10_EmpId());
//		}
//		
//		Pmx05_EvalTargetModel parentTargetModel = sqlSession.selectOne("pmx05_eval_target.selectByEmpId", parentTargetParam);
//
//		String linkedString = ""; 
//		if(parentTargetModel == null) {
//			// 제일상위의 평가자이다. 
//			linkedString = myEvalTargetModel.getTargetEmpModel().getKorName() + this.getApprStateName(myEvalTargetModel.getStep10_StateCode()) ; 
//		}
//		else {
//			// 평가대상자 이름을 한줄로 보여준다.  
//			linkedString 
//				= this.setLink(myEvalTargetModel.getTargetEmpModel().getKorName() + this.getApprStateName(myEvalTargetModel.getStep10_StateCode()), parentTargetModel.getEvalTargetId()) ;
//		}
//		
//		String empHeader 
//		= tdGrey("담당자", 50, "center")  
//		+ tdColSpanGrey(3, linkedString , 640, "center") ;
//
//		String titleHeader 
//		= tdGrey("No", 50, "center")
//		+ tdGrey("성과책임", 300, "center")
//		+ tdGrey("업적목표", 300, "center")
//		+ tdGrey("비중", 40, "center") ; 
//		
//		for(Pmx05_EvalTargetModel childEvalTargetModel : childEvalTargetList) {
//			jobHeader += tdColSpanGrey(3, childEvalTargetModel.getJobInfoModel().getJobName(), 630, "center");
//			
//			linkedString 
//				= this.setLink(childEvalTargetModel.getTargetEmpModel().getKorName() + this.getApprStateName(childEvalTargetModel.getStep10_StateCode()), childEvalTargetModel.getEvalTargetId());
//			
//			empHeader += tdColSpanGrey(3, linkedString, 630, "center") ;
//			titleHeader += tdGrey("성과책임", 300, "center") + tdGrey("업적목표", 300, "center") + tdGrey("비중", 40, "center") ; 
//		}
//		
//		tableString.add(jobHeader); 
//		tableString.add(empHeader);
//		tableString.add(titleHeader);
//	
//		/*
//		 * 상위자의 성과책임 리스트 찾기. order by seq 
//		 */
//		Map<String, Object> myRespParam = new HashMap<String, Object>();
//		myRespParam.put("jobManagerId", myJobManagerModel.getJobManagerId()); 
//		List<Pmx04_RespModel> myRespList = sqlSession.selectList("pmx04_resp.selectByJobManagerId", myRespParam) ; 
//
//		if(myRespList.size() < 1) {
//			return "등록된 성과책임이 없어 조회할 수 없습니다. "; 
//		}
//		
//		for(Pmx04_RespModel myRespModel : myRespList) {
//
//			/*
//			 * 나의 성과책임별 MBO찾기  
//			 */
//			Map<String, Object> myMboParam = new HashMap<String, Object>();
//			myMboParam.put("evalTargetId", myEvalTargetModel.getEvalTargetId()); 
//			myMboParam.put("respId", myRespModel.getRespId());
//			List<Pmx06_MboModel> myMboList = sqlSession.selectList("pmx06_mbo.selectByRespId", myMboParam) ; 
//				
//			int maxRowCount = myMboList.size() ; // 하나의 성과책임에 등록된 업적목표갯수. 
//			
////			maxRowCount = lcm(maxRowCount, myMboList.size());
//			
//			/*
//			 * 하위자의 성과책임 가져오기
//			 *  - 전체 갯수를 알아야 공배수를 구할 수 있으므로 전체 For문을 돌린다.  
//			 */
//			
//			for(Pmx05_EvalTargetModel childEvalTargetModel : childEvalTargetList) {
//
//				/* 
//				 * 하위자의 성과책임 리스트 찾기. 
//				 */
//				Map<String, Object> childRespParam = new HashMap<String, Object>();
//				childRespParam.put("startId", childEvalTargetModel.getStartId());
//				childRespParam.put("jobCodeId", childEvalTargetModel.getJobCodeId());
//				childRespParam.put("parentRespId",  myRespModel.getRespId());
//				List<Pmx04_RespModel> childRespList = sqlSession.selectList("pmx04_resp.selectByJobCodeId", childRespParam);
//				
//				System.out.println(myRespModel.getRespName() + "/" + childEvalTargetModel.getEvalTargetId() + " : RowCount is " + childRespList.size());
//				
//				if(childRespList.size() > 0 ) { 
//					// 최소공배수 구하기. 
//					maxRowCount =  lcm(maxRowCount, childRespList.size()) ;
//				} 
//				
//				/* 
//				 * 하위자의 성과책임별 MBO 가져오기   
//				 */
//				
////				int childMboCount = 0 ; 
//				
//				for(Pmx04_RespModel childRespModel : childRespList) {
//					
//					Map<String, Object> childMboParam = new HashMap<String, Object>();
//					childMboParam.put("evalTargetId", childEvalTargetModel.getEvalTargetId()); 
//					childMboParam.put("respId", childRespModel.getRespId());
//					List<Pmx06_MboModel> childMboList = sqlSession.selectList("pmx06_mbo.selectByRespId", childMboParam) ;
//				
//					if(childMboList.size() > 0) {
//						// 최소공배수 구하기.
//						maxRowCount =  lcm(maxRowCount, childMboList.size()) ;
//					} 
//				}
//			}
//			
//			if(maxRowCount < 1) { 
//				// maxRowCount가 0이면 RowSpan이 0이 되서 오류가 발생한다. 
//				maxRowCount = 1; 
//			}
////			System.out.println(myRespModel.getRespName() + " : maxRowCount is " + maxRowCount); 
//			
//			/* 
//			 * 한줄씩 채우기 
//			 */
//			
//			List<String> respStringList = new ArrayList<String>();
//			
//			for(int i = 0; i<maxRowCount; i++) {
//				respStringList.add(""); // 라인개수 만큼 미리 만들어 둔다.
//			}
//			
//			String myRespString = this.tdRowSpan(maxRowCount, myRespModel.getSeq(), 10, "center") 
//					+ this.tdRowSpan(maxRowCount, myRespModel.getRespName(), 10, "center") ; 
//			
//			respStringList.set(0,  myRespString);
//
//			/* 
//			 * 나의 업적목표 채우기 
//			 */
//			
//			if(myMboList.size() < 1) {
//				String myMboString = respStringList.get(0) 
//						+ this.tdRowSpan(maxRowCount, "", 10, "center") // 업적목표  
//						+ this.tdRowSpan(maxRowCount, "", 10, "center") ; // 비중
//				respStringList.set(0,  myMboString);
//			}
//			else {
//				
//				int myMboRowSpan = maxRowCount / (myMboList.size()<1?1:myMboList.size()) ; 
//				
//				for(int myMboIndex = 0; myMboIndex < myMboList.size() ; myMboIndex++) {
//					
//					int myMboPos = myMboRowSpan * myMboIndex; 
//					
//					Pmx06_MboModel myMboModel = myMboList.get(myMboIndex);
//						
//					String myMboString = respStringList.get(myMboPos) 
//							+ this.tdRowSpan(myMboRowSpan, myMboModel.getTargetName(), 10, "center") 
//							+ this.tdRowSpan(myMboRowSpan, myMboModel.getRatio()+"", 10, "center") ;
//					
//					respStringList.set(myMboPos,  myMboString);
//				}
//			}
//				
//			/* 
//			 * 하위자별 성과책임, 업적목표 등록하기.  
//			 */ 
//			for(Pmx05_EvalTargetModel childEvalTargetModel : childEvalTargetList) {
//				
//				/* 
//				 * 하위자의 성과책임 리스트 찾기. 
//				 */
//				Map<String, Object> childRespParam = new HashMap<String, Object>();
//				childRespParam.put("startId", childEvalTargetModel.getStartId());
//				childRespParam.put("jobCodeId", childEvalTargetModel.getJobCodeId());
//				childRespParam.put("parentRespId",  myRespModel.getRespId());
//				List<Pmx04_RespModel> childRespList = sqlSession.selectList("pmx04_resp.selectByJobCodeId", childRespParam);
//
//				if(childRespList.size() < 1) {
//					String childRespString  = respStringList.get(0) // 이전 텍스트 가져오기
//							+ this.tdRowSpan(maxRowCount, "" + childEvalTargetModel.getEvalTargetId(), 10, "center")  // 성과책임
//							+ this.tdRowSpan(maxRowCount, "", 10, "center")  // 업적목표
//							+ this.tdRowSpan(maxRowCount, "", 10, "center"); // 비중
//					respStringList.set(0,  childRespString);
//					
//				}
//				else {
//					
//					int childRespRowSpan = maxRowCount / childRespList.size() ; /// ((myMboCount < 1)?1:myMboCount) ;
//					
//					for(int childRespIndex = 0 ; childRespIndex< childRespList.size(); childRespIndex++) {
//						
//						Pmx04_RespModel childRespModel = childRespList.get(childRespIndex); 
//						
//						Map<String, Object> childMboParam = new HashMap<String, Object>();
//						childMboParam.put("respId", childRespModel.getRespId());
//						childMboParam.put("evalTargetId", childEvalTargetModel.getEvalTargetId());
//						List<Pmx06_MboModel> childMboList = sqlSession.selectList("pmx06_mbo.selectByRespId", childMboParam);
//						
//						int childRespPos = childRespRowSpan * childRespIndex ; // List<String>에서의 위치 설정.
//						
//						String childRespString  = respStringList.get(childRespPos) // 이전 텍스트 가져오기 		     
//								+ this.tdRowSpan(childRespRowSpan, childEvalTargetModel.getEvalTargetId() + ":" + childRespModel.getRespName(), 10, "left"); // 하위 성과책임
//						
//						respStringList.set(childRespPos, childRespString); // 첫줄 세팅.
//						
//						if(childMboList.size() < 1) {
//							myRespString = respStringList.get(childRespPos) 
//									+ this.tdRowSpan(childRespRowSpan, "", 10, "center")  // MBO.업적목표
//									+ this.tdRowSpan(childRespRowSpan, "", 10, "center") ; // MBO.비중
//							respStringList.set(childRespPos, myRespString); // 빈줄 세팅.
//						}
//						else {
//							
//							int childMboRowSpan = childRespRowSpan / childMboList.size(); 
//							int remainder = childRespRowSpan % childMboList.size();
//							
//							for(int childMboIndex = 0; childMboIndex < childMboList.size(); childMboIndex++ ) {
//								
//								int childMboPos = childRespPos + (childMboRowSpan * childMboIndex); 
//								
//								if(childMboIndex == childMboList.size() - 1) {
//									// 마지막 줄. 
//									childMboRowSpan += remainder; 
//								}
//								
//								Pmx06_MboModel childMboModel = childMboList.get(childMboIndex); 
//								
//								String targetName = "";
//								
//								if("edit".equals(this.actionCode)) {
//									if(childMboModel.getNote() != null) {
//										targetName = this.editMemo(childMboModel.getTargetName().replace("\n", "<br>"), childMboModel.getMboId(), true) ; // 줄바꿈 적용.	
//									}
//									else {
//										targetName = this.editMemo(childMboModel.getTargetName().replace("\n", "<br>"), childMboModel.getMboId(), false) ; // 줄바꿈 적용.
//									}
//								}
//								else {
//									targetName = childMboModel.getTargetName().replace("\n", "<br>"); 
//								}
//								
//								String childMboString  = respStringList.get(childMboPos) 		// 첫줄 가져오기    
//										+ this.tdRowSpan(childMboRowSpan, targetName, 10, "left")  // MBO.업적목표
//										+ this.tdRowSpan(childMboRowSpan, childMboModel.getRatio() + "", 10, "left") ; // MBO.비중
//								
//								respStringList.set(childMboPos, childMboString);
//							}
//						}
//						childRespPos += childRespRowSpan; 
//					}
//				}
//			}
//			
//			// 전체 취합하기. 
//			tableString.addAll(respStringList);
//			
//		}

//		int tableWidth = 630 +  (childEvalTargetList.size() * 630); 
		int tableWidth = 630 +  (100 * 630); 
		
		String htmlString = javaScript() 
				+ " <table id='mboBottomUpTable' border=1 style='width:" + tableWidth + "px; margin:10px; font-size:12px; border-collapse:collapse; border:1px silver solid; padding:0px;'>"; 
		
		for(int i=0; i < tableString.size(); i++) {
			htmlString += this.tr(tableString.get(i)); 
		}
		
		return htmlString + "</table>" ;  
	}
	
	private String mboTopDown(Long evalTargetId) {
 
		int parentRatioSum = 0;  
		int myRatioSum = 0; 
		
		SqlSession sqlSession = DatabaseFactory.openSession();

		/*
		 * 평가대상자 정보 찾기
		 */
//		Pmx05_EvalTargetModel evalTargetModel = sqlSession.selectOne("pmx05_eval_target.selectById", evalTargetId);
//		
//		if("edit".equals(this.actionCode)) {
//
//			Map<String, Object> planParam = new HashMap<String, Object>(); 
//			planParam.put("startId", evalTargetModel.getStartId()); 
//			planParam.put("planCode", "020"); // jobModeling 
//
//			Pmx02_PlanModel planModel = sqlSession.selectOne("pmx02_plan.selectByPlanCode", planParam);
//	
//			if(planModel == null ) {
//				System.out.println(" not found planModel ");
//				this.actionCode = "timeOver";
//			}
//			else { 
//				if(planModel.getStartDate().compareTo(new Date()) > 0) {
//					this.actionCode = "timeOver";
//					System.out.println(" in start date" + planModel.getStartDate());
//				}
//				else {
//					System.out.println(" out of start date" + planModel.getStartDate()); 
//				}
//		
//				if(planModel.getCloseDate().compareTo(new Date()) < 0) {
//					this.actionCode = "timeOver";
//					System.out.println(" in  close date" + planModel.getCloseDate());
//				}
//				else {
//					System.out.println(" out of close date" + planModel.getCloseDate()); 
//				}
//			}
//		} 
//		
//		/* 
//		 * 나의 평가직무정보를 찾는다.  
//		 */
//		Map<String, Object> jobManagerParam = new HashMap<String, Object>();
//		jobManagerParam.put("startId", evalTargetModel.getStartId()); 
//		jobManagerParam.put("jobCodeId", evalTargetModel.getJobCodeId());
//		jobManagerParam.put("baseDate", evalTargetModel.getBaseDate());
//		Pmx03_JobManagerModel jobManagerModel = sqlSession.selectOne("pmx03_job_mgr.selectByJobCodeId", jobManagerParam);
//		
//		/* 
//		 * 나의 직무정보를 찾는다. 상위직무가 없으면 대표이사 직무이다. 
//		 */
//		Map<String, Object> jobCodeParam = new HashMap<String, Object>();
//		jobCodeParam.put("jobCodeId", evalTargetModel.getJobCodeId());
//		jobCodeParam.put("baseDate", evalTargetModel.getBaseDate());
//		Job01_JobCodeModel jobCodeModel = sqlSession.selectOne("job01_job_code.selectByBaseDate", jobCodeParam);
//		
//		if(jobCodeModel.getJobInfoModel().getParentCodeId() == 0) {
//			// 대표이사 직무이다. 
//			return TopMbo(sqlSession, evalTargetModel); 
//		}
//		
//		/*
//		 * 상위자 평가 정보찾기 
//		 */
//		Map<String, Object> parentTargetParam = new HashMap<String, Object>();
//		parentTargetParam.put("startId", evalTargetModel.getStartId()); 
//		parentTargetParam.put("empId", evalTargetModel.getApprEmpId());
//		Pmx05_EvalTargetModel parentTargetModel = sqlSession.selectOne("pmx05_eval_target.selectByEmpId", parentTargetParam);
//		
//		
//		if(parentTargetModel ==  null ) {
//			return "상위 평가자 정보가 없어 업적목표 Matrix(Top-Down) 페이지를 조회할 수 없습니다." ;   
//		}
//		/*
//		 * 상위자의 평가직무 찾기  
//		 */
//		Map<String, Object> parentJobManagerParam = new HashMap<String, Object>();
//		
//		parentJobManagerParam.put("startId", parentTargetModel.getStartId()); 
//		parentJobManagerParam.put("jobCodeId", parentTargetModel.getJobCodeId());
//		parentJobManagerParam.put("baseDate", parentTargetModel.getBaseDate());
//		Pmx03_JobManagerModel parentJobManagerModel = sqlSession.selectOne("pmx03_job_mgr.selectByJobCodeId", parentJobManagerParam); 
//		
//		List<String> tableString = new ArrayList<String>();
//		
//		// 직무명을 한줄로 보여준다.   
//		String jobHeader 
//			= tdGrey("직무명", 10, "center") 
//			+ tdColSpanGrey(3, parentTargetModel.getJobInfoModel().getJobName(), 10, "center") 
//			+ tdColSpanGrey(4, evalTargetModel.getJobInfoModel().getJobName(), 10, "center") ; 
////			+ tdRowSpanGrey(3, "편집", 30, "center") ; 
//		tableString.add(jobHeader); 
//		
//		// 대표자 이름을 한줄로 보여준다.
//		String linkedString 
//		= this.setLink(evalTargetModel.getApprEmpModel().getKorName() + this.getApprStateName(parentTargetModel.getStep10_StateCode()), parentTargetModel.getEvalTargetId()) ;
//
//		
//		String empHeader 
//			= tdGrey("담당자", 10, "center")  
//			+ tdColSpanGrey(3, linkedString, 11, "center") ;
//		
//		linkedString 
//		= this.setLink(evalTargetModel.getTargetEmpModel().getKorName() + this.getApprStateName(evalTargetModel.getStep10_StateCode()), evalTargetModel.getEvalTargetId() ) ;		
//
//		empHeader += tdColSpanGrey(4, linkedString, 10, "center") ;   
//		
//		tableString.add(empHeader);
//
//		String titleHeader 
//		= tdGrey("No", 40, "center")
//		+ tdGrey("성과책임", 150, "center")
//		+ tdGrey("업적목표", 250, "center")
//		+ tdGrey("비중", 30, "center")
//		+ tdGrey("성과책임", 150, "center")
//		+ tdGrey("순서", 30, "center")
//		+ tdGrey("업적목표", 250, "center")
//		+ tdGrey("비중", 30, "center") ;
//		tableString.add(titleHeader);
//	
//		/*
//		 * 상위자의 성과책임 리스트 찾기. order by seq 
//		 */
//		Map<String, Object> parentRespParam = new HashMap<String, Object>();
//		parentRespParam.put("jobManagerId", parentJobManagerModel.getJobManagerId()); 
//		List<Pmx04_RespModel> parentRespList = sqlSession.selectList("pmx04_resp.selectByJobManagerId", parentRespParam) ; 
//
//		for(Pmx04_RespModel parentRespModel : parentRespList) {
//			
//			/*
//			 * 상위자의 성과책임별 MBO찾기 
//			 */
//			Map<String, Object> parentMboParam = new HashMap<String, Object>();
//			parentMboParam.put("evalTargetId", parentTargetModel.getEvalTargetId()); 
//			parentMboParam.put("respId", parentRespModel.getRespId());
//			List<Pmx06_MboModel> parentMboList = sqlSession.selectList("pmx06_mbo.selectByRespId", parentMboParam) ; 
//			
//			/*
//			 * 나의 성과책임 찾기. 
//			 */
//			Map<String, Object> myRespParam = new HashMap<String, Object>();
//			myRespParam.put("jobManagerId", jobManagerModel.getJobManagerId());
//			myRespParam.put("parentRespId", parentRespModel.getRespId());
//			List<Pmx04_RespModel> myRespList = sqlSession.selectList("pmx04_resp.selectByParentRespId", myRespParam);
//			
//			/*
//			 * 나의 업적목표 갯수 찾기 
//			 */
//			int myMboCount = 0; 
//			
//			for(Pmx04_RespModel respModel : myRespList) {
//				// 이거 MAP에 넣고 써도 되는데...
//				Map<String, Object> myMboParam = new HashMap<String, Object>();
//				myMboParam.put("respId", respModel.getRespId());
//				myMboParam.put("evalTargetId", evalTargetModel.getEvalTargetId());
//				List<Pmx06_MboModel> mboList = sqlSession.selectList("pmx06_mbo.selectByRespId", myMboParam);
//				myMboCount += mboList.size(); 
//			}
//			
//			int maxRowCount = ((parentMboList.size() < 1) ? 1 : parentMboList.size()) 
//					*  ((myRespList.size() < 1) ? 1 : myRespList.size()) 
//					*  ((myMboCount < 1) ? 1 : myMboCount) ; 
//			
//			List<String> rowStringList = new ArrayList<String>();
//			for(int i = 0; i<maxRowCount; i++) {
//				rowStringList.add(""); // 라인개수 만큼 미리 만들어 둔다.
//			}
//			
//			/* 
//			 * 한줄씩 넣기
//			 */
//			
//			/*
//			 *  1. 순서 & 상위성과책임  
//			 *   - 한줄에 한번만 나온다. rowSpan을 전체 Count로 한다. 
//			 */
//			String parentRespString = ""; 
//			parentRespString += this.tdRowSpan(maxRowCount, parentRespModel.getSeq(), 10, "center") ;    
//			parentRespString += this.tdRowSpan(maxRowCount, parentRespModel.getRespName(), 10, "left") ; 
//			rowStringList.set(0, parentRespString);
//			
//			/* 
//			 * 2. 상위자의 MBO 가져오기 
//			 */
//			if(parentMboList.size()< 1) {
//				// 등록된 상위자의 MBO가 없다. 
//				parentRespString += this.tdRowSpan(maxRowCount, "", 10, "left") ; 	// 목표
//				parentRespString += this.tdRowSpan(maxRowCount, "", 10, "center") ; // 비중 
//				rowStringList.set(0, parentRespString); // replace 1st String 
//			}
//			else {
//				
//				// step count 
//				int parentMboRowSpan = maxRowCount/ parentMboList.size(); 
//				
//				// 2. 상위 업적목표가 있다.   
//				for(int parentMboIndex = 0; parentMboIndex < parentMboList.size(); parentMboIndex++) {
//					
//					Pmx06_MboModel parentMboModel = parentMboList.get(parentMboIndex); 
//
//					// 행의 위치 찾기, 건너뛰면서 넣는다. 
//					int parentMboPos = parentMboRowSpan * parentMboIndex ;
//					
//					String parentMboString 
//						= rowStringList.get(parentMboPos)  
//						+ this.tdRowSpan(parentMboRowSpan, parentMboModel.getTargetName(), 10, "left")		// 목표
//						+ this.tdRowSpan(parentMboRowSpan, nullString(parentMboModel.getRatio()), 10, "center");	// 비중 
//					
//					String ratio = parentMboModel.getRatio() ; 
//					if(ratio == null) {
//						ratio = "0"; 
//					}
//					else {
//						ratio = ratio.replace("%", ""); 
//						parentRatioSum = parentRatioSum + Integer.parseInt(ratio); 
//					}
//					rowStringList.set(parentMboPos, parentMboString); 
//				}
//			}
//			
//			if(myRespList.size() < 1) {
//				//나의 성과책임이 하나도 없다, MBO도 없다. 
//				String myRespString  = rowStringList.get(0)				// 첫줄 가져오기    
//					+ this.tdRowSpan(maxRowCount, "", 10, "left")		// 성과책임명
//					+ this.tdRowSpan(maxRowCount, "", 10, "center")	// MBO.순서
//					+ this.tdRowSpan(maxRowCount, "", 10, "center")	// MBO.업적목표
//					+ this.tdRowSpan(maxRowCount, "", 10, "center")	// MBO.비중
//					; 
////					+ this.tdRowSpan(maxRowCount, "", 10, "center");	// 편집버튼. 
//					rowStringList.set(0, myRespString); // 첫줄에 더한다.  
//			}
//			else {
//				// 3. 나의 성과책임등록하기
//   
//				// 한줄의 높이를 구한다. 
//				int myRespRowSpan = maxRowCount / myRespList.size() ; /// ((myMboCount < 1)?1:myMboCount) ;
//
//				for(int myRespIndex=0; myRespIndex < myRespList.size(); myRespIndex++) {
//					
//					Pmx04_RespModel myRespModel = myRespList.get(myRespIndex); 
//
//					Map<String, Object> myMboParam = new HashMap<String, Object>();
//					myMboParam.put("respId", myRespModel.getRespId());
//					myMboParam.put("evalTargetId", evalTargetModel.getEvalTargetId());
//					List<Pmx06_MboModel> myMboList = sqlSession.selectList("pmx06_mbo.selectByRespId", myMboParam);
//
//					int myRespPos = myRespRowSpan * myRespIndex ; // List<String>에서의 위치 설정.
//					
//					String myRespString  = rowStringList.get(myRespPos) // 이전 텍스트 가져오기 		     
//							+ this.tdRowSpan(myRespRowSpan, myRespModel.getRespName() + this.addMbo(myRespModel.getRespId()), 10, "left")
//							; //추가버튼 추가 
//					
//					rowStringList.set(myRespPos, myRespString); // 첫줄 세팅.
//					
//					if(myMboList.size() < 1) {
//						myRespString = rowStringList.get(myRespPos) 
//							+ this.tdRowSpan(myRespRowSpan, "", 10, "center")  // MBO.순서
//							+ this.tdRowSpan(myRespRowSpan, "", 10, "center")  // MBO.업적목표
//							+ this.tdRowSpan(myRespRowSpan, "", 10, "center")  // MBO.비중
////							+ this.tdRowSpan(myRespRowSpan, "", 10, "center") // 편집버튼  
//							; 
//							//+ this.tdRowSpan(myRespRowSpan, this.addMbo(myRespModel.getRespId()), 10, "center") ; // 추가버튼
//						rowStringList.set(myRespPos, myRespString); // 첫줄 세팅.
//					}
//					else {
//						
//						int myMboRowSpan = myRespRowSpan / myMboList.size(); 
//						int remainder = myRespRowSpan % myMboList.size();
//						
//						for(int myMboIndex = 0; myMboIndex < myMboList.size(); myMboIndex++ ) {
//							
//							int myMboPos = myRespPos + (myMboRowSpan * myMboIndex); 
//							
//							if(myMboIndex == myMboList.size()-1 ) {
//								myMboRowSpan += remainder; 
//							}
//								
//							Pmx06_MboModel myMboModel = myMboList.get(myMboIndex); 
//							
//							String myMboString  = rowStringList.get(myMboPos) 		// 첫줄 가져오기    
//									+ this.tdRowSpan(myMboRowSpan, nullString(myMboModel.getSeq()), 10, "left")  // MBO.순서
//									+ this.tdRowSpan(myMboRowSpan, nullString(myMboModel.getTargetName()) + this.edit(myMboModel.getMboId()), 10, "left")  // MBO.업적목표
//									+ this.tdRowSpan(myMboRowSpan, nullString(myMboModel.getRatio() ), 10, "center")  // MBO.비중
////									+ this.tdRowSpan(myMboRowSpan, this.edit(myMboModel.getMboId()), 10, "center") ; // 편집버튼
//									; 
//							
//							String ratio = myMboModel.getRatio() ; 
//							if(ratio == null) {
//								ratio = "0"; 
//							}
//							else {
//								ratio = ratio.replace("%", ""); 
//								myRatioSum = myRatioSum + Integer.parseInt(ratio); 
//							}
//							
//							rowStringList.set(myMboPos, myMboString);
//						}
//					}
//					
//					myRespPos += myRespRowSpan; 
//				}
//			}
//			
//			// 전체 취합하기. 
//			tableString.addAll(rowStringList); 
//		}

		String htmlString = javaScript() 
				+ " <table id='mboTopDownTable' border=1 style='width:99%; margin:10px; font-size:12px; border-collapse:collapse; border:1px silver solid; padding:0px;'>"; 
		
//		for(int i=0; i < tableString.size(); i++) {
//			htmlString += this.tr(tableString.get(i)); 
//		}

		
// 비중합계 넣어준다. 		
		String ratioSum  
		= tdGrey("합계", 10, "center") 
		+ tdColSpanGrey(3, parentRatioSum + "%", 10, "center") 
		+ tdColSpanGrey(5, myRatioSum + "%", 10, "center");  
		
		
		htmlString += ratioSum + "</table>" ;  
		
		if("timeOver".equals(this.actionCode)) {
			htmlString += "<div style='font-size:11px; text-align:center'>업적목표 Matrix 작성기간이 아닙니다. 담당자에게 문의하시기 바랍니다</div>" ;
		}
		
		return htmlString; 

	}

//	private String TopMbo(SqlSession sqlSession, Pmx05_EvalTargetModel evalTargetModel) {
//		
//		/*
//		 *  대표이사 업적목표는 별도로 등록한다. 
//		 */
//		
//		List<String> tableString = new ArrayList<String>();
//		
//		// 직무명을 한줄로 보여준다.   
//		String jobHeader 
//			= tdGrey("직무명", 60, "center") 
//			+ tdColSpanGrey(4, evalTargetModel.getJobInfoModel().getJobName(), 1070, "center") 
//			+ tdRowSpanGrey(3, "추가", 60, "center") ; 
//		tableString.add(jobHeader); 
//		
//		String linkedString   
//				= this.setLink(evalTargetModel.getTargetEmpModel().getKorName() + this.getApprStateName(evalTargetModel.getStep10_StateCode()), evalTargetModel.getEvalTargetId()) ;
//		
//		// 대표자 이름을 한줄로 보여준다. 
//		String empHeader 
//			= tdGrey("담당자", 50, "center")  
//			+ tdColSpanGrey(4, linkedString, 1070, "center") ;   
//		tableString.add(empHeader);
//
//		String titleHeader 
//		= tdGrey("No.", 50, "center")
//		+ tdGrey("성과책임", 450, "center")
//		+ tdGrey("정렬<br>순서", 60, "center")
//		+ tdGrey("업적목표", 600, "center")
//		+ tdGrey("비중", 60, "center") ; 
//		tableString.add(titleHeader);
//
//		/* 
//		 * 평가직무정보를 찾는다.  
//		 */
//		Map<String, Object> jobManagerParam = new HashMap<String, Object>();
//		jobManagerParam.put("startId", evalTargetModel.getStartId()); 
//		jobManagerParam.put("jobCodeId", evalTargetModel.getJobCodeId());
//		jobManagerParam.put("baseDate", evalTargetModel.getBaseDate());
//		Pmx03_JobManagerModel jobManagerModel = sqlSession.selectOne("pmx03_job_mgr.selectByJobCodeId", jobManagerParam);
//
//		/*
//		 * 성과책임 찾기. 
//		 */
//		Map<String, Object> respParam = new HashMap<String, Object>();
//		respParam.put("jobManagerId", jobManagerModel.getJobManagerId());
//		respParam.put("parentRespId", Long.parseLong("0"));
//		List<Pmx04_RespModel> respList = sqlSession.selectList("pmx04_resp.selectByParentRespId", respParam);
//
//		/* 
//		 * 업적목표 개수 확인  
//		 */
//		long ratioSum = 0;
//		
//		for(int respIndex=0; respIndex<respList.size(); respIndex++) {
//
//			Pmx04_RespModel respModel = respList.get(respIndex); 
//			
//			Map<String, Object> mboParam = new HashMap<String, Object>();
//			mboParam.put("respId", respModel.getRespId());
//			mboParam.put("evalTargetId", evalTargetModel.getEvalTargetId());
//			List<Pmx06_MboModel> mboList = sqlSession.selectList("pmx06_mbo.selectByRespId", mboParam);
//			
//			if(mboList.size() < 1) {
//				
//				String rowString = "" ;  
//				rowString += this.tdCenter(respModel.getSeq(), 60) ;  // 번호
//				rowString += this.tdRowSpan(1, respModel.getRespName(), 450, "left");
//				rowString += this.tdCenter("", 60) ; // 정렬순서
//				rowString += this.tdCenter("", 600) ; // 업적목표
//				rowString += this.tdCenter("", 60) ; // 비중
//				rowString += this.tdRowSpan(1, this.addMbo(respModel.getRespId()), 60, "center") ;
//				
//				tableString.add(rowString); 
//				
//			}
//			else {
//				for(int mboIndex=0; mboIndex<mboList.size(); mboIndex++) {
// 
//					String rowString = "" ;
//					Pmx06_MboModel mboModel = mboList.get(mboIndex); 
//							
//					if(mboIndex == 0) {
//						rowString += this.tdRowSpan(mboList.size(), respModel.getSeq(), 60, "center");
//						rowString += this.tdRowSpan(mboList.size(), respModel.getRespName(), 450, "left");
//						rowString += this.tdCenter(mboModel.getSeq(), 60) ;  // 정렬순서
//						rowString += this.tdData(nullString(mboModel.getTargetName()) + this.edit(mboModel.getMboId()), 600) ; // 업적목표
//						rowString += this.tdCenter(nullString(mboModel.getRatio()), 60) ; // 비중
//						rowString += this.tdRowSpan(mboList.size(), this.addMbo(respModel.getRespId()), 60, "center") ;
//					}
//					else {
//						rowString += this.tdCenter(mboModel.getSeq(), 60) ;  // 정렬순서
//						rowString += this.tdData(nullString(mboModel.getTargetName()) + this.edit(mboModel.getMboId()), 600) ; // 업적목표
//						rowString += this.tdCenter(nullString(mboModel.getRatio()), 60) ; // 비중
//					}
//
//					tableString.add(rowString);
//
//					// 합계 비중 구하기 
//					String ratio = mboModel.getRatio() ; 
//					if(ratio == null) {
//						ratio = "0"; 
//					}
//					else {
//						ratio = ratio.replace("%", ""); 
//						ratioSum = ratioSum + Integer.parseInt(ratio); 
//					}
//					
//				}
//			}
//		}
//
//		String htmlString  
//			= javaScript() 
//			+ "<table id='mboTopDownTable'  border=1 style='margin:10px; font-size:12px; border-collapse:collapse; border:1px silver solid; padding:0px;'>"; 
//		
//		for(int i=0; i < tableString.size(); i++) {
//			htmlString += this.tr(tableString.get(i)); 
//		}
//		
//		String ratioSumString  
//		= tdColSpanGrey(4, "비중 합계", 10, "center") 
//		+ tdColSpanGrey(1, ratioSum + "%", 10, "center")   
//		+ tdData("", 10) ;   
//		
//		htmlString += this.tr(ratioSumString); 
//		
//		return htmlString + "</table>" ; 
//	}
	
	private String tr(String data) {
		return "<tr style='height:auto;'>" + data + "</tr>"; 
	}

	private String tdData(String data, int width) {
		return "<td style='width:" + width + "px; padding:5px; height:auto; word-wrap:break-word;' >" + data + "</td>" ; 
	}

	private String tdCenter(String data, int width) {
		return "<td style='text-align:center; width:" + width + "px; padding:5px; height:auto; word-wrap:break-word;' >" + data + "</td>" ; 
	}

	private String tdRowSpan(int rowSpan, String data, int width, String align) {
		
		int rs = rowSpan; 
		if(rs<1) rs = 1 ;
		
		return "<td rowspan=" + rs 
				+ " style='text-align:" + align + "; width:" + width 
				+ "px; padding:5px; height:auto; word-wrap:break-word;' >" + data 
				+ "</td>" ; 
	}
	
	private String tdGrey(String data, int width, String align) {
		return "<td bgcolor='#ebebec' style='text-align:" + align + "; width:" + width+ "px; "
				+ " padding:5px; height:auto; word-wrap:break-word;' >" + data 
				+ "</td>" ; 
	}

	private String tdColSpanGrey(int colSpan, String data, int width, String align) {
		return "<td bgcolor='#ebebec' colSpan =" + colSpan
				+ " style='text-align:" + align + "; width:" + width + "px; "
				+ "padding:5px; height:auto; word-wrap:break-word;' >" + data 
				+ "</td>" ; 
	}

	
	private String tdRowSpanGrey(int rowSpan, String data, int width, String align) {
		int rs = rowSpan; 
		if(rs<1) rs = 1 ;
	
		return "<td bgcolor='#ebebec' rowspan=" + rs 
				+ " style='text-align:" + align + "; width:" + width 
				+ "px; padding:5px; height:auto; word-wrap:break-word;' >" + data 
				+ "</td>" ; 
	}
	
	private String edit(Long editId) {
		if("edit".equals(this.actionCode) || "admin".equals(this.actionCode)) {
			return "&nbsp; <a href=\"javascript:editMbo(" + editId + ")\"><img style='vertical-align:bottom;' height='14' width=14' src='img/gray_plus_1Uo_12.ico'/></a>" ;	
		}
		else if("myRetrieve".equals(this.actionCode)) {
			return "&nbsp; <a href=\"javascript:viewMbo(" + editId + ")\"><img style='vertical-align:bottom;' height='14' width=14' src='img/gray_plus_1Uo_12.ico'/></a>" ; 
		}
		else {
			return ""; 
		}
		 
	}

	private String addMbo(Long respId) {
		if("edit".equals(this.actionCode) || "admin".equals(this.actionCode)) {
			return "&nbsp; <a href=\"javascript:addMbo(" + respId + ")\"><img style='vertical-align:top;' height='16' width=16' src='img/add_icon.png'/></a>" ;
		}
		else {
			return ""; 
		}
	}
	
	private String setLink(String empName, Long evalTargetId) {
		return empName + "&nbsp; <a href=\"javascript:openMboMatrix(" + evalTargetId + ");\"><img style='vertical-align:top;' health='16' width=16' src='img/add_icon.png'/></a>" ;
		
//		return "<a href=\"javascript:openBottomUp(" + jobManagerId + ");\">" + jobName + "</a>" ;
	}

	private String editMemo(String targetName, Long mboId, boolean isMemo ) {
		
		if(isMemo) {
			// 메모가 있으면
//			return respName + "&nbsp; <a href=\"javascript:respMemo(" + respId + ");\"><img style='vertical-align:bottom;' health='14' width=14' src='img/gray_plus_1Uo_12.ico'/></a>" ;
			return "<a href=\"javascript:editMboMemo(" + mboId + ");\">" + targetName + "</a>" ; 
		}
		else {
			return targetName + "&nbsp; <a href=\"javascript:editMboMemo(" + mboId + ");\"> [Note] </a>" ;
		}
//		return respName + "&nbsp; <a href=\"javascript:respMemo(" + respId + ");\"><img style='vertical-align:bottom;' health='14' width=14' src='img/gray_plus_1Uo_12.ico'/></a>" ;
//		return "<a href=\"javascript:respMemo(" + respId + ");\">" + respName + "</a>" ;
	}
	
	private String javaScript() {
		String htmlScript 
		= "<script> " 
		+ "function addMbo(respId) { "
			+ " var call = new window.parent.myApp.client.vi.pmx.Pmx06_MboJSCaller.addMboCall(respId); "
			+ " call(respId); "
		+ "} ; "
		
		+ "function editMbo(mboId) { "
			+ " var call = new window.parent.myApp.client.vi.pmx.Pmx06_MboJSCaller.editMboCall(mboId); "
			+ " call(mboId); "
		+ "} ; "
		
		+ "function viewMbo(mboId) { "
		+ " var call = new window.parent.myApp.client.vi.pmx.Pmx06_MboJSCaller.viewMboMemoCall(mboId); "
		+ " call(mboId); "
		+ "} ; "

		+ "function openMboMatrix(evalTargetId) { "
			+ " var call = new window.parent.myApp.client.vi.pmx.Pmx06_MboJSCaller.openMboMatrixCall(evalTargetId); "
			+ " call(evalTargetId); "
		+ "} ; "

		+ "function editMboMemo(mboId) { "
			+ " var call = new window.parent.myApp.client.vi.pmx.Pmx06_MboJSCaller.editMboMemoCall(mboId); "
			+ " call(mboId); "
		+ "} ; "
		+ "function mboTopDownToExcel() { "
		+ " parent.parent.fnExcelReport('mboTopDownIFrame', 'mboTopDownTable'); "
		+ "} ; \r\n"
		
		+ "function mboBottomUpToExcel() { "
		+ " parent.parent.fnExcelReport('mboBottomUpIFrame', 'mboBottomUpTable'); "
		+ "} ; \r\n"

		+ " </script> " ; 
		
		return htmlScript ; 
		
	}
	
	// 최소공배수 구하기 함수. 
	 public static int lcm(int a, int b) {
		 int gcd_value = gcd((int)a, (int)b);
		 if (gcd_value == 0) return 0; // 인수가 둘다 0일 때의 에러 처리
		 return Math.abs( (a * b) / gcd_value );
	 }
		
	 public static int gcd(int a, int b) {
		 while (b != 0) {
			 int temp = a % b;
			 a = b;
			 b = temp;
		 }
		 return Math.abs(a);
	 }
		 
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		arg1.setContentType("text/html");
		arg1.setCharacterEncoding("UTF-8");
		
		this.actionCode = arg0.getParameter("actionCode");
		String empId = arg0.getParameter("empId");
		String evalTargetId = arg0.getParameter("evalTargetId");
		
		System.out.println("actionCode : " + actionCode);
		System.out.println("evalTargetId : " + evalTargetId);
		System.out.println("empId : "  + empId);
		
		String returnHtml = "";
		
		if("myRetrieve".equals(actionCode) || "retrieve".equals(actionCode) || "edit".equals(actionCode) || "admin".equals(actionCode)) {
			returnHtml = this.mboTopDown(Long.parseLong(evalTargetId)); 
		}
		if("mboBottomUp".equals(actionCode)) {
			returnHtml = this.mboBottomUp(Long.parseLong(evalTargetId), empId); 
		}
		
		PrintWriter out = arg1.getWriter();
		out.println(returnHtml);
		
	}
	@Override
	public void destroy() { 
	}
	@Override
	public ServletConfig getServletConfig() {
		return null;
	}
	@Override
	public String getServletInfo() {
		return null;
	}
	@Override
	public void init(ServletConfig arg0) throws ServletException {
	}
}
