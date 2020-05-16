var symptomName = last_year_month();
$(function(){

    $.get("/bigData/getHospitalCount", {}, function (result) {
        console.log(result);
        init(result);
        $("#count_zmzrs").html(result.getHospitalCount[0].counts);
        $("#count_blbgs").html(result.getHospitalCount[0].counts);
        $("#count_dymzrs").html(result.getHospitalCount[1].counts);
        $("#count_zzyrs").html(result.getHospitalCount[2].counts);
        $("#count_dyzyrs").html(result.getHospitalCount[3].counts);
        $("#count_swrs").html(result.getHospitalCount[4].counts);
    }, "json").fail(function () {
        alert('服务器异常，请刷新页面或重新登录');
    });
});

function init(result){

    var myColor = ['#1089E7', '#F57474', '#56D0E3', '#F8B448', '#8B78F6'];

    //主要传染病
    var histogramChart1 = echarts.init(document.getElementById('histogramChart1'));
    histogramChart1.setOption({

        color:['#5bc0de'],
        grid:{
            left: '5%',
            right: '5%',
            bottom: '5%',
            containLabel: true
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a}<br/>{b}<br/>{c}人"
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ['感染性腹泻','流行性感冒','登革热','手足口病','水痘','流行性眼腺炎','猩红热','甲型病毒性肝炎','疟疾'],
                axisLine:{
                    lineStyle:{
                        color: '#5bc0de'
                    },
                },
                axisLabel : {
                    interval:0,
                    rotate:40,
                    textStyle: {
                        color: '#fff'
                    }
                }
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLine:{
                    lineStyle:{
                        color: '#5bc0de'
                    },
                },
                splitLine: {
                    "show": false
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff'
                    },
                    formatter: function (value) {
                        return value + ""
                    },
                },
            }
        ],
        series : [
            {
                name:'主要传染病',
                type:'bar',
                barWidth : 20,
                data:[1300,1085,926,669,634,452,412,312,156],
            },
        ]
    })

    //主要症状
    var histogramChart2 = echarts.init(document.getElementById('histogramChart2'));
    histogramChart2.setOption({

        color:['#FD6C88'],
        grid:{
            left: '5%',
            right: '5%',
            bottom: '10%',
            containLabel: true
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a}<br/>{b}<br/>{c}人"
        },
        calculable : true,
        yAxis : [
            {
                type : 'category',
                data : ['腹痛、腹胀、腹泻','恶心、呕吐、食欲不振','肌肉酸痛、乏力','持续高烧','头痛、眼眶痛、肌肉疼','皮疹、水泡','呼吸浅促','发热、咳嗽、流口水'],
                axisLine:{
                    lineStyle:{
                        color: '#FD6C88'
                    },
                },
                axisLabel : {
                    textStyle: {
                        color: '#fff'
                    }
                }
            }
        ],
        xAxis : [
            {
                type : 'value',
                axisLine:{
                    lineStyle:{
                        color: '#FD6C88'
                    },
                },
                splitLine: {
                    "show": false
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff'
                    },
                    formatter: function (value) {
                        return value + ""
                    },
                },
            }
        ],
        series : [
            {
                name:'主要症状',
                type:'bar',
                barWidth : 20,
                data:[1750,1416,1136,819,704,413,251,175],
            },
        ]
    })

    //传染病发病趋势
    var lineChart1 = echarts.init(document.getElementById('lineChart1'));
    lineChart1.setOption({
        title: {
            text: '传染病趋势',
            textStyle:{
                fontSize:16,
                color:'#ff7f50'
            },
        },
        color:["#ff7f50"],
        grid:{
            left: '15%',
            right: '5%',
            bottom: '15%',

        },
        tooltip : {
            trigger: 'item',
            formatter: "{a}<br/>{b}<br/>{c}人"
        },

        calculable : true,
        yAxis: [
            {
                type: 'value',
                axisLine:{
                    lineStyle:{
                        color: '#ff7f50'
                    },
                },
                splitLine: {
                    "show": false
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff'
                    },
                    formatter: function (value) {
                        return value + ""
                    },
                },
            }
        ],
        xAxis: [
            {
                type: 'category',
                data : symptomName,
                boundaryGap : false,
                axisLine:{
                    lineStyle:{
                        color: '#ff7f50'
                    },
                },
                splitLine: {
                    "show": false
                },
                axisLabel: {
                    // interval:0,
                    // rotate:40,
                    textStyle: {
                        color: '#fff'
                    },
                    formatter: function (value) {
                        return value + ""
                    },
                },
            }
        ],
        series : [
            {
                name:'传染病人数',
                type:'line',
                smooth:true,
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data:getDatas(result.getPatientMonthCount,symptomName)
            },
        ]

    })

    //主要疾病排行
    var histogramChart3 = echarts.init(document.getElementById('histogramChart3'));
    histogramChart3.setOption({

        grid: {
            top: '12%',
            left: '30%'
        },
        xAxis: {
            show: false
        },
        yAxis: [{
            show: true,
            data:  ['抑郁症','高血压','痔疮','肺癌','子宫肌瘤	','乙肝','水痘','肺结核'],
            inverse: true,
            axisLine: {
                show: false
            },
            splitLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                color: '#fff',
                formatter: (value, index) => {
                    return [

                        `{lg|${index+1}}  ` + '{title|' + value + '} '
                    ].join('\n')
                },
                rich: {
                    lg: {
                        backgroundColor: '#339911',
                        color: '#fff',
                        borderRadius: 15,
                        // padding: 5,
                        align: 'center',
                        width: 15,
                        height: 15
                    },
                }
            },


        }, {
            show: true,
            inverse: true,
            data: [2000, 1800, 1200, 1100,900,900,800,700],
            axisLabel: {
                textStyle: {
                    fontSize: 12,
                    color: '#fff',
                },
            },
            axisLine: {
                show: false
            },
            splitLine: {
                show: false
            },
            axisTick: {
                show: false
            },

        }],
        series: [{
            name: '条',
            type: 'bar',
            yAxisIndex: 0,
            data: [20,18,12,11,9,9,8,7],
            barWidth: 10,
            itemStyle: {
                normal: {
                    barBorderRadius: 20,
                    color: function(params) {
                        var num = myColor.length;
                        return myColor[params.dataIndex % num]
                    },
                }
            },
            label: {
                normal: {
                    show: true,
                    position: 'inside',
                    formatter: '{c}%'
                }
            },
        }, {
            name: '框',
            type: 'bar',
            yAxisIndex: 1,
            barGap: '-100%',
            data: [100, 100, 100, 100,100, 100, 100, 100],
            barWidth: 15,
            itemStyle: {
                normal: {
                    color: 'none',
                    borderColor: '#00c1de',
                    borderWidth: 1,
                    barBorderRadius: 15,
                }
            }
        }, ]
    })

    //疾病发病趋势
    var lineChart2 = echarts.init(document.getElementById('lineChart2'));
    lineChart2.setOption({
        title: {
            text: '疾病发病趋势',
            textStyle:{
                fontSize:16,
                color:'#32cd32'
            },
            x:"center"
        },
        color:["#32cd32"],
        grid:{
            left: '15%',
            right: '5%',
            bottom: '25%',

        },
        tooltip : {
            trigger: 'item',
            formatter: "{a}<br/>{b}<br/>{c}人"
        },

        calculable : true,
        yAxis: [
            {
                type: 'value',
                axisLine:{
                    lineStyle:{
                        color: '#32cd32'
                    },
                },
                splitLine: {
                    "show": false
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff'
                    },
                    formatter: function (value) {
                        return value + ""
                    },
                },
            }
        ],
        xAxis: [
            {
                type: 'category',
                data : symptomName,
                boundaryGap : false,
                axisLine:{
                    lineStyle:{
                        color: '#32cd32'
                    },
                },
                splitLine: {
                    "show": false
                },
                axisLabel: {
                    // interval:0,
                    // rotate:40,
                    textStyle: {
                        color: '#fff'
                    },
                    formatter: function (value) {
                        return value + ""
                    },
                },
            }
        ],
        series : [
            {
                name:'疾病发病人数',
                type:'line',
                smooth:true,
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data:getDatas(result.getPatientMonthCount,symptomName)
            },
        ]

    });


    var lineChart_2 = echarts.init(document.getElementById('lineChart_2'));
    lineChart_2.setOption({

        color:["#87cefa","#ff7f50","#32cd32","#da70d6",],
        legend: {
            y : '330',
            x : 'center',
            textStyle : {
                color : '#ffffff',

            },
            data :_getDept(result.getDeptNoByCount),
        },
        calculable : false,
        tooltip : {
            trigger: 'item',
            formatter: "{a}<br/>{b}<br/>{c}条"
        },
        yAxis: [
            {
                type: 'value',
                axisLine : {onZero: false},
                axisLine:{
                    lineStyle:{
                        color: '#034c6a'
                    },
                },

                axisLabel: {
                    textStyle: {
                        color: '#fff'
                    },
                    formatter: function (value) {
                        return value + "人"
                    },
                },
                splitLine:{
                    lineStyle:{
                        width:0,
                        type:'solid'
                    }
                }
            }
        ],
        xAxis: [
            {
                type: 'category',
                data : ['8:00','10:00','12:00','14:00','16:00','18:00'],
                axisLine:{
                    lineStyle:{
                        color: '#034c6a'
                    },
                },
                splitLine: {
                    "show": false
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff'
                    },
                    formatter: function (value) {
                        return value + ""
                    },
                },
                splitLine:{
                    lineStyle:{
                        width:0,
                        type:'solid'
                    }
                },
            }
        ],
        grid:{
            left: '5%',
            right: '5%',
            bottom: '20%',
            containLabel: true
        },
        series :_series(result.getPatientDayCount)
        /* [
            {
                name:'厦门第一医院',
                type:'line',
                smooth:true,
                itemStyle: {
                    normal: {
                        lineStyle: {
                            shadowColor : 'rgba(0,0,0,0.4)'
                        }
                    }
                },
                data:[15, 0, 20, 45, 22.1, 25,].reverse()
            },
            {
                name:'厦门中山医院',
                type:'line',
                smooth:true,
                itemStyle: {
                    normal: {
                        lineStyle: {
                            shadowColor : 'rgba(0,0,0,0.4)'
                        }
                    }
                },
                data:[25, 10, 30, 55, 32.1, 35, ].reverse()
            },
            {
                name:'厦门中医院',
                type:'line',
                smooth:true,
                itemStyle: {
                    normal: {
                        lineStyle: {
                            shadowColor : 'rgba(0,0,0,0.4)'
                        }
                    }
                },
                data:[35, 20, 40, 65, 42.1, 45, ].reverse()
            },
            {
                name:'厦门第五医院',
                type:'line',
                smooth:true,
                itemStyle: {
                    normal: {
                        lineStyle: {
                            shadowColor : 'rgba(0,0,0,0.4)'
                        }
                    }
                },
                data:[45, 30, 50, 75, 52.1, 55, 6].reverse()
            }
        ]*/
    });


    //年龄分布
    var pieChart1 = echarts.init(document.getElementById('pieChart1'));
    pieChart1.setOption({
        color:["#32cd32","#ff7f50","#87cefa","#FD6C88","#4b5cc4","#faff72"],
        tooltip : {
            trigger: 'item',
            formatter: "{a}<br/>{b}<br/>{c}人"
        },
        calculable : true,
        series : [
            {
                name:'发病人数',
                type:'pie',
                radius : [30, 70],
                center : ['50%', '50%'],
                roseType : 'area',
                x: '50%',



                sort : 'ascending',
                data:[
                    {value:result.getHospitalCount[7].counts, name:'婴儿(1-3岁)'},
                    {value:result.getHospitalCount[8].counts, name:'少儿(4-10岁)'},
                    {value:result.getHospitalCount[9].counts, name:'少年(10-18岁)'},
                    {value:result.getHospitalCount[10].counts, name:'青年(18-45岁)'},
                    {value:result.getHospitalCount[11].counts, name:'中年(45-60岁)'},
                    {value:result.getHospitalCount[12].counts, name:'老年(60岁以上)'},
                ]
            }
        ]
    })

    //性别分布
    var labelFromatter = {
        normal : {
            label : {
                position : 'center',
                formatter : function (params){
                    //console.log(params)
                    if(params.name == "女性"){
                        return "女性"+":"+(params.percent + '%')
                    }else{
                        return "男性"+":"+(params.percent + '%')
                    }
                },
            },
            labelLine : {
                show : false
            }
        },
    };

    var pieChart2 = echarts.init(document.getElementById('pieChart2'));
    pieChart2.setOption({

        color: ['#87cefa','#FD6C88'],
        tooltip : {
            trigger: 'item',
            formatter: "{b}({c})<br/>{d}%"
        },

        series : [
            {
                type : 'pie',
                center : ['50%', '50%'],
                radius : [55, 95],
                x: '0%', // for funnel
                itemStyle : labelFromatter,
                data : [
                    {name:'男性', value:result.getHospitalCount[5].counts},
                    {name:'女性', value:result.getHospitalCount[6].counts},
                ]
            },
        ],
    })
}

function getDatas(getPatientMonthCount,symptom_Name) {
    var data=[];
    var c;
    for (var i=0;i<symptom_Name.length;i++){
        c=getPatientMonthCount[symptom_Name[i]];
        if(c){
            data.push(c);
        }else{
            data.push(0);
        }
    }
   return  data;
}

function _series(getPatientDayCount) {
var _data=[];

    for(var i=0; i<getPatientDayCount.length;i++){
        //console.log(getPatientDayCount[i])
        var  _counts=[];
        var d={
            name:'',
            type:'line',
            smooth:true,
            itemStyle: {
                normal: {
                    lineStyle: {
                        shadowColor : 'rgba(0,0,0,0.4)'
                    }
                }
            },
            data:[]
        }
        d.name=getPatientDayCount[i].name;
        for(var j=0;j<getPatientDayCount[i].counts.length;j++){
            _counts.push(getPatientDayCount[i].counts[j].counts)
        }
        d.data=_counts.reverse();
        _data.push(d)
    }
  return _data;
}

function _getDept(getDeptNoByCount){
    var data=[];
    for(var j=0;j<getDeptNoByCount.length;j++){
        data.push(getDeptNoByCount[j].deptName)
    }
    return data;
}