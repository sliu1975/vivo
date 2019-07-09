package com.nplatform.control;


import com.nplatform.dao.ActivityRepository;
import com.nplatform.entity.Activity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/image")
public class ImageControl {

    @Value("${upload.path}")
    private String uploadPath;

    @Resource
    private ActivityRepository activityRepository;

    /**图片格式：JPG*/
    private final String PICTRUE_FORMATE_JPG = "png";

    @RequestMapping(value = "/get")
    public void getImage(HttpServletRequest request, HttpServletResponse response) {
        String filename;

        filename=request.getParameter("filename");

        FileInputStream fis = null;
        response.setContentType("image/gif");
        try {
            OutputStream out = response.getOutputStream();
            File file = new File(uploadPath + File.separator + filename);
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/getTextImage")
    public void getTextImage(HttpServletRequest request, HttpServletResponse response) {
        String app,count,filename,targetImg;
        String fontName="宋体";

        app=request.getParameter("app");
        count=request.getParameter("count");

        Activity activity = activityRepository.findById(app);

        filename=activity.getPicture();
        //targetImg=uploadPath + File.separator + filename;

        if(activity.getCountx()==0 && activity.getCounty()==0)
        {

        }
        else {
            if(activity.getFontName()!=null  && activity.getFontName()!="")
            {
                fontName=activity.getFontName();
            }
            //pressText(filename, count, fontName, activity.getFontStyle(), activity.getFontSize(), getColor(activity.getColor()), activity.getCountx(), activity.getCounty(), 1);
            pressText(filename, count, fontName, activity.getFontStyle(), activity.getFontSize(), getColor(activity.getColor()), activity.getCountx(), activity.getCounty(), 1);
        }

        FileInputStream fis = null;
        response.setContentType("image/gif");
        try {
            OutputStream out = response.getOutputStream();
            File file;
            if(activity.getCountx()==0 && activity.getCounty()==0)
            {
                file = new File(uploadPath + File.separator + filename);
            }
            else
            {
                file = new File(uploadPath + File.separator +count+"_"+ filename);
            }

            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();

                    Path path = FileSystems.getDefault().getPath(uploadPath + File.separator, count+"_"+ filename);
                    Files.deleteIfExists(path);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    **
     * 添加文字水印
     * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
     * @param pressText 水印文字， 如：10000
     * @param fontName 字体名称，    如：宋体
     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize 字体大小，单位为像素
     * @param color 字体颜色
     * @param x 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */
    public void pressText(String filename, String pressText, String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha) {
        try {
            //File file = new File(filename);
            File file = new File(uploadPath + File.separator + filename);
            File file2 = new File(uploadPath + File.separator +pressText+"_"+ filename);

            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            //BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setColor(color);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

            int width_1 = fontSize * getLength(pressText);
            int height_1 = fontSize;
            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if(x < 0){
                x = widthDiff / 2;
            }else if(x > widthDiff){
                x = widthDiff;
            }
            if(y < 0){
                y = heightDiff / 2;
            }else if(y > heightDiff){
                y = heightDiff;
            }

            g.drawString(pressText, x, y + height_1);
            g.dispose();
            ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
     * @param text
     * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
     */
    public int getLength(String text) {
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
                length++;
            }
        }
        return (length % 2 == 0) ? length / 2 : length / 2 + 1;
    }

    public Color getColor(String color) {
        if (color.charAt(0) == '#') {
            color = color.substring(1);
        }
        if (color.length() != 6) {
            return null;
        }
        try {
            int r = Integer.parseInt(color.substring(0, 2), 16);
            int g = Integer.parseInt(color.substring(2, 4), 16);
            int b = Integer.parseInt(color.substring(4), 16);
            return new Color(r, g, b);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
}
