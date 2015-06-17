var app = angular.module('sdicn-dashboard', []);
app.controller('statistics', function($scope, $http, $interval) {

    $interval(function() {
        $http.get("http://localhost:8080/stats/popularities/all").success(function(data) {
            $scope.totalPopularities = data;
            $scope.renderTotalPopularities();
        });

        var now = Date.now();
        //get popularities for 10 second intervals since the last minute
        var from = now - 60 * 1000;
        $http.get("http://localhost:8080/stats/popularities/periods/" + from + "/10").success(function(data) {
            $scope.per10SecondsPopularityPeriods = data;
            $scope.renderPer10SecondsPopularityPeriods();
        });
    }, 5000);

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
                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
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
    }

    $scope.renderTotalPopularities = function() {
        var chartCategories = [];
        var chartValues = [];
        angular.forEach($scope.totalPopularities, function(popularityEntry) {
            chartCategories.push(popularityEntry.contentName);
            chartValues.push(popularityEntry.popularity * 100);
        });

        $scope.renderBarChart(chartCategories, chartValues, "container");
    }

    $scope.renderPer10SecondsPopularityPeriods = function () {
        angular.forEach($scope.per10SecondsPopularityPeriods, function(popularityDistribution) {
            var chartCategories = [];
            var chartValues = [];
            var i = 1;
            angular.forEach(popularityDistribution, function(popularityEntry) {
                chartCategories.push(popularityEntry.contentName);
                chartValues.push(popularityEntry.popularity * 100);
                $scope.renderBarChart(chartCategories, chartValues, "per10sContainer" + i);
                i++;
            });
        });
    };
});