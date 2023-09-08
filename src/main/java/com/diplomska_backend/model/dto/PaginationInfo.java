package com.diplomska_backend.model.dto;

import com.diplomska_backend.model.helpers.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginationInfo {
    private Integer numberOfResults;
    private Integer numberOfPages;

    public PaginationInfo(Integer numberOfResults){
        this.numberOfResults=numberOfResults;
        this.numberOfPages = (int) Math.ceil((double) numberOfResults / Constants.PAGESIZE);
    }
}
