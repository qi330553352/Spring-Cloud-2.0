package com.example.qixin.web;

import com.example.qixin.entity.Users;
import com.example.qixin.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** http://localhost:8080/book
 * Thymeleaf 整合
 * 创  建   时  间： 2019/1/27 13:13
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Controller
@RequestMapping(value = "/book")
public class BookController {
    private static final String BOOK_FORM_PATH_NAME = "bookForm";
    private static final String BOOK_LIST_PATH_NAME = "bookList";
    private static final String REDIRECT_TO_BOOK_URL = "redirect:/book";

    @Autowired
    private UsersService usersService;

    /**
     * 获取 Book 列表
     * 处理 "/book" 的 GET 请求，用来获取 Book 列表
     */
    @Cacheable(value = "getBookList")
    @RequestMapping(method = RequestMethod.GET)
    public String getBookList(ModelMap map) {
        map.addAttribute("bookList",usersService.findAll());
        return BOOK_LIST_PATH_NAME;
    }

    /**
     * 获取创建 Book 表单
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createBookForm(ModelMap map) {
        map.addAttribute("book", new Users());
        map.addAttribute("action", "create");
        return BOOK_FORM_PATH_NAME;
    }

    /**
     * 创建 Book
     * 处理 "/book/create" 的 POST 请求，用来新建 Book 信息
     * 通过 @ModelAttribute 绑定表单实体参数，也通过 @RequestParam 传递参数
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postBook(@ModelAttribute Users book) {
        usersService.insertByBook(book);
        return REDIRECT_TO_BOOK_URL;
    }

    /**
     * 获取更新 Book 表单
     *    处理 "/book/update/{id}" 的 GET 请求，通过 URL 中的 id 值获取 Book 信息
     *    URL 中的 id ，通过 @PathVariable 绑定参数
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable Long id, ModelMap map) {
        map.addAttribute("book", usersService.findById(id));
        map.addAttribute("action", "update");
        return BOOK_FORM_PATH_NAME;
    }

    /**
     * 更新 Book
     * 处理 "/update" 的 PUT 请求，用来更新 Book 信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String putBook(@ModelAttribute Users book) {
        usersService.update(book);
        return REDIRECT_TO_BOOK_URL;
    }

    /**
     * 删除 Book
     * 处理 "/book/{id}" 的 GET 请求，用来删除 Book 信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable Long id) {
        usersService.delete(id);
        return REDIRECT_TO_BOOK_URL;
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            // UPLOADED_FOLDER 文件本地存储地址
            Path path = Paths.get("D:\\logs\\" + file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message","You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/uploadStatus";
    }

    @PostMapping("/uploadMore")
    public String moreFileUpload(@RequestParam("file") MultipartFile[] files,
                                 RedirectAttributes redirectAttributes) {
        if (files.length==0) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        for(MultipartFile file:files){
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get("D:\\logs\\" + file.getOriginalFilename());
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded all");
        return "redirect:/uploadStatus";
    }
}
