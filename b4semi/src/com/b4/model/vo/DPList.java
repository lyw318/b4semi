package com.b4.model.vo;

import java.sql.Timestamp;

//DB에서 보여줄 데이터를 가져오기 위한 클래스
public class DPList {
	
	private int displayListSeq;
	private String displayListTitle;	
	private String dpListAvailable;
	private Timestamp displayDate;
	private String img; //판매상품 게시글의 메인 사진 파일명
	private int minPrice; //판매 옵션들 중 최저 가격, 할인 미적용된 값
	private double discountRate; //할인률, 단위는 가격대비율
	private String reviewScore; //평균리뷰점수, DB에서 가져온 소수점 두자리를 그대로 표현해주기 위해 String으로 저장
	private String productUnit; //상품 단위
	private int optionCount; //세부옵션 수
	private String productCode; // 주의 - 세부옵션이 하나일때 이용해야 합니다
	private int discountMinPrice; //할인 가격
	private int reviewCount; // 작성된 리뷰수
	
	public DPList () {}
	
	public int getDisplayListSeq() {
		return displayListSeq;
	}
	public void setDisplayListSeq(int displayListSeq) {
		this.displayListSeq = displayListSeq;
	}
	public String getDisplayListTitle() {
		return displayListTitle;
	}
	public void setDisplayListTitle(String displayListTitle) {
		this.displayListTitle = displayListTitle;
	}
	public String getDpListAvailable() {
		return dpListAvailable;
	}
	public void setDpListAvailable(String dpListAvailable) {
		this.dpListAvailable = dpListAvailable;
	}
	public Timestamp getDisplayDate() {
		return displayDate;
	}
	public void setDisplayDate(Timestamp displayDate) {
		this.displayDate = displayDate;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	public double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}
	public String getReviewScore() {
		return reviewScore;
	}
	public void setReviewScore(String reviewScore) {
		this.reviewScore = reviewScore;
	}
	public int getOptionCount() {
		return optionCount;
	}
	public void setOptionCount(int optionCount) {
		this.optionCount = optionCount;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getDiscountMinPrice() {
		return discountMinPrice;
	}
	public void setDiscountMinPrice(int discountMinPrice) {
		this.discountMinPrice = discountMinPrice;
	}
	public void setDiscountMinPrice() {
		this.discountMinPrice = (int)Math.round(minPrice*(1-discountRate)/10)*10;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	
}