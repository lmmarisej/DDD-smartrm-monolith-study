package com.smartrm.smartrmmonolith.commodity.domain.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * 检查
 */
public class Check {
    
    /**
     * 黑词
     */
    private boolean blackWords;
    
    @XmlAttribute
    public boolean isBlackWords() {
        return blackWords;
    }
    
    public void setBlackWords(boolean blackWords) {
        this.blackWords = blackWords;
    }
    
    @Override
    public String toString() {
        return "Check{" +
                "blackWords=" + blackWords +
                '}';
    }
}
