package site.liangbai.liar.service.system.impl

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import site.liangbai.liar.service.system.FileUploadService
import java.nio.file.Path
import java.util.*
import kotlin.io.path.absolutePathString

@Service
class FileUploadServiceImpl : FileUploadService {
    override fun uploadImage(file: MultipartFile, uploadDir: Path): String {
        if (file.isEmpty) {
            return "empty"
        }

        val extension = file.contentType ?: return "illegal"
        if (!extension.startsWith("image/")) {
            return "illegal"
        }
        val fileName = "${UUID.randomUUID()}.${extension.substringAfter("/")}"
        val targetPath = uploadDir.resolve(fileName)
        try {
            file.transferTo(targetPath)
            return targetPath.absolutePathString()
        } catch (e: Exception) {
            return "error"
        }
    }
}