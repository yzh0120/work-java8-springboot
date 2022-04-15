package com.yz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yz.entity.SysFile;
import com.yz.service.SysFileService;
import com.yz.utils.web.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yz
 * @since 2022-04-15
 */
@RestController
@RequestMapping("/file")
public class SysFileController  {
    @Autowired
    SysFileService sysFileService;

    @GetMapping("/download")
    @ApiOperation(value = "下载文件", notes = "详细描述")
    public void  download(@RequestParam("fileId") String fileId, HttpServletResponse resp) {
        QueryWrapper<SysFile> queryWrapper  = new QueryWrapper<>();

        SysFile sysFile = sysFileService.getOne(queryWrapper.eq("id", fileId));


        InputStream in = null;
        OutputStream out = null;
        try{
            // 获得本地输入流   // 读取本地文件的输入流
            File file = new File(sysFile.getFilePath());
            in = new FileInputStream(file);
            /*
             * CORS请求时，XMLHttpRequest对象的getResponseHeader()方法只能拿到6个基本字段：
             * Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma。
             *如果想拿到其他字段，就必须在Access-Control-Expose-Headers里面指定。
             * */
            resp.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            // 设置响应正文的MIME类型
            resp.setContentType("application/x-download");
            resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(sysFile.getFileName(), "UTF-8"));//设置头部信息
            // 把本地文件发送给客户端  // 输出响应正文的输出流
            out = resp.getOutputStream();
            int byteRead = 0;
            byte[] buffer = new byte[512];
            while ((byteRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteRead);
            }

        }catch(IOException e){
            System.out.println("read-Exception :" + e.getMessage());
        }finally {
            if (in != null) {
                try {
                    in.close();
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    System.out.println("close-Exception :" + e.getMessage());

                }
            }

        }
    }

    //        // 获得本地输入流   // 读取本地文件的输入流
//        File file = new File(sysFile.getFilePath());
//        InputStream in = new FileInputStream(file);
//        /*
//         * CORS请求时，XMLHttpRequest对象的getResponseHeader()方法只能拿到6个基本字段：
//         * Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma。
//         *如果想拿到其他字段，就必须在Access-Control-Expose-Headers里面指定。
//         * */
//        resp.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
//        // 设置响应正文的MIME类型
//        resp.setContentType("application/x-download");
//        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(sysFile.getFileName(), "UTF-8"));//设置头部信息
//        // 把本地文件发送给客户端  // 输出响应正文的输出流
//        OutputStream out = resp.getOutputStream();
//        int byteRead = 0;
//        byte[] buffer = new byte[512];
//        while ((byteRead = in.read(buffer)) != -1) {
//            out.write(buffer, 0, byteRead);
//        }
//        in.close();
//        out.flush();
//        out.close();


    @GetMapping("/getFileListByFolderId")
    @ApiOperation(value = "获取文件", notes = "详细描述")
    public ApiResult getFileListByFolderId(@RequestParam(value = "folderId") String folderId, @RequestParam(value = "taskName") String taskName){
//        List<SysFile>  list = new ArrayList<>();
        QueryWrapper<SysFile> queryWrapper  = new QueryWrapper<>();
        List<SysFile> list = sysFileService.list(queryWrapper.eq("folderId", folderId).eq("taskName", taskName));
//        System.out.println(sysFile+"123123");
        return ApiResult.success(list);
    }


    @PostMapping("/upload")
    @ApiOperation(value = "上传文件", notes = "详细描述")
    public ApiResult  upload(MultipartFile file , @RequestParam(value = "folderId") String folderId, @RequestParam(value = "taskName") String taskName) throws IOException {

        if (file != null) {
//            System.out.println(file.getContentType());//在控制台打印文件的类型
//            System.out.println(file.getName());//返回文件的名称
//            System.out.println(file.getOriginalFilename());//返回文件的原文件名

            try {
                String filePath = "D:/test/"+ UUID.randomUUID().toString()+file.getOriginalFilename();
                file.transferTo(new File(filePath));

                SysFile sysFile = new SysFile();
                sysFile.setFileName(file.getOriginalFilename());
                sysFile.setFolderid(folderId);
                sysFile.setTaskname(taskName);
                sysFile.setFilePath(filePath);
                sysFileService.save(sysFile);

            } catch (IllegalStateException e) {
                e.printStackTrace();
                //model.addAttribute("msg", "上传失败");
                return ApiResult.error("失败");
            } catch (IOException e) {
                e.printStackTrace();
                //model.addAttribute("msg", "上传失败");
                return ApiResult.error("失败");
            }

        }
        //model.addAttribute("msg", "上传成功");
        return ApiResult.success("成功");
    }
}
/*
* MultipartFile接口中的方法：

    String getOriginalFilename();//获取源文件名

    String getName();//获取表单中的参数名

    String getContentType();//获取文件类型

    boolean isEmpty();//上传的文件是否有内容

    long getSize();//返回文件大小 以字节为单位

    byte[] getBytes() throws IOException;//将文件内容转化为字节数组

    InputStream getInputStream() throws IOException;//返回inputstream以从中读取文件的内容

    void transferTo(File var1) throws IOException, IllegalStateException;//转换文件
*
* */
