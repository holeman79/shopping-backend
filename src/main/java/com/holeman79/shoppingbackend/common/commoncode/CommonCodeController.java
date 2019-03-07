package com.holeman79.shoppingbackend.common.commoncode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/commoncode")
public class CommonCodeController {
    @Autowired
    CommonCodeService commonCodeService;

    @GetMapping("/{groupCode}")
    public ResponseEntity<List<CommonCode>> getCommonCodeList(@PathVariable String groupCode){
        return new ResponseEntity<List<CommonCode>>(commonCodeService.getCommonCodeList(groupCode), HttpStatus.OK);
    }

    @GetMapping("/list/{groupCode}")
    public ResponseEntity<List<CommonCode>> getCommonCodeListLike(@PathVariable String groupCode){
        return new ResponseEntity<List<CommonCode>>(commonCodeService.getCommonCodeListLike(groupCode), HttpStatus.OK);
    }


}
