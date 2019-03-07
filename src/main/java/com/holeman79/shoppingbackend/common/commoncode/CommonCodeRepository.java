package com.holeman79.shoppingbackend.common.commoncode;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonCodeRepository extends JpaRepository<CommonCode, CommonCodeId> {
    List<CommonCode> findByCommonCodeIdGroupCode(String groupCode);
    List<CommonCode> findByCommonCodeIdGroupCodeIgnoreCaseContaining(String groupCode);
}
