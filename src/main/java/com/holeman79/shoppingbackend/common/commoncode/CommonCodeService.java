package com.holeman79.shoppingbackend.common.commoncode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonCodeService {
    @Autowired
    CommonCodeRepository commonCodeRepository;

    public List<CommonCode> getCommonCodeList(String groupCode){
        List<CommonCode> commonCodeList = commonCodeRepository.findByCommonCodeIdGroupCode(groupCode);
        return commonCodeList;
    }

    public List<CommonCode> getCommonCodeListLike(String groupCode){
        List<CommonCode> commonCodeList = commonCodeRepository.findByCommonCodeIdGroupCodeIgnoreCaseContaining(groupCode);
        return commonCodeList;
    }
}
