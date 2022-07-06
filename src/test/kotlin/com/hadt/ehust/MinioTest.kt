package com.hadt.ehust

import io.minio.*
import io.minio.http.Method
import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileInputStream
import java.util.Base64

class MinioTest {

    @Test
    fun uploadTest() {
        val mc = MinioClient.builder()
            .endpoint("http://localhost:9000")
            .credentials("iylnllsY1LIML1Kc", "NbjEzIcII2jjwCDju73D89urAdAGVrQx")
            .build()

        if (!mc.bucketExists(BucketExistsArgs.builder().bucket("avatar").build())) {
            mc.makeBucket(MakeBucketArgs.builder().bucket("avatar").build())
        }

        val build = MinioAsyncClient.builder().build()

        mc.uploadObject(
            UploadObjectArgs.builder()
                .bucket("attachment")
                .`object`("test")
                .filename("/home/hai/Downloads/glitchy-deno.jpg")
                .build()
        )

//        val file = File("/home/hai/Downloads/glitchy-deno.jpg").inputStream()


//        mc.putObject(
//            PutObjectArgs.builder()
//                .bucket("attachment")
//                .`object`("deno.jpg")
//                .contentType("image/jpg")
//                .stream(file, file.available().toLong(), -1)
//                .build()
//        )
    }
}