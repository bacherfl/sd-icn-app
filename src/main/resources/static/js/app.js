var app = angular.module('sdicn-dashboard', ['ngRoute'])
    .config(function($routeProvider, $httpProvider) {

        $routeProvider.when('/', {
            templateUrl : 'statistics.html',
            controller : 'statistics'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    });
app.controller('statistics', function($scope, $http, $interval) {

    $scope.numberOfRequestUpdateInterval = 20;

    $scope.updateTotalPopularities = function() {
        $http.get("http://localhost:8080/stats/popularities/all")
            .success(function (data) {
            $scope.totalPopularities = data;
            $scope.renderTotalPopularities();
        });
    }

    $scope.update10sPeriodPopularities = function() {
        var now = Date.now();
        //get popularities for 10 second intervals since the last minute
        var from = now - 60 * 1000;
        $http.get("http://localhost:8080/stats/popularities/periods/" + from + "/20")
            .success(function (data) {
            $scope.per10SecondsPopularityPeriods = data;
            $scope.renderPer10SecondsPopularityPeriods();
        });
    }

    $scope.updatePerPeriodNumberOfRequests = function() {
        $scope.lastPerPeriodNUmberOfRequestsUpdate = Date.now();

        var from = $scope.lastPerPeriodNUmberOfRequestsUpdate - 480 * 1000;
        //TODO make interval configurable
        $http.get("http://localhost:8080/stats/requests/" + from + "/" + $scope.numberOfRequestUpdateInterval + "/count")
            .success(function(data) {
            $scope.perPeriodNumberOfRequests = data;
                $scope.renderPerPeriodNumberOfRequests();
        });
    }

    $scope.updateTotalPopularities();
    $scope.update10sPeriodPopularities();
    $scope.updatePerPeriodNumberOfRequests();

    $interval(function() {
        $scope.updateTotalPopularities();
    }, 60000);

    $interval(function() {
        $scope.update10sPeriodPopularities();
    }, 20000);

    $interval(function() {
        $scope.updatePerPeriodNumberOfRequests();
    }, 20000);

    $scope.renderBarChart = function (chartCategories, chartValues, chartContainerId) {
        angular.element('#' + chartContainerId).highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Content popularities'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: chartCategories,
                crosshair: true
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Popularity [%]'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} %</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: "Popularities",
                data: chartValues
            }]
        });
    };

    $scope.renderTotalPopularities = function() {
        var chartCategories = [];
        var chartValues = [];
        angular.forEach($scope.totalPopularities, function(popularityEntry) {
            chartCategories.push(popularityEntry.contentName);
            chartValues.push(popularityEntry.popularity * 100);
        });

        $scope.renderBarChart(chartCategories, chartValues, "container");
    };

    $scope.renderPer10SecondsPopularityPeriods = function () {
        var i = 1;
        angular.forEach($scope.per10SecondsPopularityPeriods, function(popularityDistribution) {
            var chartCategories = [];
            var chartValues = [];
            angular.forEach(popularityDistribution, function(popularityEntry) {
                chartCategories.push(popularityEntry.contentName);
                chartValues.push(popularityEntry.popularity * 100);

            });
            $scope.renderBarChart(chartCategories, chartValues, "per10sContainer" + i);
            i++;
        });
    };

    $scope.renderPerPeriodNumberOfRequests = function() {
        var categories = [];
        var idx = $scope.perPeriodNumberOfRequests.length - 1;
        angular.forEach($scope.perPeriodNumberOfRequests, function() {
            var tstamp = new Date($scope.lastPerPeriodNUmberOfRequestsUpdate - idx * 1000 * $scope.numberOfRequestUpdateInterval);
            var formattedDate = tstamp.getHours() + ":" + tstamp.getMinutes() + ":" + tstamp.getSeconds();
            categories.push(formattedDate);
            idx++;
        });
        categories.sort();
        $scope.perPeriodNumberOfRequests.pop();
        $scope.renderLineChart(categories, $scope.perPeriodNumberOfRequests);
    };

    $scope.renderLineChart = function(chartCategories, chartValues) {
        angular.element('#numberOfRequestsContainer').highcharts({
            chart: {
                type: 'spline'
            },
            title: {
                text: 'Number of Requests',
                x: -20 //center
            },
            subtitle: {
                text: '',
                x: -20
            },
            xAxis: {
                categories: chartCategories
            },
            yAxis: {
                title: {
                    text: '#Requests'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: ''
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                name: '#Requests',
                data: chartValues
            }]
        });
    }
});