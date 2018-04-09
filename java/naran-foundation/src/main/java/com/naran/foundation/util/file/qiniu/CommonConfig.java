package com.naran.foundation.util.file.qiniu;

import com.qiniu.util.Auth;

/**
 * 上传图片默认配置 无需改动
 * 
 * @author zefeng.xu
 */
public final class CommonConfig {

    public static final Auth testAuth = Auth.create("d3oPGOxZE0ZPMbcqMwkXAl3Y2ecHYXMoqstozeC2", "srM6cReUv5enQqbm-OBhlmIhOnV_QumxkN-D9BSF");
    public static final String bucket = "naran";
    public static final String key = "java-duke.svg";
    public static final String domain = "javasdk.qiniudn.com";

    public static final String prefix = "http://orkypu4y4.bkt.clouddn.com/";

    public CommonConfig() {
    }

    public static boolean isTravis() {
	return "travis".equals(System.getenv("QINIU_TEST_ENV"));
    }

}
