package in.co.rays.project0.form;

import in.co.rays.project0.dto.BaseDTO;

public class BaseForm {

	
	protected long id=0;
    
	private int pageNo=1;
	
	
	private Long[] chk_1;
	
	
	private int pageSize=5;
	
	
	private String operation;
	
	
	protected String createdBy;
	
	
	protected String modifiedBy;
	
	
	protected long createdDateTime;
	
	
	protected long modifiedDateTime;
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public Long[] getChk_1() {
		return chk_1;
	}


	public void setChk_1(Long[] chk_1) {
		this.chk_1 = chk_1;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public String getOperation() {
		return operation;
	}


	public void setOperation(String operation) {
		this.operation = operation;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public long getCreatedDateTime() {
		return createdDateTime;
	}


	public void setCreatedDateTime(long createdDateTime) {
		this.createdDateTime = createdDateTime;
	}


	public long getModifiedDateTime() {
		return modifiedDateTime;
	}


	public void setModifiedDateTime(long modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}


	


    public BaseDTO getDto() {
        return null;
    }

   
    public void populate(BaseDTO bDto) {

    }
    



	
	
	
	
}
