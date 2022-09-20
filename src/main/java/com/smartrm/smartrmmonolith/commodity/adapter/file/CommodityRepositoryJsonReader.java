package com.smartrm.smartrmmonolith.commodity.adapter.file;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.smartrm.smartrmmonolith.commodity.adapter.convertor.PropertyConvertor;
import com.smartrm.smartrmmonolith.commodity.domain.model.Commodity;
import com.smartrm.smartrmmonolith.commodity.domain.model.CommoditySchema;
import com.smartrm.smartrmmonolith.commodity.domain.model.PropertySchema;
import com.smartrm.smartrmmonolith.commodity.domain.repository.CommodityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * @author: yoda
 * @description: 从json数据文件加载资源库
 */
@Component
public class CommodityRepositoryJsonReader {
    
    private static Logger LOGGER = LoggerFactory.getLogger(CommodityRepositoryJsonReader.class);
    @Value("${commodity.file.json.path}")
    private String commodityJsonFilePath;
    @Autowired
    @Qualifier("jsonPropertyConvertor")
    private PropertyConvertor convertor;
    @Autowired
    private CommoditySchema schema;
    @Autowired
    private CommodityRepository commodityRepository;
    
    @PostConstruct
    public void load() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FileInputStream istream = new FileInputStream(commodityJsonFilePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(istream, "UTF-8"));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            ObjectNode obj = (ObjectNode) (mapper.readTree(line));
            Iterator<String> fields = obj.fieldNames();
            Commodity commodity = new Commodity(schema);
            while (fields.hasNext()) {
                String field = fields.next();
                PropertySchema propertySchema = schema.getPropertySchema(field);
                if (propertySchema != null) {
                    JsonNode node = obj.get(field);
                    LOGGER.info("field:{}, value:{}", field, node.toString());
                    if (node.isArray()) {
                        ArrayNode array = (ArrayNode) node;
                        for (int i = 0; i < array.size(); i++) {
                            commodity.setValue(field,
                                    this.convertor.parser(propertySchema.getValueType()).parse(array.get(i)));
                        }
                    } else {
                        commodity.setValue(field,
                                this.convertor.parser(propertySchema.getValueType()).parse(node));
                    }
                    
                }
            }
            commodityRepository.put(commodity);
            
        }
    }
    
}
