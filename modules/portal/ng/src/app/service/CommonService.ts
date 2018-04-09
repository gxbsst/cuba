/**
 * Created by yanxiaojun on 2017/2/16.
 */
import {Injectable} from '@angular/core';

import gui from './gui';

declare var child_process: any;
declare var remote: any;
declare var electron: any;
const process = remote.process;

// 应用服务
const OPC = 'WSOpcConnector.jar';
const PRINTER = 'printmanager.jar';
const RFID = 'rfid_manager-1.0-SNAPSHOT.jar';

// 应用进程
let opcProcess;
let rfidProcess;
let printerProcess;


const exec = child_process.exec;
const execSync = child_process.execSync;
const spawn = child_process.spawn;

// PATH
const MACJAVACMD = gui.app.getAppPath() + '/app/applications/jre/mac/Contents/Home/bin/java';
const WINDOWJAVACMD = gui.app.getAppPath() + '/app/applications/jre/win/bin/java.exe';
const APPHOME = gui.app.getAppPath() + '/app/applications/';

// 判断平台
const platform = process.platform;

let JAVACMD = MACJAVACMD;
if (platform === 'win32') {
    JAVACMD = WINDOWJAVACMD;
}


@Injectable()
export class CommonService {
    private last: any;

    constructor() {
    }


    isWin() {
        return process.platform.toLowerCase().indexOf('win') === 0;
    }

    isMac() {
        return process.platform.toLowerCase().indexOf('mac') === 0;
    }

    startApp(appName, exitFunc) {
        const me = this;


        if (appName === 'SCT') {

            if (this.isWin()) {
                execSync('./gradlew.bat undeploy setupTomcat deploy',
                    {
                        cwd: gui.app.getAppPath() + '/sct/'
                    });

                exec('./catalina.bat stop', {
                    cwd: gui.app.getAppPath() + '/sct/deploy/tomcat/bin'
                }, function (error, stdout, stderr) {
                    debugger;
                });

                const sctProcess = exec('./catalina.bat run', {
                    cwd: gui.app.getAppPath() + '/sct/deploy/tomcat/bin'
                }, function (error, stdout, stderr) {
                    debugger;
                });
            } else {
                execSync('./gradlew undeploy setupTomcat deploy',
                    {
                        cwd: gui.app.getAppPath() + '/sct/'
                    });

                exec('./catalina.sh stop', {
                    cwd: gui.app.getAppPath() + '/sct/deploy/tomcat/bin'
                }, function (error, stdout, stderr) {
                    debugger;
                });

                const sctProcess = exec('./catalina.sh run', {
                    cwd: gui.app.getAppPath() + '/sct/deploy/tomcat/bin'
                }, function (error, stdout, stderr) {
                    debugger;
                });
            }

            exitFunc('open');

        }


        if (appName === 'CUBA') {
            startCcuba();
        }

        // 打印服务
        if (appName === 'PRINTER') {

            printerProcess = spawn(JAVACMD + ' -jar ' + APPHOME + PRINTER, {
                cmd: APPHOME
            }, function (error, stdout, stderr) {
                debugger;
            });
        }

        // rfid 服务
        if (appName === 'RFID') {
            rfidProcess = exec(JAVACMD + ' -jar ' + APPHOME + RFID, {
                cmd: APPHOME
            }, function (error, stdout, stderr) {
                debugger;
            });
        }

        // opc 服务
        if (appName === 'OPC') {
            opcProcess = exec(JAVACMD + ' -jar ' + APPHOME + OPC, {
                cmd: APPHOME,
                maxBuffer: 1024 * 5000
            }, function (error, stdout, stderr) {
                debugger;
            });
        }

    }

}


// 启动cuba

function startCcuba() {
    if (this.isWin()) {
        execSync('./gradlew.bat undeploy setupTomcat deploy',
            {
                cwd: gui.app.getAppPath() + '/cuba/'
            });

        exec('./catalina.bat stop', {
            cwd: gui.app.getAppPath() + '/cuba/deploy/tomcat/bin'
        }, function (error, stdout, stderr) {
            debugger;
        });

        const sctProcess = exec('./catalina.bat run', {
            cwd: gui.app.getAppPath() + '/cuba/deploy/tomcat/bin'
        }, function (error, stdout, stderr) {
            debugger;
        });
    } else {
        execSync('./gradlew undeploy setupTomcat deploy',
            {
                cwd: gui.app.getAppPath() + '/cuba/'
            });

        exec('./catalina.sh stop', {
            cwd: gui.app.getAppPath() + '/cuba/deploy/tomcat/bin'
        }, function (error, stdout, stderr) {
            debugger;
        });

        const sctProcess = exec('./catalina.sh run', {
            cwd: gui.app.getAppPath() + '/cuba/deploy/tomcat/bin'
        }, function (error, stdout, stderr) {
            debugger;
        });
    }
}
