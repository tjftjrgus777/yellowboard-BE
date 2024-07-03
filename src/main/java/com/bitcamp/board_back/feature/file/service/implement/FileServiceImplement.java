package com.bitcamp.board_back.feature.file.service.implement;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bitcamp.board_back.feature.file.service.FileService;
import com.bitcamp.board_back.feature.user.dto.AccountUserDetails;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;

@Service
public class FileServiceImplement implements FileService {

    // MINIO 오브젝트 스토리지 추가
    @Autowired
    private MinioClient minioClient;

    // MINIO 버킷 이름 가져오기 (추후 사용자별 버킷 제공서비스)

    String fileUrl = "";

    @Value("${minio.access-key}")
    private String accessKey;
    @Value("${minio.secret-key}")
    private String secretKey;
    @Value("{minio.url}")
    private String minioUrl;

    @Override
    public String upload(MultipartFile file, AccountUserDetails accountUserDetails) {
        String email = accountUserDetails.getUser().getEmail();
        String bucketName = email.replace("@", "-at-").replace(".", "-dot-");
        String originalFileName = file.getOriginalFilename();
        // 추후 파일 타입에 따라 image, file, mp4로 분류
        String filePath = "image" + "/" + System.currentTimeMillis() + "_" + originalFileName;
        if (file.isEmpty())
            return null;

        // 오브젝트 스토리지에 email로 생성된 버컷이 있으면 그 이메일로 버킷을 사용하고,
        // email로 된 버킷이 없으면 해당 이메일로 버킷을 생성하여 해당 버킷에 파일저장
        try {
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(bucketName)
                                .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception
        }

        try (InputStream inputStream = file.getInputStream()) {

            // file.transferTo(new File(savePath));

            // Minio오브젝트에 파일 업로드
            // 업로드할 파일 정보
            // 인수1_String bucketName = "my-bucket"; // 버킷 이름
            // 인수2_String objectName = "example.jpg"; // 업로드할 파일의 객체 이름
            // 인수3_)InputStream inputStream = ...; // 파일 데이터를 담고 있는 InputStream
            // 인수4_String contentType = "image/jpeg"; // 파일의 MIME 타입
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(filePath)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());

            fileUrl = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(filePath)
                            // .expiry(10 * 60) // 10분 후 만료
                            .build());

        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Failed to upload file to MinIO: " + exception.getMessage());

        }

        return fileUrl;

    }

    // minio 스토리지에서 이미지 가져오기
    // 필요한 인수는 파일 네임
    @Override
    public Resource getiImage(String fileName, AccountUserDetails accountUserDetails) {
        String email = accountUserDetails.getUser().getEmail();
        String bucketName = email.replace("@", "-at-").replace(".", "-dot-");
        try {
            // MinioClient 객체 생성
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioUrl)
                    .credentials(accessKey, secretKey)
                    .build();

            // MinIO 버킷에서 파일 가져오기
            GetObjectArgs args = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .build();
            InputStream stream = minioClient.getObject(args);

            // InputStream을 리소스로 변환하여 반환
            return new InputStreamResource(stream);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public InputStream downLoad(String filePath, AccountUserDetails accountUserDetails) {
        {
            String email = accountUserDetails.getUser().getEmail();
            String bucketName = email.replace("@", "-at-").replace(".", "-dot-");
            try {
                return minioClient.getObject(
                        GetObjectArgs.builder()
                                .bucket(bucketName)
                                .object(filePath)
                                .build());

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
