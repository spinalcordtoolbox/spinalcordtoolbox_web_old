/*!
  Created by IntelliJ IDEA.
  User: Salim Ben
  Date: 2014-11-16
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
!*/


(function($) {
    var id = 1;
    var success = "";
    var dataType = "";

    $('#commandline-field').terminal(function(command, term) {
        if (command == 'help') {

            term.echo("Available commands are sct_propseg, sct_register_to_template, sct_yyyy" +
                    "\n Other commands are clear, exit" +
                    "\n Use keyboard arrow to browser throught command history");
        } else if (command == 'sct_propseg'){
            term.echo('Type help to see the command syntax');
            term.push(function(command, term) {

                if (command == 'help') {
                    term.echo('sct_propseg -i <input filename> -o <outputfilename> -t {t1, t2}');
                } else {
                    term.echo('sending command to server');
                    $.ajax({
                        type: "POST",
                        url: "/command",
                        data: "command=" +command,
                        success: function(data, textStatus, jqXHR){term.echo(data)},//http://api.jquery.com/jquery.get/
                        dataType: dataType
                    });
                    term.echo(success);
                }
            }, {

                prompt: 'sct_propseg> ',
                name: 'sct_propseg'});
        } else if (command == "sct_register_to_template") {
            term.push(function(command, term) {
                var result = window.eval(command);
                if (result != undefined) {
                    term.echo(String(result));
                }
            }, {
                name: 'js',
                prompt: 'js> '});
        } else if (command == 'sct_yyyy') {
            term.push(function(command, term) {
                term.pause();
                $.jrpc("note:jehiel/check if its useful to use this. I think its php. investigate. i got a path with ajax. see sct_progseg",
                        "query",
                        [command],
                        function(data) {
                            term.resume();
                            if (data.error && data.error.message) {
                                term.error(data.error.message);
                            } else {
                                if (typeof data.result == 'boolean') {
                                    term.echo(data.result ? 'success' : 'fail');
                                } else {
                                    var len = data.result.length;
                                    for(var i=0;i<len; ++i) {
                                        term.echo(data.result[i].join(' | '));
                                    }
                                }
                            }
                        },
                        function(xhr, status, error) {
                            term.error('[AJAX] ' + status +
                                    ' - Server reponse is: \n' +
                                    xhr.responseText);
                            term.resume();
                        });
            }, {
                greetings: "spinalweb",
                prompt: "mysql> "});
        } else {
            term.echo("unknow command " + command);
        }
    }, {
        greetings: "SpinalToolBoxWebCommand - type help to list commands",
        onBlur: function() {
            //ne pas remplir
        }
    });
})(jQuery);