package com.holeman79.shoppingbackend.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonCodeService {
    @Autowired
    CommonCodeRepository commonCodeRepository;

    public List<CommonCode> getCategoryList(){
        return commonCodeRepository.findByCommonCodeIdGroupCode("PROD001");
    }

    public List<List<CommonCode>> getAllProductCommonCodeList(){
        List<CommonCode> commonCodeList = commonCodeRepository.findByCommonCodeIdGroupCodeIgnoreCaseContaining("PROD");
        List<List<CommonCode>> resultCommonCodeList = new ArrayList<>();
        List<CommonCode> item = new ArrayList<>();
        for(int i = 0; i < commonCodeList.size(); i++){
            CommonCode commonCode = commonCodeList.get(i);
            if(i != 0 && !commonCode.getGroupName().equals(commonCodeList.get(i-1).getGroupName())){
                resultCommonCodeList.add(item);
                item = new ArrayList<>();
                item.add(commonCode);
            }else{
                item.add(commonCode);
                if(i+1 == commonCodeList.size()) resultCommonCodeList.add(item);
            }
        }
        return resultCommonCodeList;
    }
}
