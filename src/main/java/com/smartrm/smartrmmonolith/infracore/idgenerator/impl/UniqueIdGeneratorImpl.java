package com.smartrm.smartrmmonolith.infracore.idgenerator.impl;

import com.smartrm.smartrmmonolith.infracore.idgenerator.UniqueIdGenerator;
import com.smartrm.smartrmmonolith.infracore.idgenerator.impl.mapper.UniqueIdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yoda
 * @description:
 */
@Service
public class UniqueIdGeneratorImpl implements UniqueIdGenerator {
    
    @Autowired
    UniqueIdMapper mapper;
    
    @Override
    public long next() {
        UniqueIdDo id = new UniqueIdDo();
        mapper.nextUniqueId(id);
        return id.getId();
    }
}
