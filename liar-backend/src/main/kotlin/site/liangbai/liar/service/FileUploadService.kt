package site.liangbai.liar.service

import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path

interface FileUploadService {
    fun uploadImage(file: MultipartFile, uploadDir: Path): String
}