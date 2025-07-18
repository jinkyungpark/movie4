package com.example.movie.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.dto.ReviewDTO;
import com.example.movie.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{mno}")
    public Long postMethodName(@RequestBody ReviewDTO reviewDTO) {
        log.info("리뷰 등록 요청 {}", reviewDTO);

        Long rno = reviewService.insertReply(reviewDTO);

        return rno;
    }

    @GetMapping("/{mno}/{rno}")
    public ReviewDTO getReview(@PathVariable Long rno) {
        log.info("review 가져오기 {}", rno);
        return reviewService.getReply(rno);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{mno}/{rno}")
    public ReviewDTO putReply(@PathVariable Long rno, @RequestBody ReviewDTO reviewDTO) {
        log.info("review 수정 {} {}", rno, reviewDTO);
        ReviewDTO updaReviewDTO = reviewService.updateReply(reviewDTO);
        return updaReviewDTO;
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{mno}/{rno}")
    public Long remove(@PathVariable Long rno) {
        log.info("review 제거 {}", rno);
        reviewService.removeReply(rno);
        return rno;
    }

    @GetMapping("/{mno}/all")
    public List<ReviewDTO> getList(@PathVariable Long mno) {
        log.info("review 요청 {}", mno);

        return reviewService.getReplies(mno);
    }

}
