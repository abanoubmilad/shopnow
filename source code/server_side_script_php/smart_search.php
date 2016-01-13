<?php
/**
 * web service
 * smart search
 *
 * @copyright  Abanoub Milad Nassief
 * @license
 * @created    28/08/2015
 * @edited     30/08/2015
 */
define( "STATUS", "s" );
define( "RESULT", "r" );
$response = array();
session_start();
if ( isset( $_SESSION['id'] ) ) {
    if ( isset( $_POST['input'] ) ) {
        $input=$_POST['input'];
        if ( preg_match( "/[0-9a-zA-Z\s]+/", $input ) ) {
            include "Opr.php";
            $opr=new Opr();
            $result=$opr -> smart_search( $input );
            if ( $items===false )
                $response[STATUS] = 3;
            else {
                $response[STATUS] = 7;
                $response[RESULT] = $result;
            }
        }else
            $response[STATUS] = 2;
    }else
        $response[STATUS] = 1;
} else
    $response[STATUS] = 0;

echo json_encode( $response );
?>
