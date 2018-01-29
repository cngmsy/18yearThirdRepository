package com.example.laptop.facepk.Utils;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FaceUtil {


	public static Response detectFace(String faceToken, String API_KEY, String API_Secret) {
		OkHttpClient client = new OkHttpClient();
		//封装请求体
		RequestBody requestBody = postBodyDetectFace(faceToken, API_KEY, API_Secret);
		Request request = new Request.Builder().url("https://api-cn.faceplusplus.com/facepp/v3/face/analyze")
				.post(requestBody).build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
			int attemp = 0;
			while (response.code() != 200 && attemp < 10){
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				response = client.newCall(request).execute();
				attemp++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	public static Response detectFace(File imageFile, String API_KEY, String API_Secret) {
		OkHttpClient client = new OkHttpClient();
		//封装请求体
		RequestBody requestBody = postBodyDetectFace(imageFile, API_KEY, API_Secret);
		Request request = new Request.Builder().url("https://api-cn.faceplusplus.com/facepp/v3/detect")
				.post(requestBody).build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	private static RequestBody postBodyDetectFace(String faceToken, String API_KEY, String API_Secret) {

		MultipartBody.Builder builder = new MultipartBody.Builder();
		builder.setType(MultipartBody.FORM);
		builder.addFormDataPart("api_key", API_KEY);
		builder.addFormDataPart("api_secret", API_Secret);
		builder.addFormDataPart("face_tokens", faceToken);
		//builder.addFormDataPart("return_landmark", "1");
		builder.addFormDataPart("return_attributes", "gender,age,smiling,headpose,facequality,blur,eyestatus,ethnicity");
		return builder.build();
	}




	private static RequestBody postBodyDetectFace(File file, String API_KEY, String API_Secret) {
		// 设置请求体
		MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpg");
		RequestBody body = MultipartBody.create(MEDIA_TYPE_JPG, file);
		MultipartBody.Builder builder = new MultipartBody.Builder();
		builder.setType(MultipartBody.FORM);
		builder.addFormDataPart("api_key", API_KEY);
		builder.addFormDataPart("api_secret", API_Secret);
		builder.addFormDataPart("image_file", file.getName(), body);
		//builder.addFormDataPart("return_landmark", "1");
		builder.addFormDataPart("return_attributes", "gender,age,smiling,headpose,facequality,blur,eyestatus,ethnicity");
		return builder.build();
	}

}
