package uz.ages.appfileuploaddownload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.ages.appfileuploaddownload.entitiy.Attachment;
import uz.ages.appfileuploaddownload.entitiy.AttachmentContent;
import uz.ages.appfileuploaddownload.entitiy.Rating;
import uz.ages.appfileuploaddownload.repository.AttachmentContentRepository;
import uz.ages.appfileuploaddownload.repository.AttachmentRepository;
import uz.ages.appfileuploaddownload.repository.RatingRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @Autowired
    RatingRepository ratingRepository;

    private static final String uploadDirectory = "files";

//    @PostMapping("/upload")
//    public String uploadFile(MultipartHttpServletRequest request) throws IOException {
//        Iterator<String> fileNames = request.getFileNames();
//        MultipartFile file = request.getFile(fileNames.next());
//        if (file != null) {
//            //file haqida malumot olish uchun.
//            String originalFilename = file.getOriginalFilename();
//            long size = file.getSize();
//            String contentType = file.getContentType();
//            Attachment attachment = new Attachment();
//            attachment.setFileOriginalName(originalFilename);
//            attachment.setSize(size);
//            attachment.setContentType(contentType);
//            Attachment saveAttachment = attachmentRepository.save(attachment);
//
//            //fileni content(byte[]) saqlaymiz.
//            AttachmentContent attachmentContent = new AttachmentContent();
//            attachmentContent.setAsosiyContent(file.getBytes());
//            attachmentContent.setAttachment(saveAttachment);
//            attachmentContentRepository.save(attachmentContent);
//            return "File saqlandi. ID si: " + saveAttachment.getId();
//        }
//        return "Xatolik.";
//    }

    @PostMapping("/uploadFileSystem")
    public String uploadFileToFileSystem(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            Attachment attachment = new Attachment();
            String filename = file.getOriginalFilename();
            attachment.setTitle(filename);
            attachment.setPrice(12D);
            attachment.setDescription("Alisher Navoiy");
            attachment.setCategory("book");
            assert filename != null;
            String[] split = filename.split("\\.");
            String name = UUID.randomUUID() + "." + split[split.length - 1];
            attachment.setImage(uploadDirectory + "/" + name);
            attachmentRepository.save(attachment);

            Rating rating = new Rating();
            rating.setRate(3D);
            rating.setCount(10);
            ratingRepository.save(rating);

            Path path = Paths.get(uploadDirectory + "/" + name);
            Files.copy(file.getInputStream(), path);
            return "File saqlandi. ID si: " + attachment.getId();
        }
        return "File saqlanmadi.";
    }

//    @GetMapping("/getFileByName")
//    public void getFileByName(@PathVariable String name, HttpServletResponse response) throws IOException {
//        Optional<Attachment> optionalAttachment = attachmentRepository.findByName(name);
//        if (optionalAttachment.isPresent()) {
//            Attachment attachment = optionalAttachment.get();
//
//            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachmentName(name);
//
//            if (contentOptional.isPresent()) {
//                AttachmentContent attachmentContent = contentOptional.get();
//                response.setHeader("Content-Despositon", "attachment; filename=\"" + attachment.getFileOriginalName() + "\"");
//                response.setContentType(attachment.getContentType());
//                FileCopyUtils.copy(attachmentContent.getAsosiyContent(), response.getOutputStream());
//            }
//        }
//    }
    @GetMapping("/getFiles")
    public List<Attachment> getFiles() {
        return attachmentRepository.findAll();
    }

//    @GetMapping("/getFile/{id}")
//    public String getFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {
//        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
//        if (optionalAttachment.isPresent()) {
//            Attachment attachment = optionalAttachment.get();
//
//            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachmentId(id);
//
//            if (contentOptional.isPresent()) {
//                AttachmentContent attachmentContent = contentOptional.get();
//                response.setHeader("Content-Despositon", "attachment; filename=\"" + attachment.getFileOriginalName() + "\"");
//                response.setContentType(attachment.getContentType());
//                FileCopyUtils.copy(attachmentContent.getAsosiyContent(), response.getOutputStream());
//            }
//            return attachment.getName();
//        }
//        return "return";
//    }

//    @GetMapping("/getFileFromSystem/{id}")
//    public void getFileFromSystem(@PathVariable Integer id, HttpServletResponse response) throws IOException {
//        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
//        if (optionalAttachment.isPresent()) {
//            Attachment attachment = optionalAttachment.get();
//
//            response.setHeader("Content-Despositon", "attachment; filename=\"" + attachment.getFileOriginalName() + "\"");
//            response.setContentType(attachment.getContentType());
//            FileInputStream fileInputStream = new FileInputStream(uploadDirectory + "/" + attachment.getName());
//            FileCopyUtils.copy(fileInputStream, response.getOutputStream());
//        }
//    }

    @GetMapping("/getFilesFromSystem")
    public List<Attachment> getFilesFromSystem(){
        return attachmentRepository.findAll();
    }
}
