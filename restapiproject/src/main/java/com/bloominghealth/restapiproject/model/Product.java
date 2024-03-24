package com.bloominghealth.restapiproject.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product_vendor_info")
@ApiModel(description = "This table holds product information.")
public class Product
{
    @Id
    @ApiModelProperty(notes="This is a Product Id. It shall be unique.")
    private String productVendorId;
    private String productVendorName;
    private String productVendorAddress;
    private String productVendorPhoneNumber;

    public Product() {
    }

    public Product(String productVendorId, String productVendorName, String productVendorAddress, String productVendorPhoneNumber) {
        this.productVendorId = productVendorId;
        this.productVendorName = productVendorName;
        this.productVendorAddress = productVendorAddress;
        this.productVendorPhoneNumber = productVendorPhoneNumber;
    }

	public String getProductVendorId() {
		return productVendorId;
	}

	public void setProductVendorId(String productVendorId) {
		this.productVendorId = productVendorId;
	}

	public String getProductVendorName() {
		return productVendorName;
	}

	public void setProductVendorName(String productVendorName) {
		this.productVendorName = productVendorName;
	}

	public String getProductVendorAddress() {
		return productVendorAddress;
	}

	public void setProductVendorAddress(String productVendorAddress) {
		this.productVendorAddress = productVendorAddress;
	}

	public String getProductVendorPhoneNumber() {
		return productVendorPhoneNumber;
	}

	public void setProductVendorPhoneNumber(String productVendorPhoneNumber) {
		this.productVendorPhoneNumber = productVendorPhoneNumber;
	}
    
}
