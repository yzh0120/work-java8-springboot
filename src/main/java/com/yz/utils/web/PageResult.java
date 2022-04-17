package com.yz.utils.web;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private long total;

    private List<T> records;

    public PageResult() {

    }

    public PageResult(List<T> records, long total) {
        this.total = total;

        this.records = records;
    }

//    public Page(PageInfo<T> pageInfo) {
//        this.total = pageInfo.getTotal();
////        this.curPage = pageInfo.getPageNum();
////        this.pageSize = pageInfo.getPageSize();
////        this.pages =  pageInfo.getPages();
////        this.size = pageInfo.getSize();
//        this.records = pageInfo.getList();
//    }

        // //IService
//    public static  <T> PageResult<T> defaultPageList(Page<T> page, Wrapper<T> queryWrapper, BaseMapper baseMapper){
//        IPage pageObj = baseMapper.selectPage(page, queryWrapper);
//        PageResult pageResult = new PageResult(pageObj.getRecords(), pageObj.getTotal());
//        System.out.println(pageResult +"pageData");
//        return pageResult;
//
//    }

    public static  <T> PageResult<T> defaultPageList(Page<T> page, Wrapper<T> queryWrapper, IService service){
        IPage pageObj = service.page(page, queryWrapper);
        PageResult pageResult = new PageResult(pageObj.getRecords(), pageObj.getTotal());
        System.out.println(pageResult +"pageData");
        return pageResult;

    }

}