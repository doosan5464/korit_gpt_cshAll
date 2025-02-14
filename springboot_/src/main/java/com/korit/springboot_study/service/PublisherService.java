package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.ReqAddPublisherDto;
import com.korit.springboot_study.dto.request.ReqSearchPublisherDto;
import com.korit.springboot_study.entity.Publisher;
import com.korit.springboot_study.repository.PublisherRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public Publisher addPublisher(ReqAddPublisherDto reqAddPublisherDto) {
        return publisherRepository
                .save(reqAddPublisherDto.toPublisher())
                .get();
    }

    public List<Publisher> getPublishers(ReqSearchPublisherDto reqSearchPublisherDto) throws Exception {
        return publisherRepository.findAllByNameContaining(reqSearchPublisherDto.getKeyword())
                .orElseThrow(() -> new NotFoundException("조회된 출판사가 없습니다."));
    }
}