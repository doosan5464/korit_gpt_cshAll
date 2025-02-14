package com.korit.springboot_study.service;

import com.korit.springboot_study.dto.request.study.ReqAddInstructorDto;
import com.korit.springboot_study.dto.request.study.ReqAddMajorDto;
import com.korit.springboot_study.dto.request.study.ReqUpdateMajorDto;
import com.korit.springboot_study.dto.response.common.SuccessResponseDto;
import com.korit.springboot_study.entity.study.Instructor;
import com.korit.springboot_study.entity.study.Major;
import com.korit.springboot_study.repository.StudentStudyRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service // @Service라는 컴포넌트로 분리
public class StudentStudyService {

    @Autowired
    private StudentStudyRepository studentStudyRepository;


    public SuccessResponseDto<List<Major>> getMajorsAll() throws NotFoundException { // 기본을 성공으로 두고 orElseThrow로 실패로직 처리
        List<Major> foundMajors = studentStudyRepository.findMajorAll()
                .orElseThrow(() -> new NotFoundException("학과 데이터가 존재하지 않습니다."));

        return new SuccessResponseDto<>(foundMajors);
    }

    public SuccessResponseDto<List<Instructor>> getInstructorsAll() throws NotFoundException {
        List<Instructor> foundInstructors = studentStudyRepository.findInstructorAll()
                .orElseThrow(() -> new NotFoundException("교수 데이터가 존재하지 않습니다."));

        return new SuccessResponseDto<>(foundInstructors);
    }


    @Transactional(rollbackFor = Exception.class) // 이 메서드에 트랜잭션을 설정(이 메서드가 종료되지 않으면 저장하지 않음)
    // 어떤 클래스건 아무 예외가 터지면 롤백(트랜잭션 시작 전 상태, 이 메서드 실행 이전 상태)을 한다. (메서드가 종료되지 않으면 저장을 안하니까)
    // 롤백되면 이후 코드 실행 안 하고 바로 예외 던지고 끝
    public SuccessResponseDto<Major> addMajor(ReqAddMajorDto reqAddMajorDto) throws DuplicateKeyException {
        return new SuccessResponseDto<>(
                studentStudyRepository
                        .saveMajor(new Major(0, reqAddMajorDto.getMajorName())) // 0 대신에 아무 숫자넣어도 아무 상관없음
                        // .saveMajor()에서 예외가 발생하면, 아예 해당 라인에서 코드 실행이 중단되고 catch 가능한 곳으로 넘어감
                        .orElseThrow() // .get() 해도 됨
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public SuccessResponseDto<Instructor> addInstructor(ReqAddInstructorDto reqAddInstructorDto) throws DuplicateKeyException {
        return new SuccessResponseDto<>(
                studentStudyRepository
                        .saveInstructor(new Instructor(0, reqAddInstructorDto.getInstructorName()))
                        .orElseThrow()
        );
    }


    @Transactional(rollbackFor = Exception.class)
    public SuccessResponseDto<Major> modifyMajor(int majorId, ReqUpdateMajorDto reqUpdateMajorDto) throws DuplicateKeyException, NotFoundException {
        Major modifiedMajor = studentStudyRepository
                        .updateMajor(new Major(majorId, reqUpdateMajorDto.getMajorName()))
                        .orElseThrow(() -> new NotFoundException("해당 학과 ID는 존재하지 않습니다."));
    return new SuccessResponseDto<>(modifiedMajor);
    }

}