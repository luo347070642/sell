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
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单
            </div>
            <div class="modal-footer">
                <button onclick="document.getElementById('notice').pause();" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>
    </div>
</div>
<audio id="notice" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg">
</audio>
<script>
    function cancel(orderId) {
        location.href="/sell/seller/order/cancel?orderId="+orderId;
    }
    var oWebsocket = null;
    if("WebSocket" in window){
        oWebsocket = new WebSocket("ws://birdsell.mynatapp.cc/sell/webSocket");
    }else{
        alert("该浏览器不支持WebSocket");
    }
    oWebsocket.onopen = function (event) {
        console.info("建立连接");
    }
    oWebsocket.onclose = function (event) {
        console.info("关闭连接");
    }
    oWebsocket.onmessage = function (event) {
        console.info("收到消息:"+event.data);
        //弹框提醒、播放音乐
        $("#myModal").modal("show");
        document.getElementById("notice").play();
    }
    oWebsocket.onerror = function () {
        alert("WebSocket通讯发生错误")
    }
    window.onbeforeunload = function () {
        oWebsocket.close();
    }
</script>
</body>
</html>