var app = angular.module('erspApp', []);
app.controller('loginCtrl', function($scope, $http) {
	$http({
		method: 'GET',
		url: '/ersp/sso/loginInfo'
	}).then(function successCallback(response) {
			var info = response.data.msg;
			if (info !=null && info != "" && info !="null") {
				$scope.isImg = true;
				$scope.isLogout = true;
				$scope.isLogin = false;
				$scope.isReg = false;
				$scope.addproduct = true;
				$scope.addreq = true;
				$scope.loginfo = "欢迎您："+ response.data.msg +" ！";
			} else {
				$scope.loginfo = "亲，请登录";
				$scope.isImg = false;
				$scope.isLogout = false;
				$scope.isLogin = true;
				$scope.isReg = true;
				$scope.addproduct = false;
				$scope.addreq = false;
			}
		}, function errorCallback(response) {
			
	});   
});

app.directive("adminnavbar", function() {
    return {
    	scope: true,
        restrict : "A",
        templateUrl : "/ersp/site/common/admin-header.html",
        link: function(scope, element, attrs) {  
        	var address = window.location.href;
        	address = address.substring(21);
        	var bbs = element.find("li");
        	for(var i=0;i<bbs.length;i++){
        		var cc = $(bbs[i]).children();
        		for(var j=0;j<cc.length;j++) {
        			var paths = cc[j].toString();
        			paths = paths.substring(21);
        			if (paths==address) {
        				$(bbs[i]).addClass("active");
        			}
        		}
        	}
        }  
    };
});

app.directive("navbar", function() {
    return {
    	scope: true,
        restrict : "A",
        templateUrl : "/ersp/site/common/header.html"
    };
});

$(function () {
	$(".navbar-nav>li").each(function(){
		$(this).click(function(){
			$(".navbar-nav>li").removeClass("active");
			$(this).addClass("active");
		});
	});	
    
    $('#menu-nav .navbar-collapse a').click(function (e) {
        var href = $(this).attr('href');
        var tabId = $(this).attr('data-tab');
        if ('#' !== href) {
            e.preventDefault();
            $(document).scrollTop($(href).offset().top - 70);
            if (tabId) {
                $('#feature-tab a[href=#' + tabId + ']').tab('show');
            }
        }
    }); 
});

function login() {
	window.location.href = "/ersp/login.html";
}

function logout() {
	$.ajax({
		type: 'get',
		dataType:'json',
		url:'/ersp/sso/logout',
		success:function(data){
			var result = data.result;
			if (result) {
				window.location.href = "/ersp/login.html";
			}
		}
	});
}