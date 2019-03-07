package com.holeman79.shoppingbackend.common.commoncode;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "COMMON_CODE")
public class CommonCode {
    @EmbeddedId
    private CommonCodeId commonCodeId;
    private String groupName;
    private String codeValue;
    private String showValue;
}
