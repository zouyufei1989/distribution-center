var attrs = [];
$(document).ready(function () {
    new Vue({el: '#groupId'});
    // 加载画面权限数据
    findByIdOverride();
});

function additionParam(param) {
    param = {
        roleRights : buildRoleRights(),
        role : {
            id : JS_PAGE_PARAMS['id'],
            name : $('#nameForRole').val(),
            groupId:$('#groupId').val(),
        }
    };

    return param;
}

// 加载画面权限信息
function findByIdOverride() {
	var url = "queryUserRoleRights";
	if (JS_PAGE_PARAMS['id']) {
		url += "?id=" + JS_PAGE_PARAMS['id'];
	}
	$.post(url, function(result) {
		if (result.success) {
			
			if(result.role){
				$('#nameForRole').val(result.role.name);
				$('#groupId').val(result.role.groupId).trigger('change');
			}
			
			var treeNodes = buildUrlTreeNode(result.data);
			var funcNodes = [];
			$.each(result.data, function(index, item) {
				funcNodes = funcNodes.concat(buildFuncTreeNode(item));
			});

			$('#authority').on("changed.jstree", function(e, data) {
				if (!data.node) {
					return;
				}
				// 当用户取消选中查看，该页面其他权限也取消
				if (data.node.a_attr.type == 'visit' && data.action == "deselect_node" && $('#authority').jstree("get_node", data.node.parent)) {
					var childrenIds = $('#authority').jstree("get_node", data.node.parent).children;
					$.each(childrenIds, function(index, id) {
						$('#authority').jstree("deselect_node", id);
					});
					return;
				}
				// 当用户选择非查看权限，则默认查看权限也选中
				if (data.node.a_attr.type == 'func_method' && data.action == "select_node" && $('#authority').jstree("get_node", data.node.parent)) {
					var childrenIds = $('#authority').jstree("get_node", data.node.parent).children;
					$.each(childrenIds, function(index, id) {
						if ($('#authority').jstree("get_node", id).a_attr.type == 'visit') {
							$('#authority').jstree("select_node", id);
						}
					});
					return;
				}

			}).jstree({
				'core' : {
					'data' : treeNodes.concat(funcNodes),
					"themes" : {
						"variant" : "large"
					}
				},
				"plugins" : [ "checkbox", "types" ],
				"types" : {
					"default" : {
						"icon" : "glyphicon glyphicon-flash"
					}
				}
			});
		} else {
			Alert("", "获取数据失败!", "error");
		}
	});
}

function buildUrlTreeNode(resourceList) {
	return resourceList.map(function(item) {
		return {
			"id" : item.id.toString(),
			"parent" : item.parentId == 0 ? "#" : item.parentId.toString(),
			"text" : item.name,
			"icon" : item.icon ? item.icon + " fa" : "",
			'state' : {
				'opened' : true,
			},
			"a_attr" : {
				'type' : 'menu',
				'rightId' : item.rightId
			}
		}
	})
}

function buildFuncTreeNode(resourceItem) {
	var funcItems = [];
	// 如果用户有该right，则默认有该页面的查看权限
	if (resourceItem.parentId != 0) {
		funcItems.push({
			"id" : "func_visit_" + resourceItem.id,
			"parent" : resourceItem.id.toString(),
			"text" : "查看",
			"icon" : "fa-youtube-play fa",
			'state' : {
				'opened' : true,
				'selected' : resourceItem.nrrId != null
			},
			"a_attr" : {
				'type' : 'visit',
				'rightId' : resourceItem.rightId
			}
		});
	}

	if (!resourceItem.resourceFuncs) {
		return funcItems;
	}

	$.each(JS_FUNC_LIST, function(index, funcItem) {
		if ((resourceItem.resourceFuncs & funcItem.fun_id) == funcItem.fun_id) {
			funcItems.push({
				"id" : "func_" + resourceItem.id + "_" + funcItem.fun_id,
				"parent" : resourceItem.id.toString(),
				"text" : funcItem.btn_name,
				"icon" : "fa-youtube-play fa",
				'state' : {
					'opened' : true,
					'selected' : (funcItem.fun_id & resourceItem.roleFuncs) == funcItem.fun_id
				},
				"a_attr" : {
					'type' : 'func_method',
					'func_id' : funcItem.fun_id
				}
			});
		}
	})
	return funcItems;
}

function buildRoleRights() {
	var roleRights = [];
	var allCheckedNodes = $('#authority').jstree("get_checked", true);
	$.each(allCheckedNodes, function(index, node) {
		if (node.a_attr && node.a_attr.type == 'visit') {
			var resourceId = node.parent;
			var funcIds = 0;
			$.each(allCheckedNodes, function(i, funcNode) {
				if (node.a_attr && funcNode.a_attr.type == 'func_method' && funcNode.parent == resourceId) {
					funcIds += funcNode.a_attr.func_id;
				}
			});

			roleRights.push({
				roleId : JS_PAGE_PARAMS['id'],
				rightId : node.a_attr.rightId,
				funcIds : funcIds
			});
		}
	});
	return roleRights;
}
