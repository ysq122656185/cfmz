<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"></script>
<script>
    $(function () {
        $('#tt').jqGrid({
            // 加入整合bootstrap的属性
            styleUI: 'Bootstrap',
            url: '/cfmz/lb/show',
            datatype: 'json',
            colNames: ['编号', '名称', '图片', '描述', '状态', '创建时间'],
            editurl: '/cfmz/lb/edit',
            colModel: [
                {name: 'l_id', key: true, hidden: true},
                {
                    name: 'l_name',
                    editable: true
                },
                {
                    name: 'l_cover',
                    editable: true,
                    edittype: 'file',
                    formatter: function (value, options, row) {
                        return '<img style="height: 40px;width:150px" src="${pageContext.request.contextPath}/' + 'img/' + row.l_cover + '"/>';
                    }
                },
                {
                    name: 'l_describe',
                    editable: true
                },
                {
                    name: 'l_status',
                    editable: true,

                },
                {
                    name: 'l_create_date',
                    editable: false
                }
            ],
            autowidth: true,
            // 加入分页
            pager: '#pg',
            rowList: [6, 7, 8, 9],
            rowNum: 6,
            page: 1,
            viewrecords: true,
            mtype: 'GET',// 指定请求的方式  POST|GET ，默认请求方式为get
            sortname: 'l_id',// 指定排序的字段， 请求参数的key为sidx
            sortorder: 'asc', // 指定排序的规则,  请求参数的key为sord
            caption: '轮播图管理',// 展示表格的标题
            height: 400,// 设置数据表格高度
            toolbar: ['true', 'both'],
            viewrecords: true
        }).navGrid('#pg', {add: true, edit: true, del: true, search: true, refresh: true},
            {// 控制编辑的参数
                closeAfterEdit: true, // 编辑提交后关闭窗口
                beforeShowForm: function (frm) {
                    frm.find("#l_cover").attr("disabled", true);
                }

            },
            { ///控制添加操作
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var id = response.responseJSON.id;
                    var status = response.responseJSON.status;
                    if (status == "addOk") {
                        $.ajaxFileUpload({
                            url: "/cfmz/lb/upload",//用于文件上传的服务器端请求地址
                            fileElementId: 'l_cover',
                            type: 'post',
                            data: {id: id},
                            success: function () {
                                $("#tt").trigger("reloadGrid");
                            }
                        })
                        return "true";
                    }
                }
            },
            {},// 控制删除
            {// 控制搜索
                closeAfterSearch: true // 搜索提交后关闭窗口
            });
        // 在toobar指定的工具栏处，自定义自己的操作工具元素
    })

    function lbimgOut() {
        window.location.href = "${pageContext.request.contextPath}/lb/lbOut"
    }
</script>
<div>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">展示轮播图</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" onclick="lbimgOut()"
                                   data-toggle="tab">轮播数据导出</a></li>
    </ul>
</div>
<table id="tt">
</table>
<div id="pg" style="height: 40px"></div>