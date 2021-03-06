package com.bookstore.controller;

import com.bookstore.common.util.IDUtil;
import com.bookstore.common.util.JsonUtils;
import com.bookstore.common.util.MessageResult;
import com.bookstore.pojo.po.Book;
import com.bookstore.pojo.po.BookCategory;
import com.bookstore.pojo.vo.BookVO;
import com.bookstore.service.BookCategoryService;
import com.bookstore.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
public class BookController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BookCategoryService bookCategoryService;
    @Autowired
    private BookService bookService;
    //查询所有的图书  默认按照书名的模糊查询
    @RequestMapping(value = "/listBooks")
    public String listAdmins(Model model , @RequestParam(value = "pn",defaultValue ="1")Integer pn, @RequestParam(defaultValue = "") String query, HttpSession session) {
        String encodeQuery = null;
        try {
            encodeQuery = java.net.URLDecoder.decode(query, "UTF-8");
            System.out.println(encodeQuery);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        PageHelper.startPage(pn,4);
        List<Book> bookList = bookService.listBooks(query);
        PageInfo<Book> pageInfo = new PageInfo(bookList);
        session.setAttribute("query", encodeQuery);
        model.addAttribute("pageInfo",pageInfo);
        return "book_list";
    }

   // 上架
   @RequestMapping("/upBook/{ids}")
   @ResponseBody
   public MessageResult upBook(@PathVariable List<Long> ids){

       MessageResult mr = null;
       try {
           mr = bookService.upBook(ids);
       }catch (Exception e){
           logger.error(e.getMessage(),e);
       }
       return mr;
   }
    @RequestMapping("/downBook/{ids}")
    @ResponseBody
    public MessageResult downBook(@PathVariable List<Long> ids){

        MessageResult mr = null;
        try {
            mr = bookService.downBook(ids);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return mr;
    }

    //
    @RequestMapping("/addInventory/{ids}/{inventory}")
    @ResponseBody
    public MessageResult addBookInventory(@PathVariable List<Long> ids,@PathVariable int inventory){

        MessageResult mr = null;
        try {
            mr = bookService.addBookInventory(ids,inventory);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return mr;
    }
    @RequestMapping("/subInventory/{ids}/{inventory}")
    @ResponseBody
    public MessageResult subBookInventory(@PathVariable List<Long> ids,@PathVariable int inventory){

        MessageResult mr = null;
        try {
            mr = bookService.subBookInventory(ids,inventory);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return mr;
    }

    @ResponseBody
    @RequestMapping("/bookById")
    public BookVO getBookById(Long id){
        BookVO bookVO = bookService.getBookForBack(id);
        return bookVO;
    }

    @ResponseBody
    @RequestMapping("/upload/UploadController")
    public MessageResult  springUpload(Book book, HttpServletRequest request) throws IllegalStateException, IOException
    {

        List<String> imagesPath =new ArrayList<>();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();

            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    System.out.println(file.getOriginalFilename());
                    int i = file.getOriginalFilename().lastIndexOf(".");
                    String suffixName = file.getOriginalFilename().substring(i,file.getOriginalFilename().length());
                    String path="D://springUpload/"+ IDUtil.generateDocumentName()+"."+suffixName;
                    imagesPath.add(path);
                    //上传
                    file.transferTo(new File(path));
                }
            }
        }
        book.setCreated(new Date());
        book.setImage(JsonUtils.objectToJson(imagesPath));
        //book.setImage(JsonUtils.objectToJson(imagesPath));
        MessageResult messageResult = bookService.saveBook(book);
        return messageResult;
    }

    @ResponseBody
    @RequestMapping("/book/category")
    public List<BookCategory> listBookCategories(){

        List<BookCategory> list =null;
        try {
            list = bookCategoryService.listBookCategories();
        }catch (Exception e){

            logger.error(e.getMessage(),e);
        }
        return list;
    }
    // 首页新书推荐 3本
    @RequestMapping("/listNewBooks")
    @ResponseBody
    public List<BookVO> listNewBooks(){
        List<BookVO> list = bookService.listBook_new();
        return list;
    }

    //主编推荐
    @RequestMapping("/listRecommondedBooks")
    @ResponseBody
    public List<BookVO> listBook_recommended(){
        List<BookVO> list = bookService.listBook_recommended();
        return list;
    }
    //独家特供
    @RequestMapping("/listSpecialBooks")
    @ResponseBody
    public List<BookVO> listBook_specialSupply(){
        List<BookVO> list = bookService.listBook_specialSupply();
        return list;
    }


    @RequestMapping("/getBookById")
    public ModelAndView getBookById(Long id,ModelAndView modelAndView){

        BookVO bookVO = bookService.getBookForProtal(id);
        modelAndView.setViewName("protal_detail");
        modelAndView.addObject("bookVO",bookVO);
        return modelAndView;
    }
}
