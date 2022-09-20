package com.smartrm.smartrmmonolith.payment.application.dto;

/**
 * @author: yoda
 * @description:
 */
public class WxResourceDto {
    
    private String algorithm;
    private String ciphertext;
    private String originalType;
    private String nonce;
    
    public String getAlgorithm() {
        return algorithm;
    }
    
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
    
    public String getCiphertext() {
        return ciphertext;
    }
    
    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }
    
    public String getOriginalType() {
        return originalType;
    }
    
    public void setOriginalType(String originalType) {
        this.originalType = originalType;
    }
    
    public String getNonce() {
        return nonce;
    }
    
    public void setNonce(String nonce) {
        this.nonce = nonce;
    }
}
