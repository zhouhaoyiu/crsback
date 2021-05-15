package com.example.crs.Controller;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.crs.searchtext.*;
@Controller
public class backEndController {
    @ResponseBody
    @GetMapping("/backEnd")
    public List<String> hallo(HttpServletRequest request, HttpServletResponse response)
    {
//        response.addHeader("Access-Control-Allow-Origin","*");
//        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Origin","*");

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        response.setContentType("text/html;charset=UTF-8");

        response.addHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS,PUT,DELETE");
//        response.addHeader("Access-Control-Allow-Expose-Headers","Authorization");

        //获取参数
        String keyWord = request.getParameter("keyWord");

       Main t=new Main();

        //List<> b=t.findWord(keyWord);
        //返回数据

//        PrintWriter out = null;
//        try {
//            out = response.getWriter();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String a = "success";
//        assert out != null;
//        out.print(Main.findWord(keyWord));
        //清出缓存数据,关闭输出流
        //https://www.cnblogs.com/wangshuaiandroid/p/6699884.html
//        out.flush();
//        out.close();
        Map<String,String> map = new HashMap<>();

  
        return Main.findWord(keyWord);
    }
}
