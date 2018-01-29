# 人脸识别签到系统，基于科大讯飞人脸识别和Face++人脸识别api
效果图gif
![](facepk.gif)


##步骤一:

```
/**
  *初始化
  */

  @Override
  	public void onCreate() {
  		super.onCreate();
  		//第一：默认初始化
  		Bmob.initialize(this, "6e64317b6509bb503f7d9ab8404bf54c");
  		Log.i("onCreate: ","bmob!!!!");
  	}
```

##步骤二:

```
/**
 *图片保存(注册新用户)
 */

 Camera.PictureCallback jpeg = new Camera.PictureCallback() {
 		@Override
 		public void onPictureTaken(byte[] data, Camera camera) {
 			Log.i(TAG, "onPictureTaken_data.length: " + data.length);
 			String filename = "waitForRename.jpg";
 			FileOutputStream fos;
 			//获取拍到的图片Bitmap
 			Bitmap bitmap_source = null;
 			String pictureStoragePath = PictureUtil.getPictureStoragePath(getApplicationContext());
 			File f = new File(pictureStoragePath, filename);
 			try {
 				fos = new FileOutputStream(f);
 				if (data.length < 35000) {
 					YuvImage image = new YuvImage(nv21, ImageFormat.NV21, PREVIEW_WIDTH, PREVIEW_HEIGHT, null);   //将NV21 data保存成YuvImage
 					//图像压缩
 					image.compressToJpeg(
 							new Rect(0, 0, image.getWidth(), image.getHeight()),
 							100, fos);
 					Log.i(TAG, "onPictureTaken_data.length<20000: " + data.length);
 					Log.i(TAG, "onPictureTaken_nv21.length: " + nv21.length);
 					bitmap_source = PictureUtil.compressFacePhoto(f.getAbsolutePath());
 					fos = new FileOutputStream(f);
 					BufferedOutputStream bos = new BufferedOutputStream(fos);
 					//旋转图片
 					// 根据旋转角度，生成旋转矩阵
 					Matrix matrix = new Matrix();
 					if(mCameraId == Camera.CameraInfo.CAMERA_FACING_FRONT){
 					     matrix.postRotate(270);
 					}else{
 						matrix.postRotate(90);
 					}
 					Bitmap mBitmap1 = Bitmap.createBitmap(bitmap_source, 0, 0, bitmap_source.getWidth(), bitmap_source.getHeight(), matrix, true);
 					testImageView.setImageBitmap(mBitmap1);
 					boolean result = mBitmap1.compress(Bitmap.CompressFormat.JPEG, 100, bos);
 					bos.close();
 					Log.i(TAG, "onPictureTaken_mBitmap1.compress: " + result);
 				} else {
 					bitmap_source = PictureUtil.compressFacePhoto(data);
 					BufferedOutputStream bos = new BufferedOutputStream(fos);
 					//旋转图片
 					// 根据旋转角度，生成旋转矩阵
 					Matrix matrix = new Matrix();
 					if(mCameraId == Camera.CameraInfo.CAMERA_FACING_FRONT){
 						matrix.postRotate(270);
 					}else{
 						matrix.postRotate(90);
 					}
 					Bitmap mBitmap1 = Bitmap.createBitmap(bitmap_source, 0, 0, bitmap_source.getWidth(), bitmap_source.getHeight(), matrix, true);
 					testImageView.setImageBitmap(mBitmap1);
 					mBitmap1.compress(Bitmap.CompressFormat.JPEG, 70, bos);
 					bos.close();
 				}
 				fos.flush();
 				fos.close();
 				Log.i(TAG, "onPictureTaken: 图片保存成功");
 				checkFace(f);
 			} catch (Exception e) {
 				System.out.println("图片保存异常" + e.getMessage());
 				e.printStackTrace();
 			}
```

##步骤三:

