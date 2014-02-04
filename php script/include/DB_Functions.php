<?php
 
class DB_Functions {
 
    private $db;
    private $table;
 
    //put your code here
    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $this->db = new DB_Connect();
        $this->db->connect();
		
		
    }
 
    // destructor
    function __destruct() {
         
    }
 
    /**
     * Get user by email and password
     */
    public function loginUser($username, $password, $choice) {
        $this->table = $choice;
        $result = mysql_query("SELECT * FROM `User` WHERE `username` = '$username' and `table` = '$choice'") or die(mysql_error());
        // check for result
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) {
            $result = mysql_fetch_array($result);
            $pass = $result['password'];
            // check for password equality
            if ($password == $pass) {
                // user authentication details are correct
                return $result['id'];
            }
            else{
                return false;
            }
        } 
        else {
            // user not found
            return false;
        }
    }


    public function getDetails($id, $table){
        $result = mysql_query("SELECT * FROM $table WHERE `id` = $id") or die(mysql_error());
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) {
            $result = mysql_fetch_array($result);
            return $result;
        }
        else {
            return false;
        }
    }
	
	/****************************************************< time table >**************************************************************/
	public function showTimetable($table, $class_no, $section_no)
	{
		
       
		$result1 = mysql_query("SELECT  `class_no`, `section`, `day`, TIME_FORMAT(`FROM`, '%h:%i'), TIME_FORMAT(`TO`, '%h:%i %p'),  `subject`,`teacher` FROM `". $table. "` where `class_no`='".$class_no."' AND `section`='".$section_no."'") or die(mysql_error());
		$no_of_rows = mysql_num_rows($result1);
		
        
        $res = "";

        if ($no_of_rows > 0) {
		while($row = mysql_fetch_array($result1))
       {
           
		  	for($i =0; $i<7; $i++)
            {
                $res .= $row[$i] .",";
               // echo $res;
            }
           // echo $res;
            $res .= "~";
	   }
        //echo $res;
       return $res;
    }
    else
    {
        return false;
    }

	}
		
		
/****************************************************< phone-list >**************************************************************/
    public function showPhoneList($table)
    {
        
       $text = "SELECT  * FROM `". $table. "`";
      // echo $text;

        $result1 = mysql_query($text);
        $no_of_rows = mysql_num_rows($result1);
        
        
        $res = "";

        if ($no_of_rows > 0) {
        while($row = mysql_fetch_array($result1))
       {
           
            for($i =1; $i<6; $i++)
            {
                $res .= $row[$i] .",";
               // echo $res;
            }
           // echo $res;
            $res .= "~";
       }
        //echo $res;
       return $res;
    }
    else
    {
        return false;
    }

    }












//**************************************************************************************************************
	
}
 
?>