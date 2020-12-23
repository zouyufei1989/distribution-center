package com.money.h5.entity.response;

import com.money.custom.entity.Banner;
import com.money.framework.base.entity.ResponseBase;
import com.money.h5.entity.dto.H5Banner;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel
public class QueryBannerResponse extends ResponseBase {

    @Valid
    @ApiModelProperty(value = "banner列表")
    public List<H5Banner> banners;

    public QueryBannerResponse() {}

    public QueryBannerResponse(List<Banner> items) {
        if (CollectionUtils.isNotEmpty(items)) {
            this.banners = items.stream().map(H5Banner::new).collect(Collectors.toList());
        }
    }

    public List<H5Banner> getBanners() {
        return banners;
    }

}
