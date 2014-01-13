<?php
 
/**
 * A class file to connect to database
 */
 
define('DB_USER', "root");
define('DB_PASSWORD', "");
define('DB_DATABASE', "schoolapp");
define('DB_HOST', "localhost");
 
 
class DB_CONNECT {
 
    // constructor
    function __construct() {
        // connecting to database
        //$this->connect();
    }
 
    // destructor
    function __destruct() {
        // closing db connection
        //$this->close();
    }
 
    /**
     * Function to connect with database
     */
    function connect() {
        // import database connection variables
      //  require_once "config.php";
 
        // Connecting to mysql database
        $con = mysql_connect(DB_HOST, DB_USER, DB_PASSWORD) or die(mysql_error());
 
        // Selecing database
        $db = mysql_select_db(DB_DATABASE) or die(mysql_error()) or die(mysql_error());
 
      //  echo "connected to schoolapp  ";
        // returing connection cursor
        return $con;
    }
 
    /**
     * Function to close db connection
     */
    function close() {
        // closing db connection
        mysql_close();
    }
 
}
 
?>

