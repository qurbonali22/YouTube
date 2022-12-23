package com.example.YouTube.service;

import com.example.YouTube.dto.attach.AttachResponseDTO;
import com.example.YouTube.entity.AttachEntity;
import com.example.YouTube.enums.Language;
import com.example.YouTube.exception.AttachNotFoundException;
import com.example.YouTube.exception.CouldNotRead;
import com.example.YouTube.exception.OriginalFileNameNullException;
import com.example.YouTube.exception.SomethingWentWrong;
import com.example.YouTube.repository.AttachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AttachService{

    @Autowired
    private AttachRepository attachRepository;

    @Value("${attach.upload.folder}")
    private String attachUploadFolder;

    @Value("${attach.download.url}")
    private String attachDownloadUrl;

    @Autowired
    private ResourceBundleService resourceBundleService;

    public AttachResponseDTO saveToSystem(MultipartFile file, Language language) {
        try {

            String pathFolder = getYmDString(); // 2022/04/23
            File folder = new File(attachUploadFolder + pathFolder); // attaches/2022/04/23

            if (!folder.exists()) folder.mkdirs();


            String fileName = UUID.randomUUID().toString(); // dasdasd-dasdasda-asdasda-asdasd
            String extension = getExtension(file.getOriginalFilename(),language); //zari.jpg

            // attaches/2022/04/23/dasdasd-dasdasda-asdasda-asdasd.jpg

            byte[] bytes = file.getBytes();
            Path path = Paths.get(attachUploadFolder + pathFolder + "/" + fileName + "." + extension);
            Files.write(path, bytes);

            AttachEntity entity = new AttachEntity();
            entity.setId(fileName);
            entity.setOriginalName(file.getOriginalFilename());
            entity.setExtension(extension);
            entity.setCreatedDate(LocalDateTime.now());
            entity.setPath(pathFolder);
            entity.setSize(file.getSize());
            attachRepository.save(entity);


            AttachResponseDTO dto = new AttachResponseDTO();
            dto.setId(entity.getId());
            dto.setUrl(attachDownloadUrl + fileName + "." + extension);
            return dto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] open_general(String fileName, Language language) {

        try {
            AttachEntity entity = getAttach(fileName, language);

            Path file = Paths.get(attachUploadFolder + entity.getPath() + "/" + fileName);
            return Files.readAllBytes(file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Resource download(String fileName, Language language) {
        try {
            AttachEntity entity = getAttach(fileName, language);


            File file = new File(attachUploadFolder + entity.getPath() + "/" + fileName);

            File dir = file.getParentFile();
            File rFile = new File(dir, entity.getOriginalName());

            Resource resource = new UrlResource(rFile.toURI());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new CouldNotRead(resourceBundleService.getMessage("could.not.read",language));
            }
        } catch (MalformedURLException e) {
            throw new SomethingWentWrong(resourceBundleService.getMessage("wrong",language));
        }
    }


    public String deleteById(String fileName, Language language) {
        try {
            AttachEntity entity = getAttach(fileName, language);
            Path file = Paths.get(attachUploadFolder + entity.getPath() + "/" + fileName);

            Files.delete(file);
            attachRepository.deleteById(entity.getId());

            return "deleted";
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

    }

    public AttachEntity getAttach(String attachId, Language language) {

        Optional<AttachEntity> optional = attachRepository.findById(attachId);

        if (optional.isEmpty()) {
            throw new AttachNotFoundException(resourceBundleService.getMessage("item.not.found", language, attachId));
        }
        return optional.get();
    }

    public Page<AttachResponseDTO> getWithPage(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<AttachEntity> pageObj = attachRepository.findAll(pageable);

        List<AttachEntity> entityList = pageObj.getContent();
        List<AttachResponseDTO> dtoList = new ArrayList<>();


        for (AttachEntity entity : entityList) {

            AttachResponseDTO dto = new AttachResponseDTO();
            dto.setId(entity.getId());
            dto.setUrl(attachDownloadUrl + "/" + entity.getId() + "." + entity.getExtension());
            dto.setOriginalName(entity.getOriginalName());
            dto.setSize(entity.getSize());

            dtoList.add(dto);
        }

        return new PageImpl<>(dtoList, pageable, pageObj.getTotalElements());
    }


    public String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);

        return year + "/" + month + "/" + day; // 2022/04/23
    }

    public String getExtension(String fileName,Language language) {
        // mp3/jpg/npg/mp4.....
        if (fileName == null) {
            throw new OriginalFileNameNullException(resourceBundleService.getMessage("file.name.null",language));
        }
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }



}
