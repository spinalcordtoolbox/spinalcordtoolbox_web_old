
/*
THIS FUNCTION IS CALLED WHEN USER CLOSE THE TAB.
IT SHOWS UP A CONFIRMATION MESSAGE TO CLOSE OR NOT THE APPLICATION
 */
function ClosingConfimation()
{
    return "Leaving this session will delete uploaded Files ...";
};

/*
THIS FUNCTION IS CALLED WHEN THE USER CONFIRM TAB CLOSING.
IT DELETES USER UPLOADED FILES FROM DISTANT SERVER.
 */

function deleteFilesSession()
{
	// call delete controller function

    deleteUserRepositoryFromServer();

}
	  
