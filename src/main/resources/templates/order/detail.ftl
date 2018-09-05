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
                <div class="col-md-4 column">
                    <table class="table table-hover table-condensed table-bordered">
                        <thead>
                        <tr class="text-center">
                            <th class="text-center">订单id</th>
                            <th class="text-center">订单总金额</th>
                            <th class="text-center">订单状态</th>
                            <th class="text-center">创建时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.buyerAmount}</td>
                            <td>${orderDTO.getOrderStatusEnum().msg}</td>
                            <td>${orderDTO.createTime}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <table class="table table-hover table-condensed table-bordered table-striped">
                        <thead>
                        <tr class="text-center">
                            <th class="text-center">商品id</th>
                            <th class="text-center">商品名称</th>
                            <th class="text-center">价格</th>
                            <th class="text-center">数量</th>
                            <th class="text-center">总额</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list orderDTO.orderDetailList as detail>
                            <tr>
                                <td>${detail.productId}</td>
                                <td>${detail.productName}</td>
                                <td>${detail.productPrice}</td>
                                <td>${detail.productQuantity}</td>
                                <td>${detail.productPrice * detail.productQuantity}</td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <#if orderDTO.getOrderStatusEnum().code == 0>
                        <a type="button" class="btn btn-default btn-primary btn-sm" href="/sell/seller/order/finish?orderId=${orderDTO.orderId}">完结订单</a>
                        <a type="button" class="btn btn-default btn-danger btn-sm" href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消订单</a>
                    </#if>
                    <a type="button" class="btn btn-default btn-success btn-sm" href="/sell/seller/order/list">返回</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>


