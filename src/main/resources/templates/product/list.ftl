<!doctype html>
<html lang="en">
<#--head公用内容-->
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <#--主要内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-hover table-condensed table-bordered table-striped">
                        <thead>
                        <tr class="text-center">
                            <th class="text-center">商品id</th>
                            <th class="text-center">名称</th>
                            <th class="text-center">图片</th>
                            <th class="text-center">单价</th>
                            <th class="text-center">库存</th>
                            <th class="text-center">描述</th>
                            <th class="text-center">类目</th>
                            <th class="text-center">创建时间</th>
                            <th class="text-center">更新时间</th>
                            <th class="text-center" colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list productInfoPage.content as productInfo>
                            <tr>
                                <td>${productInfo.productId}</td>
                                <td>${productInfo.productName}</td>
                                <td><img src="${productInfo.productIcon}" width="50" height="50"></td>
                                <td>${productInfo.productPrice}</td>
                                <td>${productInfo.productStock}</td>
                                <td>${productInfo.productDescription}</td>
                                <td>${productInfo.categoryType}</td>
                                <td>${productInfo.createTime}</td>
                                <td>${productInfo.updateTime}</td>
                                <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                                <td>
                                    <#if productInfo.getProductStatusEnum().code == 0>
                                        <a href="/sell/seller/product/off_sale?productId=${productInfo.productId}">下架</a>
                                    <#else>
                                        <a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">上架</a>
                                    </#if>

                                </td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
                <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                <#if pageUtil.currentPage lte 1>
                    <li class="disabled"><a href="javascript:void(0);">上一页</a></li>
                <#else>
                    <li><a href="/sell/seller/product/list?page=${pageUtil.prevPage}&size=${pageUtil.pageSize}">上一页</a></li>
                </#if>
                <#list pageUtil.startNav..pageUtil.endNav as index>
                    <#if pageUtil.currentPage == index>
                        <li class="disabled"><a href="javascript:void(0);">${index}</a></li>
                    <#else>
                        <li><a href="/sell/seller/product/list?page=${index}&size=${pageUtil.pageSize}">${index}</a></li>
                    </#if>
                </#list>
                <#if pageUtil.currentPage gte pageUtil.navCount>
                    <li class="disabled"><a href="javascript:void(0);">下一页</a></li>
                <#else>
                    <li><a href="/sell/seller/product/list?page=${pageUtil.nextPage}&size=${pageUtil.pageSize}">下一页</a></li>
                </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>


