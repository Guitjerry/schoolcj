<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .back{
        background: #c12c42;
    }
</style>

<input name="msg" class="selectmsg" value="${msg}" type="hidden">
<ul id="accordion" class="accordion">

    <li>
        <div class="link"><i class="glyphicon glyphicon-home"></i>基本信息管理<i class="fa fa-chevron-down"></i></div>
        <ul class="submenu">
            <li><a href="#" class="checked">年级管理</a></li>
            <li><a href="#">班级管理</a></li>
            <li><a href="#">科目管理</a></li>
            <li><a href="#">老师管理</a></li>
            <li><a href="#">学生管理</a></li>
        </ul>
    </li>
    <li>
        <div class="link"><i class="glyphicon glyphicon-book"></i>作业管理<i class="fa fa-chevron-down"></i></div>
        <ul class="submenu">
            <li><a href="#">作业布置</a></li>
            <li><a href="#">作业反馈</a></li>
        </ul>
    </li>
    <li>
        <div class="link"><i class="glyphicon glyphicon-lock"></i>权限管理<i class="fa fa-chevron-down"></i></div>
        <ul class="submenu">
            <li><a href="#">用户管理</a></li>
            <li msg="role"><a href="/role/roleList?msg=role">角色管理</a></li>
            <li msg="source"><a href="/resource/resourceList?msg=source">资源管理</a></li>
        </ul>
    </li>

</ul>
<script>
    $(function() {
        $(".submenu li").each(function () {
            if($(this).attr("msg")==$('.selectmsg').val()){
                $(this).addClass("back");
                $(this).parent('.submenu').show();
            }
        })
        var Accordion = function(el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;

            // Variables privadas
            var links = this.el.find('.link');
            // Evento
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
        }

        Accordion.prototype.dropdown = function(e) {
            var $el = e.data.el;
            $this = $(this),
                    $next = $this.next();

            $next.slideToggle();
            $this.parent().toggleClass('open');

            if (!e.data.multiple) {
                $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
            };
        }

        var accordion = new Accordion($('#accordion'), false);
    });
</script>