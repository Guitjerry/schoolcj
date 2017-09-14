<ul id="sourcetree" class="ztree"></ul>
<script>
    var setting = {
        isSimpleData : true,              //数据是否采用简单 Array 格式，默认false
        treeNodeKey : "id",               //在isSimpleData格式下，当前节点id属性
        treeNodeParentKey : "parentid",        //在isSimpleData格式下，当前节点的父节点id属性
        showLine : true,                  //是否显示节点间的连线
        checkable : true                  //每个节点上是否显示 CheckBox
    };

    var zTree;
    var treeNodes;
    $(function(){
        $.ajax({
            async : false,
            cache:false,
            type: 'POST',
            dataType : "json",
            url: "fpResource",//请求的action路径
            error: function () {//请求失败处理函数
                toastrErrorMessage('请求失败',"错误提示")

            },
            success:function(data){ //请求成功后处理函数。
                alert(data);
                treeNodes = data;   //把后台封装好的简单Json格式赋给treeNodes
            }
        });

        zTree = $("#sourcetree").zTree(setting, treeNodes);
    });
</script>