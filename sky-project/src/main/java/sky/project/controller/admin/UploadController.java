package sky.project.controller.admin;

import constant.MessageConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import result.Result;
import utils.AliyunOSSUtils;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/admin/common/upload")
public class UploadController {
    @Autowired
    private AliyunOSSUtils aliyunOssUtils;
    @PostMapping
    public Result uploadController(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file);
        String path = aliyunOssUtils.upload(file.getBytes(), Objects.requireNonNull(file.getOriginalFilename()));
        if(path!=null){
            return Result.success(path);
        }
        return Result.error(MessageConstant.FILE_ERROR);
    }
}
