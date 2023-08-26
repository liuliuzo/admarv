package com.admarv.saas.common;

/**
 * 
 * @author liuliu
 *
 */
public class CommonConstant {
    
    private CommonConstant() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * 发帖图片文件路径
     */
    public static final String IMG_POST_PATH = "/home/ecs-user/admarv/saas/imgfb/";
    
    /**
     * 发帖视频文件路径
     */
    public static final String VIDEO_POST_PATH = "/home/ecs-user/admarv/saas/videofb/";
    
    /**
     * 询盘文件路径
     */
    public static final String LEAD_FILE_PATH = "/home/ecs-user/admarv/saas/lead/files";
    
    /**
     * 邮件附件路径
     */
    public static final String EMAIL_FILE_PATH = "/home/ecs-user/admarv/saas/email/";
}
