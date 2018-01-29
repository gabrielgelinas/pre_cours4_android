package com.example.gggab.pre_cours4_android;

public class Product {
    public static int cnt;
    private int ProductId;
    private String Name;
    private String BrandName;
    private Boolean IsAgeRequired;
    private String SizeLabel;
    private String Size;
    private String ProductUrl;
    private String ProductImageUrl;
    private Boolean HasNewPrice;
    private Double RegularPrice;
    private String PromotionName;
    private Double SalesPrice;

    Product() {
        cnt++;
    }

    Product(String name, Double regularPrice) {
        Name=name;
        RegularPrice=regularPrice;
        cnt++;
    }

    Product(int productId, String name, String brandName, Boolean isAgeRequired, String sizeLabel, String size, String productUrl, String productImageUrl, Boolean hasNewPrice, Double regularPrice, String promotionName, Double salesPrice) {

        setProductId(productId);
        setName(name);
        setBrandName(brandName);
        setAgeRequired(isAgeRequired);
        setSizeLabel(sizeLabel);
        setSize(size);
        setProductUrl(productUrl);
        setProductImageUrl(productImageUrl);
        setHasNewPrice(hasNewPrice);
        setRegularPrice(regularPrice);
        setPromotionName(promotionName);
        setSalesPrice(salesPrice);
        cnt++;
    }

    public int getProductId() {
        return ProductId;
    }

    private void setProductId(int productId) {
        ProductId = productId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBrandName() {
        return BrandName;
    }

    private void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public Boolean getAgeRequired() {
        return IsAgeRequired;
    }

    private void setAgeRequired(Boolean ageRequired) {
        IsAgeRequired = ageRequired;
    }

    public String getSizeLabel() {
        return SizeLabel;
    }

    private void setSizeLabel(String sizeLabel) {
        SizeLabel = sizeLabel;
    }

    public String getSize() {
        return Size;
    }

    private void setSize(String size) {
        Size = size;
    }

    public String getProductUrl() {
        return ProductUrl;
    }

    private void setProductUrl(String productUrl) {
        ProductUrl = productUrl;
    }

    public String getProductImageUrl() {
        return ProductImageUrl;
    }

    private void setProductImageUrl(String productImageUrl) {
        ProductImageUrl = productImageUrl;
    }

    public Boolean getHasNewPrice() {
        return HasNewPrice;
    }

    private void setHasNewPrice(Boolean hasNewPrice) {
        HasNewPrice = hasNewPrice;
    }

    Double getRegularPrice() {
            return RegularPrice;
    }

    private void setRegularPrice(Double regularPrice) {
        RegularPrice = regularPrice;
    }

    public String getPromotionName() {
        return PromotionName;
    }

    private void setPromotionName(String promotionName) {
        PromotionName = promotionName;
    }

    public Double getSalesPrice() {
        return SalesPrice;
    }

    private void setSalesPrice(Double salesPrice) {
        SalesPrice = salesPrice;
    }
}
