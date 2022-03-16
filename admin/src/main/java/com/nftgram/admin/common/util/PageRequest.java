package com.nftgram.admin.common.util;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class PageRequest {

    private int page = 1;
    private int size = 20;
    private Sort.Direction direction;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int DEFAULT_SIZE = 10;
        int MAX_SIZE = 100;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }


    public org.springframework.data.domain.PageRequest of() {
        if ( direction == null) {
            return org.springframework.data.domain.PageRequest.of(this.page -1, this.size);
        } else {
            return org.springframework.data.domain.PageRequest.of(this.page -1, this.size, this.direction, "createDate" + "");
        }
    }
}
