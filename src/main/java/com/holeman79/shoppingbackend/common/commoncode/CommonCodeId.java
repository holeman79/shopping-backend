package com.holeman79.shoppingbackend.common.commoncode;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Embeddable
public class CommonCodeId implements Serializable {
    @Column(name = "GROUP_CODE")
    private String groupCode;
    @Column(name = "CODE")
    private int code;
}
