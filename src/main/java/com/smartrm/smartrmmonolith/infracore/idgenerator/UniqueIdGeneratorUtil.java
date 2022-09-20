package com.smartrm.smartrmmonolith.infracore.idgenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: yoda
 * @description: 唯一id生成器util
 */
@Component
public class UniqueIdGeneratorUtil {
    
    private static UniqueIdGeneratorUtil instance;
    
    @Autowired(required = false)
    private UniqueIdGenerator generator;
    
    public static UniqueIdGeneratorUtil instance() {
        return instance;
    }
    
    @PostConstruct
    private void init() {
        instance = this;
    }
    
    public long nextId() {
        return generator.next();
    }
}
