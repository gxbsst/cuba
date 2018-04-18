// index.ts
import {Component, Input, OnInit} from '@angular/core';
import {Chart, Highcharts} from 'angular-highcharts';

@Component({
    selector: 'vt-gauge',
    template: `
        <div style="width: 130px; height: 90px; float: left" [chart]="chart"></div>
    `,
})
export class VtGaugeComponent implements OnInit {
    @Input() value: number;
    chart: Chart;

    ngOnInit() {
        Highcharts.setOptions({
            chart: {
                type: 'solidgauge'
            },
            title: null,
            pane: {
                center: ['50%', '85%'],
                size: '140%',
                startAngle: -90,
                endAngle: 90,
                background: {
                    backgroundColor:  '#EEE',
                    innerRadius: '60%',
                    outerRadius: '100%',
                    shape: 'arc'
                }
            },
            tooltip: {
                enabled: false
            },
            yAxis: {
                stops: [
                    [0.1, '#55BF3B'], // green
                    [0.5, '#DDDF0D'], // yellow
                    [0.9, '#DF5353'] // red
                ],
                lineWidth: 0,
                minorTickInterval: null,
                tickPixelInterval: 400,
                tickWidth: 0,
                title: {
                    y: -70
                },
                labels: {
                    y: 16
                }
            },
            plotOptions: {
                solidgauge: {
                    dataLabels: {
                        y: 5,
                        borderWidth: 0,
                        useHTML: true
                    }
                }
            }
        });
        this.chart = new Chart({
            yAxis: {
                min: 0,
                max: 5000,
                title: {
                    text: '质量'
                }
            },
            credits: {
                enabled: false
            },
            series: [{
                name: '质量',
                data: [this.value],

            }],
            plotOptions: {
                // gauge: {
                //     dataLabels: {
                //         format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                //         ( 'black') + '">{y}</span><br/>' +
                //         '<span style="font-size:12px;color:silver">km/h</span></div>'
                //     },
                // }

            },
            tooltip: {
                valueSuffix: ' KG'
            }
        });
    }


}
