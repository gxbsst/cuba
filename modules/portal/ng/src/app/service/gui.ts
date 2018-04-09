declare var electron: any;

const {dialog, Menu, MenuItem, app} = electron.remote;
var remote = electron.remote;

// var dialog = require('dialog');
var gui;
export default gui =  {
    Menu: Menu,
    MenuItem: MenuItem,
    remote: remote,
    app: app,
    dialog: dialog,

    getSeparatorMenu: function() {
        return new MenuItem({ type: 'separator' });
    },
    win: remote.getCurrentWindow(),
    getCurrentWindow: function() {
        return remote.getCurrentWindow();
    },
    Shell: electron.shell,
    on: function(type, callback) {

    }
};



