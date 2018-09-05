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
                            <th class="text-center">订单id</th>
                            <th class="text-center">姓名</th>
                            <th class="text-center">手机号</th>
                            <th class="text-center">地址</th>
                            <th class="text-center">金额</th>
                            <th class="text-center">订单状态</th>
                            <th class="text-center">支付状态</th>
                            <th class="text-center">创建时间</th>
                            <th class="text-center" colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list orderDTOPage.content as orderDTO>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.buyerName}</td>
                                <td>${orderDTO.buyerPhone}</td>
                                <td>${orderDTO.buyerAddress}</td>
                                <td>${orderDTO.buyerAmount}</td>
                                <td>${orderDTO.getOrderStatusEnum().msg}</td>
                                <td>${orderDTO.getPayStatusEnum().msg}</td>
                                <td>${orderDTO.createTime}</td>
                                <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                                <td><#if orderDTO.getOrderStatusEnum().code == 0><a href="javascript:void(0)" onclick="cancel('${orderDTO.orderId}')">取消</a></#if></td>
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
                    <li><a href="/sell/seller/order/list?page=${pageUtil.prevPage}&size=${pageUtil.pageSize}">上一页</a></li>
                </#if>
                <#list pageUtil.startNav..pageUtil.endNav as index>
                    <#if pageUtil.currentPage == index>
                        <li class="disabled"><a href="javascript:void(0);">${index}</a></li>
                    <#else>
                        <li><a href="/sell/seller/order/list?page=${index}&size=${pageUtil.pageSize}">${index}</a></li>
                    </#if>
                </#list>
                <#if pageUtil.currentPage gte pageUtil.navCount>
                    <li class="disabled"><a href="javascript:void(0);">下一页</a></li>
                <#else>
                    <li><a href="/sell/seller/order/list?page=${pageUtil.nextPage}&size=${pageUtil.pageSize}">下一页</a></li>
                </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function cancel(orderId) {
        location.href="/sell/seller/order/cancel?orderId="+orderId;
    }
</script>
</html>


