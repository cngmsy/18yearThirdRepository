#实现银行卡扫描   效果中最后一个银行卡扫描结果大家自己去试验吧
![](gif_20180124_093054.gif.gif)

#实现的核心代码是
###步骤一:
在清单文件 中加上所要用到的权限
###步骤二:
在Activity 中使用自定义布局ViewfinderView

 this.flash.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (!ScanCamera.this.getPackageManager().hasSystemFeature("android.hardware.camera.flash")) {
                    Toast.makeText(ScanCamera.this, ScanCamera.this.getResources().getString(ScanCamera.this.getResources().getIdentifier("toast_flash", "string", ScanCamera.this.getApplication().getPackageName())), Toast.LENGTH_SHORT).show();
                } else if (ScanCamera.this.camera != null) {
                    Parameters parameters = ScanCamera.this.camera.getParameters();
                    if (parameters.getFlashMode().equals("torch")) {
                        parameters.setFlashMode("off");
                        parameters.setExposureCompensation(0);
                    } else {
                        parameters.setFlashMode("torch");
                        parameters.setExposureCompensation(-1);
                    }
                    try {
                        ScanCamera.this.camera.setParameters(parameters);
                    } catch (Exception e) {
                        Toast.makeText(ScanCamera.this, ScanCamera.this.getResources().getString(ScanCamera.this.getResources().getIdentifier("toast_flash", "string", ScanCamera.this.getApplication().getPackageName())), Toast.LENGTH_SHORT).show();
                    }
                    ScanCamera.this.camera.startPreview();
                }
            }
        });


###步骤三:
调用BankCardAPI，ScanCamera，Utils中相关的方法进行扫描 并进行截图

###步骤四:
在另外一个Activity中  将截图赋值给ImageView 并且识别图片中的银行卡号码

         int[] picR = getIntent().getIntArrayExtra("PicR");
        char[] StringR = getIntent().getCharArrayExtra("StringR");

        result.setText(String.valueOf(StringR));
        Bitmap bitmap = Bitmap.createBitmap(picR, 400, 80, Bitmap.Config.ARGB_8888);
        imageView.setImageBitmap(bitmap);


