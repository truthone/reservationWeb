package kr.or.connect.production.dto;

public class Product {
  private String productDescription;
  private int categoryId;
  private int productId;
  private String productContent;
  private String placeName;
  private String productImageUrl;
  private int displayInfoId;

  public int getDisplayInfoId() {
    return displayInfoId;
  }

  public void setDisplayInfoId(int displayInfoId) {
    this.displayInfoId = displayInfoId;
  }

  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categorId) {
    this.categoryId = categoryId;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public String getProductContent() {
    return productContent;
  }

  public void setProductContent(String productContent) {
    this.productContent = productContent;
  }

  public String getPlaceName() {
    return placeName;
  }

  public void setPlaceName(String placeName) {
    this.placeName = placeName;
  }

  public String getProductImageUrl() {
    return productImageUrl;
  }

  public void setProductImageUrl(String productImageUrl) {
    this.productImageUrl = productImageUrl;
  }
}
