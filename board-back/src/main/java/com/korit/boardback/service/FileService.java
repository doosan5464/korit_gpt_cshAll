package com.korit.boardback.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {

    @Value("${user.dir}") // 루트 디렉토리 경로
    private String rootPath;

    // 파일 저장 메서드
    public String saveFile(String path, MultipartFile file) {
        if(file.isEmpty()) { // 빈 파일이면 걍 없던 일로
            return null;
        }

        String newFilename = null; // 새로 만들 파일명
        try {
            String originalFilename = file.getOriginalFilename(); // 원본 파일명
            // 고유한 파일명 생성 (이름이 겹치면 안되니까 계속 새롭게)
            newFilename = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFilename;

            // 디렉토리 생성
            File newFilePath = new File(rootPath + "/" + path);
            if(!newFilePath.exists()) { // 만약 경로가 없었다면 경로를 지정해줌
                newFilePath.mkdirs();
            }

            // 파일 저장
            File newFile = new File(rootPath + "/" + path + "/" + newFilename);
            file.transferTo(newFile); // MultipartFile의 file속에 저장된 파일이 newFile의 지정된 경로로 실제로 저장
        } catch (Exception e) {
            e.printStackTrace(); // 오류 처리
        }
        return newFilename; // 새로운 파일명 반환
    }



    // 파일 삭제 메서드
    public void deleteFile(String path) {
        File file = new File(rootPath + "/" + path);
        if(file.exists()) {
            file.delete(); // 파일 삭제
        }
    }
}
