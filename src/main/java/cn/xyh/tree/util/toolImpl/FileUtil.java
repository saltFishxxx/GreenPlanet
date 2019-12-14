package cn.xyh.tree.util.toolImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 文件上传的工具类
 */
public class FileUtil {
    private FileUtil() {}

    /**
     *
     * @param part 传part对象
     * @param path 本地存储路径
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public static String upload(Part part,String path) throws IOException, ServletException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        //获取文件后缀名,Content-Disposition：form-data; name="file"; filename="test.txt"
        String disposition = part.getHeader("Content-Disposition");
        String suffix = disposition.substring(disposition.lastIndexOf("."),disposition.length()-1);

        //随机生成一个文件名
        String filename = UuidUtil.getUuid()+suffix;
        //获取文件的输入流
        InputStream inputStream = part.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream
                (path +File.separatorChar+filename);

        //输出到本地
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0 , len);
        }

        //关闭输入输出流
        inputStream.close();
        fileOutputStream.close();

        return filename;
    }

    /**
     *
     * @param collection part集合
     * @param path 本地存储路径
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public static List<String> uploadMany(Collection<Part> collection, String path) throws IOException, ServletException {
        Iterator<Part> parts = collection.iterator();
        ArrayList<String> strings = new ArrayList<>();
        while (parts.hasNext()) {
            Part next = parts.next();
            String upload = upload(next, path);
            strings.add(upload);
        }
        return strings;
    }
}
