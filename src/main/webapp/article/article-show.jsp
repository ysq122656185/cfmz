<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
<script>
    $(function () {
        $("#article-table").jqGrid({
            url: '${pageContext.request.contextPath}/article/selectAllArticle',
            datatype: "json",
            colNames: ['编号', '标题', '作者', '内容', '时间', '操作'],
            colModel: [
                {name: 'id', hidden: true},
                {name: 'title', editable: true},
                {name: 'author', editable: true},
                {name: 'content', editable: true},
                {name: 'a_create_date'},
                {
                    name: 'aaa', formatter: function (value, options, rows) {
                        return "<a class='btn btn-success' onclick=\"openModal('edit','" + rows.id + "')\" >修改文章</a>"
                    }
                }
            ],
            styleUI: "Bootstrap",
            height: 300,
            autowidth: true,
            rowNum: 2,
            rowList: [2, 5, 10],
            pager: '#article-pager',
            viewrecords: true,
            caption: "展示文章数据",
            editurl: "${pageContext.request.contextPath}/article/edit"
        }).navGrid("#article-pager", {edit: false, add: false, del: true, search: false})
    })

    function openModal(oper, id) {
        KindEditor.html("#editor_id", "");
        var article = $("#article-table").jqGrid("getRowData", id);
        //文章的数据赋值给form表单
        $("#article-title").val(article.title);
        $("#article-author").val(article.author);
        $("#article-id").val(article.id);
        KindEditor.html("#editor_id", article.content);
        $("#article-oper").val(oper);
        $("#article-modal").modal("show");
    }

    KindEditor.create("#editor_id", {
        allowFileManager: true,
        uploadJson: "${pageContext.request.contextPath}/kindeditor/upload",
        filePostName: "img",
        fileManagerJson: "${pageContext.request.contextPath}/kindeditor/getAll",
        resizeType: 1,
        //将文本域中的值同步到form当中
        afterBlur: function () {
            this.sync();
        }
    });

    function dealSave() {
        $.ajax({
            url: "${pageContext.request.contextPath}/article/edit",
            type: "post",
            data: {
                id: $("#article-id").val(),
                oper: $("#article-oper").val(),
                title: $("#article-title").val(),
                author: $("#article-author").val(),
                content: $(document.getElementsByTagName('iframe')[0].contentWindow.document.body).html()
            },
            dataType: "json",
            success: function () {
                $("#article-modal").modal("hide");
                $("#article-table").trigger("reloadGrid");
            }
        })
    }

    function ArticleOut() {
        window.location.href = "${pageContext.request.contextPath}/article/articleOut"
    }
</script>
<%--标签页--%>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">展示文章</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" onclick="openModal('add')"
                                   data-toggle="tab">添加文章</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" onclick="ArticleOut()"
                                   data-toggle="tab">专辑数据导出</a></li>
    </ul>
</div>
<table id="article-table"></table>
<div id="article-pager" style="height: 80px"></div>
<%--模态框--%>
<div class="modal fade" id="article-modal" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 702px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">添加文章</h4>
            </div>
            <div class="modal-body">
                <form id="article-form" class="form-horizontal">
                    <input id="article-id" type="hidden" name="id">
                    <input id="article-oper" type="hidden" name="oper">
                    <div class="form-group">
                        <label for="article-title">标题</label>
                        <input type="text" name="title" id="article-title" class="form-control" placeholder="title">
                    </div>
                    <div class="form-group">
                        <label for="article-author">作者</label>
                        <input type="text" name="author" class="form-control" id="article-author" placeholder="author">
                    </div>
                    <div class="form-group">
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                        </textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="dealSave()">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->