package kr.ac.kopo.model;

public class Address {
	private Long id;
	private Long custId;
	private Long apiId;
	private Integer postcode;
	private String roadaddress;
	private String jibunaddress;
	private String detailaddress;
	private String extraaddress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Long getApiId() {
		return apiId;
	}

	public void setApiId(Long apiId) {
		this.apiId = apiId;
	}

	public Integer getPostcode() {
		return postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

	public String getRoadaddress() {
		return roadaddress;
	}

	public void setRoadaddress(String roadaddress) {
		this.roadaddress = roadaddress;
	}

	public String getJibunaddress() {
		return jibunaddress;
	}

	public void setJibunaddress(String jibunaddress) {
		this.jibunaddress = jibunaddress;
	}

	public String getDetailaddress() {
		return detailaddress;
	}

	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}

	public String getExtraaddress() {
		return extraaddress;
	}

	public void setExtraaddress(String extraaddress) {
		this.extraaddress = extraaddress;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", custId=" + custId + ", apiId=" + apiId + ", postcode=" + postcode
				+ ", roadaddress=" + roadaddress + ", jibunaddress=" + jibunaddress + ", detailaddress=" + detailaddress
				+ ", extraaddress=" + extraaddress + "]";
	}

}
