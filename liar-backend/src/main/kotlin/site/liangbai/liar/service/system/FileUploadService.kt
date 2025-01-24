package site.liangbai.liar.service.system

import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path

interface FileUploadService {
    fun uploadImage(file: MultipartFile, uploadDir: Path): String
}