package com.szzgkon.googleplay.tools;

import com.lidroid.xutils.BitmapUtils;

public class BitmapHelper {
	private BitmapHelper() {
	}

	private static BitmapUtils bitmapUtils;


	public static BitmapUtils getBitmapUtils() {
		if (bitmapUtils == null) {
			// �ڶ������� ����ͼƬ��·�� // ����ͼƬ ������Ķ��ٱ������ڴ� 0.05-0.8f
			bitmapUtils = new BitmapUtils(UIUtils.getContext(), FileUtils
					.getIconDir().getAbsolutePath(), 0.3f);
		}
		return bitmapUtils;
	}
}
