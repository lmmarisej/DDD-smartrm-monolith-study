package com.smartrm.smartrmmonolith.commodity.adapter.convertor.plaintext;

import com.smartrm.smartrmmonolith.commodity.adapter.convertor.CommodityPropertyParser;
import com.smartrm.smartrmmonolith.commodity.domain.model.ImageUrl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.xml.sax.Attributes;

/**
 * @author: yoda
 * @description:
 */
public class ImageUrlPropertyTextParser implements
        CommodityPropertyParser<ImageUrl, String> {
    
    private Attributes attrs;
    
    public ImageUrlPropertyTextParser(Attributes attrs) {
        this.attrs = attrs;
    }
    
    @Override
    public ImageUrl parse(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        ImageUrl imageUrl = new ImageUrl();
        imageUrl.setUrl(StringUtils.trimToNull(content));
        
        if (attrs != null) {
            Integer width = NumberUtils.createInteger(attrs.getValue("width"));
            Integer height = NumberUtils.createInteger(attrs.getValue("height"));
            Integer fileSize = NumberUtils.createInteger(attrs.getValue("file-size"));
            String fileMd5 = StringUtils.trimToNull(attrs.getValue("file-md5"));
            imageUrl.setWidth(width);
            imageUrl.setHeight(height);
            imageUrl.setFileSize(fileSize);
            imageUrl.setFileMd5(fileMd5);
        }
        
        return imageUrl;
    }
}
