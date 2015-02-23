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

            term.echo(//"Available commands are sct_propseg, sct_register_to_template, sct_yyyy" +
                    "\n Other commands are clear, exit" +
                    "\n Use keyboard arrow to browser throught command history");
        } else {
            //term.echo('Type help to see the command syntax');
            term.push(function(command, term) {
                if (command == 'help') {
                    //term.echo('sct_propseg -i <input filename> -o <outputfilename> -t {t1, t2}');
                    term.echo("No help file defined");
                } else {
                    term.echo('sending command to server');
                    $.ajax({
                        type: "post",
                        async : true,
                        url: "/command",
                        data: "command=" +command,
                        success: function(data, textStatus, jqXHR)
                        {

                            console.log("success terminal");
                            var commandControllerScope = angular.element("#PostsCtrl").scope();
                            commandControllerScope.commandReceptionTreatments(data);

                        },
                        dataType: dataType
                    });
                    term.echo(success);
                }
            }, {
                prompt: 'terminal> ',
                name: 'terminal'});
        } /*else {
            term.echo("unknow command " + command);
        }*/
    }, {
        greetings: "Welcome to Spinal cord ToolBox terminal - Press enter to start",
        onBlur: function() {
            //ne pas remplir
        }
    });
})(jQuery);


