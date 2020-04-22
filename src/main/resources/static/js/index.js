angular.module('myApp', ['ngRoute', 'ngResource']).config(function ($routeProvider) {
    //配置路由
    $routeProvider.when('/home/:type/:id', {//首页
        templateUrl: 'home.html',
        controller: 'HomeController'
    }).when('/hospitalized/:type/:id', {//住院管理
        templateUrl: 'hospitalized.html',
        controller: 'hospitalizedControl'
    }).when('/department/:type/:deptNo', {//科室管理
        templateUrl: 'department.html',
        controller: 'departmentControl'
    }).when('/departmentlist/:type/:deptNo', {//科室管理
        templateUrl: 'departmentlist.html',
        controller: 'departmentlistControl'
    }).when('/patient/:type/:id', {//患者管理
        templateUrl: 'patient.html',
        controller: 'patientControl'
    }).when('/casehistory/:type/:id', {//病历管理
        templateUrl: 'casehistory.html',
        controller: 'casehistoryControl'
    }).when('/userInfo/:type', {//修改信息
        templateUrl: 'userinfo.html',
        controller: 'userinfoCtrl'
    }).when('/updatePassword/:type', {//修改密码
        templateUrl: 'updatepassword.html',
        controller: 'passwordCtrl'
    }).otherwise('/home/1/0')
}).controller("indexController", function ($scope, $location, $window, $rootScope) {
    $scope.keyword = '';
    $rootScope.ntype = '1';
    $rootScope.active = 'active';
    $scope.nctive = 'active';
    $scope.menu = [
        {id: 1, url: '/home', title: '首页', fa: 'fa fa-home'},
        {id: 2, url: '/hospitalized', title: '住院管理', fa: 'fa fa-building-o'},
        {id: 3, url: '/department', title: '科室管理', fa: 'fa fa-cart-plus'},
        {id: 4, url: '/patient', title: '患者管理', fa: 'fa fa-bed'},
        {id: 5, url: '/casehistory', title: '病历管理', fa: 'fa fa-list-alt'}
    ];
    $scope.user = {
        id: '',
        name: '',
        mobile: '',
        email: '',
        deptNo: '',
        age: '',
        gender: '',
        job: '',
        password: ''
    };
    this.$onInit = function () {
        //页面加载时给用户名输入框赋值
        $scope.user.name = $scope.getCookie("doctor").name;
    };

    //获取cookie
    $scope.getCookie = function (cname) {
        let name = cname + "=";
        let ca = document.cookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i];
            //console.log(c)
            while (c.charAt(0) == ' ') c = c.substring(1);
            if (c.indexOf(name) != -1) {
                let doc = c.substring(name.length, c.length).replace("=", ",");
                doc = doc.replace(new RegExp("'", "g"), "\"");
                return JSON.parse(doc);
            }
        }
        return JSON.parse("{}");
    };
    $scope.logout = function () {
        if (confirm("确定要退出当前用户吗?")) {
            $window.location.href = '/user/logout';
        }
    };
    //搜索
    $scope.search = function (event) {
        if (event.keyCode == 13 || event.key == "Enter") { // enter 键
            alert("此处回车触发搜索事件");
            if ($scope.keyword != "") {

            }
        }
    }

}).controller('HomeController', function ($scope, $routeParams, $rootScope) {

    $rootScope.ntype = $routeParams.type;

}).controller('hospitalizedControl', function ($scope, $routeParams, $rootScope) {
    $rootScope.ntype = $routeParams.type;

}).controller('departmentControl', function ($scope, $routeParams, $resource, $rootScope) {
    $rootScope.ntype = $routeParams.type;
    $scope.deptNo = $routeParams.deptNo;
    $scope.selectall=false;
    $scope.selectDoc=false;
    $scope.deptNo = $routeParams.deptNo;
    $scope.deptlist = [];
    //页面加载部门
    $resource('/dept/getDeptInfo', {}).get(function (resp) {
        //请求成功
        $scope.deptlist = resp.deptList;
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    //加载全部医生列表
    $resource('/user/getDoctorByDeptNo', {"deptNo": $scope.deptNo}).get(function (resp) {
        //请求成功
        $scope.doctorsList = resp.doctorList;
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    $scope.selectAll=function () {
        $scope.selectDoc=! $scope.selectall;
    };

    /*            {deptNo: '1',deptName:'不能',deptService:'',totalBeds:'',useBeds:'',freeBeds:'',borrowBeds:'',borrowLevel:'',usage:''},
                {deptNo: '2',deptName:'本页面',deptService:'',totalBeds:'',useBeds:'',freeBeds:'',borrowBeds:'',borrowLevel:'',usage:''}*/


}).controller('departmentlistControl', function ($scope, $resource, $routeParams, $rootScope) {

    $scope.selectall=false;
    $scope.selectDoc=false;
    $scope.deptNo = $routeParams.deptNo;
    $scope.deptlist = [];
    //页面加载部门
    $resource('/dept/getDeptInfo', {"deptNo": $scope.deptNo}).get(function (resp) {
        //请求成功
        $scope.deptlist = resp.deptList;
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    //按部门加载医生列表
    $resource('/user/getDoctorByDeptNo', {"deptNo": $scope.deptNo}).get(function (resp) {
        //请求成功
        $scope.doctorList = resp.doctorList;
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });
    $scope.selectAll=function () {
        $scope.selectDoc=! $scope.selectall;
    };

}).controller('patientControl', function ($scope, $routeParams, $rootScope) {
    $rootScope.ntype = $routeParams.type;

    console.log($scope.type)
}).controller('casehistoryControl', function ($scope, $routeParams, $rootScope) {
    $rootScope.ntype = $routeParams.type;

}).controller('userinfoCtrl', function ($scope, $routeParams, $resource,$rootScope) {
    $rootScope.ntype =  $routeParams.type;
    $rootScope.active = 'active';
    $scope.gender = [{"value": 0, "name": "男"}, {"value": 1, "name": "女"}];

    //页面加载时查询用户信息
    $resource('/user/getUserInfo', {}).get(function (resp) {
        //请求成功
        $scope.user = resp.doctor;
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    //修改信息
    $scope.updateUserInfo = function (user) {
        $resource('/user/updateUserInfo', user).get(function (resp) {
            //请求成功
            alert(resp.msg);
        }, function (err) {
            //处理错误
            alert("网络错误,请重试");
        });
    }


}).controller('passwordCtrl', function ($scope, $routeParams, $resource,$rootScope) {
    $rootScope.ntype =  $routeParams.type;
    $rootScope.active = 'active';
    $scope.pasd = {password: '', password1: '', password2: ''};
    //修改密码
    $scope.updatePassword = function (pasd) {
        if ($scope.pasd.password1 != $scope.pasd.password2) {
            alert("两次输入密码不一致");
            return;
        }
        $resource('/user/updatePassword', pasd).get(function (resp) {
            //请求成功
            alert(resp.msg);
        }, function (err) {
            //处理错误
            alert("网络错误,请重试");
        });
    }
});
