<?php
/**
 * File to handle all API requests
 * Accepts GET and POST
 *
 * Each request will be identified by TAG
 * Response will be JSON data
 */


/**
 * check for POST request
 */




 // include db handler
    require_once 'include/DB_Functions.php';
    $db = new DB_Functions();
    // $user= $db->showTimetable("`timetable`" , "'MON'");




 if (isset($_POST['tag']) && $_POST['tag'] != '') {
    // get tag
    $tag = $_POST['tag'];

   
 
  

     // response Array
    $response = array("tag" => $tag, "success" => 0, "error" => 0);
 
    // check for tag type
    if ($tag == 'login') {
        // Request type is check Login
        $username = $_POST['username'];
        $password = $_POST['password'];
        $choice = $_POST['choice'];
 
        // check for user
        $user = $db->loginUser($username, $password, $choice);
        if ($user != false) {
            // user found
            // echo json with success = 1
            $response["success"] = 1;
            $response["user"]["id"] = $user;
            echo json_encode($response);
        } else {
            // user not found
            // echo json with error = 1
            $response["error"] = 1;
            $response["error_msg"] = "Incorrect username or password!";
            echo json_encode($response);
        }
    } 
/*
    else if($tag == 'details')
    {
    	$id = $_POST['id'];
    	$table = $_POST['table'];

    	$user = $db->getDetails($id, $table);
    	if ($user != false) {
            // user found
            // echo json with success = 1
            $response["success"] = 1;
            $response["user"]["first_name"] = $user["first_name"];
            $response["user"]["last_name"] = $user["last_name"];
            $response["user"]["email_id"] = $user["email_id"];
            $response["user"]["phone_no"] = $user["phone_no"];
            if ($table == 'Student'){
            	$response["user"]["age"] = $user["age"];
            }
            else if($table == 'Parent'){
            	$response["user"]["no_of_children"] = $user["no_of_children"];
            	$response["user"]["sec_password"] = $user["sec_password"];
            }
            else if($table == 'Teacher'){
            	$response["user"]["qualification"] = $user["qualification"];
            }
            echo json_encode($response);
        } else {
            // user not found
            // echo json with error = 1
            $response["error"] = 1;
            $response["error_msg"] = "Incorrect id or table!";
            echo json_encode($response);
        }

    }
*/	
	//*********************************************< all tables details>*****************
	 else if($tag == 'details')
     {
		$table = $_POST['table'];
        $currentPid = $_POST['pid'];

		
		$details="false";
		$details = $db->getTable($table, $currentPid);
		
        if ($details != false) {
            // user found
            // echo json with success = 1
            $response["success"] = 1;
            $response["response"] = $details;

            //echo ($table);
            echo json_encode( $response);
     }
        else {
			echo json_encode("Can't Access Table :  ".$table);
        }
    } 
	
	
	//**************************************************< PROFILE>**************************************************************************
     else if($tag == 'profileDetails')
     {
        $pid = $_POST['pid'];
		$flag = $_POST['flag'];
		
		$details="false";
		
		if($flag==0)
		{
			$details = $db->showProfile($pid);
		}
		else if($flag==1)
		{
			$details = $db->updateProfile($pid);
		}
			
        if ($details != false) {
            // user found
            // echo json with success = 1
            $response["success"] = 1;
            $response["response"] = $details;

            echo json_encode($response);
     }
        else {
        echo json_encode("Invalid Request");
        }
    } 


    //**************************************************< timeTable>**************************************************************************
     else if($tag == 'timeTableDetails')
     {
        $class_no = $_POST['class'];
        $table = $_POST['table'];
		$section = $_POST['section'];


        $details = $db->showTimetable($table , $class_no, $section);
        if ($details != false) {
            // user found
            // echo json with success = 1
            $response["success"] = 1;
            $response["response"] = $details;

            echo json_encode($response);
     }
        else {
        echo json_encode("Invalid Request");
        }
    } 

//******************************************< phone List>************************************************************
     else if($tag == 'phoneListDetails')
     {
        $table = $_POST['table'];

        $details = $db->showPhoneList($table);
        if ($details != false) {
            // user found
            // echo json with success = 1
            $response["success"] = 1;
            $response["response"] = $details;

            echo json_encode($response);
     }
        else {
        echo json_encode("Invalid Request");
        }
    } 



}    
    else {


        $table = "Class";
        $currentPid = "2";
        $details = $db->getTable($table, $currentPid);
        
        if ($details != false) {
            // user found
            // echo json with success = 1
            $response["success"] = 1;
            $response["response"] = $details;

            echo json_encode( $response);
     }
        else {
            echo json_encode("Can't Access Table :  ".$table);
        }
    echo json_encode("Access Denied");
    }  


?>