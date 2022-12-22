//package com.example.YouTube.controller;
//
//import com.example.YouTube.dto.attach.AttachResponseDTO;
//import com.example.YouTube.enums.Language;
//import com.example.YouTube.service.AttachService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/attach")
//public class AttachController {
//
//    @Autowired
//    private AttachService attachService;
//
//    @PostMapping("/upload")
//    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,
//                                    @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
//        AttachResponseDTO fileName = attachService.saveToSystem(file,language);
//        return ResponseEntity.ok().body(fileName);
//    }
//
//
////    @GetMapping(value = "/open/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
////    public byte[] open(@PathVariable("fileName") String fileName) {
////        return attachService.open(fileName);
////    }
//
//    @GetMapping(value = "/open_general/{fileName}", produces = MediaType.ALL_VALUE)
//    public byte[] open_general(@PathVariable("fileName") String fileName,
//                               @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
//        return attachService.open_general(fileName, language);
//    }
//
//    @GetMapping("/download/{fineName}")
//    public ResponseEntity<Resource> download(@PathVariable("fineName") String fileName,
//                                             @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
//        Resource file = attachService.download(fileName, language);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }
//
//
//    @DeleteMapping("/delete/{fileName}")
//    public ResponseEntity<?> deleteById(@PathVariable("fileName") String fileName,
//                                        @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
//        String result = attachService.deleteById(fileName, language);
//        return ResponseEntity.ok(result);
//    }
//
//    @GetMapping("/get")
//    public ResponseEntity<?> getWithPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
//        Page<AttachResponseDTO> result = attachService.getWithPage(page, size);
//        return ResponseEntity.ok(result);
//    }
//}