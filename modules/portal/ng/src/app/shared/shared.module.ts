import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
// delon
import {AlainThemeModule} from '@delon/theme';
import {DelonABCModule} from '@delon/abc';
import {DelonACLModule} from '@delon/acl';
// i18n
import {TranslateModule} from '@ngx-translate/core';
// region: third libs
import {NgZorroAntdModule} from 'ng-zorro-antd';
import {CountdownModule} from 'ngx-countdown';
import {UEditorModule} from 'ngx-ueditor';
import {NgxTinymceModule} from 'ngx-tinymce';
import {VtGaugeComponent} from '@shared/components/vt-gauge';
import {ChartModule, HIGHCHARTS_MODULES} from 'angular-highcharts';


// import { NzSchemaFormModule } from 'nz-schema-form';
const THIRDMODULES = [
    NgZorroAntdModule,
    CountdownModule,
    UEditorModule,
    NgxTinymceModule,
    ChartModule
    // NzSchemaFormModule
];
// endregion

// region: your componets & directives
const COMPONENTS = [
    VtGaugeComponent
];
const DIRECTIVES = [];

// endregion
import * as more from 'highcharts/highcharts-more.src';
import * as solidGauge from 'highcharts/modules/solid-gauge.src';




@NgModule({
    providers: [
        { provide: HIGHCHARTS_MODULES, useFactory: () => [more, solidGauge] } // add as factory to your providers
    ],
    imports: [
        CommonModule,
        FormsModule,
        RouterModule,
        ReactiveFormsModule,
        AlainThemeModule.forChild(),
        DelonABCModule,
        DelonACLModule,
        // third libs
        ...THIRDMODULES
    ],
    declarations: [
        // your components
        ...COMPONENTS,
        ...DIRECTIVES
    ],
    exports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule,
        AlainThemeModule,
        DelonABCModule,
        DelonACLModule,
        // i18n
        TranslateModule,
        // third libs
        ...THIRDMODULES,
        // your components
        ...COMPONENTS,
        ...DIRECTIVES
    ]
})
export class SharedModule {
}
