package com.nftgram.web.api.controller;

import com.nftgram.web.api.dto.MainPageDto;
import com.nftgram.web.api.dto.request.CommonLikeUpdateRequest;
import com.nftgram.web.api.dto.request.CommonMainPagination;
import com.nftgram.web.api.dto.request.CommonViewCountUpdateRequest;
import com.nftgram.web.api.service.ApiRestService;
import com.nftgram.web.main.dto.MainResponse;
import com.nftgram.web.main.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api")
public class ApiRestController {

    @Autowired
    private MainService mainService;
    @Autowired
    private ApiRestService apiRestService;

    @GetMapping("/main/page")
    public MainPageDto getMainPage(Pageable pageable) throws ParseException {
        List<MainResponse> mainResponseAll = mainService.findAllList(pageable);
        MainPageDto mainPageDto = new MainPageDto(mainResponseAll, mainResponseAll.size());

        return mainPageDto;
    }

    @PostMapping(value = "/mainpage", produces = "application/json")
    public List<MainResponse> responsesPage(CommonMainPagination commonMainPagination, Pageable pageable) throws ParseException {
        List<MainResponse> mainResponseAll = mainService.findAllList(pageable);

        return mainResponseAll;
    }

    @PostMapping(value = "/upview", produces = "application/json")
    public boolean updateView(CommonViewCountUpdateRequest viewCountUpdateRequest) {


        return true;
    }

    @PostMapping(value = "/uplike", produces = "application/json")
    public boolean saveLike(CommonLikeUpdateRequest likeUpdateRequest) {

        return true;
    }
}
