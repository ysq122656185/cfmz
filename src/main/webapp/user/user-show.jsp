<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $('#user_table').jqGrid({
            // 加入整合bootstrap的属性
            styleUI: 'Bootstrap',
            url: '${pageContext.request.contextPath}/user/show',
            datatype: 'json',
            colNames: ['编号', '账号', '激活码', '法号', '省份', '城市', 'sign', '性别', '头像', '状态', '电话', '创建时间', '上师名'],
            editurl: '${pageContext.request.contextPath}/user/edit',
            colModel: [
                {name: 'id', key: true, hidden: true},
                {
                    name: 'username',
                    editable: false
                },
                {
                    name: 'salt',
                    editable: false
                },
                {
                    name: 'dharma',
                    editable: false
                },
                {
                    name: 'province',
                    editable: false
                },
                {
                    name: 'city',
                    editable: false
                },
                {
                    name: 'sign',
                    editable: false
                },
                {
                    name: 'sex',
                    editable: false
                },
                {
                    name: 'photo',
                    editable: false,
                    edittype: 'file',
                    formatter: function (value, options, row) {
                        return '<img style="height: 40px;width:150px" src="${pageContext.request.contextPath}/user/' + 'img/' + row.photo + '"/>';
                    }
                },
                {
                    name: 'status',
                    editable: true
                },
                {
                    name: 'phone',
                    editable: false
                },
                {
                    name: 'u_create_date',
                    editable: false
                },
                {
                    name: 'g_name',
                    editable: false
                }
            ],
            autowidth: true,
            // 加入分页
            pager: '#user_page',
            rowList: [6, 7, 8, 9],
            rowNum: 6,
            page: 1,
            viewrecords: true,
            mtype: 'GET',// 指定请求的方式  POST|GET ，默认请求方式为get
            caption: '展示用户',// 展示表格的标题
            height: 400,// 设置数据表格高度
        }).navGrid('#user_page', {add: false, edit: true, del: false, search: true, refresh: true},
            {// 控制编辑的参数
                closeAfterEdit: true, // 编辑提交后关闭窗口
            },
            {},
            {},// 控制删除
            {// 控制搜索
                closeAfterSearch: true // 搜索提交后关闭窗口
            });
        // 在toobar指定的工具栏处，自定义自己的操作工具元素
    })

    function UserOut() {
        window.location.href = "${pageContext.request.contextPath}/user/userOut"
    }
</script>

<div class="page-header">
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">展示用户</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" onclick="UserOut()"
                                   data-toggle="tab">用户数据导出</a></li>
    </ul>
</div>
<table id="user_table">
</table>
<div id="user_page" style="height: 40px"></div>