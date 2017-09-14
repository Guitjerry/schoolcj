<%@ page import="com.xiaoyuan.util.Const" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<c:import url="../../common/include.jsp"></c:import>--%>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title" id="myModalLabel">修改资源</h4>
</div>
<div class="modal-body">
    <div class="input-group f-mb10 f-pd5">
        <span class="input-group-addon" id="basic-addon1">资源名称</span>
        <input type="text" class="form-control" name="name" id="name" placeholder="请输入资源名称" aria-describedby="basic-addon1" value="${resource.name}">
    </div>

    <div class="input-group f-mt10 f-pd5">
        <span class="input-group-addon" >菜单级别</span>

        <select class="form-control depath" name="depath" id="depth" default-select="${resource.depth}">
            <option value="">---------请选择——-----</option>
            <c:forEach items="<%=Const.getDepthArray()%>" var="os">
                <option value="${os.code}">${os.name}</option>
            </c:forEach>
        </select>
    </div>

    <div class="parentdiv input-group f-mt10 f-pd5 f-none" >
        <span class="input-group-addon" >上级菜单</span>

        <select class="form-control parentselect" name="parentid"  >
            <option value="">---------请选择——-----</option>
            <c:forEach items="${parentList}" var="parentResource">
                <option value="${parentResource.id}">${parentResource.name}</option>
            </c:forEach>
        </select>
        <input name="parentid" type="hidden" class="parentid" value="${resource.parentid}">
    </div>



    <div class="input-group f-mt10 f-pd5">
        <span class="input-group-addon" >链接地址</span>
        <input type="text" class="form-control" name="link" id="link" placeholder="请输入链接地址" value="${resource.link}">
    </div>

    <div class="input-group f-mt10 f-mb20 f-pd5">
        <span class="input-group-addon" >图标样式</span>


        <button class="btn  f-ml20 iconbtn" style="width: 20%"   onclick="loadediticon('${resource.icon}')"><span class="${resource.icon} f-ml10"></span></button>
        <input type="hidden" class="form-control" name="icon" id="icon" placeholder="图标样式" value="${resource.icon}" >
    </div>

    <!--图标选择-->
    <div id="editiconmodel"></div>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    <button type="button" class="btn btn-primary" onclick="editChildrenSource(${resource.id})">保存</button>
</div>

<script>
    $(function () {

        $('#editiconmodel').load("/init/initicon", function () {
            $('.bs-glyphicons').addClass("f-none");


        });
        $('.bs-glyphicons-list li').each(function () {
            if($(this).children('.glyphicon-class').text()==icon){
                $(this).addClass('selecticon');
                $(this).children('.glyphicon-class').addClass('wfff');

            }

        })

        $(".depath option").each(function () {

            if($(this).val()== $('.depath').attr("default-select")){

                $(this).attr("selected","selected");
            }
        })
        //加载父类
        $('.depath').on('change',function () {
            if($(this).val()=="2"){
                $('.parentdiv').removeClass('f-none');
            }else{
                $('.parentdiv').addClass('f-none');
                $('.parentselect').val("");

            }
        })

        if($('.parentid').val()!=null){
            $('.parentdiv').removeClass('f-none');
            //初始化父菜单
            $(".parentselect option").each(function () {
                if($(this).val()== $('.parentid').val()){

                    $(this).attr("selected","selected");
                }
            })
        }



    })
    //        //加载图标

    function loadediticon(icon) {

        if( $('.bs-glyphicons').hasClass('f-none')){
            $('.bs-glyphicons').removeClass('f-none').addClass("f-block");
        }else{
            $('.bs-glyphicons').removeClass("f-block").addClass("f-none");
        }


    }

    //更新资源信息
    function  editChildrenSource(resourceid) {
        var depth = $('#depth').val();
        var param={"name":$("#name").val(),"link":$("#link").val(),"depth":depth,"icon":$("#icon").val(),"id":resourceid,"parentid":$('.parentselect').val()};
        $.ajax({
            url:"editResourceSure",
            data:param,
            success:function (data) {
                var obj = eval('('+data+')')
                if(obj.status=="success"){
                    $("#editchildrenModal").modal({show:false});
                    toastrSuccessMessage(obj.msg,"信息提示");
                    setTimeout(function () {
                        location.reload();
                    },1000)

                }
            }
        });
    }


</script>
