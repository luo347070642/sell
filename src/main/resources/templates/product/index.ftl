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
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                            <label for="exampleInputEmail1">名称</label>
                            <input type="text" class="form-control" id="productName" name="productName" value="${(productInfo.productName)!''}" />
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">价格</label>
                            <input type="number" class="form-control" id="productPrice" name="productPrice" value="${(productInfo.productPrice)!''}" />
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">库存</label>
                            <input type="number" class="form-control" id="productStock" name="productStock" value="${(productInfo.productStock)!''}" />
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">介绍</label>
                            <input type="text" class="form-control" id="productDescription" name="productDescription" value="${(productInfo.productDescription)!''}" />
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">图片</label>
                            <img src="${(productInfo.productIcon)!''}" width="50px" height="50px">
                            <input type="text" class="form-control" id="productIcon" name="productIcon" value="${(productInfo.productIcon)!''}" />
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">类目</label>
                            <select name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}" <#if (productInfo.categoryType)?? && category.categoryType == (productInfo.categoryType)!''>selected="selected"</#if>>${category.categoryName}</option>
                                </#list>
                            </select>
                        </div>
                        <input type="hidden" name="productId" value="${(productInfo.productId)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>


