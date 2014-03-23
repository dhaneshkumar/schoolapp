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
	

    public function getStudents($pid){
        $result1 = mysql_query("SELECT `sid`, `class`, `section`,  CONCAT(  `class` ,  `section` ) FROM `Student` WHERE `pid` = $pid") or die(mysql_error());
        $no_of_rows = mysql_num_rows($result1);
            $result = array();
            $count =0;
            if ($no_of_rows > 0) {
            
                while($row = mysql_fetch_array($result1))
                { 
                    $result[$count++] = $row;
                }
                
                return $result;
            }
            else
            {
                return false;
            }
    }


	/************************************************< TABLE DETAILS>*************************************************/
	public function getTable($table, $currentPid)
	{
        $types = mysql_query("SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name =   \"". $table. "\"");

        $type_store = array();

        $no_of_rows = mysql_num_rows($types);
        $count1 =0;
        if ($no_of_rows > 0) { 
                while($row = mysql_fetch_array($types))
                { 
                    $type_store[$count1++] = $row[0];
                   
                }
        }
        


        $students = $this->getStudents($currentPid);
        $result1=array();

		if($table == "Parent" or $table == "Student" ){
             //echo json_encode("entered student");
			$result1 = mysql_query("SELECT * FROM  $table WHERE `pid` = $currentPid") or die(mysql_error());
		}
		else if($table =="teacher" ){

            foreach($students as $res){

                 $text = "SELECT * FROM $table WHERE `CLASSES` REGEXP \"". $res[3]."\"";

                // echo $text;
			     $result1 = mysql_query($text) or die(mysql_error());
              
             }
    
		}
        else if($table =="Class"){
           
            foreach($students as $res){

                 $result1 = mysql_query("SELECT * FROM $table WHERE  `class_no`= '".$res[1]."' AND `section`= '".$res[2]."'") or die(mysql_error());
           }
            
        }
        else if($table =="TimeTable"){

        //echo "time table";
            
            foreach($students as $res){

                $txt = "SELECT `class_no`, `section`, `day`, TIME_FORMAT(`FROM`, '%h:%i'), TIME_FORMAT(`TO`, '%h:%i %p'),  `ID`, `subject` FROM $table WHERE  `class_no`= '".$res[1]."' AND `section`= '".$res[2]."'";
                //echo $txt;
                $result1 = mysql_query($txt) or die(mysql_error());
            
             }
           
        }

        else if($table =="Attendance" or $table =="Medico" or $table == "AcadHistory"  or $table == "GradeAnalysis"){
         
            foreach($students as $res){

                 $result1 = mysql_query("SELECT * FROM $table WHERE  `sid`= '".$res[0]."'") or die(mysql_error());
               
             }
           
        }
		else
		{
             //echo json_encode("entered extra");
			 $text = "SELECT  * FROM `". $table. "`";
			 $result1 = mysql_query($text);

               //echo json_encode("result : ".$result1);
		}
		
         //echo json_encode("entered extra end");
			$no_of_rows = mysql_num_rows($result1);
			$result = array();
			$count =0;
			if ($no_of_rows > 0) {
			
				while($row = mysql_fetch_array($result1))
				{ 
                    $size=0;
                    $mini = array();
                    while($size != sizeof($row)/2)
                    {
                        //echo (gettype($row[$size]));
                        //echo "<br>";
                        if($type_store[$size] != "int")
                        {
                            $input = "'". $row[$size]."'";
                        }
                        else{
                            $input =  $row[$size];
                        }

                        $mini[$size] = $input;
                        $size++;
                    }

					$result[$count++] = $mini;
                   
				}
				
               
				return $result;
			}
			else
			{
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
           
            for($i =1; $i<7; $i++)
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