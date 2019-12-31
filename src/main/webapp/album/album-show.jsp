<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<script>
    $(function () {
        $("#album-table").jqGrid(
            {
                url: '${pageContext.request.contextPath}/album/show',
                datatype: "json",
                height: 300,
                colNames: ['编号', '标题', '封面', '作者', '播音', '分数', '集数', '简介', '时间'],
                editurl: '/cfmz/album/edit',
                colModel: [
                    {name: 'id', key: true, hidden: true},
                    {
                        name: 'title',
                        editable: true
                    },
                    {
                        name: 'cover',
                        editable: true,
                        edittype: 'file',
                        formatter: function (value, options, row) {
                            return '<img style="height: 40px;width:150px" src="${pageContext.request.contextPath}/album/' + 'img/' + row.cover + '"/>';
                        }
                    },
                    {
                        name: 'author',
                        editable: true
                    },
                    {
                        name: 'beam',
                        editable: true
                    },
                    {
                        name: 'score',
                        editable: true
                    },
                    {
                        name: 'count',
                        editable: false
                    },
                    {
                        name: 'intro',
                        editable: true
                    },
                    {
                        name: 'a_create_date',
                        editable: false
                    }
                ],
                styleUI: "Bootstrap",
                height: 300,
                autowidth: true,
                rowNum: 2,
                rowList: [2, 5, 10, 20],
                pager: '#album-pager',
                viewrecords: true,
                multiselect: false,
                subGrid: true,
                caption: "展示专辑",
                subGridRowExpanded: function (subgrid_id, row_id) {//1.当前父容器的id   2.当前专辑的id
                    var subgrid_table_id = subgrid_id + "_t";
                    var pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html(
                        "<table id='" + subgrid_table_id + "' class='scroll'></table>" +
                        "<div id='" + pager_id + "' class='scroll'></div>");
                    $("#" + subgrid_table_id).jqGrid(
                        {
                            url: '${pageContext.request.contextPath}/chap/show?id=' + row_id,
                            datatype: "json",
                            colNames: ['编号', '标题', '大小', '时长', '文件名', '创建时间', '在线播放'],
                            editurl: '/cfmz/chap/edit?album_id=' + row_id,
                            colModel: [
                                {name: "id", key: true, hidden: true},
                                {
                                    name: "title",
                                    editable: true
                                },
                                {
                                    name: "size",
                                    editable: false
                                },
                                {
                                    name: "duration",
                                    editable: false
                                },
                                {
                                    name: "cover",
                                    editable: true,
                                    edittype: 'file'
                                },
                                {
                                    name: "c_create_date",
                                    editable: false
                                },
                                {
                                    name: "aaa", width: 300, formatter: function (value, options, rows) {
                                        return "<audio controls loop>\n" +
                                            "  <source src='${pageContext.request.contextPath}/album/music/" + rows.cover + "' type=\"audio/ogg\">\n" +
                                            "</audio>"
                                    }
                                },
                            ],
                            styleUI: "Bootstrap",
                            autowidth: true,
                            rowNum: 2,
                            pager: pager_id,
                            height: '100%'
                        }).jqGrid('navGrid',
                        "#" + pager_id, {
                            edit: true,
                            add: true,
                            del: false
                        }, {
                            //控制章节修改
                            closeAfterEdit: close,
                            beforeShowForm: function (frm) {
                                frm.find("#cover").attr("disabled", true);
                            }
                        }, {
                            //控制章节的添加
                            closeAfterAdd: close,
                            afterSubmit: function (response) {
                                var id = response.responseJSON.id;
                                var status = response.responseJSON.status;
                                if (status) {
                                    $.ajaxFileUpload({
                                        type: "post",
                                        url: "${pageContext.request.contextPath}/chap/upload",
                                        data: {id: id},
                                        fileElementId: "cover",
                                        success: function () {
                                            $("#" + subgrid_table_id).trigger("reloadGrid");
                                        }
                                    })
                                }
                                return "1231";
                            }
                        });
                },
            }).navGrid("#album-pager", {edit: true, add: true, del: false, search: false}, {
            //控制修改的相关操作
            closeAfterEdit: close,
            beforeShowForm: function (frm) {
                frm.find("#cover").attr("disabled", true);
            }
        }, {
            //控制添加的相关操作
            closeAfterAdd: close,
            afterSubmit: function (response) {
                var id = response.responseJSON.id;
                var status = response.responseJSON.status;
                if (status) {
                    $.ajaxFileUpload({
                        type: "post",
                        url: "${pageContext.request.contextPath}/album/upload",
                        data: {id: id},
                        fileElementId: "cover",
                        success: function () {
                            $("#album-table").trigger("reloadGrid");
                        }
                    })
                }
                return "1231";
            }

        });
    })

    function AlbumOut() {
        window.location.href = "${pageContext.request.contextPath}/album/albumOut"
    }
</script>
<div class="page-header">
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">展示专辑</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" onclick="AlbumOut()"
                                   data-toggle="tab">专辑数据导出</a></li>
    </ul>
</div>
<table id="album-table"></table>
<div id="album-pager" style="height: 80px"></div>