```
/**
 *人脸对比(签到打卡)
 */

 	AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
    				builder.setCancelable(false);
    				builder.setTitle("温馨提示：");
    				builder.setMessage("正在比对人脸，请稍后......");
    				dialog = builder.show();
    				Camera.PictureCallback jpeg = new Camera.PictureCallback() {
    					@Override
    					public void onPictureTaken(byte[] data, Camera camera) {
    						Log.i(TAG, "onPictureTaken_data.length: " + data.length);
    						String filename = "waitForSearch.jpg";
    						FileOutputStream fos;
    						//获取拍到的图片Bitmap
    						Bitmap bitmap_source = null;
    						String pictureStoragePath = PictureUtil.getPictureStoragePath(getApplicationContext());
    						File imageFile = new File(pictureStoragePath, filename);
    						try {
    							fos = new FileOutputStream(imageFile);
    							if (data.length < 35000) {
    								YuvImage image = new YuvImage(nv21, ImageFormat.NV21, PREVIEW_WIDTH, PREVIEW_HEIGHT, null);   //将NV21 data保存成YuvImage
    								//图像压缩
    								image.compressToJpeg(
    										new Rect(0, 0, image.getWidth(), image.getHeight()),
    										100, fos);
    								Log.i(TAG, "onPictureTaken_data.length<20000: " + data.length);
    								Log.i(TAG, "onPictureTaken_nv21.length: " + nv21.length);
    								bitmap_source = PictureUtil.compressFacePhoto(imageFile.getAbsolutePath());
    								fos = new FileOutputStream(imageFile);
    								BufferedOutputStream bos = new BufferedOutputStream(fos);
    								//旋转图片
    								// 根据旋转角度，生成旋转矩阵
    								Matrix matrix = new Matrix();
    								if(mCameraId == Camera.CameraInfo.CAMERA_FACING_FRONT){
    									matrix.postRotate(270);
    								}else{
    									matrix.postRotate(90);
    								}
    								Bitmap mBitmap1 = Bitmap.createBitmap(bitmap_source, 0, 0, bitmap_source.getWidth(), bitmap_source.getHeight(), matrix, true);
    								testImageView.setImageBitmap(mBitmap1);
    								boolean result = mBitmap1.compress(Bitmap.CompressFormat.JPEG, 100, bos);
    								Log.i(TAG, "onPictureTaken_mBitmap1.compress: " + result);
    							} else {
    								bitmap_source = PictureUtil.compressFacePhoto(data);
    								BufferedOutputStream bos = new BufferedOutputStream(fos);
    								//旋转图片
    								// 根据旋转角度，生成旋转矩阵
    								Matrix matrix = new Matrix();
    								if(mCameraId == Camera.CameraInfo.CAMERA_FACING_FRONT){
    									matrix.postRotate(270);
    								}else{
    									matrix.postRotate(90);
    								}
    								Bitmap mBitmap1 = Bitmap.createBitmap(bitmap_source, 0, 0, bitmap_source.getWidth(), bitmap_source.getHeight(), matrix, true);
    								testImageView.setImageBitmap(mBitmap1);
    								mBitmap1.compress(Bitmap.CompressFormat.JPEG, 70, bos);
    							}
    							fos.flush();
    							fos.close();
    							searchByOuterId(imageFile);
```

##步骤四:

```
/**
 *人脸分析(管理已注册用户)
 */

 // 获取每个人脸的结果
 			JSONArray items = joResult.getJSONArray("face");
 			// 获取人脸数目
 			rect = new FaceRect[items.length()];
 			for(int i=0; i<items.length(); i++) {

 				JSONObject position = items.getJSONObject(i).getJSONObject("position");
 				// 提取关键点数据
 				rect[i] = new FaceRect();
 				rect[i].bound.left = position.getInt("left");
 				rect[i].bound.top = position.getInt("top");
 				rect[i].bound.right = position.getInt("right");
 				rect[i].bound.bottom = position.getInt("bottom");

 				try {
 					JSONObject landmark = items.getJSONObject(i).getJSONObject("landmark");
 					int keyPoint = landmark.length();
 					rect[i].point = new Point[keyPoint];
 					Iterator it = landmark.keys();
 					int point = 0;
 					while (it.hasNext() && point < keyPoint) {
 						String key = (String) it.next();
 						JSONObject postion = landmark.getJSONObject(key);
 						rect[i].point[point] = new Point(postion.getInt("x"), postion.getInt("y"));
 						point++;
 					}
 				} catch (JSONException e) {
 				}
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return rect;
 	}
```