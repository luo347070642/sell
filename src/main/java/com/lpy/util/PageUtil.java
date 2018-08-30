package com.lpy.util;

import lombok.Getter;

/**
 * @Author: 罗鹏远
 * @description:
 * @Date: created in 21:51 2018/8/30
 */
@Getter
public class PageUtil {
    private Integer rowCount;//总共有多少条数据
    private Integer navCount;//总共有多少个导航
    private Integer currentPage;// 当前页
    private Integer startRow;//起始行
    private Integer pageSize;//每页有多少条
    private Integer firstPage;//首页
    private Integer lastPage;//末页
    private Integer nextPage;//下一页
    private Integer prevPage;//上一页
    private Integer startNav;//起始导航
    private Integer endNav;//结束导航

    public PageUtil() {
    }

    /**
     *
     * @param rowCount  总共有多少条数据
     * @param currentPage   当前页
     */
    public PageUtil(Integer rowCount,Integer navCount,Integer currentPage,Integer pageSize){
        //初始化属性
        this.rowCount=rowCount;
        this.currentPage =currentPage;
        this.pageSize =pageSize;
        this.navCount=this.rowCount% this.pageSize >0?this.rowCount/ this.pageSize +1:this.rowCount/ this.pageSize;
        this.startRow=(currentPage-1)*10;
        this.firstPage=1;
        this.lastPage=this.navCount;
        //当前页+1>最后一页？最后一页：当前页+1
        this.nextPage=currentPage+1>this.lastPage?this.lastPage:currentPage+1;
        //当前页-1<1?1:当前页-1
        this.prevPage=currentPage-1<1?this.firstPage:currentPage-1;
        if(currentPage-5<=1){
            this.startNav=this.firstPage;
            this.endNav=10>this.lastPage?this.lastPage:10;
        }else if(currentPage+4>=this.lastPage){
            this.startNav=this.lastPage>=10?this.lastPage-9:1;
            this.endNav=this.lastPage;
        }else{
            this.startNav=currentPage-5;
            this.endNav=currentPage+4;
        }
    }

}
