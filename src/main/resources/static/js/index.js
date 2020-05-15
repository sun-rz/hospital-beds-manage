var indexpp = angular.module('myApp', ['ngRoute', 'ngResource', 'ngAnimate']).config(function ($routeProvider) {
    //配置路由
    $routeProvider.when('/home/:type/:id', {//首页
        templateUrl: 'home/home.html',
        controller: 'HomeController'
    }).when('/hospitalized/:type/:deptNo', {//住院管理
        templateUrl: '/hospitalized/hospitalized.html',
        controller: 'hospitalizedControl'
    }).when('/hospitalizedlist/:type/:deptNo', {//住院管理
        templateUrl: '/hospitalized/hospitalized_list.html',
        controller: 'hospitalizedlistCtrl'
    }).when('/edithospitalized/:bedNo', {//住院管理
        templateUrl: '/hospitalized/hospitalized_edit.html',
        controller: 'edithospitalizedCtrl'
    }).when('/department/:type/:deptNo', {//科室管理
        templateUrl: 'dept/department.html',
        controller: 'departmentControl'
    }).when('/departmentlist/:type/:deptNo', {//科室管理
        templateUrl: 'dept/department_list.html',
        controller: 'departmentlistControl'
    }).when('/editdepartment/:deptNo', {//科室管理
        templateUrl: 'dept/department_edit.html',
        controller: 'editdepartmentCtrl'
    }).when('/beds/:type/:deptNo', {//床位管理
        templateUrl: 'beds/beds.html',
        controller: 'bedsCtrl'
    }).when('/bedslist/:type/:deptNo', {//床位管理
        templateUrl: 'beds/beds_list.html',
        controller: 'bedslistCtrl'
    }).when('/editbeds/:deptNo/:bedNo', {//床位编辑
        templateUrl: 'beds/beds_edit.html',
        controller: 'editbedsCtrl'
    }).when('/editdoctor/:doctorId/:deptNo', {//医护人员管理
        templateUrl: 'user/doctor_edit.html',
        controller: 'editdoctorCtrl'
    }).when('/patient/:type/:id', {//患者管理
        templateUrl: 'patient/patient.html',
        controller: 'patientControl'
    }).when('/casehistory/:type/:id', {//病历管理
        templateUrl: 'patient/casehistory.html',
        controller: 'casehistoryControl'
    }).when('/editPatient/:id', {//病历管理
        templateUrl: 'patient/patient_edit.html',
        controller: 'editPatientCtrl'
    }).when('/userInfo/:type', {//修改信息
        templateUrl: 'user/userinfo.html',
        controller: 'userinfoCtrl'
    }).when('/updatePassword/:type', {//修改密码
        templateUrl: 'user/updatepassword.html',
        controller: 'passwordCtrl'
    }).otherwise('/home/1/0')
}).controller("indexController", function ($scope, $location, $window, $rootScope, $resource) {
    $scope.keyword = '';

    //解决刷新后无法选中
    let path = $location.path();
    var x = path.indexOf("/");
    for (var i = 0; i < 1; i++) {
        x = path.indexOf("/", x + 1);
    }
    path = path.substring(x + 1, path.lastIndexOf("/"));
    $rootScope.ntype = path;

    $rootScope.active = 'active';
    $scope.nctive = 'active';
    $scope.menu = [
        {id: 1, url: '/home', title: '首页', fa: 'fa fa-home'},
        {id: 2, url: '/hospitalized', title: '住院管理', fa: 'fa fa-building-o'},
        {id: 4, url: '/patient', title: '患者管理', fa: 'fa fa-user-circle-o'},
        {id: 5, url: '/casehistory', title: '病历管理', fa: 'fa fa-list-alt'},
        {id: 6, url: '/beds', title: '床位管理', fa: 'fa fa-bed'}
    ];

    $scope.user = {};
    $resource('/user/getUserInfo', {}).get(function (resp) {
        //请求成功
        $scope.user = resp.doctor;

        //管理员菜单权限控制
        if ($scope.user.mobile == 'admin' || $scope.user.email == 'admin@qq.com') {
            $scope.menu.push({id: 3, url: '/department', title: '科室管理', fa: 'fa fa-cart-plus'})
        }
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

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
    };

}).controller('HomeController', function ($scope, $routeParams, $rootScope) {

    $rootScope.ntype = $routeParams.type;

}).controller('hospitalizedControl', function ($scope, $routeParams, $rootScope, $resource) {
    $rootScope.ntype = $routeParams.type;
    $scope.inCount=0;
    $scope.outCount=0;
    //页面加载部门
    $resource('/dept/getDeptInfoByCondition', {"condition": "totalBeds>0"}).get(function (resp) {
        //请求成功
        $scope.deptlist = resp.deptList;
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    //页面加载患者
    $resource('/hospitalized/getHospitalizedPaient', {}).get(function (resp) {
        //请求成功
       $scope.paientList = resp.paientList;
        for (let i = 0; i < $scope.paientList.length; i++) {
            switch ($scope.paientList[i].patient_status) {
                case 1:
                    $scope.outCount += 1;
                    break;
                default:
                    $scope.inCount += 1;
            }
        }
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

}).controller("hospitalizedlistCtrl", function ($scope, $resource, $routeParams, $location, $window) {
    $scope.deptNo = $routeParams.deptNo;
    $scope.inCount=0;
    $scope.outCount=0;
    //页面加载部门
    $resource('/dept/getDeptInfoByCondition', {"condition": "totalBeds>0"}).get(function (resp) {
        //请求成功
        $scope.deptlist = resp.deptList;
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    //页面加载患者
    $resource('/hospitalized/getHospitalizedPaient', {deptNo:$scope.deptNo}).get(function (resp) {
        //请求成功
        $scope.paientList = resp.paientList;

        for (let i = 0; i < $scope.paientList.length; i++) {
            switch ($scope.paientList[i].patient_status) {
                case 1:
                    $scope.outCount += 1;
                    break;
                default:
                    $scope.inCount += 1;
            }
        }
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });
}).controller('edithospitalizedCtrl',function ($scope, $routeParams, $resource) {
    $scope.bedNo=$routeParams.bedNo;
    $scope.gender = [{"value": 0, "name": "男"}, {"value": 1, "name": "女"}];
    //页面加载部门
    $resource('/dept/getDeptInfo', {"deptNo": $scope.deptNo}).get(function (resp) {
        //请求成功
        $scope.deptlist = resp.deptList;
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    $resource('/patient/getPatientInfoByBedNo', {bedNo:$scope.bedNo}).get(function (resp) {
        //请求成功
        $scope.patient = resp.patient;
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    //
    $scope.outHospital=function (p) {
        if(confirm("是否为该患者办理出院手续")){
            $resource('/hospitalized/outHospital', {bedNo:p.bedNo,patientID:p.id,out_hospital_date:p.out_hospital_date}).get(function (resp) {
                //请求成功
                alert(resp.msg);
                history.back();
            }, function (err) {
                //处理错误
                alert("网络错误,请重试");
            });
        }
    }

}).controller('departmentControl', function ($scope, $routeParams, $resource, $rootScope) {
    $rootScope.ntype = $routeParams.type;
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
}).controller('departmentlistControl', function ($scope, $resource, $routeParams, $location, $window) {
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

    //多选删除
    $scope.checked = []; //定义一个数组 存入id或者想要用来交互的参数...
    $scope.selectAll = function () {
        if ($scope.select_all) { //判断是全选
            $scope.checked = [];//先清空，防止在操作了一个轮回之后，重复添加了...
            angular.forEach($scope.doctorList, function (i) {  //doctorList这是循环从后台获取的数组，并添加到刚刚定义的数组里
                i.checked = true; //全选即将所有的复选框变为选中
                $scope.checked.push(i.id);//将选中的内容放到数组里
            })
        } else {//判断全不选
            angular.forEach($scope.doctorList, function (i) {
                i.checked = false; //所有复选框为不选中
                $scope.checked = [];//将数组清空
            })
        }
    };

    //单选
    $scope.selectOne = function () {//下面的复选框单独点击
        angular.forEach($scope.doctorList, function (i) {//依旧是循环......
            var index = $scope.checked.indexOf(i.id);//检索checked中是否有i.id 如果没有则会返回-1
            if (i.checked && index === -1) {
                $scope.checked.push(i.id);
            } else if (!i.checked && index !== -1) {
                $scope.checked.splice(index, 1);
            }
        });
        if ($scope.doctorList.length === $scope.checked.length) {//判断checked数组的长度是否与原来请求的后台数组的长度是否相等 即是否给全选框加上选中
            $scope.select_all = true;
        } else {
            $scope.select_all = false;
        }
    };

    //删除人员
    $scope.deleteDoc = function () {
        if ($scope.checked.length == 0) {
            alert("请至少选择一条记录");
            return;
        }
        if (confirm("该操作不可恢复，是否要删除选中数据？")) {
            $resource('/user/deleteUserInfo', {"userID": JSON.stringify($scope.checked)}).get(function (resp) {
                //请求成功
                $window.location.reload();
                alert(resp.msg);
            }, function (err) {
                //处理错误
                alert("网络错误,请重试");
            });
        }
    };

    //删除部门
    $scope.deleteDept = function (dept) {
        if (dept.ID == undefined || dept.ID == "") {
            alert("请选择一条记录");
            return;
        }
        if (confirm("该操作不可恢复，是否要删除选中数据？")) {
            $resource('/dept/deleteDept', {"deptNo": dept.ID}).get(function (resp) {
                //请求成功
                $window.location.reload();
                alert(resp.msg);
            }, function (err) {
                //处理错误
                alert("网络错误,请重试");
            });
        }
    };

}).controller('editdepartmentCtrl', function ($scope, $resource, $routeParams, $location, $window) {
    $scope.deptNo = $routeParams.deptNo;
    $scope.dept = {};
    $scope.title = "修改部门信息";
    if ($scope.deptNo > 0) {
        $scope.action = "edit";
        $resource('/dept/getDeptInfoById', {"deptNo": $scope.deptNo}).get(function (resp) {
            //请求成功
            $scope.dept = resp.dept;
        }, function (err) {
            //处理错误
            alert("网络错误,请重试");
        });
    } else {
        $scope.action = "add";
        $scope.title = "新增部门"
    }

    //编辑
    $scope.updateDeptInfo = function (dept) {
        let url = $scope.action == "edit" ? "/dept/updateDeptInfo" : "/dept/addDeptInfo";
        $resource(url, dept).get(function (resp) {
            //请求成功
            history.back();
            alert(resp.msg);
        }, function (err) {
            //处理错误
            alert("网络错误,请重试");
        });
    }

}).controller("editdoctorCtrl", function ($scope, $resource, $routeParams, $window) {
    $scope.doctorId = $routeParams.doctorId;
    $scope.deptNo = $routeParams.deptNo;
    $scope.doctor = {};
    $scope.title = "修改用户信息";
    $scope.gender = [{"value": 0, "name": "男"}, {"value": 1, "name": "女"}];
    //页面加载职务de

    $resource('/dept/getJobInfo', {userId: $scope.doctorId}).get(function (resp) {
        //请求成功
        $scope.jobList = resp.jobInfo;

    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    //页面加载部门
    $resource('/dept/getDeptInfo', {"deptNo": $scope.deptNo}).get(function (resp) {
        //请求成功
        $scope.deptlist = resp.deptList;
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    if ($scope.doctorId > 0) {
        $scope.action = "edit";
        //页面加载时查询用户信息
        $resource('/user/getUserInfo', {userId: $scope.doctorId}).get(function (resp) {
            //请求成功
            $scope.doctor = resp.doctor;
        }, function (err) {
            //处理错误
            alert("网络错误,请重试");
        });
    } else {
        $scope.action = "add";
        $scope.title = "新增用户";
        $scope.doctor.deptNo = $scope.deptNo

    }
    $scope.updateUserInfo = function (doc) {
        let url = $scope.action == "edit" ? "/user/updateUserInfo" : "/user/register";
        doc.captchacode = "NotVerification";
        doc.action = "register";
        $resource(url, doc).get(function (resp) {
            //请求成功
            history.back();
            alert(resp.msg);
        }, function (err) {
            //处理错误
            alert("网络错误,请重试");
        });
    }
}).controller('patientControl', function ($scope, $routeParams, $rootScope, $resource, $window) {
    $rootScope.ntype = $routeParams.type;
    $scope.patientList = [];

    $resource('/patient/getPatientInfo', {}).get(function (resp) {
        //请求成功
        $scope.patientList = resp.patientList;
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    //多选删除
    $scope.checked = []; //定义一个数组 存入id或者想要用来交互的参数...
    $scope.selectAll = function () {
        if ($scope.select_all) { //判断是全选
            $scope.checked = [];//先清空，防止在操作了一个轮回之后，重复添加了...
            angular.forEach($scope.patientList, function (i) {  //doctorList这是循环从后台获取的数组，并添加到刚刚定义的数组里
                i.checked = true; //全选即将所有的复选框变为选中
                $scope.checked.push(i.id);//将选中的内容放到数组里
            })
        } else {//判断全不选
            angular.forEach($scope.patientList, function (i) {
                i.checked = false; //所有复选框为不选中
                $scope.checked = [];//将数组清空
            })
        }
    };

    //单选
    $scope.selectOne = function () {//下面的复选框单独点击
        angular.forEach($scope.patientList, function (i) {//依旧是循环......
            var index = $scope.checked.indexOf(i.id);//检索checked中是否有i.id 如果没有则会返回-1
            if (i.checked && index === -1) {
                $scope.checked.push(i.id);
            } else if (!i.checked && index !== -1) {
                $scope.checked.splice(index, 1);
            }
        });
        if ($scope.patientList.length === $scope.checked.length) {//判断checked数组的长度是否与原来请求的后台数组的长度是否相等 即是否给全选框加上选中
            $scope.select_all = true;
        } else {
            $scope.select_all = false;
        }
    };

    $scope.deletePatient = function () {
        if ($scope.checked.length == 0) {
            alert("请至少选择一条记录");
            return;
        }
        if (confirm("该操作不可恢复，是否要删除选中数据？")) {
            $resource('/patient/deletePatient', {"patientID": JSON.stringify($scope.checked)}).get(function (resp) {
                //请求成功
                $window.location.reload();
                alert(resp.msg);
            }, function (err) {
                //处理错误
                alert("网络错误,请重试");
            });
        }
    };

}).controller('casehistoryControl', function ($scope, $routeParams, $rootScope, $resource, $window) {
    $rootScope.ntype = $routeParams.type;
    $scope.casehistoryList = [];
    $resource('/casehistory/getCaseHistory', {}).get(function (resp) {
        //请求成功
        $scope.casehistoryList = resp.casehistoryList;
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    //多选删除
    $scope.checked = []; //定义一个数组 存入id或者想要用来交互的参数...
    $scope.selectAll = function () {
        if ($scope.select_all) { //判断是全选
            $scope.checked = [];//先清空，防止在操作了一个轮回之后，重复添加了...
            angular.forEach($scope.casehistoryList, function (i) {  //doctorList这是循环从后台获取的数组，并添加到刚刚定义的数组里
                i.checked = true; //全选即将所有的复选框变为选中
                $scope.checked.push(i.id);//将选中的内容放到数组里
            })
        } else {//判断全不选
            angular.forEach($scope.casehistoryList, function (i) {
                i.checked = false; //所有复选框为不选中
                $scope.checked = [];//将数组清空
            })
        }
    };

    //单选
    $scope.selectOne = function () {//下面的复选框单独点击
        angular.forEach($scope.casehistoryList, function (i) {//依旧是循环......
            var index = $scope.checked.indexOf(i.id);//检索checked中是否有i.id 如果没有则会返回-1
            if (i.checked && index === -1) {
                $scope.checked.push(i.id);
            } else if (!i.checked && index !== -1) {
                $scope.checked.splice(index, 1);
            }
        });
        if ($scope.casehistoryList.length === $scope.checked.length) {//判断checked数组的长度是否与原来请求的后台数组的长度是否相等 即是否给全选框加上选中
            $scope.select_all = true;
        } else {
            $scope.select_all = false;
        }
    };

    $scope.deleteCasehistory = function () {
        if ($scope.checked.length == 0) {
            alert("请至少选择一条记录");
            return;
        }
        if (confirm("该操作不可恢复，是否要删除选中数据？")) {
            $resource('/casehistory/deleteCaseHistory', {"caseHistoryID": JSON.stringify($scope.checked)}).get(function (resp) {
                //请求成功
                $window.location.reload();
                alert(resp.msg);
            }, function (err) {
                //处理错误
                alert("网络错误,请重试");
            });
        }
    };
}).controller('editPatientCtrl', function ($scope, $routeParams, $resource) {
    $scope.patient = {};
    $scope.msg = "";
    $scope.casehistory = {};
    $scope.patientID = $routeParams.id;
    $scope.title = "修改患者信息";
    $scope.casehistory_title = "修改病历信息";
    $scope.gender = [{"value": 0, "name": "男"}, {"value": 1, "name": "女"}];
    //页面加载部门
    $resource('/dept/getDeptInfo', {"deptNo": $scope.deptNo}).get(function (resp) {
        //请求成功
        $scope.deptlist = resp.deptList;
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    if ($scope.patientID > 0) {
        $scope.action = "edit";
        //页面加载时查询患者信息
        $resource('/patient/getPatientInfo', {id: $scope.patientID}).get(function (resp) {
            //请求成功
            $scope.patient = resp.patientList[0];
        }, function (err) {
            //处理错误
            alert("网络错误,请重试");
        });

        //页面加载时查询病历信息
        $resource('/casehistory/getCaseHistory', {patientID: $scope.patientID}).get(function (resp) {
            //请求成功
            $scope.casehistory = resp.casehistoryList[0];
            if (undefined == $scope.casehistory) {
                $scope.casehistory = {}
            }
        }, function (err) {
            //处理错误
            alert("网络错误,请重试");
        });

    } else {
        $scope.action = "add";
        $scope.title = "新增患者信息";
        $scope.casehistory_title = "新增病历信息";
    }

    $resource('/user/getDoctorByDeptNo', {"deptNo": $scope.patient.deptNo}).get(function (resp) {
        //请求成功
        $scope.doctorsList = resp.doctorList;
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    $scope.findBed = function (p) {
        console.log(p)

        if (!p.deptNo) {
            alert("请选择科室信息");
            return;
        }

        let cid = angular.element(document.querySelector("#doctorID")).val();

        if (!cid) {
            alert("请选择主治医生");
            return;
        }

        $resource('/beds/getBedsByRule', {"deptNo": p.deptNo, level: p.level, doctorID: cid}).get(function (resp) {
            //请求成功
            console.log(resp)
            if(resp.success){
                $scope.patient.bedNo=resp.bed.bedNo;
                $scope.msg="床位分配成功";
                $scope.cls="succ-true";
            }else{
                $scope.msg="床位分配失败，请稍后再试";
                $scope.cls="succ-false";
                $scope.lateOutHospitalPatent=resp.patientList;
            }
        }, function (err) {
            //处理错误
            alert("网络错误,请重试");
        });
    }

    $scope.updatePatientInfo = function (p, c) {
        c.doctorID = angular.element(document.querySelector("#doctorID")).val();
        c.status = angular.element(document.querySelector("#cstatus")).val();

        p.casehistory = c;
        let url = $scope.action == "edit" ? "/patient/updatePatientInfo" : "/patient/addPatientInfo";
        $resource(url, p).get(function (resp) {
            //请求成功
            history.back();
            alert(resp.msg);
        }, function (err) {
            //处理错误
            alert("网络错误,请重试");
        });
    };

    $scope.getDocByDept = function (p) {
        //根据部门加载医生列表
        $resource('/user/getDoctorByDeptNo', {"deptNo": p.patient.deptNo}).get(function (resp) {
            //请求成功
            $scope.doctorsList = resp.doctorList;
        }, function (err) {
            //处理错误
            alert("网络错误,请重试");
        });
    }
}).controller('bedsCtrl', function ($scope, $routeParams, $resource, $rootScope, $window) {
    $rootScope.ntype = $routeParams.type;
    $scope.useCount = 0;
    $scope.freeCount = 0;
    $scope.serviceCount = 0;

    //页面加载部门
    $resource('/dept/getDeptInfoByCondition', {"condition": "totalBeds>0"}).get(function (resp) {
        //请求成功
        $scope.deptlist = resp.deptList;
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    //初始化病床
    $resource('/beds/autoModeBeds', {}).get(function (resp) {
        //请求成功
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    //加载病床
    $resource('/beds/getBeds', {}).get(function (resp) {
        //请求成功
        $scope.bedList = resp.bedList;

        for (let i = 0; i < $scope.bedList.length; i++) {
            switch ($scope.bedList[i].status) {
                case 0:
                    $scope.freeCount += 1;
                    break;
                case 1:
                    $scope.useCount += 1;
                    break;
                case 2:
                    $scope.serviceCount += 1;
                    break;
            }
        }
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    //获取状态名称
    $scope.getStatusTitle = function (status) {
        let str = "";
        switch (status) {
            case 1:
                str = "占用";
                break;
            case 2:
                str = "维修";
                break;
            default:
                str = "空闲";
        }
        return str;
    }

    $scope.deleteBed = function (b) {
        if (confirm("该操作不可恢复，是否要删除该病床？")) {
            $resource('/beds/deleteBedByBedNo', b).get(function (resp) {
                //请求成功
                alert(resp.msg);
                $window.location.reload();
            }, function (err) {
                //处理错误
                alert("网络错误,请重试");
            });
        }
    }
}).controller('bedslistCtrl', function ($scope, $routeParams, $resource, $window) {
    $scope.deptNo = $routeParams.deptNo;
    $scope.useCount = 0;
    $scope.freeCount = 0;
    $scope.serviceCount = 0;
    //页面加载部门
    $resource('/dept/getDeptInfoByCondition', {"condition": "totalBeds>0"}).get(function (resp) {
        //请求成功
        $scope.deptlist = resp.deptList;
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    //加载病床
    $resource('/beds/getBeds', {deptNo: $scope.deptNo}).get(function (resp) {
        //请求成功
        $scope.bedList = resp.bedList;

        for (let i = 0; i < $scope.bedList.length; i++) {
            switch ($scope.bedList[i].status) {
                case 0:
                    $scope.freeCount += 1;
                    break;
                case 1:
                    $scope.useCount += 1;
                    break;
                case 2:
                    $scope.serviceCount += 1;
                    break;
            }
        }
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    //获取状态名称
    $scope.getStatusTitle = function (status) {
        let str = "";
        switch (status) {
            case 1:
                str = "占用";
                break;
            case 2:
                str = "维修";
                break;
            default:
                str = "空闲";
        }
        return str;
    };

    $scope.deleteBed = function (b) {
        if (confirm("该操作不可恢复，是否要删除该病床？")) {
            $resource('/beds/deleteBedByBedNo', b).get(function (resp) {
                //请求成功
                alert(resp.msg);
                $window.location.reload();
            }, function (err) {
                //处理错误
                alert("网络错误,请重试");
            });
        }
    }
}).controller('editbedsCtrl', function ($scope, $routeParams, $resource) {
    $scope.deptNo = $routeParams.deptNo;
    $scope.bedNo = $routeParams.bedNo;
    $scope.bed = {};

    //加载病床
    $resource('/beds/getBed', {bedNo: $scope.bedNo, deptNo: $scope.deptNo}).get(function (resp) {
        //请求成功
        $scope.bed = resp.bed[0];
    }, function (err) {
        //处理错误
        alert("网络错误,请重试");
    });

    //更新状态
    $scope.updateBedStatus = function (b) {
        $resource('/beds/updateBedStatus', b).get(function (resp) {
            //请求成功
            history.back();
            alert(resp.msg);
        }, function (err) {
            //处理错误
            alert("网络错误,请重试");
        });
    }

}).controller('userinfoCtrl', function ($scope, $routeParams, $resource, $rootScope) {
    $rootScope.ntype = $routeParams.type;
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

    //页面加载部门
    $resource('/dept/getDeptInfo', {"deptNo": $scope.deptNo}).get(function (resp) {
        //请求成功
        $scope.deptlist = resp.deptList;
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


}).controller('passwordCtrl', function ($scope, $routeParams, $resource, $rootScope) {
    $rootScope.ntype = $routeParams.type;
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
