

document.writeln('<ul id="accordion" class="accordion">'+
'<li>'+
'<div class="link"><i class="glyphicon glyphicon-home"></i>基本信息管理</div>'+
   '<ul class="submenu">'+
    '<li><a href="#">年级管理</a></li>'+
    '<li><a href="#">班级管理</a></li>'+
    '<li><a href="#">科目管理</a></li>'+
    '<li><a href="#">老师管理</a></li>'+
    '<li><a href="#">学生管理</a></li>'+
    '</ul>'+
    '</li>'+
    '<li>'+
    '<div class="link"><i class="glyphicon glyphicon-book"></i>作业管理<i class="fa fa-chevron-down"></i></div>'+
     '<ul class="submenu">'+
   '<li><a href="#">作业布置</a></li>'+
    '<li><a href="#">作业反馈</a></li>'+
   ' </ul>'+
    '</li>'+
    '<li>'+
    '<div class="link"><i class="glyphicon glyphicon-lock"></i>权限管理<i class="fa fa-chevron-down"></i></div>'+
    '<ul class="submenu">'+
    '<li><a href="#">用户管理</a></li>'+
    '<li msg="role"  class="checked"><a href="role/list.html?msg=role">角色管理</a></li>'+
    '<li msg="role"><a href="resource/list.html?msg=resource">资源管理</a></li>'+
    '</ul>'+
    '</li>'+


    '</ul>');
